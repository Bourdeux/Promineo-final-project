package com.tehtech.watchlist.dao;

import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;


public interface StockWatchlistDao {         
  
  Stock fetchIndexId(Indexes index);
  Stock fetchSymbol(String symbol);
  Watchlist fetchWatchlistId(String watchlistName);
  public StockWatchlist saveSymbols(Watchlist watchlistId, Stock symbol);
  
   
}
