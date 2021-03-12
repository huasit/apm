package com.huasit.apm.system.exception;

/**
 *
 */
public class SystemException extends RuntimeException {

    /**
     *
     */
    public int code;
    public Object[] args;

    /**
     *
     */
    public SystemException(int code, Object... args) {
        super();
        this.code = code;
        this.args = args;
    }

    /**
     *
     */
    public SystemException(Exception e, int code) {
        super(e);
        this.code = code;
    }
}