package com.woophee.platform.server.master.dao.mapper;

import com.woophee.platform.server.master.dao.model.DefineScheduledJob;
import com.woophee.platform.server.master.dao.model.DefineScheduledJobExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DefineScheduledJobMapper {
    long countByExample(DefineScheduledJobExample example);

    int deleteByExample(DefineScheduledJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DefineScheduledJob record);

    int insertSelective(DefineScheduledJob record);

    List<DefineScheduledJob> selectByExampleWithRowbounds(DefineScheduledJobExample example, RowBounds rowBounds);

    List<DefineScheduledJob> selectByExample(DefineScheduledJobExample example);

    DefineScheduledJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DefineScheduledJob record, @Param("example") DefineScheduledJobExample example);

    int updateByExample(@Param("record") DefineScheduledJob record, @Param("example") DefineScheduledJobExample example);

    int updateByPrimaryKeySelective(DefineScheduledJob record);

    int updateByPrimaryKey(DefineScheduledJob record);
}