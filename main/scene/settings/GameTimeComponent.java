package abhishekrmanas.scene.settings;

import abhishekrmanas.controller.GameTimeController;
import abhishekrmanas.scene.FxComponent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GameTimeComponent extends FxComponent<GameTimeController, AnchorPane> {
  private Stage stage;

  public GameTimeComponent(Stage stage) throws Exception {
    super("gameTime.fxml");
    this.stage = stage;
    this.create();
  }

  @Override
  protected GameTimeController createController() {
    return new GameTimeController(this.stage);
  }

  @Override
  protected AnchorPane createNode() throws Exception {
    return this.loadNode();
  }
}
