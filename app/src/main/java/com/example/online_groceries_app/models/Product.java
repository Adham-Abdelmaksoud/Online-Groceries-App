package com.example.online_groceries_app.models;

import java.io.Serializable;

public class Product implements Serializable {
  private String image;

  private Double price;

  private Rating rating;

  private String description;

  private Integer id;

  private String title;

  private String category;

  public String getImage() {
    return this.image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public Double getPrice() {
    return this.price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Rating getRating() {
    return this.rating;
  }

  public void setRating(Rating rating) {
    this.rating = rating;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Integer getId() {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCategory() {
    return this.category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public static class Rating implements Serializable {
    private Float rate;

    private Integer count;

    public Float getRate() {
      return this.rate;
    }

    public void setRate(Float rate) {
      this.rate = rate;
    }

    public Integer getCount() {
      return this.count;
    }

    public void setCount(Integer count) {
      this.count = count;
    }
  }
}
