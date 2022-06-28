package com.tehtech.watchlist.dao;

import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;


public interface StockWatchlistDao {       
  
  StockWatchlist saveSymbolsToWatchlist(long watchlistFK, String symbol);
  void deleteSymbolFromWatchlist(long watchlistFK, String stockSymbol);
  Stock fetchSymbol(String request);
  Watchlist fetchWatchlistFK(String request);
    
   
}
