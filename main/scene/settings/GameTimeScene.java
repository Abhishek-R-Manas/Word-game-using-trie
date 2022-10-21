package abhishekrmanas.scene.settings;

import abhishekrmanas.scene.FxDimensions;
import abhishekrmanas.scene.FxScene;
import javafx.geometry.Dimension2D;
import javafx.stage.Stage;

import java.util.Optional;

public class GameTimeScene extends FxScene {
  private GameTimeComponent component;

  public GameTimeScene(Stage stage) throws Exception {
    super(stage);
    this.component = new GameTimeComponent(stage);
  }

  @Override
  protected void build() {
    this.showCenter(this.component);
  }

  @Override
  protected String title() {
    return "Configure game time";
  }

  @Override
  protected Optional<FxDimensions> preferredDimensions() {
    FxDimensions dimensions = new FxDimensions(new Dimension2D(500, 180));
    return Optional.of(dimensions);
  }
}
