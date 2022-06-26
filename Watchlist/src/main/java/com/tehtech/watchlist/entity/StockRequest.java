package com.tehtech.watchlist.entity;

import lombok.Data;

@Data
public class StockRequest {  
  private Indexes index;
  private String symbol;
}
