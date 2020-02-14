package view.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import persistence.SQLiteDBJava;
import view.TrueCasePresenter;

import java.util.Observable;
import java.util.Observer;

public class MVVMDemoJFXController {
    @FXML private TextField textField;
    @FXML private Label label;
    @FXML private Button button;
    SQLiteDBJava db;
    TrueCasePresenter trueCasePresenter;


    public void initialize() {
        db = new SQLiteDBJava(null);
        trueCasePresenter = new TrueCasePresenter(db.getModel());

        label.setText(trueCasePresenter.getString());
        button.setOnAction(event -> changeText());

        trueCasePresenter.addObserver(new Observer() {
            @Override
            public void update(Observable observable, Object o) {
                if (observable instanceof TrueCasePresenter) {
                    String string = ((TrueCasePresenter) observable).getString();
                    label.setText(string);
                }
            }
        });
    }

    public void changeText() {
        trueCasePresenter.setString(textField.getText());
    }
}
