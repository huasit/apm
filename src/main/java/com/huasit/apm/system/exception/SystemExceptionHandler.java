package com.huasit.apm.system.exception;

import com.google.common.collect.ImmutableMap;
import com.huasit.apm.system.util.LocaleUtil;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

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
        return new ResponseEntity<>(LocaleUtil.getErrorResponseEntity(request, e.code), HttpStatus.BAD_REQUEST);
    }

    /**
     *
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, Object>> exceptionHandler(HttpServletRequest request, Exception e) throws Exception {
        LogManager.getLogger().error(e);
        return new ResponseEntity<>(ImmutableMap.of("error_code", 1000, "error_msg", e.getMessage() == null ? "" : e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}