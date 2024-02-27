package Business;

import Model.AboardPrograms;
import Model.Student;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A class representing the AboardManagement system, which manages aboard
 * programs, students, and program registrations.
 */
public class AboardManagement implements Serializable {

    private AboardProgramsManagement aboards = new AboardProgramsManagement();
    private StudentManagement students = new StudentManagement();
    private RegisterPrograms registers = new RegisterPrograms();

    /**
     * Adds a new aboard program to the system.
     */
    public void addAboardProgram() {
        aboards.addAboardProgram();
    }

    /**
     * Displays all the aboard programs in the system.
     */
    public void displayAboardProgram() {
        aboards.displayAboardProgram();
    }

    /**
     * Edits an existing aboard program in the system.
     */
    public void editAboardProgram() {
        aboards.editAboardProgram();
    }

    /**
     * Searches for a specific aboard program in the system.
     */
    public void searchAboardProgram() {
        aboards.searchAboardProgram();
    }

    /**
     * Adds a new student to the system.
     */
    public void addStudent() {
        students.addStudent();
    }

    /**
     * Displays all the students in the system.
     */
    public void displayStudent() {
        students.displayStudent();
    }

    /**
     * Edits an existing student in the system.
     */
    public void editStudent() {
        students.editStudent();
    }

    /**
     * Registers a student for an aboard program.
     */
    public void registerPrograms() {
        boolean check = false;
        String registerDate = null;
        String parentsEmail = null;
        String parentsPhone = null;
        Scanner scanner = new Scanner(System.in);
        String studentID = students.inputStudentIdExist("Enter student ID: ");
        String aboardID = aboards.inputAboardIdExist("Enter aboard program ID: ");
        if (!studentID.isEmpty() && !aboardID.isEmpty()) {
            registerDate = checkRegisterDate(aboardID, "Enter registration date [DD/MM/YYYY]: ");
            System.out.print("Enter parents mail: ");
            parentsEmail = scanner.nextLine();
            System.out.print("Enter parents phone: ");
            parentsPhone = scanner.nextLine();
            registers.registerProgram(aboards, students, studentID, aboardID, registerDate, parentsEmail, parentsPhone);
            for (Student student : students.values()) {
                if (student.getStudentID().equalsIgnoreCase(studentID)) {
                    for (AboardPrograms aboardPrograms : aboards.values()) {
                        if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                            student.setRegisterAboardPrograms(aboardPrograms);
                        }
                    }
                }
            }
        }
    }

    /**
     * Checks if the registration date is within the date range of the specified
     * aboard program.
     *
     * @param aboardID The ID of the aboard program.
     * @param requestDate The message to request the registration date from the
     * user.
     * @return The valid registration date.
     */
    public String checkRegisterDate(String aboardID, String requestDate) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate rangeStartDate;
        LocalDate rangeEndDate;
        Scanner scanner = new Scanner(System.in);
        boolean check = false;
        String registerDate = null;
        do {
            String beginDate = aboards.get(aboardID).getBeginDate();
            String endDate = aboards.get(aboardID).getEndDate();
            registerDate = aboards.getDate(requestDate);
            rangeStartDate = LocalDate.parse(beginDate, formatter);
            rangeEndDate = LocalDate.parse(endDate, formatter);
            if (isWithinDateRange(registerDate, rangeStartDate, rangeEndDate)) {
                check = true;
                return registerDate;
            } else {
                System.out.println("Registration date must be between begin and end of the program’s registration date!");
                check = false;
            }
        } while (!check);
        return registerDate;
    }

    /**
     * Checks if a date is within a specified date range.
     *
     * @param date The date to check.
     * @param beginDate The start date of the range.
     * @param endDate The end date of the range.
     * @return True if the date is within the range, false otherwise.
     */
    private boolean isWithinDateRange(String date, LocalDate beginDate, LocalDate endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate admissionDate = LocalDate.parse(date, formatter);

        return admissionDate.isEqual(beginDate) || admissionDate.isEqual(endDate)
                || (admissionDate.isAfter(beginDate) && admissionDate.isBefore(endDate));
    }

    /**
     * Prompts the user to enter a student ID and prints the registration
     * details for that student.
     */
    public void printRegistration() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        printRegistrationByStudentId(studentID);
    }

    /**
     * Prints the registration details for all students who have registered for
     * more than two programs.
     */
    public void printRegistrationMore2() {
        printRegistrationByStudentIdMore2();
    }

    /**
     * Prints the registration details for a specific student based on their
     * student ID.
     *
     * @param studentId The ID of the student.
     */
    public void printRegistrationByStudentId(String studentId) {
        String fileNamePrefix = studentId + "_";
        File folder = new File("RegistrationForm");
        File[] files = folder.listFiles((dir, name) -> name.startsWith(fileNamePrefix));

        if (files != null && files.length > 0) {
            for (File file : files) {
                try (Scanner scanner = new Scanner(file)) {
                    System.out.println("File: " + file.getName());
                    while (scanner.hasNextLine()) {
                        System.out.println(scanner.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Error reading registration details from file: " + e.getMessage());
                }
            }
        } else {
            System.out.println("No registration found for student ID: " + studentId);
        }
    }

    /**
     * Prints the registration details for all students who have registered for
     * more than two programs.
     */
    public void printRegistrationByStudentIdMore2() {
        try {
            for (Student student : students.values()) {
                String fileNamePrefix = student.getStudentID() + "_";
                File folder = new File("RegistrationForm");
                File[] files = folder.listFiles((dir, name) -> name.startsWith(fileNamePrefix));
                if (files != null && files.length > 0 && student.getRegisterAboardPrograms().size() > 2) {
                    for (File file : files) {
                        try (Scanner scanner = new Scanner(file)) {
                            System.out.println("File: " + file.getName());
                            while (scanner.hasNextLine()) {
                                System.out.println(scanner.nextLine());
                            }
                        } catch (FileNotFoundException e) {
                            System.out.println("Error reading registration details from file: " + e.getMessage());
                        }
                    }
                } else {
                    System.out.println("No students have registered for more than 2 programs.");
                }
            }
        } catch (Exception e) {
        }
    }

    /**
     * Counts the number of students who have registered for a specific program.
     */
    public void countStudentRegisteredProgram() {
        int count = 0;
        String aboardID = aboards.inputAboardIdExist("Enter program's ID: ");
        for (AboardPrograms aboardPrograms : aboards.values()) {
            if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                count = aboards.get(aboardID).getRegisterStudent().size();
            }
        }
        System.out.println("Count of students that registered to this program: " + count);
    }

    /**
     * Saves the data of the aboard programs and students to files.
     */
    public void saveData() {
        try {
            try (ObjectOutputStream aboardOutputStream = new ObjectOutputStream(new FileOutputStream("aboards.dat"))) {
                aboardOutputStream.writeObject(aboards);
            }

            try (ObjectOutputStream studentOutputStream = new ObjectOutputStream(new FileOutputStream("students.dat"))) {
                studentOutputStream.writeObject(students);
            }

            System.out.println("Data saved successfully.\n");
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads the data of the aboard programs and students from files.
     */
    public void loadData() {
        try {
            try (ObjectInputStream aboardInputStream = new ObjectInputStream(new FileInputStream("aboards.dat"))) {
                aboards = (AboardProgramsManagement) aboardInputStream.readObject();
            }
            try (ObjectInputStream studentInputStream = new ObjectInputStream(new FileInputStream("students.dat"))) {
                students = (StudentManagement) studentInputStream.readObject();
            }
            System.out.println("Data loaded successfully.\n");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    /**
     * Saves the data and prompts the user for confirmation.
     */
    public void saveDataOrder() {
        String yn;
        boolean choice = false;
        Scanner sc = new Scanner(System.in);
        while (!choice) {
            System.out.print("Save data? [Y/N]: ");
            yn = sc.nextLine();
            if (yn.equalsIgnoreCase("Y")) {
                saveData();
                choice = true;
            } else if (yn.equalsIgnoreCase("N")) {
                System.out.println("Data clear! Exiting program...");
                break;
            } else {
                System.out.println("Invalid choice! Please try again.");
                System.out.println("");
            }
        }
    }

    /**
     * Retrieves an integer input from the user.
     *
     * @return The integer input.
     */
    public int getIntInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.print("Invalid input! Please enter an integer: ");
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

}
