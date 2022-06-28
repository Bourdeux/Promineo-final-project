package com.tehtech.watchlist.entity;

import org.springframework.lang.Nullable;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockWatchlist {
  private long id;
  private long watchlistFK;
  
  @Nullable
  private String symbol;
  
  @JsonIgnore
  public Long id() {
    return id;   
  }
}


