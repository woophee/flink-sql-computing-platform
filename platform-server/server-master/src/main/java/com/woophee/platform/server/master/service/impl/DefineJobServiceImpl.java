package com.woophee.platform.server.master.service.impl;

import com.woophee.platform.server.master.dao.mapper.DefineJobMapper;
import com.woophee.platform.server.master.dao.mapper.DefineRealtimeJobMapper;
import com.woophee.platform.server.master.dao.mapper.DefineScheduledJobMapper;
import com.woophee.platform.server.master.dao.model.DefineJob;
import com.woophee.platform.server.master.dao.model.DefineRealtimeJob;
import com.woophee.platform.server.master.dao.model.DefineScheduledJob;
import com.woophee.platform.server.master.exception.ParamException;
import com.woophee.platform.server.master.service.DefineJobService;
import com.woophee.platform.server.common.model.FlinkJobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefineJobServiceImpl implements DefineJobService {

    @Autowired
    private DefineJobMapper defineJobMapper;

    @Autowired
    private DefineRealtimeJobMapper defineRealtimeJobMapper;

    @Autowired
    private DefineScheduledJobMapper defineScheduledJobMapper;

    @Override
    public FlinkJobDetail retrieve(Integer id) {
        FlinkJobDetail flinkJobDetail = new FlinkJobDetail();
        DefineJob defineJob = defineJobMapper.selectByPrimaryKey(id);
        switch (defineJob.getType()){
            case REALTIME:
                DefineRealtimeJob defineRealtimeJob = defineRealtimeJobMapper.selectByPrimaryKey(id);
                flinkJobDetail.setId(defineRealtimeJob.getId());
                flinkJobDetail.setName(defineRealtimeJob.getName());
                flinkJobDetail.setSql(defineRealtimeJob.getSqlText());
                flinkJobDetail.setParallelism(defineRealtimeJob.getParallelism());
                break;
            case SCHEDULED:
                DefineScheduledJob defineScheduledJob = defineScheduledJobMapper.selectByPrimaryKey(id);
                flinkJobDetail.setId(defineScheduledJob.getId());
                flinkJobDetail.setName(defineScheduledJob.getName());
                flinkJobDetail.setSql(defineScheduledJob.getSqlText());
                flinkJobDetail.setParallelism(defineScheduledJob.getParallelism());
                break;
            default:
                throw new ParamException("不存在的任务类型");
        }
        return flinkJobDetail;
    }
}
