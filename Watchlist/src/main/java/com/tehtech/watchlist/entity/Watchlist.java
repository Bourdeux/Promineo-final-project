package com.tehtech.watchlist.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Watchlist {
  private long id;  
  private String name;
    
  
  @JsonIgnore
  public Long id() {
    return id;
  }
}
