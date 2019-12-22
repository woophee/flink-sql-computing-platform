package com.woophee.platform.server.common.model.page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageList<E> implements Serializable {
    private static final long serialVersionUID = -5617736576038165012L;

    long totalCount= 0L;

//    private AbstractQueryParam param;

    private List<E> dataList = new ArrayList<E>();

    public PageList() {
        super();
    }

//    public PageList(AbstractQueryParam param){
//        super();
//        this.param = param;
//    }

    public long getTotalCount(){
        return totalCount;
    }

    public void setTotalCount(long totalCount){
        this.totalCount = totalCount;
    }

//    public AbstractQueryParam getParam() {
//        return param;
//    }
//
//    public void setParam(AbstractQueryParam param) {
//        this.param = param;
//    }

    public List<E> getDataList() {
        return dataList;
    }

    public void setDataList(List<E> dataList) {
        this.dataList = dataList;
    }
}
