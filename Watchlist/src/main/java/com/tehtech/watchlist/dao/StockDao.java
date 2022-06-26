package com.tehtech.watchlist.dao;

import java.util.List;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;

public interface StockDao {
  
  public List<Stock> getStockList(Indexes index);
}
