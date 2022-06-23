package com.tehtech.watchlist.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.User;
import com.tehtech.watchlist.entity.Watchlist;

/*
 * This class will:
 * - create new watchlist
 * - get created watchlist
 * - add/remove symbols to watchlist (update)
 * - delete watchlist
 */
@Component
public class DefaultWatchlistDao implements StockWatchlistDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /*
   * CREATE new watchlist name
   */
  @Override
  public StockWatchlist saveWatchlist(User user, Watchlist watchlistName) {
    SqlParams sqlParams = generateCreateSql(user, watchlistName);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source, keyHolder);
    
    Long stockWatchlistId = keyHolder.getKey().longValue();
    
    saveNewWatchlistName (watchlistName, stockWatchlistId);
    
    return StockWatchlist.builder()
        .name(watchlistName)
        .build();
  }
  
  private void saveNewWatchlistName(Watchlist watchlistName, Long stockWatchlistId) {
    // TODO Auto-generated method stub
    
  }

  /*
   * DELETE watchlist
   */
  @Override
  public void deleteWatchlist(User user, Watchlist watchlistName) {
    SqlParams sqlParams = generateDeleteWLSql(user, watchlistName);
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);    
  }  

  /*
   * ADD/UPDATE symbols to watchlist
   */
  public Watchlist saveSymbols(User user, Watchlist watchlistName, Stock symbol) {
    SqlParams sqlParams = generateAddSymbolSql(user, watchlistName, symbol);
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);
    return Watchlist.builder()
        .user_pk(user)
        .name(watchlistName)        
        .build();
  }
  /*
   * DELETE symbol from watchlist
   */
  public void deleteSymbol(Watchlist watchlistName, Stock symbol) {
    SqlParams sqlParams = generateDeleteSymbolSql(watchlistName, symbol);
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);            
  }
 
  /*
   * SQL to insert watchlist name (nickName)
   */
  private SqlParams generateCreateSql(User user, Watchlist watchlistName) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO watchlist ("
        + "user_id, name"
        + ") VALUES ("
        + ":userId, :name"
        + ")";
    
    params.source.addValue("user_id", user);
    params.source.addValue("name", watchlistName);
    
    return params;
  }
  
  /*
   * SQL to delete watchlist
   */
  private SqlParams generateDeleteWLSql(User user, String watchlistName) {
    SqlParams params = new SqlParams();
  
    params.sql = ""
        + "DELETE FROM watchlist "
        + "WHERE user_id = :user_id "
        + "AND name = :name";
  
    params.source.addValue("user_id", user);
    params.source.addValue("name", watchlistName);
  
    return params;
  }
  
  /*
   * SQL to insert stock symbol to a watchlist
   */
  private SqlParams generateAddSymbolSql(User user, String watchlistName, String symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO watchlist ("
        + "user_id, name, stock_symbol"
        + ") VALUES ("
        + ":userId, :name, :stock_symbol"
        + ")";
    
    params.source.addValue("userId", user);
    params.source.addValue("name", watchlistName);
    params.source.addValue("stock_symbol", symbol);
        
    return params;
  }
  
  /*
   *  SQL to remove stock symbol from watchlist
   */
  private SqlParams generateDeleteSymbolSql(Watchlist watchlistName, Stock symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "DELETE FROM watchlist "
        + "where name = :name "
        + "AND STOCK_SYMBOL = :stock_symbol";
    
    params.source.addValue("name", watchlistName);
    params.source.addValue("stock_symbol", symbol);
    
    return params;
  }

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }

}
