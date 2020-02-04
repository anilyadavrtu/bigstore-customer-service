package com.bigstore.controller;


import com.bigstore.datatransferobject.CustomerDTO;
import com.bigstore.domainobject.CustomerDO;
import com.bigstore.service.CustomerService;
import com.bigstore.service.SequenceGeneratorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

/**
 * @author anil
 * unit test CustomerControllerTest to test the customerController
 */
@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    private static final Logger log = LoggerFactory.getLogger(CustomerControllerTest.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    public MockMvc mockMvc;

    @MockBean
    public CustomerService customerService;

    @MockBean
    public SequenceGeneratorService sequenceGeneratorService;

    @Test
    public void find() throws Exception {
        log.info("CustomerControllerTest:find");
        CustomerDO customerDO = new CustomerDO(0L, "Anil", "Yadav", "anil.yadav@gmail.com", 999999999);
        Mockito.doReturn(customerDO).when(customerService).find(0L);
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customer/0"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Anil"))
                .andExpect(MockMvcResultMatchers.jsonPath("lastName").value("Yadav"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("anil.yadav@gmail.com"));
    }

    @Test
    public void create() throws Exception {
        log.info("CustomerControllerTest:create");
        CustomerDO customerDO = new CustomerDO(1L, "Anil", "Yadav", "anil.yadav@gmail.com", 999999999);
        Mockito.doReturn(customerDO).when(customerService).create(any(CustomerDO.class));
        mockMvc.
                perform(MockMvcRequestBuilders.post("/v1/customer")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(customerDO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Anil"))
                .andExpect(MockMvcResultMatchers.jsonPath("lastName").value("Yadav"))
                .andExpect(MockMvcResultMatchers.jsonPath("email").value("anil.yadav@gmail.com"));
    }

    @Test
    public void delete() throws Exception {
        log.info("CustomerControllerTest : delete");
        Mockito.doNothing().when(customerService).delete(0L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/customer/{customerId}", "1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void update() throws Exception {
        log.info("CustomerControllerTest : update");
        CustomerDO customerDO = new CustomerDO(0L, "Anil", "Yadav", "sach@gmail.com", 999999999);
        CustomerDTO customerDTO = new CustomerDTO(0L, "Anil", "Yadav", "sach@gmail.com", 999999999);
        Mockito.doReturn(customerDO).when(customerService).update(any(CustomerDO.class));
        mockMvc.perform(MockMvcRequestBuilders.put("/v1/customer/{email}", "sach@gmail.com")
                .content(OBJECT_MAPPER.writeValueAsString(customerDTO))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        // .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Anil"));
    }


    @Test
    public void findByEmail() throws Exception {
        log.info("CustomerControllerTest :findByEmail");
        CustomerDO customerDO = new CustomerDO(0L, "Anil", "Yadav", "sach@gmail.com", 999999999);

        Mockito.doReturn(customerDO).when(customerService).findByEmail("sach@gmail.com");
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customer/email").param("email", "sach@gmail.com"))
               // .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("firstName").value("Anil"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void findAll() throws Exception {
        log.info("CustomerControllerTest : findAll");
        CustomerDO customerDO1 = new CustomerDO(0L, "Anil", "Yadav", "sach@gmail.com", 999999991);
        CustomerDO customerDO2 = new CustomerDO(1L, "Dev", "singh", "sach1@gmail.com", 999999992);
        CustomerDO customerDO3 = new CustomerDO(2L, "Jack", "rayan", "sacc2@gmail.com", 999999993);
        List<CustomerDO> customers = new ArrayList<>();

        customers.add(customerDO1);customers.add(customerDO2);customers.add(customerDO3);
        Mockito.doReturn(customers).when(customerService).getAllCustomers();
        mockMvc.perform(MockMvcRequestBuilders.get("/v1/customer/"))
                //.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assert.assertEquals(3,customerService.getAllCustomers().size());

    }
}
