package com.huasit.apm.business.thirdparty.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.business.thirdparty.service.ThirdpartyService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/thirdparty")
public class ThirdpartyController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long id, HttpServletRequest request) {
        Thirdparty thirdparty = this.thirdpartyService.getThirdpartyById(id);
        return new ResponseEntity<>(ImmutableMap.of("thirdparty", thirdparty), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(Thirdparty form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<Thirdparty> list = this.thirdpartyService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Thirdparty form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin()) {
            throw new SystemException(10002);
        }
        this.thirdpartyService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("thirdparty", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        if(!loginUser.isAdmin()) {
            throw new SystemException(10002);
        }
        this.thirdpartyService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;

    /**
     *
     */
    @Autowired
    ThirdpartyService thirdpartyService;
}
