package abhishekrmanas.theme;

import javafx.stage.Window;

import java.util.List;

public class ThemeService {
  private ThemeFactory factory;

  public ThemeService() {
    this.factory = ThemeFactory.get();
  }

  public Theme loadTheme(ThemeType type) {
    return this.factory.get(type);
  }

  public Theme loadCurrentTheme() {
    return this.factory.loadCurrentTheme();
  }

  public Theme loadNewCurrentTheme() {
    return this.factory.loadNewCurrentTheme();
  }

  public List<ThemeType> getAll() {
    return this.factory.getAll();
  }

  public int count() {
    return this.getAll().size();
  }

  public ThemeType getCurrent() {
    return this.factory.getCurrent();
  }

  private void setCurrent(ThemeType type) {
    this.factory.setCurrent(type);
  }

  public boolean changeTheme(ThemeType type) {
    boolean changed = type != this.getCurrent();
    if (changed) {
      this.setCurrent(type);
      return true;
    } else {
      return false;
    }
  }

  public void dispatch(Window window) {
    window.getScene().getRoot().fireEvent(new Theme.ChangeEvent());
  }
}
