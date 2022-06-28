package com.tehtech.watchlist.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Watchlist {
  private long id;  
  private String name;    

}
