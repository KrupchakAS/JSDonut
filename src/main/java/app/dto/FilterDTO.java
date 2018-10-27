package app.dto;

import java.util.List;

public class FilterDTO {
    private Integer categoryId;
    private Integer fillingId;
    private Integer doughId;
    private List<Integer> sprinkleIdList;
    private String productName;
    private Integer minPrice;
    private Integer maxPrice;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getFillingId() {
        return fillingId;
    }

    public void setFillingId(Integer fillingId) {
        this.fillingId = fillingId;
    }

    public Integer getDoughId() {
        return doughId;
    }

    public void setDoughId(Integer doughId) {
        this.doughId = doughId;
    }

    public List<Integer> getSprinkleIdList() {
        return sprinkleIdList;
    }

    public void setSprinkleIdList(List<Integer> sprinkleIdList) {
        this.sprinkleIdList = sprinkleIdList;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }
}
