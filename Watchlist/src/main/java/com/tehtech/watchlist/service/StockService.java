package com.tehtech.watchlist.service;

import java.util.List;
import com.tehtech.watchlist.entity.Stock;

public interface StockService {
  List<Stock> getStock (Stock stock);
}
