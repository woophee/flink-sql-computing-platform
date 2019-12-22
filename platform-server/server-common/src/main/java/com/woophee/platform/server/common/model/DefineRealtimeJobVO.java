package com.woophee.platform.server.common.model;

import com.woophee.platform.server.master.dao.model.DefineRealtimeJob;

public class DefineRealtimeJobVO extends DefineRealtimeJob {

    private String groupName;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
