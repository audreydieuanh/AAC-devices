import java.lang.String;

import structures.KeyNotFoundException;
import structures.AssociativeArray;

/**
 * Represent a single category of items in the AAC
 *
 * @author Audrey Trinh
 */

public class AACCategory {
  String categoryName;
  AssociativeArray<String, String> imageLocToText;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new empty category with the given name
   */
  public AACCategory(String name) {
    this.categoryName = name;
    this.imageLocToText = new AssociativeArray<>();
  }

  /**
   * Adds the mapping of imageLoc to the text to the category
   */
  public void addItem(String imageLoc, String text) {
    this.imageLocToText.set(imageLoc, text);
  }

  /**
   * Returns an array of all the imageLocs in the category
   */
  public String[] getImages() {
    return this.imageLocToText.getAllKeys();
  } // getImageLocs()

  /**
   * Returns the text associated with the imageLoc
   */
  public String getText(String imageLoc) {
    try {
      return this.imageLocToText.get(imageLoc);
    } catch (KeyNotFoundException e) {
      return "Exception: could not find imageLoc " + imageLoc + " in category " + this.categoryName;
    }
  }

  /**
   * Returns the name of the category
   */
  public String getCategory() {
    return this.categoryName;
  }

  /**
   * Determines if the provided image is stored in the category
   *
   * @param imageLoc
   * @return
   */
  public boolean hasImage(String imageLoc) {
    return this.imageLocToText.hasKey(imageLoc);
  }

}
