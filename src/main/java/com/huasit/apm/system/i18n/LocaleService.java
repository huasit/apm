package com.huasit.apm.system.i18n;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.system.util.WebUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.*;

/**
 *
 */
@Service
@Transactional
public class LocaleService {

    /**
     *
     */
    private static final String LANGUAGE_IN_COOKIE = "lc_lang";

    /**
     *
     */
    private static final Locale[] accepts = new Locale[]{Locale.CHINA, Locale.US};

    /**
     *
     */
    public void dealLanguage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale = null;
        String lang = WebUtil.getCookies(request, LANGUAGE_IN_COOKIE);
        if (lang != null) {
            for (Locale accept : accepts) {
                if (accept.toString().equals(lang)) {
                    locale = accept;
                    break;
                }
            }
        }
        if (locale == null) {
            locale = localeResolver.resolveLocale(request);
        }
        if (!locale.toString().equals(lang)) {
            WebUtil.addCookie(response, LANGUAGE_IN_COOKIE, locale.toString());
        }
        if (!locale.equals(localeResolver.resolveLocale(request))) {
            RequestContextUtils.getLocaleResolver(request).setLocale(request, response, locale);
        }
    }

    /**
     *
     */
    public void setLanguage(HttpServletRequest request, HttpServletResponse response, String lang) throws Exception {
        for (Locale accept : accepts) {
            if (accept.toString().equals(lang)) {
                WebUtil.addCookie(response, LANGUAGE_IN_COOKIE, lang);
                break;
            }
        }
    }

    /**
     *
     */
    public Locale getCurrentLanguage(HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        Locale locale = null;
        String lang = WebUtil.getCookies(request, LANGUAGE_IN_COOKIE);
        if (lang != null) {
            for (Locale accept : accepts) {
                if (accept.toString().equals(lang)) {
                    locale = accept;
                    break;
                }
            }
        }
        if (locale == null) {
            locale = localeResolver.resolveLocale(request);
        }
        return locale;
    }

    /**
     *
     */
    public List<Map<String, Object>> getAcceptLanguages() {
        List<Map<String, Object>> list = new ArrayList<>();
        list.add(ImmutableMap.of("name", "简体中文", "locale", Locale.CHINA, "link", "/language/?lang=zh_CN"));
        //list.add(ImmutableMap.of("name", "English", "locale", Locale.US, "link", "/language/?lang=en_US"));
        return list;
    }

    /**
     *
     */
    public String getMessage(HttpServletRequest request, String key, Object... objects) {
        return this.messageSource.getMessage(key, objects, this.getCurrentLanguage(request));
    }

    /**
     *
     */
    @Autowired
    MessageSource messageSource;
}