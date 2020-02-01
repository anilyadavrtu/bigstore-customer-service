package com.bigstore.dataaccessobject;

import com.bigstore.controller.CustomerControllerTest;
import com.bigstore.domainobject.CustomerDO;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author anil
 */
@RunWith(SpringRunner.class)
@DataMongoTest
public class CustomerRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerControllerTest.class);

    @Autowired
    private CustomerRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void findByEmail() {
        LOGGER.info("CustomerRepositoryTest: GET :findByEmail");
        CustomerDO cusDO = new CustomerDO(1L, "Anil", "Yadav", "anil.yadav@gmail.com", "9090909090");
        BasicDBObjectBuilder dbObjectBuilder = BasicDBObjectBuilder.start().add("cusData", cusDO);
        mongoTemplate.save(dbObjectBuilder, "cusCollection");
        Assertions.assertThat(mongoTemplate.findAll(DBObject.class, "cusCollection")).extracting("cusData").containsOnly(cusDO);

    }
}