package com.impetus.commons.schedular;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.impetus.commons.exception.ServiceException;
import com.impetus.services.schedular.api.TaskSchedularService;

// TODO: Auto-generated Javadoc
/**
 * The Class Schedular.
 */
public class Schedular {

    /** The schedular service. */
    @Autowired
    private TaskSchedularService schedularService;
    
    private Logger logger=LoggerFactory.getLogger(Schedular.class);

    /**
     * Subscription alert.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    //@Scheduled(fixedDelay=5000)
    public void subscriptionAlert() {
        try{
            logger.info("in schedular subscriptionAlert() function.");
            schedularService.subscriptionEndAlertToUser();
        }
        catch(IOException e)
        {
            logger.error("IOexception occured in schedular SubscriptionAlert.");
            throw new ServiceException("IOexception occured in schedular subscriptionAlert",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in schedular SubscriptionAlert.");
            throw new ServiceException("exception occured in schedular subscriptionAlert",e);
        }
    }

    /**
     * Adds the and update book.
     * @throws ServiceException 
     */
    //@Scheduled(cron = "0 12 * * * *")
    public void addAndUpdateBook(){
        try{
            logger.info("in schedular addAndUpdateBook.");
            schedularService.addAndUpdateBook();
        }
        catch(Exception e)
        {
            logger.error("exception occured in addandupdate books");
            throw new ServiceException("exception occured in addAndUpdating book",e);
        }
    }
}
