package com.woophee.platform.server.master.service.impl;

import com.alibaba.fastjson.JSON;
import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.mapper.DefineJobMapper;
import com.woophee.platform.server.master.dao.mapper.DefineScheduledJobMapper;
import com.woophee.platform.server.master.dao.model.DefineJob;
import com.woophee.platform.server.master.dao.model.DefineScheduledJob;
import com.woophee.platform.server.master.dao.model.DefineScheduledJobExample;
import com.woophee.platform.server.master.exception.ServiceException;
import com.woophee.platform.server.master.service.DefineScheduledJobService;
import com.woophee.platform.server.common.model.JobType;
import com.woophee.platform.server.common.model.page.PageList;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefineScheduledJobServiceImpl implements DefineScheduledJobService {

    @Autowired
    private DefineJobMapper defineJobMapper;

    @Autowired
    private DefineScheduledJobMapper defineScheduledJobMapper;

    @Override
    public Response create(DefineScheduledJob defineScheduledJob) {
        DefineJob defineJob = new DefineJob();
        defineJob.setName(defineScheduledJob.getName());
        defineJob.setType(JobType.SCHEDULED);
        defineJobMapper.insert(defineJob);
        defineScheduledJob.setId(defineJob.getId());
        defineScheduledJobMapper.insert(defineScheduledJob);
        return Response.succeed();
    }

    @Override
    @Transactional
    public Response update(DefineScheduledJob defineScheduledJob) {
        DefineJob defineJob = new DefineJob();
        defineJob.setId(defineScheduledJob.getId());
        defineJob.setName(defineScheduledJob.getName());
        defineJob.setType(JobType.SCHEDULED);
        defineJobMapper.updateByPrimaryKeySelective(defineJob);

        int result = defineScheduledJobMapper.updateByPrimaryKeySelective(defineScheduledJob);
        if (result == 0) {
            throw new ServiceException("Fail to delete the data by id: " + JSON.toJSONString(defineScheduledJob));
        }
        return Response.succeed();
    }

    @Override
    @Transactional
    public Response delete(Integer id) {
        int result = defineScheduledJobMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            throw new ServiceException("Fail to delete the data by id: " + id);
        }

        defineJobMapper.deleteByPrimaryKey(id);

        return Response.succeed();
    }

    @Override
    public RestResult<DefineScheduledJob> retrieve(Integer id) {
        RestResult<DefineScheduledJob> result = new RestResult<>(true);
        DefineScheduledJob defineScheduledJob = defineScheduledJobMapper.selectByPrimaryKey(id);
        result.setData(defineScheduledJob);
        return result;
    }

    @Override
    public PageList<DefineScheduledJob> query(Integer groupId, Integer pageIndex, Integer pageSize) {
        PageList<DefineScheduledJob> result = new PageList<>();
        DefineScheduledJobExample defineScheduledJobExample = new DefineScheduledJobExample();
        defineScheduledJobExample.createCriteria().andGroupIdEqualTo(groupId);
        result.setTotalCount(defineScheduledJobMapper.countByExample(defineScheduledJobExample));
        result.setDataList(defineScheduledJobMapper.selectByExampleWithRowbounds(defineScheduledJobExample, new RowBounds((pageIndex - 1) * pageSize, pageSize)));
        return result;
    }
}
