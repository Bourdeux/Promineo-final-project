package com.tehtech.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tehtech.watchlist.entity.Watchlist;
import com.tehtech.watchlist.service.WatchlistService;

@RestController
public class DefaultWatchlistController implements WatchlistController{
  
  @Autowired
  private WatchlistService watchlistService;
  
  @Override
  public Watchlist createNewWatchlist(String watchlistName) {
    return watchlistService.createNewWatchlist(watchlistName);
  }
  
  @Override
  public Watchlist getWatchlist(String watchlistName) {    
    return watchlistService.getWatchlist(watchlistName);
  }
  
  @Override
  public Watchlist updateWatchlistName(String watchlistName, String newWatchlistName) {
    return watchlistService.updateWatchlistName(watchlistName, newWatchlistName);
  }
  
  @Override
  public void deleteWatchlist(String watchlistName) {
    watchlistService.deleteWatchlist(watchlistName);
  }
  
}
