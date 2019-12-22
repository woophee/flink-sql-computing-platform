package com.woophee.platform.server.master.service.impl;

import com.woophee.platform.server.master.common.Constant;
import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.mapper.FlinkClusterMapper;
import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.master.exception.FlinkRuntimException;
import com.woophee.platform.server.master.exception.ParamException;
import com.woophee.platform.server.master.service.FlinkJobService;
import com.woophee.platform.server.common.model.flink.FlinkSubmitRequest;
import com.woophee.platform.server.common.model.flink.FlinkSubmitResult;
import com.woophee.platform.server.common.model.flink.JarResult;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class FlinkJobServiceImpl implements FlinkJobService {
    private static final Logger LOG = LoggerFactory.getLogger(FlinkJobServiceImpl.class);

    private final static String NORMAL_JAR = Constant.NORMAL_JAR;
    private final static String JARS_LIST = Constant.JARS_LIST;
    private final static String SUBMIT_JOB = Constant.SUBMIT_JOB;
    private final static String CANCEL_JOB = Constant.CANCEL_JOB;


    private static ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FlinkClusterMapper flinkClusterMapper;

    @Override
    public RestResult<FlinkSubmitResult> submitJob(Integer clusterId, FlinkSubmitRequest flinkSubmitRequest) {

        FlinkCluster flinkCluster = flinkClusterMapper.selectByPrimaryKey(clusterId);
        if(flinkCluster == null){
            throw new ParamException("无效的clusterId");
        }
        String address = flinkCluster.getAddress();

        String jarListUrl = address + JARS_LIST;
        JarResult jarResult = restTemplate.getForEntity(jarListUrl, JarResult.class).getBody();
        List<JarResult.File> files = jarResult.getFiles();
        String jarId = null;
        for (JarResult.File file : files) {
            if (NORMAL_JAR.equals(file.getName())) {
                jarId = file.getId();
                break;
            }
        }
        if (jarId == null) {
            throw new FlinkRuntimException("Invalid jar file name: " + NORMAL_JAR);
        }
        String submitJobUrl = address + "/jars/" + jarId + SUBMIT_JOB;
        try {
            if(StringUtils.isBlank(flinkSubmitRequest.getProgramArgs())){
                throw new ParamException("programArgs不允许传空");
            }
            HttpEntity<FlinkSubmitRequest> request = new HttpEntity<>(flinkSubmitRequest, null);
            FlinkSubmitResult flinkSubmitResult = restTemplate.postForObject(submitJobUrl, request, FlinkSubmitResult.class);
            //正常返回则设置jobId以及状态
            RestResult<FlinkSubmitResult> result = new RestResult<>();
            result.setData(flinkSubmitResult);
            return result;
        } catch (Exception e) {
            LOG.error("error", e);
            throw new FlinkRuntimException("提交任务异常");
            //返回异常，则设置数据库状态为异常
        }

    }

    @Override
    public Response cancelJob(Integer clusterId, Integer jobId) {
//        FlinkJob flinkJob = flinkJobMapper.selectByPrimaryKey(id);
//        String flinkId = flinkJob.getFlinkId();
//        if (StringUtils.isEmpty(flinkId)) {
//            throw new FlinkRuntimException("Invalid flinkId! Cancel the job failed");
//        }
//        Integer groupId = flinkJob.getGroupId();
//        JobGroup jobGroup = jobGroupMapper.selectByPrimaryKey(groupId);
//        FlinkCluster flinkCluster = flinkClusterMapper.selectByPrimaryKey(jobGroup.getClusterId());
//        String address = flinkCluster.getAddress();
//
//        String cancelJobUrl = address + "/jobs/" + flinkId + CANCEL_JOB;
//        Object result = restTemplate.getForObject(cancelJobUrl, Object.class);
//        System.out.println(result);
        return null;
    }
}
