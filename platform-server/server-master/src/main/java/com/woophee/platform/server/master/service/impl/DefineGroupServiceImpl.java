package com.woophee.platform.server.master.service.impl;

import com.alibaba.fastjson.JSON;
import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.mapper.DefineGroupMapper;
import com.woophee.platform.server.master.dao.mapper.DefineRealtimeJobMapper;
import com.woophee.platform.server.master.dao.mapper.DefineScheduledJobMapper;
import com.woophee.platform.server.master.dao.model.*;
import com.woophee.platform.server.master.exception.ParamException;
import com.woophee.platform.server.master.exception.SQLException;
import com.woophee.platform.server.master.service.DefineGroupService;
import com.woophee.platform.server.common.model.page.PageList;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DefineGroupServiceImpl implements DefineGroupService {

    @Autowired
    private DefineGroupMapper defineGroupMapper;

    @Autowired
    private DefineRealtimeJobMapper defineRealtimeJobMapper;

    @Autowired
    private DefineScheduledJobMapper defineScheduledJobMapper;

    @Override
    public Response create(DefineGroup defineGroup) {
        defineGroupMapper.insert(defineGroup);
        return Response.succeed();
    }

    @Override
    public Response delete(Integer id) {
        //查询是否组下还有其他定义的任务存在，如果存在，则不允许删除
        DefineGroup defineGroup = defineGroupMapper.selectByPrimaryKey(id);
        switch (defineGroup.getType()) {
            case REALTIME:
                DefineRealtimeJobExample defineRealtimeJobExample = new DefineRealtimeJobExample();
                defineRealtimeJobExample.createCriteria().andGroupIdEqualTo(id);
                List<DefineRealtimeJob> defineRealtimeJobs = defineRealtimeJobMapper.selectByExample(defineRealtimeJobExample);
                if (!CollectionUtils.isEmpty(defineRealtimeJobs)) {
                    throw new SQLException("该组下存在定义的实时任务，请先删除组下的任务");
                }
                break;
            case SCHEDULED:
                DefineScheduledJobExample defineScheduledJobExample = new DefineScheduledJobExample();
                defineScheduledJobExample.createCriteria().andGroupIdEqualTo(id);
                List<DefineScheduledJob> defineScheduledJobs = defineScheduledJobMapper.selectByExample(defineScheduledJobExample);
                if (!CollectionUtils.isEmpty(defineScheduledJobs)) {
                    throw new SQLException("该组下存在定义的调度任务，请先删除组下的任务");
                }
                break;
            default:
                throw new ParamException("不存在的组类别");
        }
        int result = defineGroupMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            throw new SQLException("Fail to delete the data by id: " + id);
        }
        return Response.succeed();
    }

    @Override
    public Response update(DefineGroup defineGroup) {
        int result = defineGroupMapper.updateByPrimaryKeySelective(defineGroup);
        if (result == 0) {
            throw new SQLException("Fail to delete the data by id: " + JSON.toJSONString(defineGroup));
        }
        return Response.succeed();
    }

    @Override
    public RestResult<DefineGroup> retrieve(Integer id) {
        RestResult<DefineGroup> result = new RestResult<>(true);
        DefineGroup defineGroup = defineGroupMapper.selectByPrimaryKey(id);
        result.setData(defineGroup);
        return result;
    }

    @Override
    public PageList<DefineGroup> query(Integer pageIndex, Integer pageSize) {
        PageList<DefineGroup> result = new PageList<>();
        result.setTotalCount(defineGroupMapper.countByExample(null));
        result.setDataList(defineGroupMapper.selectByExampleWithRowbounds(null, new RowBounds((pageIndex - 1) * pageSize, pageSize)));
        return result;
    }
}
