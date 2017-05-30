package com.impetus.services.api;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;


import com.impetus.domain.Request;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestService.
 */
@Transactional(readOnly = false)
public interface RequestService {

    /**
     * Adds the request.
     * 
     * @param userRequest
     *            the user request
     * @return
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    Request addRequest(Request userRequest) ;

    /**
     * Gets the requests.
     * 
     * @param integer
     *            the integer
     * @return the requests
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    List<Request> getRequests(Integer integer) ;

    /**
     * Gets the requests.
     * 
     * @param user
     *            the user
     * @param status
     *            the status
     * @return the requests
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    List<Request> getRequests(Users user, Integer status) ;

    /**
     * Cancel request.
     * 
     * @param requestId
     *            the request id
     * @param role
     *            the role
     * @return the request
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    Request cancelRequest(Integer requestId, String role) ;

    /**
     * Gets the requests.
     * 
     * @param string
     *            the string
     * @return the requests
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    List<Request> getRequests(String string) ;

    /**
     * Cancel return request.
     * 
     * @param requestId
     *            the request id
     * @param roleName
     *            the role name
     * @return the request
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    Request cancelReturnRequest(Integer requestId, String roleName)
            ;

    /**
     * Generate return request.
     * 
     * @param parseInt
     *            the parse int
     * @return the request
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws ServiceException 
     */
    Request generateReturnRequest(int parseInt) ;

    /**
     * Gets the requests.
     * 
     * @return the requests
     * @throws ServiceException 
     */
    List<Integer> getRequests();
}
