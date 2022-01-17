package de.itermori.pse.kitroomfinder.backend.services;

public class MapIDServiceImp implements MapIDService{

    private MapIDRepository mapIDRepository;

    public MapIDServiceImp(MapIDRepository mapIDRepository) {
        this.mapIDRepository = mapIDRepository;
    }


    @Override
    public String getMapObject(int mapID) {
        return mapIDRepository.findByID(mapID);
    }

    @Override
    public int getMapIDByName(String mapObject) {
        return mapIDRepository.findByName(mapObject);
    }

    @Override
    public Iterable<Integer> getAllMapIDs() {
        return mapIDRepository.findAll();
    }
}
