package com.impetus.dao.api;

import com.impetus.commons.exception.DAOException;
import com.impetus.domain.Address;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserAddressDAO.
 */
public interface UserAddressDAO {

    /**
     * Adds the address.
     * 
     * @param userAddresses
     *            the user addresses
     * @return
     * @throws DAOException 
     */
    Address addAddress(Address userAddresses) throws DAOException;

    /**
     * Update address.
     * 
     * @param userAddresses
     *            the user addresses
     * @return
     * @throws DAOException 
     */
    Address updateAddress(Address userAddresses) throws DAOException;

    /**
     * Delete address.
     * 
     * @param userAddresses
     *            the user addresses
     */
    void deleteAddress(Address userAddresses);

    /**
     * Gets the address.
     * 
     * @return the address
     * @throws DAOException 
     */
    Address getAddress() throws DAOException;

}
