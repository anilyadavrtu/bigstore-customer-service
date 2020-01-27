package com.bigstore.service;


import com.bigstore.domainobject.CustomerDO;
import com.bigstore.exception.ConstraintsViolationException;
import com.bigstore.exception.EntityNotFoundException;

import java.util.List;

/**
 * @author anil
 */
public interface CustomerService {
    /**
     * @param customerId
     * @return
     * @throws EntityNotFoundException
     */
    CustomerDO find(long customerId) throws EntityNotFoundException;

    /**
     * @param customerDO
     * @return
     * @throws com.bigstore.exception.ConstraintsViolationException
     */
    CustomerDO create(CustomerDO customerDO) throws ConstraintsViolationException;

    /**
     * @param CustomerId
     * @throws EntityNotFoundException
     */
    void delete(Long CustomerId) throws EntityNotFoundException;

    /**
     * @param customerDO
     * @throws EntityNotFoundException
     */
    CustomerDO update(CustomerDO customerDO) throws EntityNotFoundException;

    /**
     * @param email
     * @return
     * @throws EntityNotFoundException
     */
    CustomerDO findByEmail(String email) throws EntityNotFoundException;

    /**
     * @return
     */
    List<CustomerDO> getAllCustomers();

}
