package org.example;

import java.sql.SQLException;
import java.util.List;

public interface Interface {
    boolean addStudent(Student student) throws ClassNotFoundException, SQLException;
    boolean addGrade(Grade grade) throws ClassNotFoundException, SQLException;
    boolean addSubject(Subject subject) throws ClassNotFoundException, SQLException;

    void updateGrade(int id, int grade) throws ClassNotFoundException, SQLException;

    void showStudent() throws ClassNotFoundException, SQLException;
    void showSubject() throws ClassNotFoundException, SQLException;
    void showGrade() throws ClassNotFoundException, SQLException;

    void averageStudent() throws ClassNotFoundException, SQLException;

    void bestStudent() throws ClassNotFoundException, SQLException;

    void badGrade() throws ClassNotFoundException, SQLException;

}
