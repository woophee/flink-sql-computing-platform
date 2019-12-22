package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.common.model.FlinkClusterRequest;
import com.woophee.platform.server.common.model.page.PageList;

public interface FlinkManageService {
    void addCluster(FlinkClusterRequest flinkClusterRequest);

    PageList<FlinkCluster> listCluster(Integer pageIndex, Integer pageSize);

    void deleteCluster(Integer id);

    void updateCluster(FlinkCluster flinkCluster);
}
