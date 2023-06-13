package src.gui;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import src.commands.AllCommands.*;
import src.database.DatabaseConnection;
import src.database.UserAuthentication;
import src.localization.Language;
import src.vehicleData.*;

import java.io.IOException;
import java.util.List;

import static src.gui.Utils.showInfo;

public class MainController {
    @FXML
    private Button Button_add;

    @FXML
    private Button Button_clear;

    @FXML
    private Button Button_count;

    @FXML
    private Button Button_executeScript;

    @FXML
    private Button Button_exit;

    @FXML
    private Button Button_help;

    @FXML
    private Button Button_info;

    @FXML
    private Button Button_map;

    @FXML
    private Button Button_removeAllByType;

    @FXML
    private Button Button_removeById;

    @FXML
    private Button Button_update;

    @FXML
    private Label Label_currentUser;

    @FXML
    private MenuButton MenuButton_all;

    @FXML
    private TableColumn<Vehicle, Long> TableColumn_capacity;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_coordinates;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_creationDate;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_creator;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_enginePower;

    @FXML
    private TableColumn<Vehicle, Long> TableColumn_fuelConsumption;

    @FXML
    private TableColumn<Vehicle, Long> TableColumn_id;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_name;

    @FXML
    private TableColumn<Vehicle, String> TableColumn_type;

    @FXML
    private TableView<Vehicle> table;

    @FXML
    void initialize() {
        updateLanguage();
        setTableColumns();
        updateTable();
    }

    private void setTableColumns() {
        TableColumn_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn_type.setCellValueFactory(vehicle ->
                vehicle.getValue().getType() == null
                        ? new SimpleStringProperty("null")
                        : new SimpleStringProperty(vehicle.getValue().getType().toString()));
        TableColumn_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        TableColumn_creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        TableColumn_creator.setCellValueFactory(new PropertyValueFactory<>("creator"));
        TableColumn_fuelConsumption.setCellValueFactory(new PropertyValueFactory<>("fuelConsumption"));
        TableColumn_enginePower.setCellValueFactory(vehicle -> new SimpleStringProperty((vehicle.getValue().getEnginePower() + "").replace(".", GUI.getAppLanguage().getString("separator"))));
        TableColumn_coordinates.setCellValueFactory(vehicle -> new SimpleStringProperty((vehicle.getValue().getCoordinates().getX() + "; " + vehicle.getValue().getCoordinates().getY()).replace(".", GUI.getAppLanguage().getString("separator"))));
    }

    private void updateTable() {
        table.setItems(FXCollections.observableList(VehicleCollection.getVehicle()));
    }

