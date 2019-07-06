package com.belber.springbootdemo;

import com.belber.springbootdemo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MongoTemplateTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test(){
        User user = new User();
        user.setEmail("belber@qq.com");
        mongoTemplate.save(user);
    }

    @Test
    public void findUserByUserNameTest(){
        Query query = new Query(Criteria.where("email").is("belber@qq.com"));
        User user = mongoTemplate.findOne(query, User.class);
        System.out.println(user.getEmail());
    }
}
