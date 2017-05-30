package com.impetus.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DAORuntimeException extends RuntimeException 
{
    
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(DAORuntimeException.class);
    public DAORuntimeException() {
        super();
    }
    
    public DAORuntimeException(String message,Throwable cause){
        super(message,cause);
        logger.info("DAORuntime Exception occured::::Message="+message+"StrackTrace="+cause);
    }
    
}
