package com.huasit.apm.system.exception;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.system.i18n.LocaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.logging.Logger;

/**
 *
 */
@ControllerAdvice
public class SystemExceptionHandler {

    /**
     *
     */
    @ResponseBody
    @ExceptionHandler(value = SystemException.class)
    public ResponseEntity<Map<String, Object>> systemExceptionHandler(HttpServletRequest request, SystemException e) throws Exception {
        String message = this.localeService.getMessage(request, e.key, e.objects);
        return new ResponseEntity<>(ImmutableMap.of("message", message), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        Logger.getLogger("").severe(e.getMessage());
        String message = this.localeService.getMessage(request, "noty_systemerror");
        return new ResponseEntity<>(ImmutableMap.of("message", message), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     */
    @Autowired
    LocaleService localeService;
}