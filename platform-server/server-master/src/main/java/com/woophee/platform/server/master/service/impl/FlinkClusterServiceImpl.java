package com.woophee.platform.server.master.service.impl;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.mapper.FlinkClusterMapper;
import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.master.service.FlinkClusterService;
import com.woophee.platform.server.common.model.page.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlinkClusterServiceImpl implements FlinkClusterService {

    @Autowired
    private FlinkClusterMapper flinkClusterMapper;

    @Override
    public Response create(FlinkCluster flinkCluster) {
        return null;
    }

    @Override
    public Response delete(Integer id) {
        return null;
    }

    @Override
    public Response update(FlinkCluster flinkCluster) {
        return null;
    }

    @Override
    public RestResult<FlinkCluster> retrieve(Integer id) {
        return null;
    }

    @Override
    public PageList<FlinkCluster> query(Integer pageIndex, Integer pageSize) {
        return null;
    }
}
