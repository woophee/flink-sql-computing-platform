package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineScheduledJob;
import com.woophee.platform.server.common.model.page.PageList;

public interface DefineScheduledJobService {
    Response create(DefineScheduledJob defineRealtimeJob);

    Response update(DefineScheduledJob defineRealtimeJob);

    Response delete(Integer id);

    RestResult<DefineScheduledJob> retrieve(Integer id);

    PageList<DefineScheduledJob> query(Integer groupId, Integer pageIndex, Integer pageSize);
}
