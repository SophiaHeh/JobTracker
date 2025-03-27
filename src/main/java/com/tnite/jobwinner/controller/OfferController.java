package com.tnite.jobwinner.controller;




import com.tnite.jobwinner.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/offer")
@RestController
public class OfferController {
@Autowired
  private OfferService offerService;

  @GetMapping("/hello")
  public String hello() {
    return offerService.test();
  }


}