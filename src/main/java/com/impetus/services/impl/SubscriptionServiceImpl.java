package com.impetus.services.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.SubscriptionDAO;
import com.impetus.domain.Subscription;
import com.impetus.domain.SubscriptionHistory;
import com.impetus.domain.Users;
import com.impetus.services.api.SubscriptionService;

// TODO: Auto-generated Javadoc
/**
 * The Class SubscriptionServiceImpl.
 */
@Service("subscriptionService")
public class SubscriptionServiceImpl implements SubscriptionService,
        Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The subscription dao. */
    @Autowired
    private SubscriptionDAO subscriptionDAO;

    /** The Constant MAXFILESIZE. */
    private static final Integer MAXFILESIZE = 10000000;

    /** The logger. */
    private Logger logger = LoggerFactory
            .getLogger(SubscriptionServiceImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#addSubscriptionPlans(com
     * .impetus.domain.Subscription)
     */
    public Subscription addSubscriptionPlans(Subscription subscription)  {
        try{
            logger.info("SERVICE: In addSubscriptionPlans(Susbcription subscription)");
            return subscriptionDAO.addSubscriptionPlans(subscription);
        }
        catch(Exception e)
        {
            logger.error("exception occured in service:addSubscriptionPlans");
            throw new ServiceException("exception occured in addSubscriptionPlans()",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.SubscriptionService#getSubscriptionPlans()
     */
    public List<Subscription> getSubscriptionPlans()  {
        try{
            logger.info("SERVICE: In getSubscriptionPlans()");
            return subscriptionDAO.getSubscriptionPlans();
        }
        catch(Exception e)
        {
            logger.error("exception occured in getSubscrioptionPlans");
            throw new ServiceException("exception occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#getSubscriptionExpiryDate
     * (java.util.Date, java.lang.Integer)
     */
    public Date getSubscriptionExpiryDate(Date date, Integer subscriptionId)  {
        try{
            logger.info("SERVICE: In getSubscriptionExpiryDate()");
            return subscriptionDAO.getSubscriptionExpiryDate(date, subscriptionId);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getSubscriptionPLansExpiry date:::"+e);
            throw new ServiceException("exception:",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#addSubscription(java.lang
     * .String, org.springframework.web.multipart.MultipartFile)
     */
    public void addSubscription(String path, MultipartFile subscriptionXMLFile)
             {
        try{
        logger.info("SERVICE: in addSubscription(String path,MultipartFile subscriptionXMLFile) Adding Subscription from XML File.");
        InputStream inputStream = subscriptionXMLFile.getInputStream();
        File xmlFileDirectory = new File(path + "resources\\data\\");
        if (!xmlFileDirectory.exists()) {
            xmlFileDirectory.mkdir();
        }
        File file = new File(path + "\\resources\\data\\"
                + subscriptionXMLFile.getOriginalFilename());
        OutputStream outputStream = new FileOutputStream(file);
        byte buffer[] = new byte[MAXFILESIZE];
        int len;
        while ((len = inputStream.read(buffer, 0, MAXFILESIZE)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        logger.info("SERVICE: " + file.getAbsolutePath()
                + " file Copied Succesfully");
        List<Subscription> subscriptionList = addSubscriptionDetailsFromXMLFile(file);
        for (Subscription obj : subscriptionList) {
            addSubscriptionPlans(obj);
        }

        outputStream.close();
        inputStream.close();
        file.delete();
        logger.info("SERVICE: file deleted Successfully from server");
        }
        catch(IOException e)
        {
            logger.error("IOException occur in addSubscription using XML file::::"+e);
            throw new ServiceException("IO error occured in addSubscription()",e);
        }
        
        catch(Exception e)
        {
            logger.error("Exception occured in addSubscription::::::"+e);
            throw new ServiceException("exception occured",e);
        }
    }

    /**
     * Adds the subscription details from xml file.
     * 
     * @param file
     *            the file
     * @return the list
     * @throws ParserConfigurationException
     *             the parser configuration exception
     * @throws SAXException
     *             the SAX exception
     * @throws IOException
     *             Signals that an I/O exception has occurred.
     */
    public List<Subscription> addSubscriptionDetailsFromXMLFile(File file)
             {
        try{
        logger.info("SERVICE: loading XML file data into Subscription Object via DOM Parser");
        List<Subscription> subscriptionList = new ArrayList<Subscription>();
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(file);
        doc.getDocumentElement().normalize();

        NodeList nList = doc.getElementsByTagName("subscription");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Subscription subscription = new Subscription();
                Element eElement = (Element) nNode;

                subscription.setMaxBook(Integer.parseInt(eElement
                        .getElementsByTagName("maxBook").item(0)
                        .getTextContent()));
                subscription.setPeriodOfSubscription(Integer.parseInt(eElement
                        .getElementsByTagName("subscriptionPeriod").item(0)
                        .getTextContent()));
                subscription.setSubscriptionAmount(Integer.parseInt(eElement
                        .getElementsByTagName("subscriptionAmount").item(0)
                        .getTextContent()));
                subscription.setSubscriptionDetails(eElement
                        .getElementsByTagName("subscriptionDetails").item(0)
                        .getTextContent());
                subscription.setSubscriptionName(eElement
                        .getElementsByTagName("subscriptionName").item(0)
                        .getTextContent());
                subscription.setSubscriptionStatus(eElement
                        .getElementsByTagName("subscriptionStatus").item(0)
                        .getTextContent());

                subscriptionList.add(subscription);
            }
        }
        return subscriptionList;
        }
        catch(IOException e)
        {
            logger.error("IOException occur in addSubscriptionfromXMLfile using XML file::::"+e);
            throw new ServiceException("IO error occured in add subscription()",e);
        }
        catch(ParserConfigurationException e)
        {
            logger.error("parserConfigurationException occur in addSubscription using XML file:::"+e);
            throw new ServiceException("parser configuration error occured",e);
        }
        catch(SAXException e)
        {
            logger.error("SAXException occur in addSubscription using XML file:::"+e);
            throw new ServiceException("SAX error occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#updateSubscription(java.
     * lang.String, org.springframework.web.multipart.MultipartFile)
     */
    public int updateSubscription(String path, MultipartFile subscriptionXMLFile)
             {
        try{
        logger.info("SERVICE: request for updating Subscription Plans.");
        InputStream inputStream = subscriptionXMLFile.getInputStream();
        File xmlFileDirectory = new File(path + "resources\\data\\");
        if (!xmlFileDirectory.exists()) {
            xmlFileDirectory.mkdir();
        }
        File file = new File(path + "\\resources\\data\\"
                + subscriptionXMLFile.getOriginalFilename());
        OutputStream outputStream = new FileOutputStream(file);
        byte buffer[] = new byte[MAXFILESIZE];
        int len;
        while ((len = inputStream.read(buffer, 0, MAXFILESIZE)) != -1) {
            outputStream.write(buffer, 0, len);
        }
        boolean updateStatus = false;
        @SuppressWarnings("unused")
        int statusTRUE = 0, statusFALSE = 0;
        List<Subscription> subscriptionList = addSubscriptionDetailsFromXMLFile(file);
        for (Subscription obj : subscriptionList) {
            updateStatus = updateSubscriptionPlans(obj);
            if (updateStatus) {
                statusTRUE++;
            } else {
                statusFALSE++;
            }
        }
        logger.info("SERVICE: subscription Plans Updated Successfully");
        outputStream.close();
        inputStream.close();
        file.delete();
        logger.info("SERVICE: file deleted Successfully");
        return statusTRUE;
        }
        catch(IOException e)
        {
            logger.error("IOException occur in updateSubscription using XML file::::"+e);
            throw new ServiceException("IO error occured in update subscription",e);
        }   
        catch(Exception e)
        {
            logger.error("Exception occur in updateSubscription using XML file::::"+e);
            throw new ServiceException("error occured in update subscription",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#getSubscriptionPlan(java
     * .lang.Integer)
     */
    public Subscription getSubscriptionPlan(Integer subscriptionId)  {
        try{
            logger.info("SERVICE: in Subscription getSubscriptionPlan(Integer subscriptionId)");
            return subscriptionDAO.getSubscription(subscriptionId);
        }
        catch(Exception e)
        {
            logger.error("exceprtion occured in getSubscription PLans(susbcriptionId)::::"+e);
            throw new ServiceException("exception occured",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#deleteSubscription(java.
     * lang.Integer)
     */
    public void deleteSubscription(Integer subscriptionId)  {
        try{
            logger.info("SERVICE: in void deleteSubscription(Integer subscriptionId)");
            subscriptionDAO.deleteSubscriptionPlan(subscriptionId);
        }
        catch(Exception e)
        {
            logger.error("exception occured in deleteSubscription method:::"+e);
            throw new ServiceException("exceptiono hitt",e);
        }

    }

    /**
     * Update subscription plans.
     * 
     * @param obj
     *            the obj
     * @return true, if successful
     * @ 
     */
    public boolean updateSubscriptionPlans(Subscription obj)  {
        try{
            logger.info("SERVICE: boolean updateSubscriptionPlans(Subscription obj)");
            return subscriptionDAO.updateSubscription(obj);
        }
        catch(Exception e)
        {
            logger.error("exception occured during update subscription::::"+e);
            throw new ServiceException("Exception hit",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#saveUserSubscriptionHistory
     * (com.impetus.domain.SubscriptionHistory)
     */
    public SubscriptionHistory saveUserSubscriptionHistory(
            SubscriptionHistory subscriptionHistory)  {
        try{
            logger.info("SERVICE: IN void saveUserSubscriptionHistory(SubscriptionHistory subscriptionHistory)");
            return subscriptionDAO.saveUserSubscriptionHistory(subscriptionHistory);
        }
        catch(Exception e)
        {
            logger.error("Error occured in saveUserSubscriptionHistroy()::::"+e);
            throw new ServiceException("excption occured",e);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.SubscriptionService#getUserSubscriptionHistory
     * (com.impetus.domain.Users)
     */
    public List<SubscriptionHistory> getUserSubscriptionHistory(Users user)  {
        try{
            logger.info("SERVICE: IN List<SubscriptionHistory> getUserSubscriptionHistory(Users user) ");
            return subscriptionDAO.getUserSubscriptionHistory(user);
        }
        catch(Exception e)
        {
            logger.error("exception occured in getUserSubscriptionHistory()::::"+e);
            throw new ServiceException("occured exception",e);
        }
    }

}
