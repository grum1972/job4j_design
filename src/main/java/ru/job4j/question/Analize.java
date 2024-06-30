package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int countDeleted = 0;
        int countChanged = 0;
        int countAdded = 0;
        if (!current.equals(previous)) {
            Map<Integer, String> mapPrevious = new HashMap<>();
            for (User user : previous) {
                mapPrevious.put(user.getId(), user.getName());
            }
            Map<Integer, String> mapCurrent = new HashMap<>();
            for (User user : current) {
                mapCurrent.put(user.getId(), user.getName());
            }
            for (Integer key : mapPrevious.keySet()) {
                if (mapCurrent.containsKey(key)) {
                    if (!mapCurrent.get(key).equals(mapPrevious.get(key))) {
                        countChanged++;
                    }
                } else {
                    countDeleted++;
                }
            }
            for (Integer key : mapCurrent.keySet()) {
                if (!mapPrevious.containsKey(key)) {
                    countAdded++;
                }
            }
        }
        return new Info(countAdded, countChanged, countDeleted);
    }

}