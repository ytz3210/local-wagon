package com.el.common.model;

import java.io.Serializable;

public class ZTreeData implements Serializable {

    private static final long serialVersionUID = 1L;
    public Long id;
    public Integer pId;
    public String name;
    public Integer checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

}
