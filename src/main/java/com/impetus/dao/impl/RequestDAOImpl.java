package com.impetus.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.RequestDAO;
import com.impetus.domain.Book;
import com.impetus.domain.History;
import com.impetus.domain.Request;
import com.impetus.domain.Users;

// TODO: Auto-generated Javadoc
/**
 * The Class RequestDAOImpl.
 */
@Repository("requestDAO")
@SuppressWarnings("unchecked")
public class RequestDAOImpl implements RequestDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;

    /** The Constant DAYSEXTENSION. */
    private static final int DAYSEXTENSION = 30;

    /** The property. */
    private Properties property = new Properties();

    /** The property file name. */
    private String propertyFileName = "status.properties";

    /** The input stream. */
    private InputStream inputStream = getClass().getClassLoader()
            .getResourceAsStream(propertyFileName);

    private static final String PENDING = "PENDING";

    private static final int DAYSINWEEK = 7;
    
    private Logger logger = LoggerFactory.getLogger(RequestDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.RequestDAO#addRequest(com.impetus.domain.Request)
     */
    public Request addRequest(Request userRequest) throws DAOException {
        try{
        logger.info("in addRequest Method");
        property.load(inputStream);
        userRequest.setDelieveryRequeststatus(Integer.parseInt(property
                .getProperty(PENDING)));
        hibernateTemplate.save(userRequest);
        return userRequest;
        }
        catch(IOException e)
        {
            logger.error("IOexception occured in addRequest MEthod::::"+e);
            throw new DAOException("exception occured in addRequest MEthod",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in adding request::::"+e);
            throw new DAOException("exception occured in addRequest()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#getRequests(com.impetus.domain.Users,
     * java.lang.Integer)
     */

    public List<Request> getRequests(Users user, Integer status)
            throws DAOException{
        try{
            logger.info("in getREquest(user,integer method");
        property.load(inputStream);
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Request.class);
        if (status == 0) {
            logger.info("selected status is delievertRequestStatus");
            dCriteria.add(Restrictions.and(
                    Restrictions.eq("user", user),
                    Restrictions.eq("delieveryRequeststatus",
                            Integer.parseInt(property.getProperty(PENDING)))));
        } else if (status == 1) {
            logger.info("selected status is returnREquestStatus");
            dCriteria.add(Restrictions.and(
                    Restrictions.eq("user", user),
                    Restrictions.eq("returnRequestStatus",
                            Integer.parseInt(property.getProperty(PENDING)))));
        } else if (status == 2) {
            logger.info("selected status is delievry request status closed and return request status is null");
            dCriteria.add(Restrictions.and(Restrictions.eq("user", user),
                    Restrictions.and(Restrictions.eq("delieveryRequeststatus",
                            Integer.parseInt(property.getProperty("CLOSED"))),
                            Restrictions.isNull("returnRequestStatus"))));
        }

        return (List<Request>) hibernateTemplate.findByCriteria(dCriteria);
        }
        catch(IOException e)
        {
            logger.error("IO exception occured in getRequest MEthod::::"+e);
            throw new DAOException("IOexception occured in getREquest",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getRequest MEthod::::"+e);
            throw new DAOException("exception occured in getRequest(user,integer)", e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#cancelRequest(java.lang.Integer,
     * java.lang.String)
     */
    public Request cancelRequest(Integer requestId, String role)
            throws DAOException {
        try{
        logger.info("in cancelRequest(requestId,role) method");
        property.load(inputStream);
        Request request = (Request) hibernateTemplate.get(Request.class,
                requestId);
        Users user = (Users) hibernateTemplate.get(Users.class, request
                .getUser().getUserId());
        Book book = (Book) hibernateTemplate.get(Book.class, request.getBook()
                .getBookId());

        if (role == null) {
            logger.info("admin is accessing the method and closing the request");
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(request.getIssuedDate());
            calendar.add(Calendar.DATE, DAYSEXTENSION);

            request.setDelieveryRequeststatus(Integer.parseInt(property
                    .getProperty("CLOSED")));
            request.setIssuedDate(new Date());
            request.setExpectedReturnedDate(calendar.getTime());

            hibernateTemplate.update(request);
            hibernateTemplate.update(book);
            hibernateTemplate.update(user);

            request = (Request) hibernateTemplate.get(Request.class, requestId);
        } else {
            logger.info("user is accessing the method and cancelling the request");
            user.setRequestBookCount(user.getRequestBookCount() - 1);
            hibernateTemplate.update(user);
            request.setDelieveryRequeststatus(Integer.parseInt(property
                    .getProperty("CANCELLED")));
            hibernateTemplate.update(request);
            History history = setHistoryBean(request);
            hibernateTemplate.save(history);
            hibernateTemplate.delete(request);
            request = (Request) hibernateTemplate.get(Request.class, requestId);
        }

        return request;
        }
        catch(IOException e)
        {
            logger.error("IOexception occured in cancelRequest MEthod::::"+e);
            throw new DAOException("IOException occured",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in cancelREquest method::::"+e);
            throw new DAOException("Exception occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#getRequests(java.lang.String)
     */
    public List<Request> getRequests(String string) throws DAOException {
        try{
        logger.info("in getRequest(String method");
        property.load(inputStream);
        List<Request> requestList = null;
        if (string.equalsIgnoreCase("PendingDelievery")) {
            logger.info("PendingDelievery comes as an argument.");
            requestList = (List<Request>) hibernateTemplate.find("from Request where delieveryRequestStatus=?",Integer.parseInt(property.getProperty(PENDING)));
        } else if (string.equalsIgnoreCase("PendingReturn")) {
            logger.info("PendingReturn comes as an argument");
            requestList = (List<Request>) hibernateTemplate.find("from Request where returnRequestStatus=?",Integer.parseInt(property.getProperty(PENDING)));
        }
            return requestList;
        }
        catch(IOException e)
        {
            logger.error("IOexception occur :::: "+e);
            throw new DAOException("IOException occured",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getRequest MEthod::::"+e);
            throw new DAOException("exception occured in getRequest method",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#getRequest(java.lang.Integer)
     */
    public List<Request> getRequest(Integer userId) throws DAOException {
        try{
            logger.info("in getREquest(userId)");
            return (List<Request>) hibernateTemplate.find("from Request where user_userId=?", userId);
        }
        catch(Exception e)
        {
            logger.error("Exception occured in getREquest(userid) method::::"+e);
            throw new DAOException("error occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.RequestDAO#cancelReturnRequest(java.lang.Integer,
     * java.lang.String)
     */
    public Request cancelReturnRequest(Integer requestId, String roleName)
            throws DAOException {
        try{
            logger.info("in cancelReturnRequest(requestId,roleNAme) method");
        property.load(inputStream);
        Request userRequest = (Request) hibernateTemplate.get(Request.class,
                requestId);
        Request tempUserRequest=userRequest;
        Book book = (Book) hibernateTemplate.get(Book.class, userRequest
                .getBook().getBookId());
        if (roleName == null) {
            logger.info("role is ADMIN");
            userRequest.setReturnDate(new Date());
            userRequest.setReturnRequestStatus(Integer.parseInt(property
                    .getProperty("CLOSED")));
            book.setAvailability(book.getAvailability() + 1);

            hibernateTemplate.update(userRequest);
            hibernateTemplate.update(book);

            History history = setHistoryBean(userRequest);
            hibernateTemplate.save(history);

            userRequest = (Request) hibernateTemplate.get(Request.class,
                    requestId);
            hibernateTemplate.delete(userRequest);
            return tempUserRequest;
        } else {
            logger.info("role is USER");
            if (Days.daysBetween(
                    new DateTime(new Date()),
                    new DateTime(userRequest.getUser().getUserDetails()
                            .getSubscriptionEndDate())).getDays() > DAYSINWEEK) {
                logger.info("days difference is greater than 7");
                userRequest.setReturnRequestStatus(null);
                hibernateTemplate.update(userRequest);
            } else {
                logger.info("days difference is <=7");
                return null;
            }
        }
        return (Request) hibernateTemplate.get(Request.class, requestId);
        }
        catch(IOException e)
        {
            logger.error("IOException occured in cancelREturnRequest(reqiestId,roleName) method::::"+e);
            throw new DAOException("IOException occured",e);
        }
        catch(Exception e)
        {
            logger.error("exception occured in cancelReturnRequest(requestId,roleName )method::::"+e);
            throw new DAOException("error occured in cancelling the request", e);
        }
        
    }

    /**
     * Sets the history bean.
     * 
     * @param request
     *            the request
     * @return the history
     * @throws DAOException 
     */
    public History setHistoryBean(Request request) throws DAOException {
        try{
            logger.info("Setting history Bean");
        History history = new History();
        history.setBookId(request.getBook().getBookId());
        history.setDelieveryAddress(request.getDelieveryAddress());
        history.setDelieveryRequeststatus(request.getDelieveryRequeststatus());
        history.setExpectedReturnedDate(request.getExpectedReturnedDate());
        history.setIssuedDate(request.getIssuedDate());
        history.setRequestId(request.getRequestId());
        history.setReturnDate(request.getReturnDate());
        history.setUserId(request.getUser().getUserId());
        history.setReturnRequestStatus(request.getReturnRequestStatus());
        return history;
        }
        catch(Exception e)
        {
            logger.error("Exception occured in setHistoryBean() method::::"+e);
            throw new DAOException("exception occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#generateReturnRequest(int)
     */
    public Request generateReturnRequest(int requestId) throws DAOException {
        try{
            logger.info("in generateREturnRequest(requestId)method");
        property.load(inputStream);
        Request userRequest = (Request) hibernateTemplate.get(Request.class,
                requestId);
        userRequest.setReturnRequestStatus(Integer.parseInt(property
                .getProperty(PENDING)));
        hibernateTemplate.update(userRequest);
        return (Request) hibernateTemplate.get(Request.class, requestId);
        }
        catch(Exception e)
        {
            logger.error("exception occured in generateReturnREquest(requestID) method::::"+e);
            throw new DAOException("exception occured in generateREturnREquest",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.RequestDAO#getRequests()
     */
    public List<Integer> getRequests() throws DAOException {
        try{
         logger.info("in getRequest m,ethod");
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Request.class);
        dCriteria.setResultTransformer(DetachedCriteria.DISTINCT_ROOT_ENTITY);
        List<Request> requestList = (List<Request>) hibernateTemplate
                .findByCriteria(dCriteria);

        List<Integer> requestedBookIdList = new ArrayList<Integer>();
        for (Request r : requestList) {
            requestedBookIdList.add(r.getBook().getBookId());
        }
        return requestedBookIdList;
        }
        catch(Exception e)
        {
            logger.error("exception occured in getRequestMethod()::::"+e);
            throw new DAOException("exception occured - > getRequests() method",e);
        }
    }
}
