package app.service.api;

import app.dto.AddressDTO;

import java.util.List;

public interface AddressService {
    void create(AddressDTO addressDTO);

    void update(AddressDTO addressDTO);

    void delete(AddressDTO addressDTO);

    AddressDTO getById(Integer id);


    List<AddressDTO> getAll();

    List<AddressDTO> getAddressesByUserId (Integer id);

}
