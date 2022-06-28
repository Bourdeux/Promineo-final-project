package com.tehtech.watchlist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tehtech.watchlist.dao.StockWatchlistDao;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockRequest;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class DefaultStockWatchlistService implements StockWatchlistService{
    
  @Autowired
  private StockWatchlistDao stockWatchlistDao;    
  
  @Transactional
  @Override
  public StockWatchlist addSymbolsToWatchlist(StockRequest addRequest) {
    
    Watchlist watchlistFK = getWatchlistFK(addRequest);
    log.debug("Returned watchlistFK={}", watchlistFK);
    
    Stock stockSymbol = getSymbol(addRequest);
    log.debug("Returned symbolName={}", stockSymbol);
    
    return stockWatchlistDao.saveSymbolsToWatchlist(watchlistFK.getId(), stockSymbol.getSymbolPK());
  }
  
  public void deleteSymbolFromWatchlist(StockRequest deleteRequest) {
    Watchlist watchlistFK = getWatchlistFK(deleteRequest);
    Stock stockSymbol = getSymbol(deleteRequest);
    
    stockWatchlistDao.deleteSymbolFromWatchlist(watchlistFK.getId(), stockSymbol.getSymbolPK());
    System.out.println("Symbol Deleted");
  }
 
  private Watchlist getWatchlistFK(StockRequest request) {    
    return stockWatchlistDao.fetchWatchlistFK(request.getWatchlistName());
  }
  
  private Stock getSymbol(StockRequest request) {    
    return  stockWatchlistDao.fetchSymbol(request.getSymbol());         
  }

}
