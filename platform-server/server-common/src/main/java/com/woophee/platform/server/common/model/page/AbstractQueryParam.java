package com.woophee.platform.server.common.model.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractQueryParam implements Serializable {
    private static final long serialVersionUID = 3060478329341466541L;

    protected Integer page = 1;
    //默认为20条
    protected Integer pageSize = 20;
    //石佛u需要返回count
    protected Boolean countNeeded = false;
    protected List<Integer> idList = new ArrayList<>();
    protected Integer id;
    protected Date gmtCreate;
    protected Date gmtModified;
    protected String userId;
    public Integer getStartNum(){
        if(page == null || page <= 0){
            page = 1;
        }
        return (page - 1) * pageSize;
    }
    public Integer getEndNum(){
        return getStartNum() + pageSize;
    }
    public void setPageSize(Integer pageSize){
        if(pageSize == null){
            return;
        }
        this.pageSize = pageSize;
    }

    public Integer getPage(){
        if(page == null){
            return 0;
        }
        return page;
    }
}
