package com.el.common.model;

import java.io.Serializable;
import java.util.List;


public class JsonTreeData implements Serializable {

    private static final long serialVersionUID = 1L;
    public String id;
    public String pid;
    public String text;
    public String state;
    public Attributes attributes;
    public List<JsonTreeData> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<JsonTreeData> getChildren() {
        return children;
    }

    public void setChildren(List<JsonTreeData> children) {
        this.children = children;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
