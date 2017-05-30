package com.impetus.dao.api;


import java.util.List;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Request;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Interface RequestDAO.
 */
public interface RequestDAO {

    /**
     * Adds the request.
     * 
     * @param userRequest
     *            the user request
     * @return
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws DAOException 
     */
    Request addRequest(Request userRequest) throws  DAOException;

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
     * @throws DAOException 
     */
    List<Request> getRequests(Users user, Integer status) throws  DAOException;

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
     * @throws DAOException 
     */
    Request cancelRequest(Integer requestId, String role) throws  DAOException;

    /**
     * Gets the requests.
     * 
     * @param string
     *            the string
     * @return the requests
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws DAOException 
     */
    List<Request> getRequests(String string) throws  DAOException;

    /**
     * Gets the request.
     * 
     * @param userId
     *            the user id
     * @return the request
     * @throws DAOException 
     */
    List<Request> getRequest(Integer userId) throws DAOException;

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
     * @throws DAOException 
     */
    Request cancelReturnRequest(Integer requestId, String roleName)
            throws  DAOException;

    /**
     * Generate return request.
     * 
     * @param requestId
     *            the request id
     * @return the request
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     * @throws DAOException 
     */
    Request generateReturnRequest(int requestId) throws  DAOException;

    /**
     * Gets the requests.
     * 
     * @return the requests
     * @throws DAOException 
     */
    List<Integer> getRequests() throws DAOException;

}
