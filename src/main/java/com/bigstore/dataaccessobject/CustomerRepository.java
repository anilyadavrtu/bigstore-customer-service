package com.bigstore.dataaccessobject;

import com.bigstore.domainobject.CustomerDO;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Database Access Object for Customer.
 *
 * @author anil
 */
public interface CustomerRepository extends MongoRepository<CustomerDO, Long> {

    /**
     * this
     * method to get the Customer By ID
     *
     * @param email email
     * @return CustomerDO
     */
    CustomerDO findByEmail(String email);

}
