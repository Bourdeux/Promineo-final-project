package com.tehtech.watchlist.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tehtech.watchlist.dao.StockWatchlistDao;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;


@Service
public class DefaultStockWatchlistService implements StockWatchlistService{
  
  private User user;
  private User userPK;  
  
  @Autowired
  private StockWatchlistDao stockWatchlistDao;    
  
  @Transactional
  @Override
  public Watchlist createWatchlist(String user, String watchlistName) {
   return stockWatchlistDao.createWatchlist(user, watchlistName);
  }

  public List<Watchlist> getWatchlistNames(String user) {
    this.userPK = getUserPK(user);
    return stockWatchlistDao
        .printWatchlistNames(userPK);        
  }
  
  public StockWatchlist addSymbol(long watchlistId, String symbol) {
    return stockWatchlistDao.saveSymbols(watchlistId, symbol);
  }

  private User getUserPK(String user) {
    
    return stockWatchlistDao
        .getUserPK(user);
  }

 

}
