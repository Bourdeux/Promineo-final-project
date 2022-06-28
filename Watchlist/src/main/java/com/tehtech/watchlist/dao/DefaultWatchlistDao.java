package com.tehtech.watchlist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.tehtech.watchlist.entity.Watchlist;

@Component
@Service
public class DefaultWatchlistDao implements WatchlistDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  /*
   * Create new watchlist   
   */ 
  @Override
  public Watchlist createNewWatchlist(String watchlistName) {
    SqlParams sqlParams = generateNewWatchlistSql(watchlistName);
    KeyHolder keyHolder = new GeneratedKeyHolder();
    
    jdbcTemplate.update(sqlParams.sql, sqlParams.source, keyHolder);
    
    Long watchlistPk = keyHolder.getKey().longValue();
    
    return Watchlist.builder()
        .id(watchlistPk)
        .name(watchlistName)
        .build();  
   }
  
  /*
   * SQL to create new watchlist
   */
  private SqlParams generateNewWatchlistSql(String watchlistName) {
    SqlParams params = new SqlParams();
    
    params.sql = ""
        + "INSERT INTO watchlist "
        + "(name"
        + ") VALUES ("
        + ":watchlistName)";
    
    params.source.addValue("watchlistName", watchlistName);
    
    return params;
  }
  
  /*
   * Rename watchlist name
   */
  @Override
  public Watchlist updateWatchlistName(long watchlistPK, String newWatchlistName) {
    String sql = ""
        + ""
        + "UPDATE watchlist "
        + "SET name = :newWatchlistName "
        + "WHERE watchlist_PK = :watchlistPK";    
    
    Map <String, Object> params = new HashMap<>();
    params.put("newWatchlistName", newWatchlistName);
    params.put("watchlistPK", watchlistPK);
    
    jdbcTemplate.update(sql, params);    
        
    return Watchlist.builder()
        .id(watchlistPK)
        .name(newWatchlistName)
        .build();
  }
  
  @Override
  public void deleteWatchlistName(String watchlistName) {
    String sql = ""
        + "DELETE FROM watchlist "
        + "WHERE name = :watchlistName";
    
    Map <String, Object> params = new HashMap<>();
    params.put("watchlistName", watchlistName); 
    
   if (jdbcTemplate.update(sql, params) != 1) {throw new NoSuchElementException();}
  }
  
  @Override
  public Watchlist fetchWatchlist(String watchlistName) {
    String sql = ""
        + "SELECT * FROM "
        + "watchlist WHERE "
        + "name = :watchlistName";
    
    Map <String, Object> params = new HashMap<>();
    params.put("watchlistName", watchlistName);    
        
    return jdbcTemplate.query(sql, params, new WatchlistResultSetExtractor());
  }
  
  @Override
  public Watchlist getWatchlistPK(Watchlist currentWatchlistName) {
    String sql = ""
        + "SELECT * FROM "
        + "watchlist WHERE "
        + "name = :watchlistName";
    
    Map <String, Object> params = new HashMap<>();
    params.put("watchlistName", currentWatchlistName); 
    
    return Watchlist.builder()
        .id(currentWatchlistName.getId())
        .build();
  }

  
  /*
   * extracts Watchlist info
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
