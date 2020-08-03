package com.huasit.apm.business.submission.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.submission.entity.Submission;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/submission")
public class SubmissionController {

    /**
     *
     */
    @ResponseBody
    @PostMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody Submission form, String[] mId, String[] mName, String[] mFileIds, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        return new ResponseEntity<>(ImmutableMap.of("1", 1), HttpStatus.OK);
    }


    /**
     *
     */
    @Autowired
    UserService userService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
