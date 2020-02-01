package com.bigstore.util;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * to create DatabaseSequence .
 *
 * @author anil
 */
@Document
public class DatabaseSequence {
    /**
     * id
     */
    @Id
    private String id;
    /**
     * sequence
     */
    private long sequence;

    /**
     * @return String
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(final String id) {
        this.id = id;
    }

    /**
     * @return long
     */
    public long getSequence() {
        return sequence;
    }

    /**
     * @param sequence
     */
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }
}
