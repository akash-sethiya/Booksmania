package com.impetus.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.impetus.commons.exception.DAOException;
import com.impetus.dao.api.UserAddressDAO;
import com.impetus.domain.Address;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAddressDAOImpl.
 */
@Repository("userAddressDAO")
public class UserAddressDAOImpl implements UserAddressDAO, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The hibernate template. */
    @Autowired
    private HibernateTemplate hibernateTemplate = null;
    private Logger logger = LoggerFactory.getLogger(UserAddressDAOImpl.class);

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserAddressDAO#addAddress(com.impetus.domain.Address)
     */
    public Address addAddress(Address userAddresses) throws DAOException {
        try{
            logger.info("in addAddress(Address) method");
            hibernateTemplate.save(userAddresses);
            return userAddresses;
        }
        catch(Exception e)
        {
            logger.error("exception hit in addAddress(Address) method::::"+e);
            throw new DAOException("exception occured", e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserAddressDAO#updateAddress(com.impetus.domain.Address
     * )
     */
    public Address updateAddress(Address userAddresses) throws DAOException {
        try{
            logger.error("updateAddress(address)");
            hibernateTemplate.update(userAddresses);
            return userAddresses;
        }
        catch(Exception e){
            logger.info("exception hit in updateAddress method::::"+e);
            throw new DAOException("exception hit in updatinga address", e);
        }
        
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.dao.api.UserAddressDAO#deleteAddress(com.impetus.domain.Address
     * )
     */
    public void deleteAddress(Address userAddresses) {

    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.dao.api.UserAddressDAO#getAddress()
     */
    @SuppressWarnings("unchecked")
    public Address getAddress() throws DAOException {
        try{
            logger.info("gerAddress()");
            DetachedCriteria dCriteria = DetachedCriteria.forClass(Address.class);
            dCriteria.addOrder(Order.desc("addresId"));
            List<Address> list = (List<Address>) hibernateTemplate.findByCriteria(dCriteria);
            return list.get(0);
        }
        catch(Exception e)
        {
            logger.error("error hit in getAddress()::::"+e);
            throw new DAOException("exception hit in getAddresss", e);
        }
    }
}
