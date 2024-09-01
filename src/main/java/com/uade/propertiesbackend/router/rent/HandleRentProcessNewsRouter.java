package com.uade.propertiesbackend.router.rent;

import com.uade.propertiesbackend.core.usecase.HandleRentProcessNews;
import com.uade.propertiesbackend.router.request.RentProcessNews;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HandleRentProcessNewsRouter {

  private final HandleRentProcessNews handleRentProcessNews;

  public HandleRentProcessNewsRouter(HandleRentProcessNews handleRentProcessNews) {
    this.handleRentProcessNews = handleRentProcessNews;
  }

  @PostMapping("/rent-process/news")
  public ResponseEntity<Void> handleRentProcessNews(
      @RequestBody final RentProcessNews rentProcessNews) {
    handleRentProcessNews.accept(
        HandleRentProcessNews.Model.builder().rentProcessId(rentProcessNews.getRentProcessId())
            .status(rentProcessNews.getStatus()).build());
    return ResponseEntity.ok().build();
  }
}
