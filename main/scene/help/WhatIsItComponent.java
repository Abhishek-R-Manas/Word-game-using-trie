package abhishekrmanas.scene.help;

import abhishekrmanas.controller.BasicController;
import abhishekrmanas.scene.FxComponent;
import javafx.scene.control.TextArea;

public class WhatIsItComponent extends FxComponent<BasicController, TextArea> {
  protected WhatIsItComponent() throws Exception {
    super("whatIsIt.fxml");
    this.create();
  }

  @Override
  protected BasicController createController() {
    return new BasicController();
  }

  @Override
  protected TextArea createNode() throws Exception {
    return this.loadNode();
  }
}
