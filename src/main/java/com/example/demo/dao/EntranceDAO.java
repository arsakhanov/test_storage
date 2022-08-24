package com.example.demo.dao;



import com.example.demo.model.Entrance;

import java.util.*;

public class EntranceDAO {

    private static final Map<Long, Entrance> entranceMap = new HashMap<>();

    public static List<Entrance> getEntranceDocuments(){
        Collection<Entrance> entranceCollection = entranceMap.values();
        return new ArrayList<>(entranceCollection);
    }

/*    public static Entrance addEntrance(Entrance entrance){
        entranceMap.put(entrance.getNumber(), entrance);
        StorageDAO.addProductInStorage(entrance.getStorage().getName(),entrance.getProducts());
        return entrance;
    }*/

    public static boolean updateEntrance(Entrance entrance){
        if(entranceMap.containsKey(entrance.getNumber())){
            entranceMap.put(entrance.getNumber(), entrance);
            return true;
        } else return false;
    }

    public static boolean deleteEntrance(long number){
        if(entranceMap.containsKey(number)){
            entranceMap.remove(number);
            return true;
        } else return false;
    }

}
