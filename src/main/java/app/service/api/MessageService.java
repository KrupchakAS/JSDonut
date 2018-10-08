package app.service.api;

import app.dto.ProductDTO;
import app.entity.Product;

import java.util.List;
import java.util.Map;

public interface MessageService {
    void sendCreateMessage(Map<Integer, List<Integer>> productMap);

    void sendUpdateMessage(ProductDTO productDTO);
}
