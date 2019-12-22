package com.woophee.platform.server.master.service;

import com.woophee.platform.server.master.common.Response;
import com.woophee.platform.server.master.common.RestResult;
import com.woophee.platform.server.master.dao.model.DefineGroup;
import com.woophee.platform.server.common.model.page.PageList;

public interface DefineGroupService {
    Response create(DefineGroup defineGroup);

    Response delete(Integer id);

    Response update(DefineGroup defineGroup);

    RestResult<DefineGroup> retrieve(Integer id);

    PageList<DefineGroup> query(Integer pageIndex, Integer pageSize);
}
