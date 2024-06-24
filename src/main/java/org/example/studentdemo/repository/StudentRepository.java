package org.example.studentdemo.repository;

import org.example.studentdemo.entity.Students;

import java.sql.*;
import java.util.ArrayList;

public class StudentRepository {

    private final String MYSQL_CONNECTION_STRING = "jdbc:mysql://localhost:3306/testjavafx01";
    private final String MYSQL_USERNAME = "root";
    private final String MYSQL_PASSWORD = "";

    public void save(Students student){
        try{
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String strSql = "INSERT INTO students (code,fullName,phone,email)  VALUES (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(strSql);
            ps.setString(1,student.getStudentCode());
            ps.setString(2,student.getStudentName());
            ps.setString(3,student.getStudentPhone());
            ps.setString(4,student.getStudentEmail());
            ps.executeUpdate();
            connection.close();
        }catch (SQLException e){
                e.printStackTrace();
        }
        System.out.println(student.getStudentCode());
        System.out.println(student.getStudentName());
        System.out.println(student.getStudentPhone());
        System.out.println(student.getStudentEmail());

    }
    public Students update(Students student){
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "UPDATE student SET code= ?,fullName= ?,phone= ? , email = ? where id= ?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getStudentCode());
            preparedStatement.setString(2, student.getStudentName());
            preparedStatement.setString(3, student.getStudentPhone());
            preparedStatement.setString(4, student.getStudentEmail());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return student;

    }
    public void delete(Students student){
        try(Connection conn =DriverManager.getConnection(MYSQL_CONNECTION_STRING,MYSQL_USERNAME,MYSQL_PASSWORD)){
            String sql = "Update student set status = -1 where code=?";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, student.getStudentCode());
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<Students> findAll(){
        ArrayList<Students> students = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(MYSQL_CONNECTION_STRING, MYSQL_USERNAME, MYSQL_PASSWORD);
            String prSql = "SELECT * FROM students";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(prSql);
            while (resultSet.next()) {
                String code = resultSet.getString("code");
                String fullName = resultSet.getString("fullName");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("email");
                Students obj = new Students();
                obj.setStudentCode(code);
                obj.setStudentName(fullName);
                obj.setStudentPhone(phone);
                obj.setStudentEmail(email);
                students.add(obj);
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

}
