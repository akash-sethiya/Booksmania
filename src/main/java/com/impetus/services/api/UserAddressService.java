package com.impetus.services.api;

import org.springframework.transaction.annotation.Transactional;

import com.impetus.domain.Address;

// TODO: Auto-generated Javadoc
/**
 * The Interface UserAddressService.
 */
@Transactional(readOnly = false)
public interface UserAddressService {

    /**
     * Adds the address.
     * 
     * @param userAddresses
     *            the user addresses
     * @return
     * @ 
     */
    Address addAddress(Address userAddresses) ;

    /**
     * Update address.
     * 
     * @param userAddresses
     *            the user addresses
     * @return
     * @ 
     */
    Address updateAddress(Address userAddresses) ;

    /**
     * Delete address.
     * 
     * @param userAddresses
     *            the user addresses
     * @ 
     */
    void deleteAddress(Address userAddresses) ;

    /**
     * Gets the address.
     * 
     * @return the address
     * @ 
     */
    Address getAddress() ;

}
