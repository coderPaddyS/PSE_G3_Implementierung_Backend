package de.itermori.pse.kitroomfinder.backend.services;

public interface MapIDService {

    public String getMapObject(int mapID);
    public int getMapIdByName(int mapObject);
    public Iterable<int> getAllMapIDs();

}
