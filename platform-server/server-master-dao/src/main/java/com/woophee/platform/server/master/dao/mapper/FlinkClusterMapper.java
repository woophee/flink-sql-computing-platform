package com.woophee.platform.server.master.dao.mapper;

import com.woophee.platform.server.master.dao.model.FlinkCluster;
import com.woophee.platform.server.master.dao.model.FlinkClusterExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface FlinkClusterMapper {
    long countByExample(FlinkClusterExample example);

    int deleteByExample(FlinkClusterExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(FlinkCluster record);

    int insertSelective(FlinkCluster record);

    List<FlinkCluster> selectByExampleWithRowbounds(FlinkClusterExample example, RowBounds rowBounds);

    List<FlinkCluster> selectByExample(FlinkClusterExample example);

    FlinkCluster selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") FlinkCluster record, @Param("example") FlinkClusterExample example);

    int updateByExample(@Param("record") FlinkCluster record, @Param("example") FlinkClusterExample example);

    int updateByPrimaryKeySelective(FlinkCluster record);

    int updateByPrimaryKey(FlinkCluster record);
}