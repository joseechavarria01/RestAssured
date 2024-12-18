package org.example.dto.respose;

import org.example.utils.Pair;

import java.util.Map;

public class Category {
    private Map<String, String> usertype;
    //private Pair<String, String> usertype;
    private String category;

    public Map<String, String> getUsertype() {
        return usertype;
    }

    public void setUsertype(Map<String, String> usertype) {
        this.usertype = usertype;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return String.format("{\"usertype\":{\"usertype\":\"%s\"},\"category\":\"%s\"}",usertype.get("usertype"), category);
    }
}
