package com.tehtech.watchlist.service;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tehtech.watchlist.dao.StockWatchlistDao;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;


@Service
public class DefaultStockWatchlistService implements StockWatchlistService{
    
  @Autowired
  private StockWatchlistDao stockWatchlistDao;    
  

  @Override
  public StockWatchlist addSymbol(StockRequest stockRequest) {
    Stock indexId = getIndexId(stockRequest);
    Stock symbol = getSymbol(stockRequest);
    
    return stockWatchlistDao.saveSymbols(indexId, symbol);
  }
 
  private Stock getIndexId(StockRequest stockRequest) {    
    return stockWatchlistDao.fetchIndexId(stockRequest.getIndex());      
  } 
  
  private Stock getSymbol(StockRequest stockRequest) {    
    return  stockWatchlistDao.fetchSymbol(stockRequest.getSymbol());         
  }

}
