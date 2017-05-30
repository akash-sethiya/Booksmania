package com.impetus.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAOException extends Exception {
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(DAOException.class);

    public DAOException(String message, Throwable cause) {
        super(message,cause);
        logger.error("Exception in DAO: MESSAGE=" + message + " STRACKTRACE=" + cause);
    }
    public DAOException() {
        super();
    }
    
    

}
