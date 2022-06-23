package com.tehtech.watchlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockWatchlist {
  private long id;
  private long watchlistId;
  private String stock_symbol;
  
  @JsonIgnore
  public Long id() {
    return id;
   
  }
}


