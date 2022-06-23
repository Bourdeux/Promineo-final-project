package com.tehtech.watchlist.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {
  private String symbol;
  private String name;
  private String cusip;
  private float lastPrice;
}
