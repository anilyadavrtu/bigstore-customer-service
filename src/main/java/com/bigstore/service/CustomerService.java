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
     * @return CustomerDO
     * @throws EntityNotFoundException
     */
    CustomerDO find(long customerId) throws EntityNotFoundException;

    /**
     * @param customerDO
     * @return CustomerDO
     * @throws com.bigstore.exception.ConstraintsViolationException
     */
    CustomerDO create(CustomerDO customerDO) throws ConstraintsViolationException;

    /**
     * @param customerId
     * @throws EntityNotFoundException
     */
    void delete(Long customerId) throws EntityNotFoundException;

    /**
     * @param customerDO
     * @throws EntityNotFoundException
     * @return CustomerDO
     */
    CustomerDO update(CustomerDO customerDO) throws EntityNotFoundException;

    /**
     * @param email
     * @return CustomerDO
     * @throws EntityNotFoundException
     */
    CustomerDO findByEmail(String email) throws EntityNotFoundException;

    /**
     * @return List<CustomerDO>
     */
    List<CustomerDO> getAllCustomers();

}
