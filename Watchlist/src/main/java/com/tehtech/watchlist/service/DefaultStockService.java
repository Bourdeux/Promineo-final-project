package com.tehtech.watchlist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.tehtech.watchlist.dao.StockDao;
import com.tehtech.watchlist.entity.Stock;

public class DefaultStockService implements StockService {
  
   @Autowired
   private StockDao stockDao;
   
  @Override
  public List<Stock> getStock(Stock stock) {
    
    return stockDao.getStock(stock);
  }

}
