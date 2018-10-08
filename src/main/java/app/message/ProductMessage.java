package app.message;

import app.dto.ProductDTO;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductMessage implements Serializable{
    private Map<Integer,List<Integer>> productMap;
    private MessageStatus status;

    public ProductMessage() {
    }

    public ProductMessage(Map<Integer, List<Integer>> donutMap, MessageStatus status) {
        this.productMap = donutMap;
        this.status = status;
    }

    public ProductMessage(ProductDTO productDTO,MessageStatus status) {
        productMap = new HashMap<>();
        productMap.put(productDTO.getId(), Arrays.asList(productDTO.getId()));
        this.status = status;
    }

    public Map<Integer, List<Integer>> getProductMap() {
        return productMap;
    }

    public void setProductMap(Map<Integer, List<Integer>> productMap) {
        this.productMap = productMap;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
