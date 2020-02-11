package view.javafx;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Model;
import persistence.SQLiteDBJava;
import view.TrueCasePresenter;

public class MVVMDemoJFXController {
    @FXML private TextField textField;
    @FXML private Label label;
    @FXML private Button button;
    SQLiteDBJava db;
    Model model;
    TrueCasePresenter trueCasePresenter;


    public void initialize() {
        model = new Model();
        trueCasePresenter = new TrueCasePresenter();
        model.addObserver(trueCasePresenter);

        db = new SQLiteDBJava();

        label.setText(db.read());
        button.setOnAction(event -> changeText());
    }

    public void changeText() {
        model.setInput(textField.getText());
        label.setText(trueCasePresenter.getString());
        db.update(trueCasePresenter.getString());
    }
}
