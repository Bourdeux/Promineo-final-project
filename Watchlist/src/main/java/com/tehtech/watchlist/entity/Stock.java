package com.tehtech.watchlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
  private String symbol;
  private String name;
  private String cusip;
  private float lastPrice;
  
  @JsonIgnore
  public String cusip() {
    return cusip;
  }
}
