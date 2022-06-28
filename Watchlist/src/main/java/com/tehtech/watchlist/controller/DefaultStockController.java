package com.tehtech.watchlist.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.service.StockService;


@RestController
public class DefaultStockController implements StockController{
  
  @Autowired
  private StockService stockService;

  public List<Stock> getStock(Indexes index){
    
    return stockService.getStock(index);
  }
}
