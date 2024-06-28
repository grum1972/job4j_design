package ru.job4j.map;

import java.util.HashMap;

public class UserMain {
    public static void main(String[] args) {
        User user1 = new User("John", 15);
        int hashCode1 = user1.hashCode();
        int hash1 = hashCode1 ^ (hashCode1 >>> 16);
        int bucket1 = hash1 & 15;
        User user2 = new User("John", 15);
        int hashCode2 = user2.hashCode();
        int hash2 = hashCode2 ^ (hashCode2 >>> 16);
        int bucket2 = hash2 & 15;
        HashMap<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(hashCode1 + " " + hash1 + " " + bucket1);
        System.out.println(hashCode2 + " " + hash2 + " " + bucket2);
        System.out.println(map);

    }
}
