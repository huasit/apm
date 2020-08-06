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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

@Controller
@RequestMapping("/file")
public class FileController {

    /**
     *
     */
    @GetMapping("/download/")
    public void file(@RequestParam Long id, HttpServletResponse response) throws Exception {
        File file = this.fileService.getFileById(id);
        if(file == null) {
            return;
        }
        String path = this.fileService.getStoragePath() + file.getPath();
        java.io.File f = new java.io.File(path);
        if(!f.exists()) {
            return;
        }
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Length", "" + f.length());
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getName(),"UTF-8"));
        InputStream inputStream = new FileInputStream(path);
        OutputStream outputStream = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while((length = inputStream.read(buffer)) != -1)  {
            outputStream.write(buffer,0,length);
        }
        inputStream.close();
        outputStream.close();
    }

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
