package com.huasit.apm.core.file.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.service.FileService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/upload/")
    public ResponseEntity<Map<String, Object>> upload(MultipartFile formFile, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        File file = this.fileService.upload(formFile, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("id", file.getId()), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    FileService fileService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
