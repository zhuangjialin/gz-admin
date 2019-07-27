package com.lam.gz.common.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lam.gz.common.exception.BaseException;

/**
 * @author bo.liu01 on 2017/10/24.
 * @author liubo
 *
 */
public class HttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    
    public static final String CHARSET = "UTF-8";
    public static final int MAX_LIMIT = 6;
    
    private HttpClientUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 下载网络文件
     * @param httpUrl
     * @param fileName
     * @return
     */
    public static void downloadFile(String httpUrl, String fileName) throws IOException {

        URL url = null;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e) {
            logger.error(e.getMessage(), e);
            logger.error("下载链接错误, url:{}", httpUrl);
            throw new BaseException("请检查下载链接是正确", e);
        }
        
        File file = new File(getFileDir(fileName));
        if(!file.exists()){
            file.mkdirs();
        }
        
        try(FileOutputStream fs = new FileOutputStream(fileName);) {
            URLConnection conn = url.openConnection();
            InputStream inStream = conn.getInputStream();
            
            byte[] buffer = new byte[1024];
            int byteReadCount;
            while ((byteReadCount = inStream.read(buffer)) != -1) {
                fs.write(buffer, 0, byteReadCount);
            }
            logger.debug("文件下载完成! fileName:{}", fileName);
        } catch (FileNotFoundException e) {
        	logger.error("不存在的目录或文件, fileName:{}", fileName);
            logger.error(e.getMessage(), e);
            throw new BaseException("不存在的目录或文件："+fileName, e);
        } catch (IOException e) {
        	logger.error("文件下载失败, url:{}, fileName:{}", httpUrl, fileName);
            logger.error(e.getMessage(), e);
            throw e;
        }
    }

    private static String getFileDir(String fileName){
    	int i1 = fileName.lastIndexOf('/');
		int i2 = fileName.lastIndexOf('\\');
        return fileName.substring(0, (i1 > i2 ? i1 : i2));
    }

    public static void close(Closeable c){
        if(c != null){
            try {
                c.close();
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
    
    public static boolean download(String url, String fileName) throws IOException {  
    	if(StringUtil.isBlank(url) || StringUtil.isBlank(fileName)){
    		logger.error("下载文件时地址为空");
    		return false;
    	}
    	url = url.replace("\\", "/");
    	FileOutputStream fileout=null;
    	InputStream is = null;
    	CloseableHttpClient httpclient = null;
        try {  
        	httpclient = HttpClients.createDefault();
            HttpGet httpget = new HttpGet(url);  
            HttpResponse response = httpclient.execute(httpget);  
  
            HttpEntity entity = response.getEntity();  
            is = entity.getContent();  
            File file = new File(fileName);  
            file.getParentFile().mkdirs();  
            fileout = new FileOutputStream(file);  
            FileUtil.copy(is, fileout);  
            fileout.flush();  
            return true;
        } catch (IOException e) {
        	logger.error("文件下载失败, url:{}, fileName:{}", url, fileName);
            logger.error(e.getMessage(), e);
            throw e;
        } finally {
            close(fileout);
            close(is);
            close(httpclient);
        }  
    }  
    
    private static Map<String, String> addDefaultHeader(Map<String, String> header){
    	if(header == null){
    		header = new HashMap<>();
    	}
    	if(!header.containsKey("Content-Type")){
    		header.put("Content-Type", "application/json");
    	}
    	if(!header.containsKey("Accept-Charset")){
    		header.put("Accept-Charset", CHARSET);
    	}
    	return header;
    }
	
	private static <T> ResponseHandler<T> newResponseHandler(final Class<T> clazz, String uid) {
		return new ResponseHandler<T>() {
			@SuppressWarnings("unchecked")
			@Override
			public T handleResponse(HttpResponse response) throws IOException {
				final String responseString = IOUtils.toString(response.getEntity().getContent(), CHARSET);
				logger.info("uid:{}, response:{}", uid, responseString);
				if(StringUtil.isBlank(responseString)){
					return null;
				}
				if(clazz == null || String.class == clazz){
					return (T)responseString;
				}
				try {
					return JSON.parseObject(responseString, clazz);
				} catch (Exception e) {
					logger.error("uid:"+uid+", 返回结果解析异常", e);
					throw new IOException("返回结果解析异常");
				}
			}
		};
	}
	
	private static <T> T execute(HttpRequestBase method, Class<T> clazz, String uid) throws IOException{
		
		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		method.setConfig(RequestConfig.custom()
				  .setSocketTimeout(30000)
				  .setConnectTimeout(30000)
				  .setConnectionRequestTimeout(30000).build());
		try {
			return httpClient.execute(method, newResponseHandler(clazz, uid));
		} catch (Exception e) {
			logger.error("uid:"+uid+", "+e.getMessage(), e);
			throw e;
		} finally {
			close(httpClient);
		}
	}
	
	private static String assembleParam(HttpRequestBase method, String url, Map<String, String> header) throws IOException{
		if(StringUtil.isBlank(url)){
			logger.error("http请求URL不能为空!");
			throw new IOException("http请求URL不能为空!");
		}
		
		method.setURI(URI.create(url));
		
		if(CollectionUtil.isNotEmpty(header)){
			for (Entry<String, String> entries : header.entrySet()) {
				method.addHeader(entries.getKey(), entries.getValue());
			}
			return JSON.toJSONString(header);
		}
		return "";
	}
	
	public static <T> T callWithBodyForm(String url, HttpEntityEnclosingRequestBase method, Map<String, Object> bodyForm, 
			Map<String, String> header, Class<T> clazz) throws IOException {
		
		String headerStr = assembleParam(method, url, header);
		
		String body = "";
		if(CollectionUtil.isNotEmpty(bodyForm)){
			body = JSON.toJSONString(bodyForm);
			List<NameValuePair> param = new ArrayList<>(bodyForm.size());
			for (Entry<String, Object> entry : bodyForm.entrySet()) {
				if(StringUtil.isNotBlank(entry.getKey()) && entry.getValue() != null){
					param.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
				}
			}
			method.setEntity(new UrlEncodedFormEntity(param, CHARSET));
		}
		
		String uid = printBodyRequest(url, method, headerStr, body);
		return execute(method, clazz, uid);
	}

	private static String printBodyRequest(String url, HttpEntityEnclosingRequestBase method, String headerStr,
			String body) {
		String uid = getUid();
		logger.info("uid:{}, request method:{}, url:{}, body:{}, header:{}", 
				uid, method.getMethod(), url, body, headerStr);
		return uid;
	}

	public static <T> T callWithBody(String url, HttpEntityEnclosingRequestBase method, Object requestBody, 
			Map<String, String> header, Class<T> clazz) throws IOException {
		
		String headerStr = assembleParam(method, url, addDefaultHeader(header));
		
		String body = "";
		if(requestBody != null){
			body = requestBody.getClass() == String.class?(String)requestBody
					:JSON.toJSONString(requestBody);
			method.setEntity(new StringEntity(body, CHARSET));
		}
		
		String uid = printBodyRequest(url, method, headerStr, body);
		return execute(method, clazz, uid);
	}
	
	public static <T> T callWithGet(String url, Map<String, Object> param, Map<String, String> header, Class<T> clazz) throws IOException {
		HttpGet method = new HttpGet();
		url = addUrlParam(url, param);
		String headerStr = assembleParam(method, url, addDefaultHeader(header));
		
		String uid = getUid();
		logger.info("uid:{}, request method:{}, url:{}, header:{}", 
				uid, method.getMethod(), url, headerStr);
		return execute(method, clazz, uid);
	}

	private static String getUid() {
		return System.currentTimeMillis() + RandomStringUtils.randomNumeric(MAX_LIMIT);
	}
	
	// POST FORM 请求方法 -- start --  
	/**
	 * @param url
	 * @param requestBody
	 * @param header
	 * @param clazz 返回结果对象类
	 * @return
	 * @throws IOException
	 */
	public static <T> T postForm(String url, Map<String, Object> requestBody, Map<String, String> header, Class<T> clazz) throws IOException {
		return callWithBodyForm(url, new HttpPost(), requestBody, header, clazz);
	}
	
	public static <T> T postForm(String url, Map<String, Object> requestBody, Class<T> clazz) throws IOException {
		return postForm(url, requestBody, null, clazz);
	}
	
	public static JSONObject postForm(String url, Map<String, Object> requestBody, Map<String, String> header) throws IOException {
		return postForm(url, requestBody, header, JSONObject.class);
	}
	
	public static JSONObject postForm(String url, Map<String, Object> requestBody) throws IOException {
		return postForm(url, requestBody, null, JSONObject.class);
	}
	// POST FORM 请求方法 -- end -- 
	
	// POST请求方法 -- start --  
	/**
	 * @param url
	 * @param requestBody
	 * @param header
	 * @param clazz 返回结果对象类
	 * @return
	 * @throws IOException
	 */
	public static <T> T post(String url, Object requestBody, Map<String, String> header, Class<T> clazz) throws IOException {
		return callWithBody(url, new HttpPost(), requestBody, header, clazz);
	}
	
	public static <T> T post(String url, Object requestBody, Class<T> clazz) throws IOException {
		return post(url, requestBody, null, clazz);
	}
	
	public static JSONObject post(String url, Object requestBody, Map<String, String> header) throws IOException {
		return post(url, requestBody, header, JSONObject.class);
	}
	
	public static JSONObject post(String url, Object requestBody) throws IOException {
		return post(url, requestBody, null, JSONObject.class);
	}
	
	// POST请求方法 -- end -- 

	// PUT请求方法 -- start --  
	/**
	 * @param url
	 * @param requestBody
	 * @param header
	 * @param clazz 返回结果对象类
	 * @return
	 * @throws IOException
	 */
	public static <T> T put(String url, Object requestBody, Map<String, String> header, Class<T> clazz) throws IOException {
		return callWithBody(url, new HttpPut(), requestBody, header, clazz);
	}
	
	public static <T> T put(String url, Object requestBody, Class<T> clazz) throws IOException {
		return put(url, requestBody, null, clazz);
	}
	
	public static JSONObject put(String url, Object requestBody, Map<String, String> header) throws IOException {
		return put(url, requestBody, header, JSONObject.class);
	}
	
	public static JSONObject put(String url, Object requestBody) throws IOException {
		return put(url, requestBody, null, JSONObject.class);
	}
	// PUT请求方法 -- end --
	
	// GET请求方法 -- start --  
	/**
	 * @param url
	 * @param param
	 * @param header
	 * @param clazz 返回结果对象类
	 * @return
	 * @throws IOException
	 */
	public static <T> T get(String url, Map<String, Object> param, Map<String, String> header, Class<T> clazz) throws IOException {
		
		return callWithGet(url, param, header, clazz);
	}
	
	public static <T> T get(String url, Map<String, Object> param, Class<T> clazz) throws IOException {
		
		return get(url, param, null, clazz);
	}
	
	public static JSONObject get(String url, Map<String, Object> param, Map<String, String> headers) throws IOException {
		return get(url, param, headers, JSONObject.class);
	}
	
	public static JSONObject get(String url, Map<String, Object> param) throws IOException {
		return get(url, param, null, JSONObject.class);
	}
	
	public static JSONObject get(String url) throws IOException {
		return get(url, null, null, JSONObject.class);
	}
	// GET请求方法 -- end -- 

	/**
	 * 向URL添加查询参数
	 * @param url
	 * @param param
	 * @return
	 */
	public static String addUrlParam(String url, Map<String, Object> param){
		if(StringUtil.isBlank(url)){
			return StringUtil.EMPTY; 
		}
		String paramStr = serialize(param);
		if(StringUtil.isNotBlank(paramStr)){
			url += url.contains("?") ? "&" : "?";
			url += paramStr;
		}
		return url;
	}
	
	/**
	 * 序列化参数
	 * @param param
	 * @return
	 */
	public static String serialize(Map<String, Object> param) {
		if(CollectionUtil.isEmpty(param)){
			return StringUtil.EMPTY;
		}
		StringBuilder paramStr = new StringBuilder();
		Set<Entry<String, Object>> set = param.entrySet();
		int i = 0;
		for (Entry<String, Object> entry : set) {
			if(StringUtil.isBlank(entry.getKey()) || entry.getValue() == null){
				continue;
			}
			if(i > 0){
				paramStr.append("&");
			}
			paramStr.append(entry.getKey()).append("=").append(entry.getValue());
			i++;
		}
		return paramStr.toString();
	}

}
