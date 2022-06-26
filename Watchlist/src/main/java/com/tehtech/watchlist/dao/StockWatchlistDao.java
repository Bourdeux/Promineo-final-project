package com.tehtech.watchlist.dao;

import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;


public interface StockWatchlistDao {         
  
  Stock fetchIndexId(Indexes index);
  Stock fetchSymbol(String symbol);
  StockWatchlist saveSymbols(Stock indexId, Stock symbol);
   
}
