package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActionImpl implements Interface {

    @Override
    public boolean addStudent(Student student) throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête SQL pour insérer un nouvel étudiant
        String insertionQuery = "INSERT INTO student (firstName, lastName) VALUES (?, ?)";

        // Préparer une déclaration pour l'insertion
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);

        // Attribuer les valeurs de l'étudiant à la déclaration préparée
        preparedStatement.setString(1, student.getFirstName());
        preparedStatement.setString(2, student.getLastName());

        // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    }

    @Override
    public boolean addGrade(Grade grade) throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête SQL pour insérer une nouvelle note
        String insertionQuery = "INSERT INTO grade (id_student, id_subject,grade) VALUES (?, ?, ?)";

        // Préparer une déclaration pour l'insertion
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);

        // Attribuer les valeurs de l'étudiant à la déclaration préparée
        preparedStatement.setInt(1, grade.getIdStudent());
        preparedStatement.setInt(2, grade.getIdSubject());
        preparedStatement.setInt(3, grade.getGrade());

        // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    }

    @Override
    public boolean addSubject(Subject subject) throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête SQL pour insérer une nouvelle matière
        String insertionQuery = "INSERT INTO subject (name, factor) VALUES (?, ?)";

        // Préparer une déclaration pour l'insertion
        PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);

        // Attribuer les valeurs de l'étudiant à la déclaration préparée
        preparedStatement.setString(1, subject.getName());
        preparedStatement.setInt(2, subject.getFactor());

        // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
        int rowsAffected = preparedStatement.executeUpdate();

        return rowsAffected > 0;
    }

    @Override
    public void updateGrade(int id, int grade) throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig gradeDBConfig = new DBconfig();
        Connection connection = gradeDBConfig.connection();

        // Requête SQL pour mettre à jour la note
        String updateQuery = "UPDATE grade SET grade = ? WHERE id = ?";

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
        preparedStatement.setInt(1, grade);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();

        // Fermer la déclaration et la connexion pour éviter les fuites de mémoire
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void showStudent() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from student");
        ResultSet resultSet = preparedStatement.executeQuery();

        // Parcourir les résultats et créer des objets étudiants pour chaque enregistrement
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            System.out.println(id+" - "+firstName+" "+lastName); // Affichage de chaque étudiant récupéré
        }
    }

    @Override
    public void showSubject() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from subject");
        ResultSet resultSet = preparedStatement.executeQuery();

        // Parcourir les résultats et créer des objets étudiants pour chaque enregistrement
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int factor = resultSet.getInt("factor");
            System.out.println(id+" - "+name+" "+factor); // Affichage de chaque matière récupéré
        }
    }

    @Override
    public void showGrade() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from grade");
        ResultSet resultSet = preparedStatement.executeQuery();

        // Parcourir les résultats et créer des objets étudiants pour chaque enregistrement
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int id_subject = resultSet.getInt("id_subject");
            int id_student = resultSet.getInt("id_student");
            int grade = resultSet.getInt("grade");

            // Requête + Préparation + Exécution
            PreparedStatement preparedSub = connection.prepareStatement("SELECT * from subject WHERE id = ?");
            preparedSub.setInt(1, id_subject);
            ResultSet resultsub = preparedSub.executeQuery();
            ArrayList<Subject> subjects = new ArrayList<>();
            while (resultsub.next()) {
                int idsub = resultsub.getInt("id");
                String name = resultsub.getString("name");
                int factor = resultsub.getInt("factor");
                subjects.add(new Subject(idsub, name, factor));
            }

            // Requête + Préparation + Exécution
            PreparedStatement preparedStud = connection.prepareStatement("SELECT * from student WHERE id = ?");
            preparedStud.setInt(1, id_student);
            ResultSet resultstud = preparedStud.executeQuery();
            ArrayList<Student> students = new ArrayList<>();
            while (resultstud.next()) {
                int idstud = resultstud.getInt("id");
                String firstName = resultstud.getString("firstName");
                String  lastName = resultstud.getString("lastName");
                students.add(new Student(firstName, lastName, idstud));
            }
            for (Subject sub : subjects) {
                for (Student stud : students) {
                    System.out.println(id + " - " + stud.getFirstName() + " " + stud.getLastName() + " - " + sub.getName() + " - " + grade); // Affichage de chaque matière récupéré
                }
            }
        }
    }

    @Override
    public void averageStudent() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementsub = connection.prepareStatement("SELECT * from subject");
        ResultSet resultSub = preparedStatementsub.executeQuery();
        //Création de la liste subjects
        ArrayList<Subject> subjects = new ArrayList<>();
        while (resultSub.next()) {
            int id = resultSub.getInt("id");
            String name = resultSub.getString("name");
            int factor = resultSub.getInt("factor");
            subjects.add(new Subject(id, name, factor));
        }

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementstud = connection.prepareStatement("SELECT * from student");
        ResultSet resultstud = preparedStatementstud.executeQuery();
        //Création de la liste students
        ArrayList<Student> students = new ArrayList<>();
        while (resultstud.next()) {
            int id = resultstud.getInt("id");
            String firstName = resultstud.getString("firstName");
            String  lastName = resultstud.getString("lastName");
            students.add(new Student(firstName, lastName, id));
        }

        // Parcourir les résultats pour faire les moyennes
        for (Subject sub : subjects) {
            System.out.println(sub.getName() + " :"); // Affichage de la matière
            for (Student stud : students) {
                // Requête SQL pour recup les notes
                String insertionQuery = "SELECT * FROM grade WHERE id_student = ? AND id_subject = ?";
                // Préparer une déclaration pour l'insertion
                PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
                // Attribuer les valeurs de l'étudiant à la déclaration préparée
                preparedStatement.setInt(1, stud.getId());
                preparedStatement.setInt(2, sub.getId());

                // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
                ResultSet resultnote = preparedStatement.executeQuery();

                int totonote = 0;
                int i = 0;
                while (resultnote.next()) {
                    totonote = totonote + resultnote.getInt("grade");
                    i++;
                }
                int average = totonote / i;
                // Affichage des moyennes
                System.out.println("- " + stud.getFirstName() + " " + stud.getLastName() + " à une moyenne de " + average);
            }
        }
    }

    @Override
    public void bestStudent() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementsub = connection.prepareStatement("SELECT * from subject");
        ResultSet resultSub = preparedStatementsub.executeQuery();
        //Création de la liste subjects
        ArrayList<Subject> subjects = new ArrayList<>();
        while (resultSub.next()) {
            int id = resultSub.getInt("id");
            String name = resultSub.getString("name");
            int factor = resultSub.getInt("factor");
            subjects.add(new Subject(id, name, factor));
        }

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementstud = connection.prepareStatement("SELECT * from student");
        ResultSet resultstud = preparedStatementstud.executeQuery();
        //Création de la liste students
        ArrayList<Student> students = new ArrayList<>();
        while (resultstud.next()) {
            int id = resultstud.getInt("id");
            String firstName = resultstud.getString("firstName");
            String  lastName = resultstud.getString("lastName");
            students.add(new Student(firstName, lastName, id));
        }

        // Parcourir les résultats pour faire les moyennes
        for (Subject sub : subjects) {
            String eleve = "";
            int bestnote = 0;
            System.out.println("Le meilleur élève en "+ sub.getName() + " :"); // Affichage de la matière
            for (Student stud : students) {
                // Requête SQL pour recup les notes
                String insertionQuery = "SELECT * FROM grade WHERE id_student = ? AND id_subject = ?";
                // Préparer une déclaration pour l'insertion
                PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
                // Attribuer les valeurs de l'étudiant à la déclaration préparée
                preparedStatement.setInt(1, stud.getId());
                preparedStatement.setInt(2, sub.getId());

                // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
                ResultSet resultnote = preparedStatement.executeQuery();

                int totonote = 0;
                int i = 0;
                while (resultnote.next()) {
                    totonote = totonote + resultnote.getInt("grade");
                    i++;
                }
                int average = totonote / i;
                if (average > bestnote){
                    bestnote = average;
                    eleve = stud.getFirstName() + " " + stud.getLastName();
                }
            }
            // Affichage les meilleurs élèves
            System.out.println(eleve + " avec une moyenne de " + bestnote);
        }
    }

    @Override
    public void badGrade() throws ClassNotFoundException, SQLException {
        // Connexion a la base de donnée
        DBconfig studentDBConfig = new DBconfig();
        Connection connection = studentDBConfig.connection();

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementsub = connection.prepareStatement("SELECT * from subject");
        ResultSet resultSub = preparedStatementsub.executeQuery();
        //Création de la liste subjects
        ArrayList<Subject> subjects = new ArrayList<>();
        while (resultSub.next()) {
            int id = resultSub.getInt("id");
            String name = resultSub.getString("name");
            int factor = resultSub.getInt("factor");
            subjects.add(new Subject(id, name, factor));
        }

        // Requête + Préparation + Exécution
        PreparedStatement preparedStatementstud = connection.prepareStatement("SELECT * from student");
        ResultSet resultstud = preparedStatementstud.executeQuery();
        //Création de la liste students
        ArrayList<Student> students = new ArrayList<>();
        while (resultstud.next()) {
            int id = resultstud.getInt("id");
            String firstName = resultstud.getString("firstName");
            String  lastName = resultstud.getString("lastName");
            students.add(new Student(firstName, lastName, id));
        }

        // Parcourir les résultats pour faire les moyennes
        for (Subject sub : subjects) {
            System.out.println("Les élève ayant besoin de travailler en "+ sub.getName() + " :"); // Affichage de la matière
            for (Student stud : students) {
                // Requête SQL pour recup les notes
                String insertionQuery = "SELECT * FROM grade WHERE id_student = ? AND id_subject = ?";
                // Préparer une déclaration pour l'insertion
                PreparedStatement preparedStatement = connection.prepareStatement(insertionQuery);
                // Attribuer les valeurs de l'étudiant à la déclaration préparée
                preparedStatement.setInt(1, stud.getId());
                preparedStatement.setInt(2, sub.getId());

                // Exécuter la mise à jour de la base de données et obtenir le nombre de lignes affectées
                ResultSet resultnote = preparedStatement.executeQuery();

                String eleve = "None";
                int badnote = 15;
                while (resultnote.next()) {
                    if (resultnote.getInt("grade") < badnote){
                        eleve = stud.getFirstName() + " " + stud.getLastName();
                        badnote = resultnote.getInt("grade");
                    }
                }
                if (!eleve.equals("None")){
                    // Affichage les mauvais élèves
                    System.out.println(eleve + " avec une note de " + badnote);
                }
            }
        }
    }
}
