package com.bigstore.mapper;

import com.bigstore.datatransferobject.CustomerDTO;
import com.bigstore.domainobject.CustomerDO;
import org.springframework.stereotype.Service;

/**
 * @author anil
 */
@Service
public class CustomerMapper {
    
    public static CustomerDTO makeCustomerDTO(CustomerDO customerDO) {
        if(customerDO!=null){
           return new CustomerDTO(customerDO.getId(), customerDO.getFirstName(), customerDO.getLastName(), customerDO.getEmail(), customerDO.getPhoneNumber());
        }
        return null;
    }

    public static CustomerDO makeCustomerDO(CustomerDTO customerDTO) {

        return new CustomerDO(customerDTO.getId(),customerDTO.getFirstName(),customerDTO.getLastName(),customerDTO.getEmail(),customerDTO.getPhoneNumber());
    }
}
