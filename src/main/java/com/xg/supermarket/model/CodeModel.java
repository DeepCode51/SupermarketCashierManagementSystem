package com.xg.supermarket.model;

public class CodeModel {

    private String url;
    private String api;
    private String no;
    private String type;
    private String des;
    private String nextType;
    private Boolean bindPage;

    public CodeModel(String url, String api, String type, String des, String nextType, Boolean bindPage) {
        this.url = url;
        this.api = api;
        this.des = des;
        this.type = type;
        this.nextType = nextType;
        this.bindPage = bindPage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getNextType() {
        return nextType;
    }

    public void setNextType(String nextType) {
        this.nextType = nextType;
    }

    public Boolean getBindPage() {
        return bindPage;
    }

    public void setBindPage(Boolean bindPage) {
        this.bindPage = bindPage;
    }
}
