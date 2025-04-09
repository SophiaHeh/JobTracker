package com.tnite.jobwinner.controller;

import com.tnite.jobwinner.bean.SophiaUser;
import com.tnite.jobwinner.service.SophiaUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sophia")
public class SophiaUserController {
  @Autowired
  private SophiaUserService userService;

  @GetMapping("")
  public List<SophiaUser> getSophiaUserList(){
    List<SophiaUser> list = userService.list();
    return list;
  }
}



