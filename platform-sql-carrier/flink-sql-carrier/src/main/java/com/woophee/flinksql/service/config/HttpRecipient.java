package com.woophee.flinksql.service.config;

import com.alibaba.fastjson.JSON;
import com.woophee.flinksql.common.Constant;
import com.woophee.flinksql.common.HttpClientUtil;

public class HttpRecipient {

    private final static String JOB_DETAIL_URL = Constant.JOB_DETAIL_URL;
    private FlinkJobDetail flinkJobDetail;


    public HttpRecipient(Integer jobId){
//        Map<String, String> params = new HashMap<>();
//        params.put("jobId", String.valueOf(jobId));
        String url = JOB_DETAIL_URL + "/" + jobId;
        String result = HttpClientUtil.doGet(url);
        flinkJobDetail = JSON.parseObject(result, FlinkJobDetail.class);
    }

    public String getSQl(){
        return flinkJobDetail.getSql();
    }

    public String getName(){
        return flinkJobDetail.getName();
    }
}
