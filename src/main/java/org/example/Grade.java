package org.example;

public class Grade {
    int id;
    int idStudent;
    int idSubject;
    int grade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdSubject() {
        return idSubject;
    }

    public void setIdSubject(int idSubject) {
        this.idSubject = idSubject;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Grade(int id, int idStudent, int idSubject, int grade) {
        this.id = id;
        this.idStudent = idStudent;
        this.idSubject = idSubject;
        this.grade = grade;
    }
}
