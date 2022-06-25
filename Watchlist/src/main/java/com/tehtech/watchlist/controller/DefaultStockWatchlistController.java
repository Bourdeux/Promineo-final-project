package com.tehtech.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;
import com.tehtech.watchlist.service.StockWatchlistService;

@RestController
public class DefaultStockWatchlistController implements StockWatchlistController {
  
  @Autowired
  private StockWatchlistService stockWatchlistService;
  
  @Override
  public Watchlist createWatchlist(String user, String watchlistName) {
    return stockWatchlistService.createWatchlist(user, watchlistName);
  }
  
  

}
