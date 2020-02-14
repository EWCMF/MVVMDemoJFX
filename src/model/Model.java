package model;
import java.util.Observable;

public class Model extends Observable {
    private String input = "";

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        if (input != null) {
            this.input = input;
            super.setChanged();
            super.notifyObservers();
        }
    }
}
