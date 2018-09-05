package app.service.api;

import app.dto.SprinkleDTO;

import java.util.List;

public interface SprinkleService {

    void create(SprinkleDTO sprinkleDTO);

    void update(SprinkleDTO sprinkleDTO);

    void delete(SprinkleDTO sprinkleDTO);

    SprinkleDTO getById(Integer id);

    SprinkleDTO getByName(String name);

    List<SprinkleDTO> getAll();
}
