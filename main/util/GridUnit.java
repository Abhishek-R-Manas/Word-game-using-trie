package abhishekrmanas.util;

import javafx.scene.image.Image;

import java.util.Objects;

public class GridUnit {
  private Alphabet alphabet;
  private GridPoint gridPoint;
  private String imgPath;
  private Image img;

  public GridUnit(Alphabet alphabet, GridPoint gridPoint) {
    this.alphabet = alphabet;
    this.gridPoint = gridPoint;
    this.imgPath = "images/alphabet/" + this.alphabet.get() + ".png";
  }

  public Image getImage() {
    if (this.img == null) {
      this.img = new Image(this.imgPath);
    }
    return this.img;
  }

  public String getLetter() {
    return alphabet.get();
  }

  public int getScore() {
    return alphabet.getScore();
  }

  public int getWeight() {
    return alphabet.getWeight();
  }

  public GridPoint getPoint() {
    return gridPoint;
  }



  @Override
  public boolean equals(Object object) {
    if (object instanceof GridUnit) {
      GridUnit unit = (GridUnit) object;
      return this.alphabet == unit.alphabet && this.gridPoint.equals(unit.gridPoint);
    }

    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(alphabet, gridPoint);
  }
}

// inheritance
class addImage {
  private Image img1;
  protected String imgAddr;
  public Image getImage1() {
    if (this.img1 == null) {
      this.img1 = new Image(this.imgAddr);
    }
    return this.img1;
  }
  public void setImage(String Addr, Image img){
    this.imgAddr = Addr;
    this.img1 = img;
  }
}

class changeImage extends addImage{
  Image previmdAdrr = this.getImage1();
  String newImagAddr = "images/alphabet/" + this.imgAddr + ".png";
  public void changeimage(){
    Image newImg = getImage1();
    setImage(newImagAddr, newImg);
  }
}
