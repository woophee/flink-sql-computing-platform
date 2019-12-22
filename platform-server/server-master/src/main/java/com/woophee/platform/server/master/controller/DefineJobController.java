package com.woophee.platform.server.master.controller;

import com.woophee.platform.server.common.model.FlinkJobDetail;
import com.woophee.platform.server.master.service.DefineJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/define-job/v1")
public class DefineJobController {

    @Autowired
    private DefineJobService defineJobService;

    @RequestMapping(value = "/job/{id}", method = RequestMethod.GET)
    public FlinkJobDetail retrieve(@PathVariable("id") Integer id) {
        return defineJobService.retrieve(id);
    }

}
