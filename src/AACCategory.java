import java.lang.String;

import structures.AssociativeArray;

public class AACCategory {
    String categoryName;
    AssociativeArray<String, String> imageLocToText;

    public AACCategory(String name) {
        this.categoryName = name;
        this.imageLocToText = new AssociativeArray<>();
    }

    public void addItem(String imageLoc, String text) throws Exception {
        this.imageLocToText.set(imageLoc, text);
    }

   public String[] getImageLocs() {
        return this.imageLocToText.getAllKeys();
    } // getImageLocs()

    public String getText(String imageLoc) throws Exception {
        return this.imageLocToText.get(imageLoc); 
    }

    public String getCurrentCategory() {
        return this.categoryName;
    }

    public boolean hasImage(String imageLoc) {
        return this.imageLocToText.hasKey(imageLoc);
    }
    
}
