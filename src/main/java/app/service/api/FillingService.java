package app.service.api;

import app.dto.FillingDTO;

import java.util.List;

public interface FillingService {

    void create(FillingDTO fillingDTO);

    void update(FillingDTO fillingDTO);

    void delete(FillingDTO fillingDTO);

    FillingDTO getById(Integer id);

    FillingDTO getByName(String name);

    List<FillingDTO> getAll();

    FillingDTO getLastItem();
}
