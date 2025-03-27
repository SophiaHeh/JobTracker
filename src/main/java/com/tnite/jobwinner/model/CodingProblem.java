package com.tnite.jobwinner.model;

public class CodingProblem {
  private String question;
  private String platform; // LeetCode, HackerRank, etc.
  private String difficulty; // Easy, Medium, Hard

  public CodingProblem(String question, String platform, String difficulty) {
    this.question = question;
    this.platform = platform;
    this.difficulty = difficulty;
  }
}
