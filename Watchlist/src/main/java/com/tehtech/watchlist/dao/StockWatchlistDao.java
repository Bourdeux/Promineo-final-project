package com.tehtech.watchlist.dao;

import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;

public interface StockWatchlistDao {

   StockWatchlist saveWatchlist(User user, Watchlist watchlistName);   
   Watchlist saveSymbols(User user, Watchlist watchlistName, Stock symbol);
   void deleteSymbol (String nickName, String symbol);
   
}
