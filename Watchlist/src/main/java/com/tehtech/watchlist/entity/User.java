package com.tehtech.watchlist.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
  private long user_pk;
  private String userName;
  private String firstName;
  private String lastName;  
}
