package org.example.studentdemo.studentController;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.studentdemo.entity.Students;
import org.example.studentdemo.repository.StudentRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentForm implements Initializable {

    public TextField studentCode;
    public TextField studentName;
    public TextField studentPhone;
    public TextField studentEmail;
    public StudentRepository repository = new StudentRepository();
    private ObservableList<Students> studentsList;

    public TableView<Students> tableView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

//        ObservableList<Students> data = FXCollections.observableArrayList();


//        idColumn.setCellValueFactory(new PropertyValueFactory<>("studentCode"));

//        nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));

//        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));

//        emailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));

//        data.addAll(repository.findAll());
//        for (Students student : data) {
//            idColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
//            nameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
//            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("studentPhone"));
//            emailColumn.setCellValueFactory(new PropertyValueFactory<>("studentEmail"));
//            tableView.getItems().add(student);
//            System.out.println(student.getStudentCode());
//        }
//        tableView.setItems(data);
        ObservableList<Students> studentList = FXCollections.observableArrayList();
        studentsList = FXCollections.observableArrayList(studentList);
        TableColumn<Students, String> idColumn = new TableColumn<>("code");
        idColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("code"));
        TableColumn<Students, String> nameColumn = new TableColumn<>("name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("fullName"));
        TableColumn<Students, String> phoneColumn = new TableColumn<>("phone");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("phone"));
        TableColumn<Students, String> emailColumn = new TableColumn<>("email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<Students, String>("email"));

        tableView = new TableView<>();
        studentList.addAll(repository.findAll());
        tableView.setItems(studentsList);

        tableView.getColumns().addAll(idColumn, nameColumn, phoneColumn, emailColumn);
        tableView.setEditable(true);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        Stage window = new Stage();
        window.setTitle("Student Form");
        Scene scene = new Scene(tableView, 800, 600);
        window.setScene(scene);
        window.show();
    }
    public void doSomething(ActionEvent actionEvent) {
        Students students = new Students();
        students.setStudentCode(studentCode.getText());
        students.setStudentName(studentName.getText());
        students.setStudentPhone(studentPhone.getText());
        students.setStudentEmail(studentEmail.getText());
        repository.save(students);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Action Successful");
        alert.setHeaderText("Action Saved!!!");
        alert.setContentText(repository.toString());
        alert.show();
    }


    public void clear(ActionEvent actionEvent) {
        studentCode.clear();
        studentName.clear();
        studentPhone.clear();
        studentEmail.clear();
    }

    public void update(ActionEvent actionEvent) {
        Students students = new Students();
        students.setStudentCode(studentCode.getText());
        students.setStudentName(studentName.getText());
        students.setStudentPhone(studentPhone.getText());
        students.setStudentEmail(studentEmail.getText());
        repository.update(students);
    }

    public void delete(ActionEvent actionEvent) {
        Students students = new Students();

    }

    public void show(ActionEvent actionEvent) {

    }
}

