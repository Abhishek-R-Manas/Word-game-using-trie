package abhishekrmanas;

import abhishekrmanas.db.PreferenceStore;
import abhishekrmanas.game.GameService;
import abhishekrmanas.scene.FxScene;
import abhishekrmanas.util.AppLogger;
import abhishekrmanas.scene.start.StartScene;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class App extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  private static boolean exitCheck(Stage stage) {
    Alert alert =
        new Alert(Alert.AlertType.CONFIRMATION, "Are you sure?", ButtonType.NO, ButtonType.YES);
    alert.setHeaderText("");
    alert.setTitle("Really Exit?");
    alert.initOwner(stage);
    Optional<ButtonType> type = alert.showAndWait();
    return type.isPresent() && type.get() == ButtonType.YES;
  }

  public static boolean close(Stage stage) {
    if (exitCheck(stage)) {
      Platform.exit();
      return true;
    }

    return false;
  }

  @Override
  public void start(Stage stage) throws Exception {
    AppLogger.info(getClass().getCanonicalName(), "Starting application...");
    stage.setOnCloseRequest(
        event -> {
          if (!close(stage)) {
            event.consume();
          }
        });

    FxScene scene = new StartScene(stage);
    scene.show();
  }

  @Override
  public void init() {
    GameService.init();
  }

  @Override
  public void stop() throws Exception {
    GameService.close();
    PreferenceStore.close();
    AppLogger.info(getClass().getCanonicalName(), "Stopped application...");
  }
}
