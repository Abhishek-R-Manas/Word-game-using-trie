package abhishekrmanas.scene.game;

import abhishekrmanas.controller.GameEndResultController;
import abhishekrmanas.pojo.GameStats;
import abhishekrmanas.scene.FxComponent;
import javafx.scene.layout.VBox;

public class GameEndResultComponent extends FxComponent<GameEndResultController, VBox> {
  private GameGridComponent ref;

  public GameEndResultComponent(GameGridComponent ref) throws Exception {
    super("gameEndResult.fxml");
    this.ref = ref;
    this.create();
    this.updateStats();
  }

  @Override
  protected GameEndResultController createController() {
    return new GameEndResultController(this.ref.getController());
  }

  @Override
  protected VBox createNode() throws Exception {
    return this.loadNode();
  }

  private void updateStats() {
    GameStats stats = this.ref.computeStats();
    this.getController().updateText(stats.to());
  }
}
