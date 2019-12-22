package com.woophee.platform.server.master.dao.mapper;

import com.woophee.platform.server.master.dao.model.DefineGroup;
import com.woophee.platform.server.master.dao.model.DefineGroupExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DefineGroupMapper {
    long countByExample(DefineGroupExample example);

    int deleteByExample(DefineGroupExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DefineGroup record);

    int insertSelective(DefineGroup record);

    List<DefineGroup> selectByExampleWithRowbounds(DefineGroupExample example, RowBounds rowBounds);

    List<DefineGroup> selectByExample(DefineGroupExample example);

    DefineGroup selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DefineGroup record, @Param("example") DefineGroupExample example);

    int updateByExample(@Param("record") DefineGroup record, @Param("example") DefineGroupExample example);

    int updateByPrimaryKeySelective(DefineGroup record);

    int updateByPrimaryKey(DefineGroup record);
}