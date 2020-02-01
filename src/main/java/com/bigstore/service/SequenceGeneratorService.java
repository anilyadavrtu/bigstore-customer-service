package com.bigstore.service;

import com.bigstore.util.DatabaseSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * SequenceGeneratorService
 *
 * @author anil
 */
@Service
public class SequenceGeneratorService {

    /**
     * mongoOperations
     */
    private MongoOperations mongoOperations;

    /**
     * @param mongoOperations
     */
    @Autowired
    public SequenceGeneratorService(final MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    /**
     * @param seqName
     * @return long
     */
    public long generateSequence(final String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(Query.query(Criteria.where("_id").is(seqName)),
                new Update().inc("sequence", 1), FindAndModifyOptions.options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSequence() : 1;

    }
}
