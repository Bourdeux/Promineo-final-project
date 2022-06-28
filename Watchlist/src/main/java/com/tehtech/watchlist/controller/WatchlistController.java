package com.tehtech.watchlist.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.tehtech.watchlist.entity.Watchlist;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(title = "Watchlist Service"), servers = {
    @Server(url = "Http://localhost:8080", description = "Local server.")})
@RequestMapping("/watchlists")
public interface WatchlistController {
 
  /*
   * CREATE
   */
//@formatter:off
  @Operation(
      summary = "Creates a new watchlist",
      description = "Creates a new for the user",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = "New Watchlist created!", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Watchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The watchlist name is invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid input.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "watchlistName", 
              allowEmptyValue = false, 
              required = false, 
              description = "Create a new watchlist!"),
      }
   )
  //@formatter:on
  @PostMapping
  @ResponseStatus(code = HttpStatus.CREATED)
  public Watchlist createNewWatchlist(@RequestParam String watchlistName);
  
  
//@formatter:off
  @Operation(
      summary = "Returns a watchlist",
      description = "Returns a watchlist for the user",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " Watchlist found and returned.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Watchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid watchlist.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "watchlistName", 
              allowEmptyValue = false, 
              required = false, 
              description = "The watchlist name (i.e., 'Watchlist One')"),
      }
   )
  //@formatter:on
 
  @GetMapping
  @ResponseStatus(code = HttpStatus.OK)
  Watchlist getWatchlist(@RequestParam String watchlistName);

  
  /*
   * UPDATE
   */
//@formatter:off
  @Operation(
      summary = "Rename a watchlist",
      description = "Renames a watchlist for the user",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " Watchlist found and renamed.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Watchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid watchlist.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "watchlistName", 
              allowEmptyValue = false, 
              required = true, 
              description = "The watchlist name (i.e., 'Watchlist One')"),     
            
          @Parameter(name = "newWatchlistName", 
              allowEmptyValue = false, 
              required = true, 
              description = "The new watchlist name")      
      }
      
   )
  //@formatter:on
  @PutMapping
  @ResponseStatus(code = HttpStatus.OK)
  Watchlist updateWatchlistName(@RequestParam String watchlistName, @RequestParam String newWatchlistName);

  
  /*
   * DELETE
   */
  @Operation(
      summary = "Deletes a watchlist",
      description = "Delete a watchlist for the user",
      responses = {
          @ApiResponse(responseCode = "200", 
              description = " Watchlist found and deleted.", 
              content = @Content(
                  mediaType = "application/json", 
                  schema = @Schema(implementation = Watchlist.class))),          
          @ApiResponse(
              responseCode = "400", 
              description =  "The request parameters are invalid.", 
              content = @Content(mediaType = "application/json")), 
          @ApiResponse(
              responseCode = "404", 
              description =  "Not a valid watchlist.", 
              content = @Content(mediaType = "application/json")),
          @ApiResponse(responseCode = "500", 
              description =  "An unplanned error occured.", 
              content = @Content(mediaType = "application/json"))
      },
      parameters = { 
          @Parameter(name = "watchlistName", 
              allowEmptyValue = false, 
              required = false, 
              description = "The watchlist name (i.e., 'Watchlist One')"),
      }
   )
  @DeleteMapping
  @ResponseStatus(code = HttpStatus.OK)
  void deleteWatchlist(@RequestParam String watchlistName);

}
