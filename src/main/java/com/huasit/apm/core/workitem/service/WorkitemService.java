package com.huasit.apm.core.workitem.service;

import com.huasit.apm.core.workitem.entity.Workitem;
import com.huasit.apm.core.workitem.entity.WorkitemRepository;
import com.huasit.apm.system.util.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class WorkitemService {

    /**
     *
     */
    public Page<Workitem> getUserWorkitem(Long userId, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("reachTime")));
        return this.workitemRepository.findByApproverId(userId, pageRequest);
    }

    /**
     *
     */
    public Page<Workitem> getUserWorkitem(Long userId, int page, int pageSize, HttpServletRequest request) {
        Page<Workitem> workitems = this.getUserWorkitem(userId, page, pageSize);
        if(workitems == null || !workitems.hasContent()) {
            return workitems;
        }
        for(Workitem workitem : workitems.getContent()) {
            workitem.setStageStr(LocaleUtil.getMessage(request, "stage_"+workitem.getStage()));
            workitem.setTargetStr(LocaleUtil.getMessage(request, "target_"+workitem.getTarget()));
        }
        return workitems;
    }

    /**
     *
     */
    @Autowired
    WorkitemRepository workitemRepository;
}
