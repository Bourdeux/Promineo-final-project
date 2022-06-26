package com.tehtech.watchlist.service;

import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;


public interface StockWatchlistService {  
  
  public StockWatchlist addSymbol(StockRequest stockRequest);
  
}
