package com.bigstore.service;

import com.bigstore.dataaccessobject.CustomerRepository;
import com.bigstore.domainobject.CustomerDO;
import com.bigstore.exception.ConstraintsViolationException;
import com.bigstore.exception.EntityNotFoundException;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
    @Cacheable(value = "find")
    @Override
    @HystrixCommand(fallbackMethod = "findFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })

    public CustomerDO find(final long customerId) throws EntityNotFoundException {
        return repository.findById(customerId).orElseThrow(() -> new EntityNotFoundException("Could not find the entity with id " + customerId));
    }

    /**
     * Add new Customer
     *
     * @param customerDO
     * @return
     * @throws ConstraintsViolationException
     */
    @Override
    @HystrixCommand(fallbackMethod = "createFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
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
    @HystrixCommand(fallbackMethod = "deleteFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
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
    @HystrixCommand(fallbackMethod = "updateFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
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
    @Cacheable(value = "email")
    @HystrixCommand(fallbackMethod = "findByEmailFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
            })
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
  //  @Cacheable(value = "getAllCustomers")
//    @HystrixCommand(fallbackMethod = "customersfallback",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
//                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
//            })
    public List<CustomerDO> getAllCustomers() {
        return repository.findAll();
    }

    private CustomerDO findFallback(long id) {
        return new CustomerDO(0L, "FirstName", "LastName", "test@user.com", 991179888);
    }

    private CustomerDO createFallback(CustomerDO customerDO) {
        return new CustomerDO(0L, "FirstName", "LastName", "test@user.com", 991179888);
    }

    private String deleteFallback(long customerDO) {
        return "Error while deleting the record ,Please try after some time.";
    }

    private String updateFallback(CustomerDO customerDO) {
        return "Error while updating the record ,Please try after some time.";
    }

    private CustomerDO findByEmailFallback(String email) {
        return new CustomerDO(0L, "FirstName", "LastName", "test@user.com", 991179888);
    }

    private List<CustomerDO> customersfallback() {
        List<CustomerDO> customerDOS = new ArrayList<>();
        customerDOS.add(new CustomerDO(0L, "FirstName", "LastName", "test@user.com", 991179888));
        return customerDOS;
    }


}
