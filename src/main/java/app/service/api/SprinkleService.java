package app.service.api;

import app.dto.SprinkleDTO;

import java.util.List;

public interface SprinkleService {

    SprinkleDTO create(SprinkleDTO sprinkleDTO);

    void update(SprinkleDTO sprinkleDTO);

    void delete(SprinkleDTO sprinkleDTO);

    SprinkleDTO getById(Integer id);

    SprinkleDTO getByName(String name);

    List<SprinkleDTO> getAll();

    SprinkleDTO getLastSprinkle();
}
