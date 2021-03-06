package app.service.api;

import app.dto.DoughDTO;

import java.util.List;

public interface DoughService {

    DoughDTO create(DoughDTO cornDTO);

    void update(DoughDTO cornDTO);

    void delete(DoughDTO cornDTO);

    DoughDTO getById(Integer id);

    DoughDTO getByName(String name);

    List<DoughDTO> getAll();

    void checkDoughByProducts(Integer id);
}
