package com.bigstore.domainobject;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author anil
 */
@Data
@Document(collection = "Customer")
public class CustomerDO {
    @Transient
    public static final String SEQUENCE_NAME = "customer_sequence";

    @Id
    private Long id;
    /**
     * Customer firstName
     */
    private String firstName;
    /**
     * Customer lastName
     */
    private String lastName;
    /**
     * Customer email
     */
    private String email;
    /**
     * Customer phoneNumber
     */
    private String phoneNumber;

    private CustomerDO() {
    }

    public CustomerDO(Long id,String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id=id;
    }


}
