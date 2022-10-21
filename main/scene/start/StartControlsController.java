package abhishekrmanas.scene.start;

import abhishekrmanas.controller.FxController;
import abhishekrmanas.controller.MenuBarController;
import javafx.fxml.FXML;

public class StartControlsController implements FxController {
  private final MenuBarController ref;

  public StartControlsController(MenuBarController ref) {
    this.ref = ref;
  }

  @FXML
  private void onPlayBtnClick() throws Exception {
    this.ref.newGame();
  }

  @FXML
  private void onChangeThemeBtnClick() throws Exception {
    this.ref.editTheme();
  }

  @FXML
  private void onWhatIsItBtnClick() throws Exception {
    this.ref.whatIsIt();
  }
}
