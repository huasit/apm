package com.huasit.apm.system.exception;

/**
 *
 */
public class SystemException extends RuntimeException {

    /**
     *
     */
    public int code;

    /**
     *
     */
    public SystemException(int code) {
        super();
        this.code = code;
    }

    /**
     *
     */
    public SystemException(Exception e, int code) {
        super(e);
        this.code = code;
    }
}