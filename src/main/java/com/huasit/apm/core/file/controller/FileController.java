package com.huasit.apm.core.file.controller;

import com.google.common.collect.ImmutableMap;
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
    public ResponseEntity<Map<String, Object>> upload(MultipartFile file, HttpServletRequest request) {
        return new ResponseEntity<>(ImmutableMap.of("id", 1), HttpStatus.OK);
    }
}
