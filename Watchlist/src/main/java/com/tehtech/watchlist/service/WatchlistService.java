package com.tehtech.watchlist.service;

import com.tehtech.watchlist.entity.Watchlist;

public interface WatchlistService {

  Watchlist createNewWatchlist(String watchlistName);
  Watchlist getWatchlist(String watchlistName);
  Watchlist updateWatchlistName(String watchlistName, String newWatchlistName);
  void deleteWatchlist(String watchlistName);

    
}
