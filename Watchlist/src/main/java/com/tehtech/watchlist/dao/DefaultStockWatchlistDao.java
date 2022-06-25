package com.tehtech.watchlist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import com.mysql.cj.x.protobuf.MysqlxPrepare.Execute;
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
public class DefaultStockWatchlistDao implements StockWatchlistDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /*
   * CREATE new watchlist name;
   * generateCreateSql will input new watchlist for the user;
   */
  @Override
  public Watchlist createWatchlist(String user, String watchlistName) {
    SqlParams sqlParams = generateCreateSql(user, watchlistName);    
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);    
    
    return Watchlist.builder()        
        .name(watchlistName)
        .build();
  }

  /*
   * Return list of watchlists for a user (query)
   */
  public List<Watchlist> printWatchlistNames(User userPK) {
    SqlParams sqlParams = generatePrintWatchlistSql(userPK);
    
    List<Watchlist> watchlists = new ArrayList<>();
    
    jdbcTemplate.query(sqlParams.sql, sqlParams.source, new RowMapper<>() {
      
      @Override
      public Watchlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        
        return Watchlist.builder()
            .id(rs.getLong("watchlist_pk"))
            .name(rs.getString("name"))
            .build();
      }});
    return watchlists;  
    
  }


  private SqlParams generatePrintWatchlistSql(User userPK) {
    SqlParams params = new SqlParams();
    params.sql = ""
        + "SELECT * FROM "
        + "watchlist WHERE "
        + "user_fk = :user_fk";
    
    params.source.addValue("user_fk", userPK);
        
    return params;
  }

  /*
   * ADD/UPDATE symbols to watchlist
   */
  public StockWatchlist saveSymbols(Long watchlistId, String symbol) {
    SqlParams sqlParams = generateAddSymbolSql(watchlistId, symbol);
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);
    
    return StockWatchlist.builder()
        .watchlistId(watchlistId)
        .stock_symbol(symbol)
        .build();
  }     
 
  /*
   *  Method to remove stock symbol from watchlist
   */
  public StockWatchlist deleteSymbols(Long watchlistId, String symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "DELETE FROM stockwatchlist "
        + "WHERE watchlist_fk = :watchlistId "
        + "AND symbol = :symbol";
    
    params.source.addValue("watchlist_fk", watchlistId);
    params.source.addValue("symbol", symbol);
    
    jdbcTemplate.update(params.sql, params.source);
    
    return StockWatchlist.builder()
        .watchlistId(watchlistId)
        .stock_symbol(symbol)
        .build();
  }
  
  /*
   * SQL to insert watchlist name (watchlistName)
   */
  private SqlParams generateCreateSql(String userName, String watchlistName) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO watchlist ("
        + "user_name, watchlistName"
        + ") VALUES ("
        + ":user_name, :watchlistName"
        + ")";
    
    params.source.addValue("user_id", userName);
    params.source.addValue("watchlistName", watchlistName);
    
    return params;
  } 

  /*
   * method to delete watchlist from watchlists
   */
  private SqlParams generateDeleteWLSql(User user, String watchlistName) {
    SqlParams params = new SqlParams();
  
    params.sql = ""
        + "DELETE FROM watchlist "
        + "WHERE user_name = :user_name "
        + "AND watchlistName = :watchlistName";
  
    params.source.addValue(":user_name", user);
    params.source.addValue("watchlistName", watchlistName);
  
    return params;
  }
  
  /*
   * SQL to insert stock symbol to a watchlist
   */
  private SqlParams generateAddSymbolSql(long watchlistId, String symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO stockwatchlist ("
        + "watchlistId, stock_symbol"
        + ") VALUES ("
        + ":watchlistId, :stock_symbol"
        + ")";    
    
    params.source.addValue("watchlistId", watchlistId);
    params.source.addValue("stock_symbol", symbol);
        
    return params;
  }
  
  class UserResultSetExtractor implements ResultSetExtractor<User> {
    @Override
    public User extractData(ResultSet rs) throws SQLException {
      rs.next();
      
      return User.builder()
          .user_pk(rs.getLong("user_pk"))
          .userName(rs.getString("userName"))
          .firstName(rs.getString("first_name"))
          .lastName(rs.getString("last_name"))          
          .build();
    }
  }
  
  class StockResultSetExtractor implements ResultSetExtractor<Stock> {
    @Override
    public Stock extractData(ResultSet rs) throws SQLException{
      rs.next();
      
      return Stock.builder()
          .symbol(rs.getString("symbol"))
          .name(rs.getString("name"))
          .lastPrice(rs.getFloat("lastprice"))
          .build();
    }
  }
  
  class WatchlistResultSetExtractor implements ResultSetExtractor<Watchlist> {
    @Override
    public Watchlist extractData(ResultSet rs) throws SQLException {
      rs.next();
      
      return Watchlist.builder()
          .id(rs.getLong("id"))
          .user_pk(rs.getLong("user_pk"))
          .name(rs.getString("name"))
          .build();
    }  
  }
  
  /*
   * get UserName from service then converts UserName to user_pk
   * to communicate to DB  
   */
  @Override
  public User getUserPK(String user) {    
    SqlParams params = new SqlParams();
    params.sql = ""
        + "SELECT * FROM "
        + "WHERE "
        + "userName = :userName";   
    
    params.source.addValue("userName", user);    
     
    return User.builder()
        .user_pk(1)
        .build();
  }


  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }







 
  
}
