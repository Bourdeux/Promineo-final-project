package com.tehtech.watchlist.dao;


import java.security.InvalidParameterException;
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
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;


/*
 * This class will:
 * - add stocks to watchlist 
 * - get created watchlist
 * - remove symbols to watchlist * 
 */

@Component
public class DefaultStockWatchlistDao implements StockWatchlistDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /*
   * ADD symbols to watchlist
   */
  @Override
  public StockWatchlist saveSymbolsToWatchlist(long watchlistFK, String symbol) {    
    SqlParams sqlParams = generateAddSymbolToWatchlistSql(watchlistFK, symbol);
    
    KeyHolder keyHolder = new GeneratedKeyHolder();    
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source, keyHolder);
  
    if(jdbcTemplate.update(sqlParams.sql, sqlParams.source) == 0) {
      throw new InvalidParameterException("No watchlist/symbol found!");
    }
    
    Long stockWatchlistId = keyHolder.getKey().longValue();
    
    return StockWatchlist.builder()
        .id(stockWatchlistId)
        .watchlistFK(watchlistFK)
        .symbol(symbol)
        .build();
  }
  
  /*
   * SQL to insert stock symbol to a watchlist
   */
  private SqlParams generateAddSymbolToWatchlistSql(long watchlistFK, String symbol) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO stockwatchlist ("
        + "watchlist_fk, stock_symbol"
        + ") VALUES ("
        + ":watchlistId, :stock_symbol"
        + ")";    
    
    params.source.addValue("watchlistId", watchlistFK);
    params.source.addValue("stock_symbol", symbol);
        
    return params;
  }

  
 
  /*
   * Remove stock symbol from 
   * selected watchlist  
   */ 
  @Override
  public void deleteSymbolFromWatchlist(long watchlistFK, String stockSymbol) {
    
    String sql = ""
        + "DELETE FROM stockwatchlist "
        + "WHERE watchlist_fk = :watchlistFK "
        + "AND stock_symbol = :symbol";
    
    Map <String, Object> params = new HashMap<>();
    params.put("watchlistFK", watchlistFK);
    params.put("symbol", stockSymbol);
    
    jdbcTemplate.update(sql, params);   
    
  }
  
  public List<StockWatchlist> checkWatchlist(long watchlistFK){
    SqlParams sqlParams = new SqlParams();
    
    sqlParams.sql = ""
        + "SELECT watchlist.name, stock.symbol "
        + "FROM watchlist, stockwatchlist, stock "
        + "WHERE watchlist_pk = stockwatchlist.watchlist_fk "
        + "AND stockwatchlist.watchlist_fk = :watchlistFK "
        + "AND stockwatchlist.stock_symbol  = stock.symbol";
    
    Map <String,Object> params = new HashMap<>();
    params.put("watchlistFK", watchlistFK);
    
    return jdbcTemplate.query(sqlParams.sql, params, new RowMapper<>() {

      @Override
      public StockWatchlist mapRow(ResultSet rs, int rowNum) throws SQLException {
        return StockWatchlist.builder()
            .symbol(rs.getString("symbol"))
            .build(); 
      }});
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
  public Watchlist fetchWatchlistFK(String request) {
    String sql = ""
        + "SELECT * FROM "
        + "watchlist WHERE "
        + "name = :watchlistName";
    
    Map<String, Object> params = new HashMap<>();
    params.put("watchlistName", request);
    
    return jdbcTemplate.query(sql, params, new WatchlistResultSetExtractor());
  }  

  /*
   * extracts stock_symbols data
   */
  class StockResultSetExtractor implements ResultSetExtractor<Stock> {
    @Override
    public Stock extractData(ResultSet rs) throws SQLException{
      rs.next();
      
      return Stock.builder()
          .symbolPK(rs.getString("symbol"))
          .indexId(Indexes.valueOf(rs.getString("index_Id")))
          .name(rs.getString("name"))
          .lastPrice(rs.getFloat("lastprice"))
          .build();
    }
  }
  /*
   * extracts watchlistId data
   */
  class WatchlistResultSetExtractor implements ResultSetExtractor<Watchlist> {
    @Override
    public Watchlist extractData(ResultSet rs) throws SQLException {
      rs.next();
      
      return Watchlist.builder()
          .id(rs.getLong("watchlist_pk"))
          .name(rs.getString("name"))
          .build();
    }  
  }  

  class SqlParams {
    String sql;
    MapSqlParameterSource source = new MapSqlParameterSource();
  }



}
