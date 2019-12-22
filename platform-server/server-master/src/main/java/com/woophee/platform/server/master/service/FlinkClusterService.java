package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.common.model.page.PageList;

public interface FlinkClusterService {
    Response create(FlinkCluster flinkCluster);

    Response delete(Integer id);

    Response update(FlinkCluster flinkCluster);

    RestResult<FlinkCluster> retrieve(Integer id);

    PageList<FlinkCluster> query(Integer pageIndex, Integer pageSize);
}
