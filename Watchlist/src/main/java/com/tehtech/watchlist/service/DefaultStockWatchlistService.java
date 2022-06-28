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
    Stock stockSymbol = getSymbol(addRequest);    
    log.debug("Returned watchlistFK={} symbolName={}", watchlistFK, stockSymbol);    
    
    return stockWatchlistDao.saveSymbolsToWatchlist(watchlistFK.getId(), stockSymbol.getSymbolPK());
  }
  
  public void deleteSymbolFromWatchlist(StockRequest deleteRequest) {
    
    Watchlist watchlistFK = getWatchlistFK(deleteRequest);
    Stock stockSymbol = getSymbol(deleteRequest);    
    log.debug("Returned watchlistFK={} symbolName={}", watchlistFK, stockSymbol);
    
    stockWatchlistDao.deleteSymbolFromWatchlist(watchlistFK.getId(), stockSymbol.getSymbolPK());
    System.out.println("Symbol " + stockSymbol + "deleted!");
  }
  
  @Override
  public List<StockWatchlist> checkWatchlist(StockRequest readRequest){
    Watchlist watchlistFK = getWatchlistFK(readRequest);
    return stockWatchlistDao.checkWatchlist(watchlistFK.getId());
  }
  
  /*
   * Converts string to primary keys
   */
  private Watchlist getWatchlistFK(StockRequest request) {
    if(request.getWatchlistName().isEmpty()) {
      throw new NoSuchElementException("Watchlist does not exist!"); 
    }
    return stockWatchlistDao.fetchWatchlistFK(request.getWatchlistName());
  }
  
  /*
   * Converts string to primary keys
   */
  private Stock getSymbol(StockRequest request) {
    if(request.getSymbol().isEmpty()) {
      throw new NoSuchElementException("No symbol found!"); 
    }
    return  stockWatchlistDao.fetchSymbol(request.getSymbol());         
  }

}
