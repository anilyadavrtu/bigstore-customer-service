package com.bigstore.service;

import com.bigstore.dataaccessobject.CustomerRepository;
import com.bigstore.domainobject.CustomerDO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author anil
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceImplTest {

    private static Logger logger = LoggerFactory.getLogger(CustomerServiceImplTest.class);

    @Mock
    private CustomerRepository repository;

    @Test
    public void findByEmail() {
        logger.info("CustomerServiceImplTest :findByEmail");
        BDDMockito.doReturn(new CustomerDO(1L, "Anil", "Yadav", "anil@gmail.com", 998989898))
                .when(repository).findByEmail("anil@gmail.com");
        CustomerDO customerDO = repository.findByEmail("anil@gmail.com");
        Assertions.assertThat(customerDO.getEmail()).isEqualTo("anil@gmail.com");

    }

}