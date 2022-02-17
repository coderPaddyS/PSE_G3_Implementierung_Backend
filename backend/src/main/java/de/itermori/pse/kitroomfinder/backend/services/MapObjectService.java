package de.itermori.pse.kitroomfinder.backend.services;

public interface MapObjectService {

    public String getMapObjectName(int mapID);
    public Integer getMapIDByName(String mapObject);
    public Iterable<Integer> getAllMapIDs();
    public Iterable<String> getAllMapObjectsName();

}
