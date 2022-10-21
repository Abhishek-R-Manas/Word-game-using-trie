package abhishekrmanas.util;

import java.util.concurrent.ThreadLocalRandom;

public enum Alphabet {
  A(9, 1), B(3, 3), C(5, 3),
  D(4, 2), E(11, 1), F(2, 4),
  G(3, 2), H(3, 4), I(8, 1),
  J(1, 8), K(2, 5), L(6, 1),
  M(3, 3), N(7, 1), O(7, 1),
  P(3, 3), Q(1, 10), R(8, 1),
  S(6, 1), T(7, 1), U(4, 1),
  V(1, 4), W(2, 4), X(1, 8),
  Y(2, 4), Z(1, 10);

  private static int totalWeight = 0;

  static {
    for (Alphabet alphabet : values()) {
      totalWeight += alphabet.getWeight();
    }
  }

  private int weight;
  private int score;

  Alphabet(int weight, int score) {
    this.weight = weight;
    this.score = score;
  }

  public static Alphabet newRandom() {
    int value = 1 + ThreadLocalRandom.current().nextInt(totalWeight);
    for (Alphabet alphabet : values()) {
      value = value - alphabet.getWeight();
      if (value <= 0) {
        return alphabet;
      }
    }

    return null;
  }

  public String get() {
    return this.toString().toLowerCase();
  }

  public int getWeight() {
    return weight;
  }

  public int getScore() {
    return score;
  }
}
