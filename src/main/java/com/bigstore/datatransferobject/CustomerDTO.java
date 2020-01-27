package com.bigstore.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author anil
 */
@Data
public class CustomerDTO {
    @JsonIgnore
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id,String firstName, String lastName, String email, String phoneNumber) {
        this.id=id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
