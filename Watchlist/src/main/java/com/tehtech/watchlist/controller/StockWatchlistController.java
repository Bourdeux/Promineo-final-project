package com.tehtech.watchlist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tehtech.watchlist.entity.StockWatchlist;
import com.tehtech.watchlist.entity.Watchlist;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(info = @Info(title = "Create Watchlist Service"), servers = {
@Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/stockwatchlist")
public interface StockWatchlistController {
  
  @Operation(
      summary = "Create a watchlist",
      description = "Returns created watchlist",
      responses = {
          @ApiResponse(responseCode = "201", 
              description = " The created watchlist is returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Watchlist.class))),          
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
      },
      parameters = { 
          @Parameter(name = "createWatchlist",               
              required = true, 
              description = "The watchlist as JSON")         
      }
   )
  
  
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  Watchlist createWatchlist(@RequestBody String user, String watchlistName); 

   
}
