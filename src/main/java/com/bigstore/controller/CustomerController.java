package com.bigstore.controller;

import com.bigstore.datatransferobject.CustomerDTO;
import com.bigstore.domainobject.CustomerDO;
import com.bigstore.exception.ConstraintsViolationException;
import com.bigstore.exception.EntityNotFoundException;
import com.bigstore.mapper.CustomerMapper;
import com.bigstore.service.CustomerService;
import com.bigstore.service.SequenceGeneratorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author anil
 */
@RestController
@RequestMapping("v1/customer")
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    /**
     * customerService : to perform the customer baseness operations
     */

    private final CustomerService customerService;
    /**
     * customerMapper : to convert DTO to DO and DTO to DTO
     */


    private final SequenceGeneratorService generateSequence;

    @Autowired
    public CustomerController(final CustomerService customerService, final SequenceGeneratorService generateSequence) {
        this.customerService = customerService;
        this.generateSequence = generateSequence;
    }

    /**
     * @param customerId
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping("/{customerId}")
    public CustomerDTO find(@PathVariable long customerId) throws EntityNotFoundException {
        log.info("CustomerController : findCustomer");
        CustomerDO customerDO = customerService.find(customerId);
        customerDO.setId(customerId);
        return CustomerMapper.makeCustomerDTO(customerDO);
    }

    /**
     * @param customerDTO
     * @return
     * @throws ConstraintsViolationException
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO create(@RequestBody CustomerDTO customerDTO) throws ConstraintsViolationException {
        log.info("CustomerController : createCustomer");
        customerDTO.setId(generateSequence.generateSequence(CustomerDO.SEQUENCE_NAME));
        CustomerDO customerDO = customerService.create(CustomerMapper.makeCustomerDO(customerDTO));
        return CustomerMapper.makeCustomerDTO(customerDO);
    }

    /**
     * @param customerId
     * @throws EntityNotFoundException
     */
    @DeleteMapping("/{customerId}")
    public void delete(@PathVariable long customerId) throws EntityNotFoundException {
        log.info("CustomerController : delete");
        customerService.delete(customerId);
    }

    /**
     * @param customerDTO
     * @return
     * @throws EntityNotFoundException
     */
    @PutMapping("/{email}")
    public CustomerDTO update(@PathVariable String email, @RequestBody CustomerDTO customerDTO) throws EntityNotFoundException {
        log.info("CustomerController : update");
        CustomerDO customer = null;
        CustomerDO customerDO = customerService.findByEmail(email);
        if (customerDO != null) {
            customerDTO.setId(customerDO.getId());
            customer = customerService.update(CustomerMapper.makeCustomerDO(customerDTO));
        }
        return CustomerMapper.makeCustomerDTO(customer);
    }

    /**
     * @param email
     * @return
     * @throws EntityNotFoundException
     */
    @GetMapping(value = "/email")
    public CustomerDTO findByEmail(@RequestParam String email) throws EntityNotFoundException {
        log.info("CustomerController : findByEmail");
        return CustomerMapper.makeCustomerDTO(customerService.findByEmail(email));
    }

    /**
     * findAll
     * * @return
     */
    @GetMapping
    public List<CustomerDTO> findAll() {
        log.info("CustomerController : findAll");
        List<CustomerDO> customers = customerService.getAllCustomers();
        return customers.stream().map(CustomerMapper::makeCustomerDTO).collect(Collectors.toList());
    }


}
