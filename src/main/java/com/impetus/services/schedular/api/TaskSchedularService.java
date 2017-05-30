package com.impetus.services.schedular.api;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Interface TaskSchedularService.
 */
public interface TaskSchedularService {

    /**
     * Subscription end alert to user.
     * 
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException
     */
    void subscriptionEndAlertToUser() throws IOException;

    /**
     * Adds the and update book.
     * @throws ServiceException 
     */
    void addAndUpdateBook();
}
