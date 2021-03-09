package com.huasit.apm.business.bid.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.business.bid.entity.BidNoty;
import com.huasit.apm.business.bid.service.BidNotyService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/bid/noty")
public class BidNotyController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/get/")
    public ResponseEntity<Map<String, Object>> get(Long bidId, HttpServletRequest request) {
        BidNoty bidNoty = this.bidNotyService.getBidNotyByBidId(bidId);
        if (bidNoty == null) {
            return new ResponseEntity<>(ImmutableMap.of("empty", true), HttpStatus.OK);
        }
        return new ResponseEntity<>(ImmutableMap.of("bid_noty", bidNoty), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/list/")
    public ResponseEntity<Map<String, Object>> list(BidNoty form, @RequestParam("page") int page, @RequestParam(name = "pageSize", defaultValue = "10") int pageSize, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        Page<BidNoty> list = this.bidNotyService.list(form, page, pageSize, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("list", list), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/addOrUpdate/")
    public ResponseEntity<Map<String, Object>> addOrUpdate(@RequestBody BidNoty form, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidNotyService.save(form, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("role", form), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @RequestMapping("/delete/")
    public ResponseEntity<Map<String, Object>> delete(@RequestParam("id") Long id, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        this.bidNotyService.delete(id, loginUser);
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @Autowired
    BidNotyService bidNotyService;

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}
