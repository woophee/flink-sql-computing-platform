package com.woophee.platform.server.master.dao.mapper;

import com.woophee.platform.server.master.dao.model.DefineRealtimeJob;
import com.woophee.platform.server.master.dao.model.DefineRealtimeJobExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DefineRealtimeJobMapper {
    long countByExample(DefineRealtimeJobExample example);

    int deleteByExample(DefineRealtimeJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DefineRealtimeJob record);

    int insertSelective(DefineRealtimeJob record);

    List<DefineRealtimeJob> selectByExampleWithRowbounds(DefineRealtimeJobExample example, RowBounds rowBounds);

    List<DefineRealtimeJob> selectByExample(DefineRealtimeJobExample example);

    DefineRealtimeJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DefineRealtimeJob record, @Param("example") DefineRealtimeJobExample example);

    int updateByExample(@Param("record") DefineRealtimeJob record, @Param("example") DefineRealtimeJobExample example);

    int updateByPrimaryKeySelective(DefineRealtimeJob record);

    int updateByPrimaryKey(DefineRealtimeJob record);
}