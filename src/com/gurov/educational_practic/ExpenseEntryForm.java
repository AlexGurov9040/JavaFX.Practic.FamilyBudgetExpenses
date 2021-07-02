package com.gurov.educational_practic;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ExpenseEntryForm extends Application{

    //ArrayList<Expenses> expensesRecords;

    public static void main(String[] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        TextField nutritionField = new TextField();
        nutritionField.setPromptText("Питание");
        nutritionField.setMinWidth(63);

        TextField medicineField = new TextField();
        medicineField.setPromptText("Медицина");
        medicineField.setMinWidth(75);

        TextField houseField = new TextField();
        houseField.setPromptText("Дом");

        TextField transportField = new TextField();
        transportField.setPromptText("Транспорт");
        transportField.setMinWidth(75);

        TextField educationField = new TextField();
        educationField.setPromptText("Обучение");
        educationField.setMinWidth(70);

        TextField borrowField = new TextField();
        borrowField.setPromptText("Займы");
        borrowField.setMinWidth(53);

        TextField otherField = new TextField();
        otherField.setPromptText("Прочее");
        otherField.setMinWidth(57);

        ObservableList<Expenses> expenses = FXCollections.observableArrayList(
                new Expenses(0.0,0.0,0.0,0.0,0.0,0.0,0.0)
        );
        TableView<Expenses> table = new TableView<Expenses>(expenses);
        table.setPrefWidth(588);
        table.setPrefHeight(460);
        table.setPadding(new Insets(10));
        table.setTooltip(new Tooltip("Расходы"));
        //table.setTableMenuButtonVisible(true);
        //Nutrition column
        TableColumn<Expenses,Double> nutritionColumn = new TableColumn<Expenses,Double>("Питание");
        nutritionColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("nutrition"));
        table.getColumns().add(nutritionColumn);
        //Medicine column
        TableColumn<Expenses,Double> medicineColumn = new TableColumn<Expenses,Double>("Здоровье/медицина");
        medicineColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("medicine"));
        table.getColumns().add(medicineColumn);
        //House column
        TableColumn<Expenses,Double> houseColumn = new TableColumn<Expenses,Double>("Дом");
        houseColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("house"));
        table.getColumns().add(houseColumn);
        //Transport column
        TableColumn<Expenses,Double> transportColumn = new TableColumn<Expenses,Double>("Транспорт");
        transportColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("transport"));
        table.getColumns().add(transportColumn);
        //Education column
        TableColumn<Expenses,Double> educationColumn = new TableColumn<Expenses,Double>("Обучение");
        educationColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("education"));
        table.getColumns().add(educationColumn);
        //Borrow column
        TableColumn<Expenses,Double> borrowColumn = new TableColumn<Expenses,Double>("Займы");
        borrowColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("borrow"));
        table.getColumns().add(borrowColumn);
        //Other column
        TableColumn<Expenses,Double> otherColumn = new TableColumn<Expenses,Double>("Прочие расходы");
        otherColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("other"));
        table.getColumns().add(otherColumn);

        Button btCreate = new Button("Создать новый журнал");
        btCreate.setMaxWidth(400);
        HBox.setHgrow(btCreate, Priority.ALWAYS);
        btCreate.setTooltip(new Tooltip("Нажмите, чтобы очистить таблицу записей\n" +
                "и создать новый журнал записей"));

        Button btOpen = new Button("Открыть журнал");
        btOpen.setMaxWidth(400);
        HBox.setHgrow(btOpen,Priority.ALWAYS);
        btOpen.setTooltip(new Tooltip("Нажмите, чтобы открыть файл журнала\n" +
                "и отобразить его в таблице"));

        Button btSave = new Button("Сохранить");
        btSave.setMaxWidth(400);
        HBox.setHgrow(btSave,Priority.ALWAYS);
        btSave.setTooltip(new Tooltip("Нажмите, чтобы сохранить все записи\n" +
                "из таблицы в файл"));

        //RadioButtons group
        RadioButton nutritionBtn = new RadioButton("Питание");
        RadioButton medicineBtn = new RadioButton("Медицина");
        RadioButton houseBtn = new RadioButton("Дом");
        RadioButton transportBtn = new RadioButton("Транспорт");
        RadioButton educationBtn = new RadioButton("Обучение");
        RadioButton borrowBtn = new RadioButton("Займы");
        RadioButton otherBtn = new RadioButton("Прочие расходы");
        ToggleGroup radioButtonsGroup = new ToggleGroup();
        nutritionBtn.setToggleGroup(radioButtonsGroup);
        medicineBtn.setToggleGroup(radioButtonsGroup);
        houseBtn.setToggleGroup(radioButtonsGroup);
        transportBtn.setToggleGroup(radioButtonsGroup);
        educationBtn.setToggleGroup(radioButtonsGroup);
        borrowBtn.setToggleGroup(radioButtonsGroup);
        otherBtn.setToggleGroup(radioButtonsGroup);

        Button btSort = new Button("Сортировать записи");
        btSort.setMinWidth(90);
        btSort.setMaxWidth(264);
        btSort.setMaxHeight(44);
        btSort.setWrapText(true);
        HBox.setHgrow(btSort,Priority.ALWAYS);
        btSort.setTooltip(new Tooltip("Нажмите, чтобы отсортировать записи в таблице"));

        Button btChange = new Button("Изменить запись");
        btChange.setMinWidth(80);
        btChange.setMaxWidth(264);
        btChange.setMaxHeight(44);
        btChange.setWrapText(true);
        HBox.setHgrow(btChange,Priority.ALWAYS);
        btChange.setTooltip(new Tooltip("Нажмите, чтобы изменить выделенную в таблице запись"));

        Button btDelete = new Button("Удалить запись");
        btDelete.setMinWidth(80);
        btDelete.setMaxWidth(264);
        btDelete.setMaxHeight(44);
        btDelete.setWrapText(true);
        HBox.setHgrow(btDelete,Priority.ALWAYS);
        btDelete.setTooltip(new Tooltip("Нажмите, чтобы удалить выделенную в таблице запись"));

        Button btAdded = new Button("Добавить запись");
        btAdded.setMinWidth(80);
        btAdded.setMaxWidth(120);
        btAdded.setMinHeight(44);
        btAdded.setMaxHeight(44);
        btAdded.setWrapText(true);
        btAdded.setTooltip(new Tooltip("Нажмите, чтобы добавить запись в таблицу"));
        btAdded.setOnAction(actionEvent -> {
            /**
             *
             */
        });

        HBox buttonRadioButtonsHBox = new HBox(10);
        buttonRadioButtonsHBox.setAlignment(Pos.TOP_CENTER);

        HBox buttonHBox = new HBox(10,btCreate,btOpen,btSave);
        buttonHBox.setAlignment(Pos.TOP_CENTER);
        buttonHBox.setPadding(new Insets(0,0,10,0));

        FlowPane radioButtons = new FlowPane(10,10,nutritionBtn,medicineBtn,houseBtn,
                transportBtn,educationBtn,borrowBtn,otherBtn);

        buttonRadioButtonsHBox.getChildren().addAll(radioButtons,btSort,btChange,btDelete);
        buttonRadioButtonsHBox.setPadding(new Insets(0,0,10,0));

        HBox textButtonHBox = new HBox(10,nutritionField,medicineField,houseField,transportField,
                educationField,borrowField,otherField,btAdded);
        textButtonHBox.setPadding(new Insets(0,0,10,0));
        textButtonHBox.setAlignment(Pos.CENTER);

        HBox tableHBox = new HBox(table);
        tableHBox.setMaxHeight(Double.MAX_VALUE);
        tableHBox.setAlignment(Pos.BOTTOM_CENTER);

        VBox root = new VBox(buttonHBox,buttonRadioButtonsHBox,textButtonHBox,tableHBox);
        root.setPadding(new Insets(10));
        VBox.setVgrow(tableHBox,Priority.ALWAYS);

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Учет расходов семейного бюджета");
        stage.setHeight(600);
        stage.setMinHeight(400);
        stage.setWidth(800);
        stage.setMinWidth(624);
        stage.show();
    }
}
