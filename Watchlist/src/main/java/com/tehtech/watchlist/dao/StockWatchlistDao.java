package com.tehtech.watchlist.dao;


import java.util.List;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;

public interface StockWatchlistDao {

  Watchlist createWatchlist(String user, String watchlistName);
  List<Watchlist> printWatchlistNames(User userPK);      
  StockWatchlist saveSymbols(Long watchlistId, String symbol);
  User getUserPK(String user);   
   
}
