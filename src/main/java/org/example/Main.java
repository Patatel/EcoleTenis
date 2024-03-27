package org.example;

import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        int choice;
        Random random = new Random();
        Interface inter = new ActionImpl();

        do {
            System.out.println("Menu :");
            System.out.println("1 - Ajouter des informations");
            System.out.println("2 - Afficher les moyennes des élèves (par matières)");
            System.out.println("3 - Afficher le meilleur élève par matière");
            System.out.println("4 - Afficher les matières a retravailler (par élèves)");
            System.out.println("5 - Modifier les notes");
            System.out.println("6 - Quitter");

            // Création d'un objet Scanner pour lire l'entrée utilisateur
            Scanner mainScan = new Scanner(System.in);
            // Demander à l'utilisateur de choisir son action
            System.out.print("Donner le numéro de l'action a effectuer :");
            choice = Integer.parseInt(mainScan.nextLine());

            //Ajouter infos
            if (choice == 1){
                System.out.println("1 - Ajouter un élève");
                System.out.println("2 - Ajouter une note");
                System.out.println("3 - Ajouter une matière");
                System.out.println("Que voulez vous ajouter ?");
                // Demander à l'utilisateur de choisir son action
                System.out.print("Donner le numéro de l'action a effectuer :");
                choice = Integer.parseInt(mainScan.nextLine());

                //Student
                if (choice == 1){
                    String firstName;
                    String lastName;
                    int id = random.nextInt(101);

                    System.out.println("Nom de l'élève :");
                    firstName = mainScan.nextLine();
                    System.out.println("Prénom de l'élève :");
                    lastName = mainScan.nextLine();

                    Student student = new Student(firstName,lastName,id);
                    inter.addStudent(student);

                    //Grade
                } else if (choice == 2) {
                    int idStudent;
                    int idSubject;
                    int note;
                    int id = random.nextInt(101);

                    inter.showStudent();
                    // Demander à l'utilisateur de choisir l'élève
                    System.out.print("Choisissez le numéro de l'élève à noter :");
                    idStudent = Integer.parseInt(mainScan.nextLine());

                    inter.showSubject();
                    // Demander à l'utilisateur de choisir la matière
                    System.out.print("Choisissez le numéro de la matière :");
                    idSubject = Integer.parseInt(mainScan.nextLine());

                    // Demander à l'utilisateur de choisir la note de l'élève
                    System.out.print("Choisissez la note :");
                    note = Integer.parseInt(mainScan.nextLine());

                    Grade grade = new Grade(id,idStudent,idSubject,note);
                    inter.addGrade(grade);

                    //Subject
                } else if (choice == 3) {
                    String name;
                    int factor;
                    int id = random.nextInt(101);

                    System.out.println("Nom de la matière :");
                    name = mainScan.nextLine();
                    System.out.println("Coéficient de la matière :");
                    factor = Integer.parseInt(mainScan.nextLine());

                    Subject subject = new Subject(id,name,factor);
                    inter.addSubject(subject);
                }else{
                    System.out.print("Le numéro de l'action n'existe pas");
                }

                //Afficher les moyennes
            }else if (choice == 2) {
                inter.averageStudent();

                //Afficher le meilleur élève par matières
            }else if (choice == 3) {
                inter.bestStudent();

                //Afficher les élèves qui doivent travailler
            }else if (choice == 4) {
                inter.badGrade();

                //Modifier les notes
            }else if (choice == 5) {
                int id;
                int newNote;
                inter.showGrade();

                // Demander à l'utilisateur de choisir la note à modifier
                System.out.print("Choisissez le numéro de la ligne que vous voulez modifier :");
                id = Integer.parseInt(mainScan.nextLine());

                // Demander à l'utilisateur de modifier la note
                System.out.print("Nouvelle note :");
                newNote = Integer.parseInt(mainScan.nextLine());

                inter.updateGrade(id,newNote);

                //Quitter
            }else if (choice == 6) {
                System.exit(0);
            }else{
                System.out.println("Le numéro d'action n'existe pas");
            }
        }while (choice != 6);
    }
}