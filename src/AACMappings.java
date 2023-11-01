import structures.AssociativeArray;

public class AACMappings {
    AACCategory currentCategory;
    AssociativeArray<String, AACCategory> filenameToCtg;

    public AACMappings(String filename) {
        this.currentCategory = new AACCategory(filename);
        this.filenameToCtg = new AssociativeArray<>();
    }

    public void add(String imageLoc, String text) throws Exception {
        this.currentCategory.addItem(imageLoc, text);
    }

    public String[] getImageLocs() {
        return this.currentCategory.getImageLocs();
    } // getImageLocs()

    public String getText(String imageLoc) throws Exception {
        if (getCurrentCategory().equals("")) {
            return filenameToCtg.get(imageLoc).getCurrentCategory();
        } else {
            return this.currentCategory.getText(imageLoc);
        } // STUB
    }

    public String getCurrentCategory() {
        return this.currentCategory.getCurrentCategory();
    }

    public boolean isCategory(String imageLoc) {
        return true; // STUB
    }

    public void reset() {
        this.currentCategory = new AACCategory("");
    }

    public void writeToFile(String filename) {

    }
    
}
