package com.huasit.apm.core.comment.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.comment.entity.Comment;
import com.huasit.apm.core.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/comment")
public class CommentController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(@RequestParam("target") String target,@RequestParam("targetId") Long targetId, HttpServletRequest request) {
        List<Comment> list = this.commentService.getByTarget(target, targetId, request);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    CommentService commentService;
}
