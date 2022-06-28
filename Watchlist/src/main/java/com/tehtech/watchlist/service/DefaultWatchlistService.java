package com.tehtech.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tehtech.watchlist.dao.WatchlistDao;
import com.tehtech.watchlist.entity.Watchlist;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultWatchlistService implements WatchlistService {
  
  @Autowired
  private WatchlistDao watchlistDao;
  
  @Override
  public Watchlist createNewWatchlist(String watchlistName) {    
    return watchlistDao.createNewWatchlist(watchlistName);
  }
  
  @Override
  public Watchlist getWatchlist(String watchlistName) {    
    return watchlistDao.fetchWatchlist(watchlistName);
  }

  @Override
  public Watchlist updateWatchlistName(String watchlistName, String newWatchlistName){
    Watchlist currentWatchlistName = watchlistDao.fetchWatchlist(watchlistName);    
    Watchlist watchlistPK = getWatchlistPK(currentWatchlistName);    
    return watchlistDao.updateWatchlistName(watchlistPK.getId(), newWatchlistName);
  }
  @Override
  public void deleteWatchlist(String watchlistName) {
    watchlistDao.deleteWatchlistName(watchlistName);
  }

  private Watchlist getWatchlistPK(Watchlist currentWatchlistName) {    
    return watchlistDao.getWatchlistPK(currentWatchlistName);
  }

}
