package com.kwetter.serviceusers.repository;

import com.kwetter.serviceusers.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDataConnection {
    private static List<User> users = new ArrayList<User>(){{
        add(new User(0, "Person 1", "This is a very nice description of Person 1"));
        add(new User(1, "Person 2", "This is a very nice description of Person 2"));
        add(new User(3, "Person 3", "This is a very nice description of Person 3"));
    }};

    // This will later be handled with a coupling table in the database
    private static Map<Integer, int[]> friendTable = new HashMap<>() {{
        put(0, new int[]{1});
        put(1, new int[]{0, 2});
        put(2, new int[]{0});
    }};


}
