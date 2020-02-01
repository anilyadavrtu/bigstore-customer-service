package com.bigstore.service;

import java.util.List;
import com.bigstore.dataaccessobject.CustomerRepository;
import com.bigstore.domainobject.CustomerDO;
import com.bigstore.exception.ConstraintsViolationException;
import com.bigstore.exception.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some customer specific things.
 * <p/>
 *
 * @author anil
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    /**
     * logger
     */
   private final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    /**
     * repository
     */
    private CustomerRepository repository;

    /**
     * @param customerRepository
     */
    @Autowired
    public CustomerServiceImpl(final CustomerRepository customerRepository) {
        this.repository = customerRepository;
    }

    /**
     * Get Customer with Id
     *
     * @param customerId
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public CustomerDO find(final long customerId) throws EntityNotFoundException {
        return repository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Could not find the entity with id " + customerId));
    }

    /**Add new Customer
     * @param customerDO
     * @return
     * @throws ConstraintsViolationException
     */
    @Override
    public CustomerDO create(final CustomerDO customerDO) throws ConstraintsViolationException {
        CustomerDO customer = null;
        try {
            customer = repository.save(customerDO);
        } catch (Exception e) {
            logger.info("Exception occur while saving record : " + e.getMessage());
        }
        return customer;
    }

    /**
     * @param customerId
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    public void delete(final Long customerId) throws EntityNotFoundException {
        logger.info("CustomerServiceImpl: delete");
        repository.deleteById(customerId);
    }

    /**
     * @param customerDO
     * @return
     */
    @Override
    @Transactional
    public CustomerDO update(final CustomerDO customerDO) {
        logger.info("CustomerServiceImpl: update");
        repository.save(customerDO);
        return customerDO;
    }

    /**
     * findCustomerByEmail
     *
     * @param email
     * @return
     * @throws EntityNotFoundException
     */
    @Override
    public CustomerDO findByEmail(final String email) throws EntityNotFoundException {
        logger.info("CustomerServiceImpl: findCustomerByEmail");
        return repository.findByEmail(email);
    }

    /**
     * getAllCustomers
     *
     * @return List<CustomerDO>
     */
    @Override
    public List<CustomerDO> getAllCustomers() {
        return repository.findAll();
    }
}
