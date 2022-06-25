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
import com.tehtech.watchlist.entity.Stock;

@Component
@Service
public class DefaultStockDao implements StockDao {
  
  @Autowired
  private NamedParameterJdbcTemplate jdbcTemplate;

  @Override
  public List<Stock> getStockList() {
    String sql = ""
        + "SELECT * "
        + "FROM stock ";
               
    
    Map<String, Object> params = new HashMap<>();
    params.put("stock", toString());    
        
    return jdbcTemplate.query(sql, params, new RowMapper<>() {

      @Override
      public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
       
        return Stock.builder()
            .symbol(rs.getString("symbol"))
            .name(rs.getString("name"))
            .lastPrice(rs.getFloat("lastprice"))
            .build();
      }});
  }

}
