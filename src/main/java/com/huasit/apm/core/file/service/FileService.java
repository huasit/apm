package com.huasit.apm.core.file.service;

import com.huasit.apm.core.file.entity.File;
import com.huasit.apm.core.file.entity.FileRepository;
import com.huasit.apm.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class FileService {

    /**
     *
     */
    @Value("${temp.path}")
    private String tempPath;

    /**
     *
     */
    @Value("${storage.path}")
    private String storagePath;

    /**
     *
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        if(!new java.io.File(tempPath).exists()) {
            new java.io.File(tempPath).mkdirs();
        }
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(tempPath);
        return factory.createMultipartConfig();
    }

    /**
     *
     */
    public File getFileById(Long id){
        return this.fileRepository.findFileById(id);
    }

    /**
     *
     */
    public List<File> getFileByIds(List<Long> ids){
        List<File> files = this.fileRepository.findByIds(ids);
        if(files != null) {
            for(File file : files) {
                file.setUrl("/file/download/?id=" + file.getId());
            }
        }
        return files;
    }

    /**
     *
     */
    public File upload(MultipartFile formFile, User loginUser) {
        File file = new File();
        file.setDel(false);
        file.setName(formFile.getOriginalFilename());
        file.setPath(this.stroageUploadFile(formFile));
        file.setCreatorId(loginUser.getId());
        file.setCreateTime(new Date());
        return this.fileRepository.save(file);
    }

    /**
     *
     */
    private String stroageUploadFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String path = "upload/" + UUID.randomUUID().toString() + "/";
        new java.io.File(storagePath + path).mkdirs();
        try {
            file.transferTo(new java.io.File(storagePath + path + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return path + fileName;
    }

    public String getStoragePath() {
        return storagePath;
    }

    /**
     *
     */
    @Autowired
    FileRepository fileRepository;
}