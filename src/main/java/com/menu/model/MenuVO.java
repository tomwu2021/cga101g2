package com.menu.model;

public class MenuVO {
    private Integer mid;
    private Integer pid;
    private String name;
    private String path;
    private String page;
    private String nameZh;
    private Byte breadcrumb;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
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

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    public Byte getBreadcrumb() {
        return breadcrumb;
    }

    public void setBreadcrumb(Byte breadcrumb) {
        this.breadcrumb = breadcrumb;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "MenuVO{" +
                "mid=" + mid +
                ", pid=" + pid +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", page='" + page + '\'' +
                ", nameZh='" + nameZh + '\'' +
                ", breadcrumb=" + breadcrumb +
                '}';
    }
}
