package abhishekrmanas.controller;

public interface ValidationRule<T, S> {
  S validate(T obj);
}
