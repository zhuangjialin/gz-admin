package com.lam.gz.admin.entity;

import java.util.ArrayList;
import java.util.List;

import com.lam.gz.common.base.BaseObject;

/**
 * @author bo.liu01 on 2017/11/17.
 */
public class RouteConfig extends BaseObject{
	private static final long serialVersionUID = 1L;
	
	//路由配置属性,说明参考菜单实体类
    /** 命名路由 */
    private String name;
    /** 编码，唯一标识 */
    private String code;
    /** 跳转路径，相对路径 */
    private String path;
    /** 跳转绝对路径 */
    private String absolutePath;
    /** 父路径 */
    private String parentCode;
    /** 图标 */
    private String icon;
    /** 菜单类型 */
    private Integer type;
    /** 排序号 */
    private Integer sortNum;
    /** 组件 */
    private String component;
    /** string | Location | Function */
    private String redirect;
    /** 别名 */
    private String alias;
    /** 嵌套路由 */
    private List<RouteConfig> children;
    //beforeEnter?: (to: Route, from: Route, next: Function) => void
    /** 自定义数据 */
    private RouteConfigMeta meta = new RouteConfigMeta();
    // vue 2.6.0+
    /** 匹配规则是否大小写敏感？(默认值：false) */
    private boolean caseSensitive; 
    /** 编译正则的选项 */
    private Object pathToRegexpOptions; 

    //过滤路由菜单时的条件字段

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }

    public String getRedirect() {
        return redirect;
    }

    public void setRedirect(String redirect) {
        this.redirect = redirect;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public List<RouteConfig> getChildren() {
        return children;
    }

    public void add(RouteConfig routeConfig) {
        if(this.children == null){
            this.children = new ArrayList<>();
        }
        this.children.add(routeConfig);
    }

    public void setChildren(List<RouteConfig> children) {
        this.children = children;
    }

    public RouteConfigMeta getMeta() {
        return meta;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public Object getPathToRegexpOptions() {
        return pathToRegexpOptions;
    }

    public void setPathToRegexpOptions(Object pathToRegexpOptions) {
        this.pathToRegexpOptions = pathToRegexpOptions;
    }
    
    public String getTitle() {
		return meta.getTitle();
	}

	public void setTitle(String title) {
		this.meta.setTitle(title);
	}

}
