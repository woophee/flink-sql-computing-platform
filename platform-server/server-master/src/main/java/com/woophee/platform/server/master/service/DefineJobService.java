package com.woophee.platform.server.master.service;

import com.woophee.platform.server.common.model.FlinkJobDetail;

public interface DefineJobService {
    FlinkJobDetail retrieve(Integer id);
}
