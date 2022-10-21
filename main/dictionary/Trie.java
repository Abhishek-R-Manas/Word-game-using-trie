package abhishekrmanas.dictionary;

import abhishekrmanas.util.Alphabet;

public class Trie {
  private TrieNode root;

  Trie() {
    this.root = new TrieNode();
  }

  public void insert(String word) {
    char[] arr = word.toCharArray();
    TrieNode node = root;
    int score = 0;

    for (char c : arr) {
      int idx = c - 'a';
      Alphabet alphabet = Alphabet.values()[idx];
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) {
        children[idx] = new TrieNode();
      }

      node = children[idx];
      score += alphabet.getScore();
    }

    node.setScore(score);
  }


  public int search(String word) {
    TrieNode node = root;
    char[] arr = word.toCharArray();
    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) {
        return 0;
      }

      node = children[idx];
    }

    return node != null ? node.getScore() : 0;
  }


  public boolean prefix(String word) {
    TrieNode node = root;
    char[] arr = word.toCharArray();
    for (char c : arr) {
      int idx = c - 'a';
      TrieNode[] children = node.getChildren();
      if (children[idx] == null) {
        return false;
      }

      node = children[idx];
    }

    return node != null;
  }
}
