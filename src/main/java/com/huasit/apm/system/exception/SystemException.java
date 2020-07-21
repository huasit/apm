package com.huasit.apm.system.exception;

/**
 *
 */
@SuppressWarnings("serial")
public class SystemException extends RuntimeException {

    /**
     *
     */
    public String key;

    /**
     *
     */
    public Object[] objects;

    /**
     *
     */
    public SystemException(String key, Object ...objects) {
        super();
        this.key = key;
        this.objects = objects;
    }

    /**
     *
     */
    public SystemException(Exception e, String key, Object ...objects) {
        super(e);
        this.key = key;
        this.objects = objects;
    }
}