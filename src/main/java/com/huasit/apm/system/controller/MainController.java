package com.huasit.apm.system.controller;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.system.i18n.LocaleService;
import com.huasit.apm.system.util.WebUtil;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *
 */
@Controller
public class MainController {

    /**
     *
     */
    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        User loginUser = this.userLoginService.getLoginUser(request);
        model.addAttribute("acceptLanguages", this.localeService.getAcceptLanguages());
        model.addAttribute("currentLanguage", this.localeService.getCurrentLanguage(request));
        return "core/index/main";
    }

    /**
     *
     */
    @GetMapping("/login/")
    public String login(Model model, HttpServletRequest request) {
        String username = WebUtil.getCookies(request, UserLoginService.USERNAME_IN_COOKIE);
        if (username != null && !"".equals(username)) {
            model.addAttribute("username", username);
        }
        return "core/login/main";
    }

    /**
     *
     */
    @ResponseBody
    @PostMapping("/login/")
    public ResponseEntity<Map<String, Object>> login(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = this.userLoginService.userLogin(username, password, request, response);
        if (user == null) {
            String error = this.localeService.getMessage(request, "noty_uoperror");
            return new ResponseEntity<>(ImmutableMap.of("message", error), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(ImmutableMap.of("id", user.getId(), "name", user.getName()), HttpStatus.OK);
    }

    /**
     *
     */
    @GetMapping("/logout/")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        this.userLoginService.userLogout(request, response);
        return "redirect:/login/";
    }

    /**
     *
     */
    @GetMapping("/language/")
    public String language(HttpServletRequest request, HttpServletResponse response, String lang) throws Exception {
        this.localeService.setLanguage(request, response, lang);
        return "redirect:/";
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
    LocaleService localeService;
}