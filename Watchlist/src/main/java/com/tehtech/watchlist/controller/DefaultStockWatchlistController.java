package com.tehtech.watchlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.service.StockWatchlistService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DefaultStockWatchlistController implements StockWatchlistController {
  
  @Autowired
  private StockWatchlistService stockWatchlistService;

  @Override
  public StockWatchlist addSymbol(StockRequest stockRequest) {
    log.debug("Watchlist={}", stockRequest);
    return stockWatchlistService.addSymbol(stockRequest);
  }  
  

}
