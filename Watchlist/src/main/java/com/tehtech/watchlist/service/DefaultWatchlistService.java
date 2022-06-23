package com.tehtech.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tehtech.watchlist.dao.WatchlistDao;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;

@Service
public class DefaultWatchlistService implements watchlistService{
  
  @Autowired
  private WatchlistDao watchlistDao;
  
  @Transactional
  @Override
  public Watchlist createWatchlist(long userId, String watchlistName) {
    User user = getUserId(userId);
    Watchlist watchlistName = getNickName(watchList);
  }

  @Override
  public void deleteWatchlist(long userId, String watchlistName) {
    
    
  }
  
  private User getUserId(long userId) {
    
    return null;
  }
  
  private Watchlist getNickName(String watchlistName) {
    
    return null;
  }

}