    @FXML
    void setButton_add() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);

        Label label1 = new Label(GUI.getAppLanguage().getString("name") + ":");
        TextField textField1 = new TextField();
        textField1.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        Label label2 = new Label(GUI.getAppLanguage().getString("capacity") + ":");
        TextField textField2 = new TextField();
        textField2.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        Label label3 = new Label("X:");
        TextField textField3 = new TextField();
        textField3.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        Label label4 = new Label("Y:");
        TextField textField4 = new TextField();
        textField4.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label4, 2, 2);
        gridPane.add(textField4, 3, 2);

        Label label5 = new Label(GUI.getAppLanguage().getString("ep") + ":");
        TextField textField5 = new TextField();
        textField5.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label5, 0, 3);
        gridPane.add(textField5, 1, 3);

        Label label7 = new Label(GUI.getAppLanguage().getString("fc") + ":");
        TextField textField7 = new TextField();
        textField7.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label7, 0, 4);
        gridPane.add(textField7, 1, 4);

        Label label6 = new Label(GUI.getAppLanguage().getString("type") + ":");
        RadioButton radioButton1 = new RadioButton("Plane");
        radioButton1.setStyle("-fx-focus-color: #06498c");
        radioButton1.setUserData("plane");
        RadioButton radioButton2 = new RadioButton("Helicopter");
        radioButton2.setStyle("-fx-focus-color: #06498c");
        radioButton2.setUserData("helicopter");
        RadioButton radioButton3 = new RadioButton("Submarine");
        radioButton3.setStyle("-fx-focus-color: #06498c");
        radioButton3.setUserData("submarine");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setStyle("-fx-focus-color: #06498c");
        radioButton4.setUserData("null");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup1);
        radioButton2.setToggleGroup(toggleGroup1);
        radioButton3.setToggleGroup(toggleGroup1);
        radioButton4.setToggleGroup(toggleGroup1);
        gridPane.add(label6, 0, 5);
        gridPane.add(radioButton1, 1, 5);
        gridPane.add(radioButton2, 2, 5);
        gridPane.add(radioButton3, 3, 5);
        gridPane.add(radioButton4, 4, 5);
        Button button = new Button(GUI.getAppLanguage().getString("add"));
        button.setCursor(Cursor.HAND);
        button.setOnAction(event1 -> {
            textField1.setPromptText("");
            textField2.setPromptText("");
            textField3.setPromptText("");
            textField4.setPromptText("");
            textField5.setPromptText("");
            textField7.setPromptText("");
            label6.setTextFill(Color.BLACK);
            boolean error = false;
            if (textField1.getText().trim().isEmpty() | textField1.getText().contains("'")) {
                textField1.setText("");
                textField1.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            try {
                Long.parseLong(textField2.getText());
            } catch (NumberFormatException numberFormatException) {
                textField2.setText("");
                textField2.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            try {
                Double.parseDouble(textField3.getText());
            } catch (NumberFormatException numberFormatException) {
                textField3.setText("");
                textField3.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            try {
                Long y = Long.parseLong(textField4.getText());
                if (y > 746) {
                    textField4.setText("");
                    textField4.setPromptText("Y =< 746");
                    error = true;
                }
            } catch (NumberFormatException numberFormatException) {
                textField4.setText("");
                textField4.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            try {
                Double.parseDouble(textField5.getText());
            } catch (NumberFormatException numberFormatException) {
                textField5.setText("");
                textField5.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            try {
                Long.parseLong(textField7.getText());
            } catch (NumberFormatException numberFormatException) {
                textField7.setText("");
                textField7.setPromptText(GUI.getAppLanguage().getString("invalid"));
                error = true;
            }
            if (toggleGroup1.getSelectedToggle() == null) {
                label6.setTextFill(Color.RED);
                error = true;
            }
            if (error) return;
            try {
                Adder.vehicleAdderToDB(new Vehicle(
                        textField1.getText(),
                        new Coordinates(Double.parseDouble(textField3.getText()), Long.parseLong(textField4.getText())),
                        Double.parseDouble(textField5.getText()),
                        Long.parseLong(textField2.getText()),
                        Long.parseLong(textField7.getText()),
                        VehicleType.getType(toggleGroup1.getSelectedToggle().getUserData().toString())));
                button.getScene().getWindow().hide();
                updateTable();
            } catch (Exception ignored) {}
        });
        gridPane.add(button, 2, 6);
        Scene scene = new Scene(gridPane, 700, 270);
        scene.getStylesheets().add("/css/style.css");
        button.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void setButton_clear() {
        new Clear().GUI_execute();
        updateTable();
        showInfo("Result", GUI.getAppLanguage().getString("delete_ps"));
    }

    @FXML
    void setButton_count() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
        Label label6 = new Label(GUI.getAppLanguage().getString("type") + ":");
        RadioButton radioButton1 = new RadioButton("Plane");
        radioButton1.setStyle("-fx-focus-color: #06498c");
        radioButton1.setUserData("plane");
        RadioButton radioButton2 = new RadioButton("Helicopter");
        radioButton2.setStyle("-fx-focus-color: #06498c");
        radioButton2.setUserData("helicopter");
        RadioButton radioButton3 = new RadioButton("Submarine");
        radioButton3.setStyle("-fx-focus-color: #06498c");
        radioButton3.setUserData("submarine");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setStyle("-fx-focus-color: #06498c");
        radioButton4.setUserData("null");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup1);
        radioButton2.setToggleGroup(toggleGroup1);
        radioButton3.setToggleGroup(toggleGroup1);
        radioButton4.setToggleGroup(toggleGroup1);
        gridPane.add(label6, 0, 0);
        gridPane.add(radioButton1, 1, 0);
        gridPane.add(radioButton2, 2, 0);
        gridPane.add(radioButton3, 3, 0);
        gridPane.add(radioButton4, 4, 0);
        Button button = new Button(GUI.getAppLanguage().getString("count"));
        button.setCursor(Cursor.HAND);
        button.setOnAction(event1 -> {
            label6.setTextFill(Color.BLACK);
            if (toggleGroup1.getSelectedToggle() == null) {
                label6.setTextFill(Color.RED);
                return;
            }
            button.getScene().getWindow().hide();
            showInfo("Result", new CountGreaterThanType().getCountOfGreaterThanType(VehicleType.getType(toggleGroup1.getSelectedToggle().getUserData().toString())));
        });
        gridPane.add(button, 2, 1);
        Scene scene = new Scene(gridPane, 500, 80);
        scene.getStylesheets().add("/css/style.css");
        button.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void setButton_executeScript() {
        try {
            showInfo("README", GUI.getAppLanguage().getString("script_help"));
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose a script file");
            new ExecuteScript().GUI_execute(fileChooser.showOpenDialog(table.getScene().getWindow()).getAbsolutePath());
            updateTable();
        } catch (Exception ignored) {}
    }

    @FXML
    void setButton_exit() {
        Button_exit.getScene().getWindow().hide();
        VehicleCollection.getVehicle().clear();
        UserAuthentication.logOut();
        try {
            GUI.openAuthWindow(new Stage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void setButton_help() {
        showInfo("Help", GUI.getAppLanguage().getString("help"));
    }

    @FXML
    void setButton_info() {
        showInfo("Info", VehicleCollection.getInfo());
    }

    @FXML
    void setButton_map() {
        Button_map.getScene().getWindow().hide();
        GUI.openMapWindow();
    }

    @FXML
    void setButton_ru() {
        GUI.setAppLanguage(Language.ru);
        updateLanguage();
    }
    @FXML
    void setButton_hu() {
        GUI.setAppLanguage(Language.hu);
        updateLanguage();
    }
    @FXML
    void setButton_mk() {
        GUI.setAppLanguage(Language.mk);
        updateLanguage();
    }
    @FXML
    void setButton_es() {
        GUI.setAppLanguage(Language.es);
        updateLanguage();
    }

    private void updateLanguage() {
        Button_add.setText(GUI.getAppLanguage().getString("add"));
        Button_exit.setText(GUI.getAppLanguage().getString("exit"));
        Button_clear.setText(GUI.getAppLanguage().getString("clear"));
        Button_help.setText(GUI.getAppLanguage().getString("b_help"));
        Button_info.setText(GUI.getAppLanguage().getString("info"));
        Button_map.setText(GUI.getAppLanguage().getString("map"));
        Button_count.setText(GUI.getAppLanguage().getString("count"));
        Button_executeScript.setText(GUI.getAppLanguage().getString("script"));
        Button_removeAllByType.setText(GUI.getAppLanguage().getString("remove_type"));
        Button_removeById.setText(GUI.getAppLanguage().getString("remove_id"));
        Button_update.setText(GUI.getAppLanguage().getString("upd"));
        Label_currentUser.setText(GUI.getAppLanguage().getString("username") + ": " + UserAuthentication.getCurrentUser());
        TableColumn_name.setText(GUI.getAppLanguage().getString("name"));
        TableColumn_creator.setText(GUI.getAppLanguage().getString("creator"));
        TableColumn_coordinates.setText(GUI.getAppLanguage().getString("coords"));
        TableColumn_creationDate.setText(GUI.getAppLanguage().getString("creation_date"));
        TableColumn_type.setText(GUI.getAppLanguage().getString("type"));
        TableColumn_capacity.setText(GUI.getAppLanguage().getString("capacity"));
        TableColumn_enginePower.setText(GUI.getAppLanguage().getString("ep"));
        TableColumn_fuelConsumption.setText(GUI.getAppLanguage().getString("fc"));
        MenuButton_all.setText(GUI.getAppLanguage().getString("all"));
        table.refresh();
    }

    @FXML
    void setButton_removeAllByType() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);
        Label label6 = new Label(GUI.getAppLanguage().getString("type") + ":");
        RadioButton radioButton1 = new RadioButton("Plane");
        radioButton1.setStyle("-fx-focus-color: #06498c");
        radioButton1.setUserData("plane");
        RadioButton radioButton2 = new RadioButton("Helicopter");
        radioButton2.setStyle("-fx-focus-color: #06498c");
        radioButton2.setUserData("helicopter");
        RadioButton radioButton3 = new RadioButton("Submarine");
        radioButton3.setStyle("-fx-focus-color: #06498c");
        radioButton3.setUserData("submarine");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setStyle("-fx-focus-color: #06498c");
        radioButton4.setUserData("null");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup1);
        radioButton2.setToggleGroup(toggleGroup1);
        radioButton3.setToggleGroup(toggleGroup1);
        radioButton4.setToggleGroup(toggleGroup1);
        gridPane.add(label6, 0, 0);
        gridPane.add(radioButton1, 1, 0);
        gridPane.add(radioButton2, 2, 0);
        gridPane.add(radioButton3, 3, 0);
        gridPane.add(radioButton4, 4, 0);
        Button button = new Button(GUI.getAppLanguage().getString("remove_type"));
        button.setCursor(Cursor.HAND);
        button.setOnAction(event1 -> {
            label6.setTextFill(Color.BLACK);
            if (toggleGroup1.getSelectedToggle() == null) {
                label6.setTextFill(Color.RED);
                return;
            }
            button.getScene().getWindow().hide();
            updateTable();
            showInfo("Result", new RemoveAllByType().GUI_execute(VehicleType.getType(toggleGroup1.getSelectedToggle().getUserData().toString())));
        });
        gridPane.add(button, 2, 1);
        Scene scene = new Scene(gridPane, 500, 80);
        scene.getStylesheets().add("/css/style.css");
        button.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @FXML
    void setButton_removeById() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label("ID:");
        gridPane.add(label, 0, 0);
        TextField textField = new TextField();
        textField.setStyle("-fx-focus-color: #06498c");
        gridPane.add(textField, 1, 0);
        Button submit = new Button(GUI.getAppLanguage().getString("remove_id"));
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 210, 80);
        scene.getStylesheets().add("/css/style.css");
        submit.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
        submit.setOnAction(event1 -> {
            String result = new RemoveById().GUI_execute(textField.getText());
            if (result == null) {
                submit.getScene().getWindow().hide();
                updateTable();
            } else {
                textField.setText("");
                textField.setPromptText(result);
            }
        });
    }

    @FXML
    void setButton_update() {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label label = new Label("ID:");
        gridPane.add(label, 0, 0);
        TextField textField = new TextField();
        textField.setStyle("-fx-focus-color: #06498c");
        gridPane.add(textField, 1, 0);
        Button submit = new Button(GUI.getAppLanguage().getString("upd"));
        gridPane.add(submit, 1, 1);

        Scene scene = new Scene(gridPane, 210, 80);
        scene.getStylesheets().add("/css/style.css");
        submit.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
        submit.setOnAction(event1 -> {
            try {
                long id = Long.parseLong(textField.getText());
                List<Vehicle> vehicleCollection = VehicleCollection.getVehicle().stream().filter(vehicle1 -> vehicle1.getId() == id).toList();
                if (vehicleCollection.isEmpty()) {
                    textField.setText("");
                    textField.setPromptText(GUI.getAppLanguage().getString("doesnt_exist"));
                    return;
                }
                if (!vehicleCollection.get(0).getCreator().equals(UserAuthentication.getCurrentUser())) {
                    textField.setText("");
                    textField.setPromptText(GUI.getAppLanguage().getString("not_ur"));
                    return;
                }
                submit.getScene().getWindow().hide();
                updateFromGUI(id);
            } catch (NumberFormatException numberFormatException) {
                textField.setText("");
                textField.setPromptText(GUI.getAppLanguage().getString("invalid"));
            }
        });
    }

    private void updateFromGUI(long id) {
        Stage primaryStage = new Stage();
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        GridPane gridPane = new GridPane();
        gridPane.setStyle("-fx-background-color: #92A1D6");
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(20);
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setPercentWidth(20);
        ColumnConstraints column3 = new ColumnConstraints();
        column3.setPercentWidth(20);
        ColumnConstraints column4 = new ColumnConstraints();
        column4.setPercentWidth(20);
        ColumnConstraints column5 = new ColumnConstraints();
        column5.setPercentWidth(20);
        gridPane.getColumnConstraints().addAll(column1, column2, column3, column4, column5);

        Label label1 = new Label(GUI.getAppLanguage().getString("name") + ":");
        TextField textField1 = new TextField();
        textField1.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label1, 0, 0);
        gridPane.add(textField1, 1, 0);

        Label label2 = new Label(GUI.getAppLanguage().getString("capacity") + ":");
        TextField textField2 = new TextField();
        textField2.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label2, 0, 1);
        gridPane.add(textField2, 1, 1);

        Label label3 = new Label("X:");
        TextField textField3 = new TextField();
        textField3.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label3, 0, 2);
        gridPane.add(textField3, 1, 2);

        Label label4 = new Label("Y:");
        TextField textField4 = new TextField();
        textField4.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label4, 2, 2);
        gridPane.add(textField4, 3, 2);

        Label label5 = new Label(GUI.getAppLanguage().getString("ep") + ":");
        TextField textField5 = new TextField();
        textField5.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label5, 0, 3);
        gridPane.add(textField5, 1, 3);

        Label label7 = new Label(GUI.getAppLanguage().getString("fc") + ":");
        TextField textField7 = new TextField();
        textField7.setStyle("-fx-focus-color: #06498c;");
        gridPane.add(label7, 0, 4);
        gridPane.add(textField7, 1, 4);

        Label label6 = new Label(GUI.getAppLanguage().getString("type") + ":");
        RadioButton radioButton1 = new RadioButton("Plane");
        radioButton1.setStyle("-fx-focus-color: #06498c");
        radioButton1.setUserData("plane");
        RadioButton radioButton2 = new RadioButton("Helicopter");
        radioButton2.setStyle("-fx-focus-color: #06498c");
        radioButton2.setUserData("helicopter");
        RadioButton radioButton3 = new RadioButton("Submarine");
        radioButton3.setStyle("-fx-focus-color: #06498c");
        radioButton3.setUserData("submarine");
        RadioButton radioButton4 = new RadioButton("null");
        radioButton4.setStyle("-fx-focus-color: #06498c");
        radioButton4.setUserData("null");
        ToggleGroup toggleGroup1 = new ToggleGroup();
        radioButton1.setToggleGroup(toggleGroup1);
        radioButton2.setToggleGroup(toggleGroup1);
        radioButton3.setToggleGroup(toggleGroup1);
        radioButton4.setToggleGroup(toggleGroup1);
        gridPane.add(label6, 0, 5);
        gridPane.add(radioButton1, 1, 5);
        gridPane.add(radioButton2, 2, 5);
        gridPane.add(radioButton3, 3, 5);
        gridPane.add(radioButton4, 4, 5);
        Button button = new Button(GUI.getAppLanguage().getString("upd"));
        button.setCursor(Cursor.HAND);
        button.setOnAction(event1 -> {
            textField1.setPromptText("");
            textField2.setPromptText("");
            textField3.setPromptText("");
            textField4.setPromptText("");
            textField5.setPromptText("");
            textField7.setPromptText("");
            label6.setTextFill(Color.BLACK);
            boolean error = false;
            if (!textField1.getText().isEmpty()) {
                if (textField1.getText().contains("'")) {
                    textField1.setText("");
                    textField1.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                } else {
                    DatabaseConnection.executeStatement("update vehicle set name = '" + textField1.getText() + "' where id = " + id);
                }
            }
            if (!textField2.getText().isEmpty()) {
                try {
                    long capacity = Long.parseLong(textField2.getText());
                    DatabaseConnection.executeStatement("update vehicle set capacity = '" + capacity + "' where id = " + id);
                } catch (NumberFormatException numberFormatException) {
                    textField2.setText("");
                    textField2.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                }
            }
            if (!textField3.getText().isEmpty()) {
                try {
                    double x = Double.parseDouble(textField3.getText());
                    DatabaseConnection.executeStatement("update vehicle set x = '" + x + "' where id = " + id);
                } catch (NumberFormatException numberFormatException) {
                    textField3.setText("");
                    textField3.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                }
            }
            if (!textField4.getText().isEmpty()) {
                try {
                    long y = Long.parseLong(textField4.getText());
                    if (y > 746) {
                        textField4.setText("");
                        textField4.setPromptText("Y =< 746");
                        error = true;
                    } else {
                        DatabaseConnection.executeStatement("update vehicle set y = '" + y + "' where id = " + id);
                    }
                } catch (NumberFormatException numberFormatException) {
                    textField4.setText("");
                    textField4.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                }
            }
            if (!textField5.getText().isEmpty()) {
                try {
                    double ep = Double.parseDouble(textField5.getText());
                    DatabaseConnection.executeStatement("update vehicle set enginepower = '" + ep + "' where id = " + id);
                } catch (NumberFormatException numberFormatException) {
                    textField5.setText("");
                    textField5.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                }
            }
            if (!textField7.getText().isEmpty()) {
                try {
                    long fc = Long.parseLong(textField7.getText());
                    DatabaseConnection.executeStatement("update vehicle set fuelconsumption = '" + fc + "' where id = " + id);
                } catch (NumberFormatException numberFormatException) {
                    textField7.setText("");
                    textField7.setPromptText(GUI.getAppLanguage().getString("invalid"));
                    error = true;
                }
            }
            if (toggleGroup1.getSelectedToggle() != null) {
                DatabaseConnection.executeStatement("update vehicle set type = '" + toggleGroup1.getSelectedToggle().getUserData().toString() + "' where id = " + id);
            }
            if (error) return;
            VehicleCollection.updateFromDB();
            updateTable();
            button.getScene().getWindow().hide();
        });
        gridPane.add(button, 2, 6);
        Scene scene = new Scene(gridPane, 700, 270);
        scene.getStylesheets().add("/css/style.css");
        button.getStyleClass().add("submit-button");
        primaryStage.initModality(Modality.APPLICATION_MODAL);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}