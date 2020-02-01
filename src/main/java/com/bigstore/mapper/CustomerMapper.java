package com.bigstore.mapper;

import com.bigstore.datatransferobject.CustomerDTO;
import com.bigstore.domainobject.CustomerDO;

/**
 * @author anil
 */
public class CustomerMapper {

    /**
     * @param customerDO
     * @return CustomerDTO
     */
    public static CustomerDTO makeCustomerDTO(final CustomerDO customerDO) {
        if (customerDO != null) {
            return new CustomerDTO(customerDO.getId(), customerDO.getFirstName(),
                    customerDO.getLastName(), customerDO.getEmail(), customerDO.getPhoneNumber());
        }
        return null;
    }

    /**
     * @param customerDTO
     * @return CustomerDO
     */
    public static CustomerDO makeCustomerDO(final CustomerDTO customerDTO) {

        return new CustomerDO(customerDTO.getId(), customerDTO.getFirstName(),
                customerDTO.getLastName(), customerDTO.getEmail(), customerDTO.getPhoneNumber());
    }
}
