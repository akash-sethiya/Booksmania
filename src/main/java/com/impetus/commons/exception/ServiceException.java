package com.impetus.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceException extends RuntimeException  {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(ServiceException.class);

    public ServiceException(String message, Throwable cause) {
        super(message,cause);
        logger.error("Exception in SERVICE: MESSAGE=" + message
                + " :::::: STRACKTRACE=" + cause);
    }

    public ServiceException() {
        logger.error("Exception in SERVICE");

    }

}
