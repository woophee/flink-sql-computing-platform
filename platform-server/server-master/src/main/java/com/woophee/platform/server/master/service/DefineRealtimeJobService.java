package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineRealtimeJob;
import com.woophee.platform.server.master.model.DefineRealtimeJobVO;
import com.woophee.platform.server.common.model.page.PageList;

public interface DefineRealtimeJobService {
    Response create(DefineRealtimeJob defineRealtimeJob);

    Response update(DefineRealtimeJob defineRealtimeJob);

    Response delete(Integer id);

    RestResult<DefineRealtimeJob> retrieve(Integer id);

    PageList<DefineRealtimeJob> query(Integer groupId, Integer pageIndex, Integer pageSize);

    PageList<DefineRealtimeJobVO> webQuery(Integer groupId, Integer pageIndex, Integer pageSize);

    Response submit(Integer id);
}
