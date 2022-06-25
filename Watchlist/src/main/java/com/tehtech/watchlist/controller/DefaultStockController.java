package com.tehtech.watchlist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.service.StockService;

public class DefaultStockController implements StockController{
  
  @Autowired
  private StockService stockService;

  public List<Stock> getStock(){
    return stockService.getStock();
  }
}
