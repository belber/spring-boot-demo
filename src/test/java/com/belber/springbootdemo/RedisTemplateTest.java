package com.belber.springbootdemo;

import com.belber.springbootdemo.domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testString()  {
        redisTemplate.opsForValue().set("neo", "ityouknow");
        Assert.assertEquals("ityouknow", redisTemplate.opsForValue().get("neo"));
    }

    @Test
    public void testObj(){
        User user=new User("ityouknow@126.com", "smile", "youknow", "know","2020");
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set("com.neo", user);
        User u = operations.get("com.neo");
        System.out.println("user: " + u.toString());
    }

    @Test
    public void testExpire() throws InterruptedException {
        User user=new User("ityouknow@126.com", "expire", "youknow", "expire","2020");
        ValueOperations<String, User> operations=redisTemplate.opsForValue();
        operations.set("expire", user,100, TimeUnit.MILLISECONDS);
        Thread.sleep(85);
        boolean exists = redisTemplate.hasKey("expire");
        if(exists){
            System.out.println("exists is true");
        }else{
            System.out.println("exists is false");
        }
    }

    @Test
    public void testHash() {
        HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
        hash.put("hash","you","you");
        String value=(String) hash.get("hash","you");
        System.out.println("hash value :"+value);
    }
}  