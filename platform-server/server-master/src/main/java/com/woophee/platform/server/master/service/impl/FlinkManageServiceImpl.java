package com.woophee.platform.server.master.service.impl;

import com.alibaba.fastjson.JSON;
import com.woophee.platform.server.master.dao.mapper.FlinkClusterMapper;
import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.master.exception.FlinkRuntimException;
import com.woophee.platform.server.common.model.FlinkClusterRequest;
import com.woophee.platform.server.common.model.page.PageList;
import com.woophee.platform.server.master.service.FlinkManageService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class FlinkManageServiceImpl implements FlinkManageService {

    @Autowired
    private FlinkClusterMapper flinkClusterMapper;

    @Override
    public void addCluster(FlinkClusterRequest flinkClusterRequest) {
        FlinkCluster flinkCluster = new FlinkCluster();
        flinkCluster.setName(flinkClusterRequest.getName());
        flinkCluster.setAddress(flinkClusterRequest.getAddress());
        flinkClusterMapper.insert(flinkCluster);
    }

    @Override
    public PageList<FlinkCluster> listCluster(Integer pageIndex, Integer pageSize) {
        PageList<FlinkCluster> result = new PageList<>();
        result.setTotalCount(flinkClusterMapper.countByExample(null));
        result.setDataList(flinkClusterMapper.selectByExampleWithRowbounds(null, new RowBounds((pageIndex - 1) * pageSize, pageSize)));
        return result;
    }

    @Override
    public void deleteCluster(Integer id) {
        int result = flinkClusterMapper.deleteByPrimaryKey(id);
        if (result == 0) {
            throw new FlinkRuntimException("Fail to delete the data by id: " + id);
        }
    }

    @Override
    public void updateCluster(FlinkCluster flinkCluster) {
        int result = flinkClusterMapper.updateByPrimaryKeySelective(flinkCluster);
        if (result == 0) {
            throw new FlinkRuntimException("Fail to update the data by id: " + JSON.toJSONString(flinkCluster));
        }
    }
}
