package abhishekrmanas.scene.theme;

import abhishekrmanas.scene.FxDimensions;
import abhishekrmanas.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class ChangeThemeScene extends FxScene {
  private ChangeThemeFooterComponent footerComponent;
  private ChangeThemeComponent themeComponent;

  public ChangeThemeScene(Stage stage) throws Exception {
    super(stage);
    this.footerComponent = new ChangeThemeFooterComponent();
    this.themeComponent = new ChangeThemeComponent(stage, this.footerComponent);
  }

  @Override
  protected void build() {
    this.showCenter(this.themeComponent).showBottom(this.footerComponent);
  }

  @Override
  public String title() {
    return "Change theme";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions =
        new FxDimensions(
            new Dimension2D(800, 550), new Dimension2D(800, 550), new Dimension2D(800, 550));
    return Optional.of(dimensions);
  }
}
