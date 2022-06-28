package com.tehtech.watchlist.dao;

import com.tehtech.watchlist.entity.Watchlist;

public interface WatchlistDao {

  Watchlist createNewWatchlist(String watchlistName);  
  Watchlist updateWatchlistName(long watchlistPK, String newWatchlistName);
  Watchlist fetchWatchlist(String watchlistName);
  Watchlist getWatchlistPK(Watchlist currentWatchlistName);
  void deleteWatchlistName(String watchlistName);
      
}
