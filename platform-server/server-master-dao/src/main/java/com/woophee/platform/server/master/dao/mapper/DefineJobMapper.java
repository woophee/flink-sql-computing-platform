package com.woophee.platform.server.master.dao.mapper;

import com.woophee.platform.server.master.dao.model.DefineJob;
import com.woophee.platform.server.master.dao.model.DefineJobExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface DefineJobMapper {
    long countByExample(DefineJobExample example);

    int deleteByExample(DefineJobExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DefineJob record);

    int insertSelective(DefineJob record);

    List<DefineJob> selectByExampleWithRowbounds(DefineJobExample example, RowBounds rowBounds);

    List<DefineJob> selectByExample(DefineJobExample example);

    DefineJob selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DefineJob record, @Param("example") DefineJobExample example);

    int updateByExample(@Param("record") DefineJob record, @Param("example") DefineJobExample example);

    int updateByPrimaryKeySelective(DefineJob record);

    int updateByPrimaryKey(DefineJob record);
}