package com.tehtech.watchlist.service;

import java.util.List;
import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;



public interface StockWatchlistService {  
  
  StockWatchlist addSymbolsToWatchlist(StockRequest addRequest);
  void deleteSymbolFromWatchlist(StockRequest deleteRequest);
  List<StockWatchlist> checkWatchlist(StockRequest readRequest);
  
}
