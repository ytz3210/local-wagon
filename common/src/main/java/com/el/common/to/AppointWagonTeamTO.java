package com.el.common.to;

public class AppointWagonTeamTO {

    /**
     * 一个或多个路线编码
     */
    private String ids;

    /**
     * 车队编码
     */
    private String wagonTeamId;

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getWagonTeamId() {
        return wagonTeamId;
    }

    public void setWagonTeamId(String wagonTeamId) {
        this.wagonTeamId = wagonTeamId;
    }
}