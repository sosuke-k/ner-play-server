package models;

import java.util.ArrayList;
import java.util.List;

public class Tokens {
  public List<Token> tokens;

  public Tokens() {
    this.tokens = new ArrayList<Token>();
  }

  public void add(Token token) {
    this.tokens.add(token);
  }
}
