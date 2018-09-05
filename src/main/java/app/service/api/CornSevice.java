package app.service.api;

import app.dto.CornDTO;

import java.util.List;

public interface CornSevice {

    void create(CornDTO cornDTO);

    void update(CornDTO cornDTO);

    void delete(CornDTO cornDTO);

    CornDTO getById(Integer id);

    CornDTO getByName(String name);

    List<CornDTO> getAll();
}
