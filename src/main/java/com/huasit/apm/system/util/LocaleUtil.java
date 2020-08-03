package com.huasit.apm.system.util;

import com.google.common.collect.ImmutableMap;
import org.springframework.context.MessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

public class LocaleUtil {

    /**
     *
     */
    private static MessageSource messageSource;

    /**
     *
     */
    public static void init(MessageSource messageSource) {
        LocaleUtil.messageSource = messageSource;
    }

    /**
     *
     */
    public static String getMessage(HttpServletRequest request, String key, Object... objects) {
        return messageSource.getMessage(key, objects, getCurrentLanguage(request));
    }

    /**
     *
     */
    public static Locale getCurrentLanguage(HttpServletRequest request) {
        LocaleResolver localeResolver = RequestContextUtils.getLocaleResolver(request);
        return localeResolver.resolveLocale(request);
    }

    /**
     *
     */
    public static Map<String, Object> getErrorResponseEntity(HttpServletRequest request, int errorCode) {
        return ImmutableMap.of("error_code", errorCode, "error_msg", getMessage(request, "err_" + errorCode));
    }
}