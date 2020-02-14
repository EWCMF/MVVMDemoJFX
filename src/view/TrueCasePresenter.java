package view;

import model.Model;
import java.util.Observable;
import java.util.Observer;

public class TrueCasePresenter extends Observable {
    private String string;
    private Model model;

    public TrueCasePresenter(Model model) {
        this.model = model;
        observeModel(this.model);
        string = this.model.getInput().toLowerCase();
    }

    private void observeModel(Model model) {
        model.addObserver(new Observer() {
            @Override
            public void update(Observable o, Object arg) {
                if (o instanceof Model) {
                    String data = ((Model) o).getInput();
                    string = data;

                    TrueCasePresenter.super.setChanged();
                    TrueCasePresenter.super.notifyObservers();
                }
            }
        });
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        model.setInput(string);
    }
}
