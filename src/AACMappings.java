import java.util.Arrays;
import java.util.Scanner;

import java.io.File;
import java.io.PrintWriter;
import java.lang.Exception;

import java.lang.String;

import structures.AssociativeArray;
import structures.KeyNotFoundException;

import java.io.FileNotFoundException;
import java.io.FileWriter;

/**
 * Keep track of the complete set of AAC mappings.
 *
 * @author: Audrey Trinh
 */

public class AACMappings {
  String filename;
  AACCategory currentCategory;
  AACCategory topLevelCategory;
  AssociativeArray<String, AACCategory> categories;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a new AACMappings object.
   */
  public AACMappings(String filename) {
    this.filename = filename;
    // maps category names to categories
    this.categories = new AssociativeArray<String, AACCategory>();

    // default category
    this.topLevelCategory = new AACCategory("");
    this.currentCategory = this.topLevelCategory;
    this.categories.set("", this.currentCategory);

    try {
      File file = new File(filename);
      Scanner scanner = new Scanner(file);
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] imageText = line.split(" ");
        String imageLoc = imageText[0];
        String text = imageText[1];
        // Category
        if (!line.startsWith(">")) {
          this.currentCategory = new AACCategory(text);
          this.topLevelCategory.addItem(imageLoc, text);
          this.categories.set(imageLoc, currentCategory);

        } else {
          imageLoc = imageLoc.substring(1, imageLoc.length());
          this.currentCategory.addItem(imageLoc,
                  String.join(" ", Arrays.copyOfRange(imageText, 1, imageText.length)));
        }
      } // while
      scanner.close();
      reset();
    } catch (FileNotFoundException e) {
      System.err.println("Error: File not found");
      e.printStackTrace();
    }
  } // AACMappings(String)

  // +---------+------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Adds the mapping to the current category (or the default category if that is
   * the current category)
   */
  public void add(String imageLoc, String text) {
    this.currentCategory.addItem(imageLoc, text);
  }

  /**
   * Resets the current category of the AAC back to the default category
   */
  public void reset() {
    try {
      this.currentCategory = this.categories.get("");
    } catch (KeyNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Gets the current category
   */
  public String getCurrentCategory() {
    return this.currentCategory.getCategory();
  } // getCurrentCategory()

  /**
   * Given the image location selected,
   * it determines the associated text with the
   * image.
   */
  public String getText(String imageLoc) {
    if (isCategory(imageLoc)) {
      try {
        AACCategory category = this.categories.get(imageLoc);
        this.currentCategory = category;
        return category.getCategory();
      } catch (KeyNotFoundException e) {
        e.printStackTrace();
        return "Error: category not found";
      }
    } else {
      return this.currentCategory.getText(imageLoc);
    }
  }

  /**
   * Provides an array of all the images in the current category
   */
  public String[] getImageLocs() {
    return this.currentCategory.getImages();
  } // getImageLocs

  /**
   * Determines if the image represents a category or text to speak
   */
  public boolean isCategory(String imageLoc) {
    return this.categories.hasKey(imageLoc);
  } // isCategory(String)

  /**
   * Writes the ACC mappings stored to a file.
   */
  public void writeToFile(String filename) {
    try {
      PrintWriter pen = new PrintWriter(new FileWriter(filename, true));

      for (String category : this.categories.getAllKeys()) {
        if (category.equals("")) {
          continue;
        }
        AACCategory cat = this.categories.get(category);
        pen.write(category + " " + cat.getCategory() + "\n");

        for (String imageLoc : cat.getImages()) {
          pen.write(">" + imageLoc + " " + cat.getText(imageLoc) + "\n");
        }
      }
      pen.close();
    } catch (Exception e) {
      System.out.println("Error writing to file");
      e.printStackTrace();
    }
  }

}
