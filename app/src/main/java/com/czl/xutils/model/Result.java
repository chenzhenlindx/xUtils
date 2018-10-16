package com.czl.xutils.model;

import java.util.List;

public class Result {
    /**
     * _id : 5bbea1039d21220316428f35
     * createdAt : 2018-10-11T01:01:55.789Z
     * desc : 一个经过充分测试和线上验证的Android客户端自定义数据埋点SDK
     * images : ["https://ww1.sinaimg.cn/large/0073sXn7ly1fw8wbk8plng30mr0fr4qp","https://ww1.sinaimg.cn/large/0073sXn7ly1fw8wbkcdmnj30dh0cg3zb"]
     * publishedAt : 2018-10-15T00:00:00.0Z
     * source : web
     * type : Android
     * url : https://github.com/ccj659/JJEvent
     * used : true
     * who : Chauncey
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
