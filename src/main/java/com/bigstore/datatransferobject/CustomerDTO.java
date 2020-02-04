package com.bigstore.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * CustomerDTO
 *
 * @author anil
 */
@Data
public class CustomerDTO {
    /**
     * ID
     */
    @JsonIgnore
    private Long id;
    /**
     * firstName
     */
    private String firstName;
    /**
     * lastName
     */
    private String lastName;
    /**
     * email
     */
    private String email;
    /**
     * phoneNumber
     */
    private Integer phoneNumber;

    /**
     * @param id id
     * @param firstName firstName
     * @param lastName lastName
     * @param email email
     * @param phoneNumber phoneNumber
     */
    public CustomerDTO(final Long id, final String firstName, final String lastName,
                       final String email, final Integer phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
