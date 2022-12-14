package abhishekrmanas.dictionary;

import abhishekrmanas.util.AppLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

public class Dictionary {
  private static final String WORDS_FILE = "words.txt";

  private Trie trie;

  public Dictionary() {
    BufferedReader reader = null;
    try {
      this.trie = new Trie();
      InputStream inputStream = ClassLoader.getSystemResourceAsStream(WORDS_FILE);
      if (inputStream == null) {
        throw new IOException("Invalid URL specified");
      }
      reader = new BufferedReader(new InputStreamReader(inputStream));
      String word;

      while ((word = reader.readLine()) != null) {
        word = word.trim().toLowerCase();
        trie.insert(word);
      }

      AppLogger.fine(getClass().getCanonicalName(), "Completed loading all words into the trie.");
    } catch (IOException e) {
      AppLogger.severe(getClass().getCanonicalName(), "Could not load all words into the trie.");
      throw new RuntimeException(e);
    } finally {
      try {
        if (reader != null) {
          reader.close();
        }
      } catch (IOException e) {
        AppLogger.severe(getClass().getCanonicalName(), "Could not successfully close reader.");
      }
    }
  }

  public static void main(String[] args) {
    Dictionary dictionary = new Dictionary();
    System.out.println(dictionary.search("ameya"));
    System.out.println(dictionary.search("p"));
    System.out.println(dictionary.prefix("horakeri"));
  }

  public int search(String word) {
    return this.trie.search(word);
  }

  public boolean prefix(String word) {
    return this.trie.prefix(word);
  }
}
