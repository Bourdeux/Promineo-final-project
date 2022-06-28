package com.tehtech.watchlist.dao;

import java.util.List;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;


public interface StockWatchlistDao {       
  
  StockWatchlist saveSymbolsToWatchlist(long watchlistFK, String symbol);
  void deleteSymbolFromWatchlist(long watchlistFK, String stockSymbol);
  Stock fetchSymbol(String request);
  Watchlist fetchWatchlistFK(String request);
  List<StockWatchlist> checkWatchlist(long watchlistFK);
    
   
}
