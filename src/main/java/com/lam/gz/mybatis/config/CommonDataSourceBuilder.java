package com.lam.gz.mybatis.config;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.jdbc.DatabaseDriver;
import org.springframework.util.ClassUtils;

/**
 * Convenience class for building a {@link DataSource} with common implementations and
 * properties. If Tomcat, HikariCP or Commons DBCP are on the classpath one of them will
 * be selected (in that order with Tomcat first). In the interest of a uniform interface,
 * and so that there can be a fallback to an embedded database if one can be detected on
 * the classpath, only a small set of common configuration properties are supported. To
 * inject additional properties into the result you can downcast it, or use
 * {@code @ConfigurationProperties}.
 *
 * @author liubo
 * @since 1.1.0
 */
public class CommonDataSourceBuilder {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final String[] DATA_SOURCE_TYPE_NAMES = new String[] {
			"com.alibaba.druid.pool.DruidDataSource",
			"org.apache.tomcat.jdbc.pool.DataSource",
			"com.zaxxer.hikari.HikariDataSource",
			"org.apache.commons.dbcp2.BasicDataSource" };
	
	private static final String DRIVER_CLASS_NAME = "driverClassName";
	private static final String URL = "url";
	
	private Class<? extends DataSource> type;
	private ClassLoader classLoader;
	private Map<String, String> properties = new HashMap<>();

	public static CommonDataSourceBuilder create() {
		return new CommonDataSourceBuilder(null);
	}

	public static CommonDataSourceBuilder create(ClassLoader classLoader) {
		return new CommonDataSourceBuilder(classLoader);
	}

	public CommonDataSourceBuilder(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public DataSource build() {
		type = findType();
		DataSource result = BeanUtils.instantiate(type);
		maybeGetDriverClassName();
		bind(result);
		return result;
	}

	private void maybeGetDriverClassName() {
		if (!this.properties.containsKey(DRIVER_CLASS_NAME) && this.properties.containsKey(URL)) {
			String url = this.properties.get(URL);
			String driverClass = DatabaseDriver.fromJdbcUrl(url).getDriverClassName();
			this.properties.put(DRIVER_CLASS_NAME, driverClass);
		}
	}

	private void bind(DataSource result) {
		MutablePropertyValues mpvs = new MutablePropertyValues(this.properties);
		new RelaxedDataBinder(result).withAlias(URL, "jdbcUrl")
				.withAlias("username", "user").bind(mpvs);
	}

	public CommonDataSourceBuilder type(Class<? extends DataSource> type) {
		this.type = type;
		return this;
	}

	public CommonDataSourceBuilder url(String url) {
		this.properties.put(URL, url);
		return this;
	}

	public CommonDataSourceBuilder driverClassName(String driverClassName) {
		this.properties.put(DRIVER_CLASS_NAME, driverClassName);
		return this;
	}

	public CommonDataSourceBuilder username(String username) {
		this.properties.put("username", username);
		return this;
	}

	public CommonDataSourceBuilder password(String password) {
		this.properties.put("password", password);
		return this;
	}

	@SuppressWarnings("unchecked")
	public Class<? extends DataSource> findType() {
		if (this.type != null) {
			return this.type;
		}
		for (String name : DATA_SOURCE_TYPE_NAMES) {
			try {
				return (Class<? extends DataSource>) ClassUtils.forName(name,
						this.classLoader);
			}
			catch (Exception ex) {
				logger.debug("未找到连接池:{}", name);
			}
		}
		throw new IllegalStateException("No supported DataSource type found");
	}

}
