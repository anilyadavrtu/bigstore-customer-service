package com.bigstore.integration;

import com.bigstore.domainobject.CustomerDO;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author anil
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CustomerIntegrationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerIntegrationTest.class);

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void findByEmail() {
        LOGGER.info("CustomerIntegrationTest :findByEmail");
        ResponseEntity<CustomerDO> response = testRestTemplate
                .getForEntity("/v1/customer/email/anil@gmail.com", CustomerDO.class);
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(response.getBody().getEmail()).isEqualTo("anil@gmail.com");

    }
}
