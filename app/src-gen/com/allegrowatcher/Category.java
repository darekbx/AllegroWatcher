package com.allegrowatcher;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table CATEGORY.
 */
public class Category {

    private Long id;
    private Integer category_id;
    private String category_name;

    public Category() {
    }

    public Category(Long id) {
        this.id = id;
    }

    public Category(Long id, Integer category_id, String category_name) {
        this.id = id;
        this.category_id = category_id;
        this.category_name = category_name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

}