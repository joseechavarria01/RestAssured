package org.example.dto.respose;

public class Product {
    private int id;
    private String name;
    private String price;
    private String brand;
    private Category category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("{\"id\":%s,\"name\":\"%s\",\"price\":\"%s\",\"brand\":\"%s\",\"category\":%s}", id, name, price, brand, category);
    }
}
