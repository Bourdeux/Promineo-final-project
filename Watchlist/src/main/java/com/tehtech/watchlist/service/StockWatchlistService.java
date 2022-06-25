package com.tehtech.watchlist.service;

import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;

public interface StockWatchlistService {
  
  Watchlist createWatchlist(String user, String watchlistName);
  public StockWatchlist addSymbol(long watchlistId, String symbol);
  
}
