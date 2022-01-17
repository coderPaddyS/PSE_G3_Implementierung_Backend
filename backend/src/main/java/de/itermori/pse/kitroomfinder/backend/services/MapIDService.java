package de.itermori.pse.kitroomfinder.backend.services;

public interface MapIDService {

    public String getMapObject(int mapID);
    public int getMapIDByName(String mapObject);
    public Iterable<Integer> getAllMapIDs();

}
