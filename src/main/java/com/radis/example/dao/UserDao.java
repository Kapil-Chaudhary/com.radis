package com.radis.example.dao;

import com.radis.example.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class UserDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String KEY = "USER";

    public User save(User user){
        this.redisTemplate.opsForHash().put(KEY, user.getUserId(), user);
        return user;
    }


    public User get(String userId){
        Object o = this.redisTemplate.opsForHash().get(KEY, userId);
        return (User) o;
    }

    // find all
    public Map<Object, Object> findAll(){
        return redisTemplate.opsForHash().entries(KEY);
    }


    // delete
    public void delete(String userId){
        this.redisTemplate.opsForHash().delete(KEY, userId);
    }

}
