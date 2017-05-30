package com.impetus.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceRuntimeException extends RuntimeException 
{
    
    private static final long serialVersionUID = 1L;
    private Logger logger=LoggerFactory.getLogger(ServiceRuntimeException.class);
    public ServiceRuntimeException(){
        super();
    }
    
    public ServiceRuntimeException(String message,Throwable cause){
        super(message,cause);
        logger.info("Service Runtime Exception occured::::MESSAGE="+message+":::STRACKTRACE="+cause);
    }
}
