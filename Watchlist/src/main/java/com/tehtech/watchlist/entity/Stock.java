package com.tehtech.watchlist.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
  private String symbolPK;  
  private Indexes indexId;
  private String name;
  private String cusip;
  private double lastPrice;  

}
