package com.impetus.services.schedular.impl;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impetus.commons.exception.ServiceException;
import com.impetus.commons.mail.MailToUser;
import com.impetus.dao.schedular.api.TaskSchedularDAO;
import com.impetus.domain.UserDetails;
import com.impetus.domain.Users;
import com.impetus.services.api.UserDetailsService;
import com.impetus.services.api.UserService;
import com.impetus.services.schedular.api.TaskSchedularService;

// TODO: Auto-generated Javadoc
/**
 * The Class TaskSchedularServiceImpl.
 */
@Service("schedularService")
public class TaskSchedularServiceImpl implements TaskSchedularService {

    /** The user details service. */
    @Autowired
    private UserDetailsService userDetailsService;

    /** The user service. */
    @Autowired
    private UserService userService;

    /** The schedular dao. */
    @Autowired
    private TaskSchedularDAO schedularDAO;

    /** The mail to user. */
    private MailToUser mailToUser = new MailToUser();

    /** The today. */
    private DateTime today = new DateTime(); 
    
    private Logger logger = LoggerFactory.getLogger(TaskSchedularServiceImpl.class);

        /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.schedular.api.TaskSchedularService#
     * subscriptionEndAlertToUser()
     */
    public void subscriptionEndAlertToUser() throws
            IOException { 
        try{
        logger.info("in SubscriptionEndALerttoUser function");
        List<UserDetails> details = userDetailsService.getUserDetailsList();
        for (UserDetails u : details) {
            DateTime subscriptionExpiryDate = new DateTime(
                    u.getSubscriptionEndDate());
            int daysDuration = Days.daysBetween(today, subscriptionExpiryDate)
                    .getDays();
            Users user = userService.getUser(u.getUserDetailsId());
            if(user.getRole().getRoleName().equals("ROLE_USER"))
            {
                if (daysDuration == 30) {
                    logger.info("daysDuration="+30);
                    logger.info("username="+user.getUserDetails().getName());
                    mailToUser.shootMail("SUBSCRIPTION_ALERT", user, daysDuration);
                }   
                if (daysDuration <= 7) {
                    logger.info("daysDuration<="+7);
                    logger.info("username="+user.getUserDetails().getName());
                    mailToUser.shootMail("SUBSCRIPTION_ALERT", user, daysDuration);
                }
                if (daysDuration == 0) {
                    logger.info("daysDuration="+30);
                    logger.info("before update status="+user.getStatus());
                    user.setStatus(false);
                    user=userService.updateUser(user);
                    logger.info("after update status="+user.getStatus());
                    mailToUser.shootMail("SUBSCRIPTION_END", user, daysDuration);
                }
            }
        }
        }
        catch(IOException e)
        {
            logger.error("IO error occured in subscriptionEndAlertToUser::::"+e);
            throw new ServiceException("IOException occur",e);
        }
        catch(Exception e)
        {
            logger.error("error occured in subscriptionEndAlertToUser::::"+e);
            throw new ServiceException("Exception occur",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.schedular.api.TaskSchedularService#addAndUpdateBook
     * ()
     */
    public void addAndUpdateBook()  {
        try{
            logger.info("Add and update book schedular function.");
            String csvFilePath="D:\\dook_data.csv";
            schedularDAO.addAndUpdateBook(csvFilePath);
        }
        catch(Exception e)
        {
            logger.error("exception occur in addAndUpdateBook()::::"+e);
            throw new ServiceException("exception occured",e);
        }

    }
}
