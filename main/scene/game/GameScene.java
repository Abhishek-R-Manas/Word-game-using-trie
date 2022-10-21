package abhishekrmanas.scene.game;

import abhishekrmanas.db.PreferenceStore;
import abhishekrmanas.game.Game;
import abhishekrmanas.game.GameService;
import abhishekrmanas.scene.FxScene;
import abhishekrmanas.scene.menu.MenuBarComponent;
import abhishekrmanas.theme.Theme;
import javafx.application.Platform;
import javafx.stage.Stage;

public class GameScene extends FxScene {
  private GameGridComponent gridComponent;
  private GameResultComponent resultComponent;
  private ProgressBarComponent progressBarComponent;
  private MenuBarComponent menuBarComponent;

  public GameScene(Stage stage) throws Exception {
    super(stage);
    Game game = new GameService().fetch();
    this.gridComponent = new GameGridComponent(game);
    this.resultComponent = new GameResultComponent(this.gridComponent);
    this.progressBarComponent = new ProgressBarComponent(root);
    this.menuBarComponent = new MenuBarComponent(stage, root);
  }

  @Override
  protected void build() {
    this.showTop(this.menuBarComponent)
        .showCenter(this.gridComponent)
        .showRight(this.resultComponent)
        .showBottom(this.progressBarComponent);
  }

  private void endGame() {
    this.gridComponent.endGame();
    this.showGameEndScene();
    Game.setRunning(false);
  }

  @Override
  protected Theme loadTheme() {
    return this.themes.loadNewCurrentTheme();
  }

  @Override
  public void setEventHandlers() {
    super.setEventHandlers();
    this.root.addEventHandler(
        Game.EndEvent.GAME_END_EVENT_EVENT_TYPE,
        (event -> {
          this.endGame();
          event.consume();
        }));
  }

  private void showGameEndScene() {
    Platform.runLater(
        () -> {
          try {
            FxScene scene = new GameEndScene(this.stage, this.gridComponent);
            scene.show();
          } catch (Exception e) {
            throw new RuntimeException(e);
          }
        });
  }

  @Override
  public String title() {
    return "Game #" + PreferenceStore.getGameId();
  }
}
