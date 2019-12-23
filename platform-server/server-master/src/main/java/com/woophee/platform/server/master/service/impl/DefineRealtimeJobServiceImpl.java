package com.woophee.platform.server.master.service.impl;

import com.alibaba.fastjson.JSON;
import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.mapper.DefineGroupMapper;
import com.woophee.platform.server.master.dao.mapper.DefineJobMapper;
import com.woophee.platform.server.master.dao.mapper.DefineRealtimeJobMapper;
import com.woophee.platform.server.master.dao.model.*;
import com.woophee.platform.server.master.exception.FlinkRuntimException;
import com.woophee.platform.server.master.exception.ServiceException;
import com.woophee.platform.server.master.service.DefineRealtimeJobService;
import com.woophee.platform.server.master.model.DefineRealtimeJobVO;
import com.woophee.platform.server.common.model.JobType;
import com.woophee.platform.server.common.model.flink.FlinkSubmitRequest;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.FlinkJobService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefineRealtimeJobServiceImpl implements DefineRealtimeJobService {

    @Autowired
    private DefineGroupMapper defineGroupMapper;

    @Autowired
    private DefineJobMapper defineJobMapper;

    @Autowired
    private DefineRealtimeJobMapper defineRealtimeJobMapper;

    @Autowired
    private FlinkJobService flinkJobService;

    @Override
    @Transactional
    public Response create(DefineRealtimeJob defineRealtimeJob) {
        //TODO:名字不能重复
        DefineJob defineJob = new DefineJob();
        defineJob.setName(defineRealtimeJob.getName());
        defineJob.setType(JobType.REALTIME);
        defineJobMapper.insert(defineJob);
        defineRealtimeJob.setId(defineJob.getId());
        defineRealtimeJobMapper.insert(defineRealtimeJob);
        return Response.succeed();
    }

    @Override
    @Transactional
    public Response update(DefineRealtimeJob defineRealtimeJob) {

        DefineJob defineJob = new DefineJob();
        defineJob.setId(defineRealtimeJob.getId());
        defineJob.setName(defineRealtimeJob.getName());
        defineJob.setType(JobType.REALTIME);
        defineJobMapper.updateByPrimaryKeySelective(defineJob);

        int result = defineRealtimeJobMapper.updateByPrimaryKeySelective(defineRealtimeJob);
        if (result == 0) {
            throw new FlinkRuntimException("Fail to delete the data by id: " + JSON.toJSONString(defineRealtimeJob));
        }
        return Response.succeed();
    }

    @Override
    @Transactional
    public Response delete(Integer id) {

        int result = defineRealtimeJobMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            throw new ServiceException("Fail to delete the data by id: " + id);
        }

        defineJobMapper.deleteByPrimaryKey(id);

        return Response.succeed();
    }

    @Override
    public RestResult<DefineRealtimeJob> retrieve(Integer id) {
        RestResult<DefineRealtimeJob> result = new RestResult<>(true);
        DefineRealtimeJob defineRealtimeJob = defineRealtimeJobMapper.selectByPrimaryKey(id);
        result.setData(defineRealtimeJob);
        return result;
    }

    @Override
    public PageList<DefineRealtimeJob> query(Integer groupId, Integer pageIndex, Integer pageSize) {
        PageList<DefineRealtimeJob> result = new PageList<>();
        DefineRealtimeJobExample defineRealtimeJobExample = new DefineRealtimeJobExample();
        if(groupId != null) {
            defineRealtimeJobExample.createCriteria().andGroupIdEqualTo(groupId);
        }
        result.setTotalCount(defineRealtimeJobMapper.countByExample(defineRealtimeJobExample));
        result.setDataList(defineRealtimeJobMapper.selectByExampleWithRowbounds(defineRealtimeJobExample, new RowBounds((pageIndex - 1) * pageSize, pageSize)));
        return result;
    }

    @Override
    public PageList<DefineRealtimeJobVO> webQuery(Integer groupId, Integer pageIndex, Integer pageSize) {
        //分别查询两张表对应数据
        DefineRealtimeJobExample defineRealtimeJobExample = new DefineRealtimeJobExample();
        if(groupId != null) {
            defineRealtimeJobExample.createCriteria().andGroupIdEqualTo(groupId);
        }
        List<DefineRealtimeJob> defineRealtimeJobs = defineRealtimeJobMapper.selectByExampleWithRowbounds(defineRealtimeJobExample, new RowBounds((pageIndex - 1) * pageSize, pageSize));
        List<Integer> groupIds = defineRealtimeJobs.stream().map(DefineRealtimeJob::getGroupId).collect(Collectors.toList());
        DefineGroupExample defineGroupExample = new DefineGroupExample();
        defineGroupExample.createCriteria().andIdIn(groupIds);
        List<DefineGroup> defineGroups = defineGroupMapper.selectByExample(defineGroupExample);

        //service层拼接联表查询
        List<DefineRealtimeJobVO> dataList = new ArrayList<>();
        defineRealtimeJobs.forEach(defineRealtimeJob -> {
            DefineRealtimeJobVO defineRealtimeJobVO = new DefineRealtimeJobVO();
            BeanUtils.copyProperties(defineRealtimeJob, defineRealtimeJobVO);
            defineRealtimeJobVO.setGroupName(defineGroups.stream()
                    .filter(defineGroup -> defineGroup.getId().equals(defineRealtimeJob.getGroupId())).findFirst().get().getName());
            dataList.add(defineRealtimeJobVO);
        });

        //拼装分页结果
        PageList<DefineRealtimeJobVO> result = new PageList<>();
        result.setTotalCount(defineRealtimeJobMapper.countByExample(defineRealtimeJobExample));
        result.setDataList(dataList);

        return result;
    }

    @Override
    public Response submit(Integer id) {
        DefineRealtimeJob defineRealtimeJob = defineRealtimeJobMapper.selectByPrimaryKey(id);
        FlinkSubmitRequest flinkSubmitRequest = new FlinkSubmitRequest();
        flinkSubmitRequest.setProgramArgs("-j "+ id);
        flinkSubmitRequest.setParallelism(String.valueOf(defineRealtimeJob.getParallelism()));
        //先硬编码，指定集群id为1的集群提交任务
        flinkJobService.submitJob(1,flinkSubmitRequest);
        return Response.succeed();
    }
}
