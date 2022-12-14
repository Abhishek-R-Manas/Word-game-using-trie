package abhishekrmanas.controller;

import abhishekrmanas.game.Game;
import abhishekrmanas.game.UserResult;
import abhishekrmanas.pojo.GameStats;
import abhishekrmanas.pojo.WordResult;
import abhishekrmanas.util.GridPoint;
import abhishekrmanas.util.GridUnit;
import abhishekrmanas.util.Utils;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class GameGridController implements FxController {
  private Game game;

  private Map<String, ImageView> imgViewMap;
  private GamePlayValidator validator;
  private GamePlayStyler styler;
  private UserResult userResult;

  @FXML private GridPane gamePane;

  @FXML private ImageView imgView$0_0;
  @FXML private ImageView imgView$0_1;
  @FXML private ImageView imgView$0_2;
  @FXML private ImageView imgView$0_3;
  @FXML private ImageView imgView$1_0;
  @FXML private ImageView imgView$1_1;
  @FXML private ImageView imgView$1_2;
  @FXML private ImageView imgView$1_3;
  @FXML private ImageView imgView$2_0;
  @FXML private ImageView imgView$2_1;
  @FXML private ImageView imgView$2_2;
  @FXML private ImageView imgView$2_3;
  @FXML private ImageView imgView$3_0;
  @FXML private ImageView imgView$3_1;
  @FXML private ImageView imgView$3_2;
  @FXML private ImageView imgView$3_3;

  public GameGridController(Game game) {
    this.game = game;
    this.imgViewMap = new HashMap<>();
    this.validator = new GamePlayValidator(game.result());
    this.userResult = new UserResult();
  }

  @FXML
  public void initialize() {
    this.imgViewMap.put("imgView$0_0", this.imgView$0_0);
    this.imgViewMap.put("imgView$0_1", this.imgView$0_1);
    this.imgViewMap.put("imgView$0_2", this.imgView$0_2);
    this.imgViewMap.put("imgView$0_3", this.imgView$0_3);
    this.imgViewMap.put("imgView$1_0", this.imgView$1_0);
    this.imgViewMap.put("imgView$1_1", this.imgView$1_1);
    this.imgViewMap.put("imgView$1_2", this.imgView$1_2);
    this.imgViewMap.put("imgView$1_3", this.imgView$1_3);
    this.imgViewMap.put("imgView$2_0", this.imgView$2_0);
    this.imgViewMap.put("imgView$2_1", this.imgView$2_1);
    this.imgViewMap.put("imgView$2_2", this.imgView$2_2);
    this.imgViewMap.put("imgView$2_3", this.imgView$2_3);
    this.imgViewMap.put("imgView$3_0", this.imgView$3_0);
    this.imgViewMap.put("imgView$3_1", this.imgView$3_1);
    this.imgViewMap.put("imgView$3_2", this.imgView$3_2);
    this.imgViewMap.put("imgView$3_3", this.imgView$3_3);
    this.styler = new GamePlayStyler(this.gamePane, this.imgViewMap.values());
  }

  @FXML
  public void onImgViewClick(MouseEvent event) {
    ImageView imgView = (ImageView) event.getSource();
    GridPoint point = Utils.getGridPointFromImageViewLabel(imgView.getId());
    GridUnit unit = game.getGridUnit(point);
    ValidationResult validation = this.validator.validate(unit);
    this.applyStyleAfterValidation(imgView, validation);
  }

  public void rotate() {
    this.styler.rotate();
  }

  private void applyStyleAfterValidation(ImageView imgView, ValidationResult result) {
    switch (result) {
      case ALL_INVALID:
        this.styler.invalidate();
        break;
      case LAST_INVALID:
        this.styler.forLastInvalidClick(imgView);
        break;
      case ALL_VALID:
        this.styler.forValidClick(imgView);
        break;
      default:
        break;
    }
  }

  public Optional<WordResult> validateWordOnBtnClick() {
    WordResult result = null;
    String word = this.validator.validate();

    if (word == null) {
      this.styler.forIncorrectWord();
    } else if (this.userResult.exists(word)) {
      this.styler.forRepeatedWord();
    } else {
      int points = this.game.result().getPoints(word);
      List<GridPoint> seq = this.validator.getSeq();
      result = new WordResult(word, points, seq);
      this.userResult.add(word, result);
      this.styler.forCorrectWord();
    }

    this.validator.reset();
    return result == null ? Optional.empty() : Optional.of(result);
  }

  public void revisitUserWord(String word) {
    WordResult result = this.userResult.get(word);
    this.revisit(result);
  }

  public void revisitGameWord(String word) {
    WordResult result = this.game.result().getWordResult(word);
    this.revisit(result);
  }

  public Map<String, WordResult> getAllGameWords() {
    return this.game.result().all();
  }

  public Map<String, WordResult> getAllUserWords() {
    return this.userResult.all();
  }

  private void revisit(WordResult result) {
    List<ImageView> imgViews =
        result.getSeq().stream()
            .map(
                point -> {
                  String id = Utils.getImgViewLabelFromGridPoint(point);
                  return this.imgViewMap.get(id);
                })
            .collect(Collectors.toList());

    this.validator.reset();
    this.styler.revisit(imgViews);
  }

  public void endGame() {
    this.imgViewMap.forEach(
        (label, imgView) -> {
          imgView.setDisable(true);
        });

    this.validator.reset();
    this.styler.invalidate();
    this.styler.rotateOnEnd();
    this.styler.applyEndTransition();
  }

  public GameStats computeStats() {
    return new GameStats(this.game.result(), this.userResult);
  }
}
