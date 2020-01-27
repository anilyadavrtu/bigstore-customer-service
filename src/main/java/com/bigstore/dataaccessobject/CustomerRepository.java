package com.bigstore.dataaccessobject;

import com.bigstore.domainobject.CustomerDO;
import org.springframework.data.mongodb.repository.MongoRepository;


/**
 * Database Access Object for Customer.
 * @author anil
 */
public interface CustomerRepository extends MongoRepository<CustomerDO,Long> {

    /**method to get the Customer By ID
     * @param email
     * @return
     */
   CustomerDO findByEmail(String email);

}
