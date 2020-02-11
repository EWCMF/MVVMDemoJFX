package view;

import model.Model;
import persistence.SQLiteDBJava;

import java.util.Observable;
import java.util.Observer;

public class TrueCasePresenter implements Observer {
    private String string;

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Model) {
            string = ((Model) o).getInput();
        }
    }

    public String getString() {
        return string;
    }
}
