package com.tehtech.watchlist.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.StockRequest;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(info = @Info(title = "Add symbols to watchlist"), servers = {
@Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/stockwatchlist")
public interface StockWatchlistController {
  
  @Operation(
      summary = "Create a watchlist of stocks",
      description = "Build lists of stocks in a watchlist",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " Symbols are added", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = StockWatchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "A watchlist was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )  
  @PostMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CREATED)  
  StockWatchlist addSymbolsToWatchlist(StockRequest addRequest);
  
  @Operation(
      summary = "Delete a symbol from watchlist",
      description = "Remove stocks in watchlist",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " Symbols are deleted", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = StockWatchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "A symbol was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )
  @DeleteMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.CREATED)   
  void deleteSymbolFromWatchlist(StockRequest deleteRequest);
  
  @Operation(
      summary = "Get a list of symbols from created watchlist",
      description = "Track stocks in watchlist",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = "Stocks in watchlist", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = StockWatchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "A watchlist was not found with the input criteria", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      }

   )
  @GetMapping
  @ResponseBody
  @ResponseStatus(code = HttpStatus.OK)
  List<StockWatchlist> createdWatchlist(StockRequest readRequest); 

   
}
