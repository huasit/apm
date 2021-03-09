package com.huasit.apm.core.comment.service;

import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.entity.CommentRepository;
import com.huasit.apm.system.util.LocaleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class CommentService {

    /**
     *
     */
    public List<Comment> getByTarget(String target, Long targetId) {
        return this.commentRepository.findByTarget(target, targetId);
    }

    /**
     *
     */
    @Autowired
    CommentRepository commentRepository;
}
