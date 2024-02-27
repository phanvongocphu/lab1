package Business;

import Model.Student;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/**
 * This class represents a Student Management system that manages a collection
 * of students. It extends the HashMap class and implements the Serializable
 * interface.
 */
public class StudentManagement extends HashMap<String, Student> implements Serializable {

    /**
     * Displays all the students in the collection.
     */
    public void displayStudent() {
        System.out.println("\n[All Students]\n");
        for (Student student : this.values()) {
            System.out.println(student);
        }
    }

    /**
     * Adds a new student to the collection.
     */
    public void addStudent() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n[Adding new student]");
            String studentID = isStudentIdExist("Enter student ID: ");
            System.out.print("Enter student name: ");
            String studentName = scanner.nextLine();
            String studentMajor = checkMajor("Enter student major: ");
            String studentEmail = inputEmail("Enter student email [FPT email]: ");
            System.out.print("Enter phone: ");
            String studentPhone = checkPhoneNumber("(\\(\\+[0-9]{2}\\)|0)([0-9]{9,10})");
            System.out.print("Enter student passport: ");
            String studentPassport = scanner.nextLine();
            System.out.print("Enter student address: ");
            String studentAddress = scanner.nextLine();
            if (studentID.isEmpty() || studentName.isEmpty() || studentMajor.isEmpty() || studentEmail.isEmpty()
                    || studentPhone.isEmpty() || studentPassport.isEmpty() || studentAddress.isEmpty()) {
                System.out.println("All fields are required!");
                System.out.println("Add student failed!");
            } else {
                Student student = new Student();
                student.setStudentID(studentID);
                student.setStudentName(studentName);
                student.setStudentMajor(studentMajor);
                student.setStudentEmail(studentEmail);
                student.setStudentPhone(studentPhone);
                student.setStudentPassport(studentPassport);
                student.setStudentAddress(studentAddress);
                this.put(studentID, student);
                System.out.println("Add student successfully!");
            }
        } catch (Exception e) {
            System.out.println("Add student failed!");
        }
        if (continueAddStudent()) {
            addStudent();
        }
    }

    /**
     * Edits the details of a student by ID.
     */
    public void editStudent() {
        Scanner scanner = new Scanner(System.in);
        String studentID = checkStudentIdExist("Enter student ID to edit: ");
        for (Student student : this.values()) {
            if (student.getStudentID().equalsIgnoreCase(studentID)) {
                try {
                    System.out.print("Update student name: ");
                    String studentName = scanner.nextLine();
                    System.out.print("Update major: ");
                    String studentMajor = scanner.nextLine();
                    String studentEmail = inputEmail("Update FPT email: ");
                    System.out.print("Update student phone: ");
                    String studentPhone = checkPhoneNumber("(\\(\\+[0-9]{2}\\)|0)([0-9]{9,10})");
                    System.out.print("Update student passport: ");
                    String studentPassport = scanner.nextLine();
                    System.out.print("Update student address: ");
                    String studentAddress = scanner.nextLine();
                    if (studentID.isEmpty() || studentName.isEmpty() || studentMajor.isEmpty() || studentEmail.isEmpty()
                            || studentPhone.isEmpty() || studentPassport.isEmpty() || studentAddress.isEmpty()) {
                        System.out.println("All fields are required!");
                    } else {
                        student.setStudentName(studentName);
                        student.setStudentMajor(studentMajor);
                        student.setStudentEmail(studentEmail);
                        student.setStudentPhone(studentPhone);
                        student.setStudentPassport(studentPassport);
                        student.setStudentAddress(studentAddress);
                        this.put(studentID, student);
                        System.out.println("Update student successfully!");
                    }
                } catch (Exception e) {
                    System.out.println("Update student failed!");
                }
            }
        }
    }

    //========================================================================
    /**
     * Checks if a student ID already exists in the collection.
     *
     * @param requestEnterID The message to display when asking the user to
     * enter a student ID.
     * @return The student ID entered by the user.
     */
    public String isStudentIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String studentID = null;
        boolean check = true;
        do {
            try {
                System.out.print(requestEnterID);
                studentID = sc.nextLine();
                for (Student student : this.values()) {
                    if (student.getStudentID().equalsIgnoreCase(studentID)) {
                        System.out.println("Student ID has exist! Please try again.");
                    } else {
                        check = false;
                        return studentID;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Student ID doesn't exist!");
            }
        } while (!check);
        return studentID;
    }

    /**
     * Checks if a student ID exists in the collection.
     *
     * @param requestEnterID The message to display when asking the user to
     * enter a student ID.
     * @return The student ID entered by the user, or null if the ID doesn't
     * exist.
     */
    public String checkStudentIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String studentID = null;
        boolean idExists = false;
        try {
            System.out.print(requestEnterID);
            studentID = sc.nextLine();
            for (Student student : this.values()) {
                if (student.getStudentID().equalsIgnoreCase(studentID)) {
                    idExists = true;
                    break;
                }
            }
            if (!idExists) {
                System.out.println("Student ID doesn't exist!");
            }
        } catch (Exception ex) {
            System.out.println("Student ID doesn't exist!");
        }
        return idExists ? studentID : null;
    }

    /**
     * Prompts the user to enter a student ID that already exists in the
     * collection.
     *
     * @param requestEnterID The message to display when asking the user to
     * enter a student ID.
     * @return The student ID entered by the user.
     */
    public String inputStudentIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String studentID = null;
        boolean idExists = false;
        boolean check = false;
        do {
            try {
                System.out.print(requestEnterID);
                studentID = sc.nextLine();
                for (Student student : this.values()) {
                    if (student.getStudentID().equalsIgnoreCase(studentID)) {
                        idExists = true;
                        check = true;
                        return studentID;
                    }
                }
                
                if (!idExists) {
                    System.out.println("Student ID doesn't exist!");
                    check = false;
                }
            } catch (Exception ex) {
                System.out.println("Student ID doesn't exist!");
            }
        } while (!check);
        return studentID;
    }

    /**
     * Asks the user if they want to continue adding students.
     *
     * @return true if the user wants to continue adding students, false
     * otherwise.
     */
    public boolean continueAddStudent() {
        String yn;
        boolean go = false;
        boolean choice = false;
        Scanner sc = new Scanner(System.in);
        while (!choice) {
            System.out.print("Continue to add student? [Y/N]: ");
            yn = sc.nextLine();
            if (yn.equalsIgnoreCase("Y")) {
                go = true;
                choice = true;
            } else if (yn.equalsIgnoreCase("N")) {
                go = false;
                break;
            } else {
                System.out.println("Invalid choice! Please try again.\n");
            }
        }
        return go;
    }

    /**
     * Checks if a phone number matches a specified format.
     *
     * @param formatPhone The regular expression format for the phone number.
     * @return The phone number entered by the user.
     */
    public String checkPhoneNumber(String formatPhone) {
        String inputUser;
        Scanner sc = new Scanner(System.in);
        inputUser = sc.nextLine();
        while (!inputUser.matches(formatPhone)) {
            System.out.println("Invalid phone number format! Please try again.");
            System.out.print("Enter phone: ");
            inputUser = sc.nextLine();
        }
        return inputUser;
    }

    /**
     * Checks if a major is valid.
     *
     * @param major The message to display when asking the user to enter a
     * major.
     * @return The major entered by the user.
     */
    public String checkMajor(String major) {
        String inputMajor = null;
        boolean choice = false;
        Scanner sc = new Scanner(System.in);
        while (!choice) {
            System.out.print(major);
            inputMajor = sc.nextLine();
            if (inputMajor.equalsIgnoreCase("SE") || inputMajor.equalsIgnoreCase("SB")
                    || inputMajor.equalsIgnoreCase("GD") || inputMajor.equalsIgnoreCase("MC")) {
                choice = true;
            } else {
                System.out.println("Invalid major! Please try again.");
            }
        }
        return inputMajor;
    }

    /**
     * Prompts the user to enter an email and checks if it is valid.
     *
     * @param enterEmail The message to display when asking the user to enter an
     * email.
     * @return The email entered by the user.
     */
    public String inputEmail(String enterEmail) {
        Scanner scanner = new Scanner(System.in);
        String email = null;
        boolean check = false;
        do {
            System.out.print(enterEmail);
            email = scanner.nextLine();
            if (checkEmail(email)) {
                check = true;
                return email;
            } else {
                System.out.println("Invalid email! Email must be FPT email.");
            }
        } while (!check);
        return email;
    }

    /**
     * Checks if an email is valid.
     *
     * @param email The email to check.
     * @return true if the email is valid, false otherwise.
     */
    public boolean checkEmail(String email) {
        String pattern = "[a-zA-Z0-9._%+-]+@fpt.edu.vn";
        return email.matches(pattern);
    }

}
