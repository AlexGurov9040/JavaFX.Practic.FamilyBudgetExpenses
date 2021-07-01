package com.gurov.educational_practic;
import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ExpenseEntryForm extends Application{

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Button btCreate = new Button("Создать новый журнал");
        Button btOpen = new Button("Открыть журнал");
        Button btSave = new Button("Сохранить");
        Button btSort = new Button("Сортировать записи");
        Button btChange = new Button("Изменить запись");
    }
}
