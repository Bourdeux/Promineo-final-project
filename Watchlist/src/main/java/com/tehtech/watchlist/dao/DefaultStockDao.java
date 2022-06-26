package com.tehtech.watchlist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import lombok.extern.slf4j.Slf4j;

@Component
@Service
@Slf4j
public class DefaultStockDao implements StockDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;
  
  
  /*
   * Get list of stocks from specific Indexes
   * this can be expanded to different indexes
   */
  // select index_id, name, cusip, lastprice from stock where index_id = 'DOW';
  @Override
  public List<Stock> getStockList(Indexes index) {
    log.debug("DAO: index={}, symbol={}", index);
    String sql = ""
        + "SELECT * "
        + "FROM stock "
        + "WHERE index_id = :index_id ";           

    Map<String, Object> params = new HashMap<>();    
    params.put("index_id", index.toString());    
        
    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
       
        return Stock.builder() 
            .symbolPK(rs.getString("symbol"))
            .indexId(Indexes.valueOf(rs.getString("index_Id")))            
            .name(rs.getString("name"))
            .cusip(rs.getString("cusip"))
            .lastPrice(rs.getFloat("lastprice"))
            .build();
      }});
  }

}
