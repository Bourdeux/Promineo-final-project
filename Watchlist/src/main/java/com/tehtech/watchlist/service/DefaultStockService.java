package com.tehtech.watchlist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tehtech.watchlist.dao.StockDao;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;


@Service
public class DefaultStockService implements StockService {
  
  @Autowired
  private StockDao stockDao;
   
  @Override
  public List<Stock> getStock(Indexes index) {   
    return stockDao.getStockList(index);
  }

}
