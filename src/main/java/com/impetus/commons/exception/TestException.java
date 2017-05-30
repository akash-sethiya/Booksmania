package com.impetus.commons.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestException extends Exception 
{
    
    private static final long serialVersionUID = 1L;
    private Logger logger = LoggerFactory.getLogger(TestException.class);
    
    public TestException(String msg,Throwable cause) {
        logger.error("Exception in test cases="+msg+" and STRACKTRACE ="+cause);
    }
    public TestException() {
     logger.error("EXCEPTION in TEST CASES");
    }
}
