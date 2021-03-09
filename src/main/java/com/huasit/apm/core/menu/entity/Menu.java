package com.huasit.apm.core.menu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 *
 */
@Entity
@Table(name = "MENU")
public class Menu implements Serializable {

    /**
     *
     */
    public enum MenuOpenMethod {
        NORMAL
    }

    /**
     *
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     *
     */
    @JsonIgnore
    @Column(nullable = false)
    private boolean del;

    /**
     *
     */
    @JsonIgnore
    @Column(nullable = false)
    private Long pid;

    /**
     *
     */
    @JsonProperty("label")
    @Column(nullable = false, length = 100)
    private String name;

    /**
     *
     */
    @JsonProperty("value")
    @Column(nullable = false)
    private String url;

    /**
     *
     */
    @JsonProperty("method")
    @Column(nullable = false)
    private MenuOpenMethod method;

    /**
     *
     */
    @JsonProperty("icon")
    @Column(nullable = true)
    private String icon;

    /**
     *
     */
    @JsonIgnore
    @Column(nullable = false)
    private int auth;

    /**
     *
     */
    @JsonIgnore
    @Column(nullable = false)
    private int orderIndex;

    /**
     *
     */
    @JsonProperty("children")
    @Transient
    private List<Menu> childrens;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isDel() {
        return del;
    }

    public void setDel(boolean del) {
        this.del = del;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuOpenMethod getMethod() {
        return method;
    }

    public void setMethod(MenuOpenMethod method) {
        this.method = method;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getAuth() {
        return auth;
    }

    public void setAuth(int auth) {
        this.auth = auth;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public List<Menu> getChildrens() {
        return childrens;
    }

    public void setChildrens(List<Menu> childrens) {
        this.childrens = childrens;
    }
}