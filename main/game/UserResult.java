package abhishekrmanas.game;

import abhishekrmanas.pojo.WordResult;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserResult {
  private final Map<String, WordResult> wordToResultMap;
  private int totalScore = 0;

  public UserResult() {
    this.wordToResultMap = new LinkedHashMap<>();
  }

  public void add(String word, WordResult result) {
    this.wordToResultMap.put(word, result);
    this.totalScore += result.getScore();
  }

  public WordResult get(String word) {
    return this.wordToResultMap.get(word);
  }

  public Map<String, WordResult> all() {
    return this.wordToResultMap;
  }

  public boolean exists(String word) {
    return this.wordToResultMap.containsKey(word);
  }

  public int getTotalScore() {
    return this.totalScore;
  }

  public int getWordCount() {
    return this.wordToResultMap.size();
  }
}
