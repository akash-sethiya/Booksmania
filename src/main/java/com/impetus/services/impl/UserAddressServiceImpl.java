package com.impetus.services.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.impetus.commons.exception.ServiceException;
import com.impetus.dao.api.UserAddressDAO;
import com.impetus.domain.Address;
import com.impetus.services.api.UserAddressService;

// TODO: Auto-generated Javadoc
/**
 * The Class UserAddressServiceImpl.
 */
@Service("userAddressService")
public class UserAddressServiceImpl implements UserAddressService, Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The address dao. */
    @Autowired
    private UserAddressDAO addressDAO;

    public void setAddressDAO(UserAddressDAO addressDAO) {
            this.addressDAO = addressDAO;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserAddressService#addAddress(com.impetus.domain
     * .Address)
     */
    public Address addAddress(Address userAddresses)  {
        try{
            return addressDAO.addAddress(userAddresses);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception in addAddress()::::",e);
            
        }
            

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserAddressService#updateAddress(com.impetus
     * .domain.Address)
     */
    @Transactional
    public Address updateAddress(Address userAddresses)  {
        try{
            return addressDAO.updateAddress(userAddresses);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception in updateAddress()::::",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.impetus.services.api.UserAddressService#deleteAddress(com.impetus
     * .domain.Address)
     */
    public void deleteAddress(Address userAddresses)  {
        try{
            addressDAO.deleteAddress(userAddresses);
        }
        catch(Exception e)
        {
            throw new ServiceException("exception in deleteAddress",e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.impetus.services.api.UserAddressService#getAddress()
     */
    public Address getAddress()  {
        try{
            return addressDAO.getAddress();
        }
        catch(Exception e)
        {
            throw new ServiceException("exception in getAddress::::",e);
        }
    }

}
