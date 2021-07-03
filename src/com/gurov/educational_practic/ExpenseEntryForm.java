package com.gurov.educational_practic;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseEntryForm extends Application{

    String fileName = "Expenses.dat";

    public static void main(String[] args) {

        Application.launch(args);
    }
    @SuppressWarnings("unchecked")
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

        Button btDelete = new Button("Удалить запись");

        ObservableList<Expenses> expenses = FXCollections.observableArrayList();

        TableView<Expenses> table = new TableView<Expenses>(expenses);
        table.setPrefWidth(588);
        table.setPrefHeight(460);
        table.setPadding(new Insets(10));
        table.setTooltip(new Tooltip("Расходы"));
        table.setTableMenuButtonVisible(true);

        //Nutrition column
        TableColumn<Expenses,Double> nutritionColumn = new TableColumn<Expenses,Double>("Питание");
        nutritionColumn.setPrefWidth(60);
        nutritionColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("nutrition"));
        table.getColumns().add(nutritionColumn);
        //Medicine column
        TableColumn<Expenses,Double> medicineColumn = new TableColumn<Expenses,Double>("Здоровье/медицина");
        medicineColumn.setPrefWidth(135);
        medicineColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("medicine"));
        table.getColumns().add(medicineColumn);
        //House column
        TableColumn<Expenses,Double> houseColumn = new TableColumn<Expenses,Double>("Дом");
        houseColumn.setPrefWidth(48);
        houseColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("house"));
        table.getColumns().add(houseColumn);
        //Transport column
        TableColumn<Expenses,Double> transportColumn = new TableColumn<Expenses,Double>("Транспорт");
        transportColumn.setPrefWidth(75);
        transportColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("transport"));
        table.getColumns().add(transportColumn);
        //Education column
        TableColumn<Expenses,Double> educationColumn = new TableColumn<Expenses,Double>("Обучение");
        educationColumn.setPrefWidth(70);
        educationColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("education"));
        table.getColumns().add(educationColumn);
        //Borrow column
        TableColumn<Expenses,Double> borrowColumn = new TableColumn<Expenses,Double>("Займы");
        borrowColumn.setPrefWidth(55);
        borrowColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("borrow"));
        table.getColumns().add(borrowColumn);
        //Other column
        TableColumn<Expenses,Double> otherColumn = new TableColumn<Expenses,Double>("Прочие расходы");
        otherColumn.setPrefWidth(125);
        otherColumn.setCellValueFactory(new PropertyValueFactory<Expenses,Double>("other"));
        table.getColumns().add(otherColumn);

        table.setOnMouseClicked(mouseEvent -> {
            if (expenses.size() != 0)
                btDelete.setDisable(false);
            else
                btDelete.setDisable(true);
        });

        Button btCreate = new Button("Создать новый журнал");
        btCreate.setMaxWidth(400);
        HBox.setHgrow(btCreate, Priority.ALWAYS);
        btCreate.setTooltip(new Tooltip("Нажмите, чтобы очистить таблицу записей\n" +
                "и создать новый журнал записей"));
        btCreate.setOnAction(actionEvent -> {
            expenses.clear();
            btDelete.setDisable(true);
        });

        Button btOpen = new Button("Открыть журнал");
        btOpen.setMaxWidth(400);
        HBox.setHgrow(btOpen,Priority.ALWAYS);
        btOpen.setTooltip(new Tooltip("Нажмите, чтобы открыть файл журнала\n" +
                "и отобразить его в таблице"));
        btOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                expenses.clear();
                Node source = (Node) actionEvent.getSource();
                Stage primaryStage = (Stage) source.getScene().getWindow();
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Текстовые файлы (*.txt)","*.txt");
                fileChooser.getExtensionFilters().add(txtFilter);
                fileChooser.getExtensionFilters().addAll(txtFilter);
                fileChooser.setTitle("Выбор файла журнала");
                File file = fileChooser.showOpenDialog(primaryStage);
                try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"cp1251"))){
                    String string = bufferedReader.readLine();
                    while(string != null) {
                        String[] values = string.split(" ");
                        if (values.length == 7){
                            expenses.add(new Expenses(Double.parseDouble(values[0]),Double.parseDouble(values[1]),
                                    Double.parseDouble(values[2]),Double.parseDouble(values[3]),
                                    Double.parseDouble(values[4]),Double.parseDouble(values[5]),Double.parseDouble(values[6])));
                        }
                        string = bufferedReader.readLine();
                    }
                }
                catch (FileNotFoundException e){}
                catch (IOException e){}

                /*ObservableList<Expenses> newExpenses = FXCollections.observableArrayList();
                try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))){
                    newExpenses = ((ObservableList<Expenses>) objectInputStream.readObject());
                    //expenses = newExpenses;
                    for (Expenses item : newExpenses){
                        expenses.add(new Expenses(item.getNutrition(),item.getMedicine(),item.getHouse(),item.getTransport(),
                                item.getEducation(),item.getBorrow(),item.getOther()));
                    }
                }
                catch (Exception ex){
                    ex.getMessage();
                }*/
            }
        });

        Button btSave = new Button("Сохранить");
        btSave.setMaxWidth(400);
        HBox.setHgrow(btSave,Priority.ALWAYS);
        btSave.setTooltip(new Tooltip("Нажмите, чтобы сохранить все записи\n" +
                "из таблицы в файл"));
        btSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName))){
                    objectOutputStream.writeObject(expenses);
                }
                catch (Exception ex){
                    ex.getMessage();
                }
            }
        });

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
        btSort.setOnAction(actionEvent -> {
            RadioButton selectionRadioButton = (RadioButton) radioButtonsGroup.getSelectedToggle();
            switch (selectionRadioButton.getText()){
                case "Питание":
                    expenses.sort(new ExpensesNutritionComparator());
                    break;
                case "Медицина":
                    expenses.sort(new ExpensesMedicineComparator());
                    break;
                case "Дом":
                    expenses.sort(new ExpensesHouseComparator());
                    break;
                case "Транспорт":
                    expenses.sort(new ExpensesTransportComparator());
                    break;
                case "Обучение":
                    expenses.sort(new ExpensesEducationComparator());
                    break;
                case "Займы":
                    expenses.sort(new ExpensesBorrowComparator());
                    break;
                case "Прочие расходы":
                    expenses.sort(new ExpensesOtherComparator());
                    break;
            }
        });

        Button btChange = new Button("Изменить запись");
        btChange.setMinWidth(80);
        btChange.setMaxWidth(264);
        btChange.setMaxHeight(44);
        btChange.setWrapText(true);
        HBox.setHgrow(btChange,Priority.ALWAYS);
        btChange.setTooltip(new Tooltip("Нажмите, чтобы изменить выделенную в таблице запись"));
        btChange.setOnAction(actionEvent -> {
            int selectIndex = table.getSelectionModel().getSelectedIndex();
            expenses.set(selectIndex,new Expenses(Double.parseDouble(nutritionField.getText()),Double.parseDouble(medicineField.getText()),
                    Double.parseDouble(houseField.getText()),Double.parseDouble(transportField.getText()),
                    Double.parseDouble(educationField.getText()),Double.parseDouble(borrowField.getText()),Double.parseDouble(otherField.getText())));
        });

        btDelete.setMinWidth(80);/**кнопка удалить*/
        btDelete.setMaxWidth(264);
        btDelete.setMaxHeight(44);
        btDelete.setWrapText(true);
        btDelete.setDisable(true);
        HBox.setHgrow(btDelete,Priority.ALWAYS);
        btDelete.setTooltip(new Tooltip("Нажмите, чтобы удалить выделенную в таблице запись"));
        btDelete.setOnAction(actionEvent -> {
            int selectIndex = table.getSelectionModel().getSelectedIndex();
            table.getItems().remove(selectIndex);
            if (expenses.size() == 0)
                btDelete.setDisable(true);
        });

        Button btAdded = new Button("Добавить запись");
        btAdded.setMinWidth(80);
        btAdded.setMaxWidth(120);
        btAdded.setMinHeight(44);
        btAdded.setMaxHeight(44);
        btAdded.setWrapText(true);
        btAdded.setTooltip(new Tooltip("Нажмите, чтобы добавить запись в таблицу"));
        btAdded.setOnAction(actionEvent -> {
            expenses.add(new Expenses(Double.parseDouble(nutritionField.getText()),Double.parseDouble(medicineField.getText()),
                    Double.parseDouble(houseField.getText()),Double.parseDouble(transportField.getText()),
                    Double.parseDouble(educationField.getText()),Double.parseDouble(borrowField.getText()),Double.parseDouble(otherField.getText())));
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
