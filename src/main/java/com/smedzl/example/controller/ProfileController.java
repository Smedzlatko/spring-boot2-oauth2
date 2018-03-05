package com.smedzl.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {

  /**
   * <p>
   * Return profile page.
   * </p>
   */
  @GetMapping("/profile")
  public String profile() {
    return "profile";
  }

}
