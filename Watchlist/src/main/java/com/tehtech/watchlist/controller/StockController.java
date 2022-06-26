package com.tehtech.watchlist.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tehtech.watchlist.entity.Indexes;
import com.tehtech.watchlist.entity.Stock;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Stock symbols"), servers = {
    @Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/stocks")
public interface StockController {
  @Operation(
      summary = "Returns a list of stock symbols",
      description = "Returns a list of stock symbols",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " A list of stock symbols is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Stock.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "No symbols were found with the input criteria.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "Index", 
              allowEmptyValue = false, 
              required = false, 
              description = "The symbol name"),         
      }
   )
  
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  List<Stock> getStock(@RequestBody (required = false) Indexes index);
}
