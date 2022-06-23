package com.tehtech.watchlist.service;

import com.tehtech.watchlist.entity.Watchlist;

public interface watchlistService {
  
  Watchlist createWatchlist(long userId, String nickName);
  
}
