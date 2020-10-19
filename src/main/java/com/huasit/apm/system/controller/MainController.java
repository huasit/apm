package com.huasit.apm.system.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import com.huasit.apm.system.util.LocaleUtil;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MainController {

    /**
     *
     */
    @ResponseBody
    @GetMapping("/login/")
    public ResponseEntity<Map<String, Object>> login(HttpServletRequest request) {
        return new ResponseEntity<>(LocaleUtil.getErrorResponseEntity(request, 10000), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/login/")
    public ResponseEntity<Map<String, Object>> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = this.userLoginService.userLogin(username, password, request);
        if (user == null) {
            return new ResponseEntity<>(LocaleUtil.getErrorResponseEntity(request, 20000), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ImmutableMap.of("id", user.getId(), "name", user.getName(), "token", user.getToken().getToken()), HttpStatus.OK);
    }

    /**
     *
     */
    @ResponseBody
    @GetMapping("/logout/")
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest request, HttpServletResponse response) {
        return new ResponseEntity<>(ImmutableMap.of("success", true), HttpStatus.OK);
    }

    /**
     *
     */
    @RequestMapping("/third-party/callback/")
    public void thirdPartyCallback(HttpServletRequest request, HttpServletResponse response) throws  Exception {
        Assertion assertion = (Assertion) request.getSession().getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion != null) {
            AttributePrincipal principal = assertion.getPrincipal();
            this.userLoginService.userLoginByThirdParty(principal, request, response);
            Map<String, Object> attributes = principal.getAttributes();
            BASE64Decoder decoder = new BASE64Decoder();
            for(String key : attributes.keySet()) {
                System.out.println(key + ":" + new String(decoder.decodeBuffer(attributes.get(key).toString()), "UTF-8"));
            }
        }
        response.sendRedirect("/#/index/");
    }

    /**
     *
     */
    @Autowired
    UserLoginService userLoginService;
}