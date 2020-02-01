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

    /**
     * SEQUENCE_NAME
     */
    @Transient
    public static final String SEQUENCE_NAME = "customer_sequence";

    /**
     * id
     */
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

    public CustomerDO(final Long id, final String firstName,
                      final String lastName, final String email, final String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.id = id;
    }


}
