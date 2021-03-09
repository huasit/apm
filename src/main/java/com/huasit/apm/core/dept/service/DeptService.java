package com.huasit.apm.core.dept.service;

import com.huasit.apm.core.dept.entity.Dept;
import com.huasit.apm.core.dept.entity.DeptRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService {

    /**
     *
     */
    @Resource
    DeptRepository deptRepository;

    /**
     *
     */
    public List<Dept> list() {
        return this.deptRepository.findAll();
    }
}
