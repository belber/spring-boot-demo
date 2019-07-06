package com.belber.springbootdemo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

}