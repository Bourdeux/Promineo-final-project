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
  
  @Transactional
  @Override
  public StockWatchlist addSymbol(StockRequest stockRequest) {
    Watchlist watchlistId = getWatchlistId(stockRequest);
    Stock symbol = getSymbol(stockRequest);
    
    return stockWatchlistDao.saveSymbols(watchlistId, symbol);
  }
 
  private Watchlist getWatchlistId(StockRequest stockRequest) {    
    return stockWatchlistDao.fetchWatchlistId(stockRequest.getWatchlistFK());
  }

  private Stock getIndexId(StockRequest stockRequest) {    
    return stockWatchlistDao.fetchIndexId(stockRequest.getIndex());      
  } 
  
  private Stock getSymbol(StockRequest stockRequest) {    
    return  stockWatchlistDao.fetchSymbol(stockRequest.getSymbol());         
  }

}
