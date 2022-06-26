package com.tehtech.watchlist.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;
import lombok.extern.slf4j.Slf4j;

/*
 * This class will:
 * - create new watchlist
 * - get created watchlist
 * - add/remove symbols to watchlist (update)
 * - delete watchlist
 */
@Slf4j
@Component
public class DefaultStockWatchlistDao implements StockWatchlistDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /*
   * ADD/UPDATE symbols to watchlist
   */
  public StockWatchlist saveSymbols(Watchlist watchlistId, Stock symbol) {
    log.debug("add symbols method called: watchlistId={}, symbol={}", watchlistId, symbol);
    SqlParams sqlParams = generateAddSymbolSql(watchlistId, symbol);    
    
    jdbcTemplate.execute(sqlParams.sql.toString(),null);
    
    return StockWatchlist.builder()
        .watchlistId(watchlistId)
        .symbol(symbol)
        .build();
  }
  
  /*
   * Create new watchlist for future expansion
   */
  public Watchlist createWatchlist(String name) {
    SqlParams sqlParams = generateNewWatchlistSql(name);
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source);
    
    return Watchlist.builder()
        .name(name)
        .build();  
   }
  
 
  /*
   * Remove stock symbol from watchlist
   */
  public StockWatchlist deleteSymbols(Watchlist watchlistId, Stock symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "DELETE FROM stockwatchlist "
        + "WHERE watchlist_fk = :watchlistId "
        + "AND symbol = :symbol";
    
    params.source.addValue("watchlistId", watchlistId);
    params.source.addValue("symbol", symbol);
    
    jdbcTemplate.update(params.sql, params.source);
    
    return StockWatchlist.builder()
        .watchlistId(watchlistId)
        .symbol(symbol)
        .build();
  }  

  @Override
  public Stock fetchIndexId(Indexes index) {
    String sql = ""
        + "SELECT * FROM stock "
        + "WHERE index_id = :index_d";
    
    Map<String, Object> params = new HashMap<>();
    params.put("index_Id", index);
    
    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }
  
  @Override
  public Stock fetchSymbol(String symbol) {
    String sql = ""
        + "SELECT * FROM stock "
        + "WHERE symbol = :symbolPK";
    
    Map<String, Object> params = new HashMap<>();
    params.put("symbolPK", symbol);
    
    return jdbcTemplate.query(sql, params, new StockResultSetExtractor());
  }
  
  @Override
  public Watchlist fetchWatchlistId(long watchlistFK) {
    String sql = ""
        + "SELECT * FROM "
        + "watchlist WHERE "
        + "watchlist_pk = :watchlistFK";
    
    Map<String, Object> params = new HashMap<>();
    params.put("watchlistFK", watchlistFK);
    
    return jdbcTemplate.query(sql, params, new WatchlistResultSetExtractor());
  }
  /*
   * SQL to create new watchlist
   */
  private SqlParams generateNewWatchlistSql(String name) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO watchlist "
        + "(name"
        + ") VALUES ("
        + ":watchlistName)";
    
    params.source.addValue("watchlistName", name);
    
    return params;
  }
  
  /*
   * SQL to insert stock symbol to a watchlist
   */
  private SqlParams generateAddSymbolSql(Watchlist watchlistId, Stock symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO stockwatchlist ("
        + "indexId, stock_symbol"
        + ") VALUES ("
        + ":indexId, :stock_symbol"
        + ")";    
    
    params.source.addValue("watchlistId", watchlistId);
    params.source.addValue("stock_symbol", symbol);
        
    return params;
  }
  
  class StockResultSetExtractor implements ResultSetExtractor<Stock> {
    @Override
    public Stock extractData(ResultSet rs) throws SQLException{
      rs.next();
      
      return Stock.builder()
          .symbolPK(rs.getString("symbol"))
          .indexId(Indexes.valueOf(rs.getString("indexId")))
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
          .name(rs.getString("name"))
          .build();
    }  
  }  

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }



}
