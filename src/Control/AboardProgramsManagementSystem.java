package Control;

import Business.AboardManagement;
import java.util.Scanner;

/**
 * This class represents the Aboard Programs Management System. It allows users
 * to manage aboard programs, students, and perform various operations.
 *
 */
public class AboardProgramsManagementSystem {

    /**
     * The main entry point of the program.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AboardManagement aboardManagement = new AboardManagement();
        aboardManagement.loadData();
        try (Scanner scanner = new Scanner(System.in)) {
            int choice;
            boolean quit = false;
            while (!quit) {
                System.out.println("[Aboard Programs Management System]");
                System.out.println("1. Manage aboard programs");
                System.out.println("2. Manage students");
                System.out.println("3. Register a program for a student");
                System.out.println("4. Report");
                System.out.println("0. Quit");
                System.out.print("Enter your choice: ");
                try {
                    choice = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice! Please try again.");
                    System.out.println("");
                    continue;
                }
                switch (choice) {
                    case 1:
                        manageAboardPrograms(aboardManagement, scanner);
                        break;
                    case 2:
                        manageStudents(aboardManagement, scanner);
                        break;
                    case 3:
                        registerPrograms(aboardManagement, scanner);
                        break;
                    case 4:
                        report(aboardManagement, scanner);
                        break;
                    case 5:
                        aboardManagement.loadData();
                        break;
                    case 0:
                        String yn;
                        boolean sure = false;
                        boolean quitconfirm = false;
                        Scanner sc = new Scanner(System.in);
                        while (!sure) {
                            System.out.print("Do you want to close the program? [Y/N]: ");
                            yn = sc.nextLine();

                            if (yn.equalsIgnoreCase("Y")) {
                                aboardManagement.saveDataOrder();
                                quitconfirm = true;
                                sure = true;
                            } else if (yn.equalsIgnoreCase("N")) {
                                System.out.println("");
                                quitconfirm = false;
                                break;
                            } else {
                                System.out.println("Invalid choice! Please try again.");
                                System.out.println("");
                            }
                        }
                        quit = quitconfirm;
                        break;
                    default:
                        System.out.println("Invalid choice! Please try again.");
                        System.out.println("");
                }
            }
        }
    }

    /**
     * Displays the menu for managing aboard programs and handles user input.
     *
     * @param aboardManagement the instance of AboardManagement
     * @param scanner the scanner for user input
     */
    private static void manageAboardPrograms(AboardManagement aboardManagement, Scanner scanner) {
        boolean backToMenu = false;
        int choice;
        while (!backToMenu) {
            System.out.println("\n[Manage Aboard Programs]");
            System.out.println("1. Display all aboard programs");
            System.out.println("2. Add a new aboard program");
            System.out.println("3. Edit information a program by ID");
            System.out.println("4. Search and Display a program by ID");
            System.out.println("5. Back to main menu");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    aboardManagement.displayAboardProgram();
                    break;
                case 2:
                    aboardManagement.addAboardProgram();
                    break;
                case 3:
                    aboardManagement.editAboardProgram();
                    break;
                case 4:
                    aboardManagement.searchAboardProgram();
                    break;
                case 5:
                    System.out.println("");
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    /**
     * Displays the menu for managing students and handles user input.
     *
     * @param aboardManagement the instance of AboardManagement
     * @param scanner the scanner for user input
     */
    private static void manageStudents(AboardManagement aboardManagement, Scanner scanner) {
        boolean backToMenu = false;
        int choice;
        while (!backToMenu) {
            System.out.println("\n[Manage Students]");
            System.out.println("1. Display all students");
            System.out.println("2. Add a new student");
            System.out.println("3. Edit information a student by ID");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    aboardManagement.displayStudent();
                    break;
                case 2:
                    aboardManagement.addStudent();
                    break;
                case 3:
                    aboardManagement.editStudent();
                    break;
                case 4:
                    System.out.println("");
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    /**
     * Registers a program for a student.
     *
     * @param aboardManagement the instance of AboardManagement
     * @param scanner the scanner for user input
     */
    private static void registerPrograms(AboardManagement aboardManagement, Scanner scanner) {
        System.out.println("");
        aboardManagement.registerPrograms();
    }

    /**
     * Displays the menu for generating reports and handles user input.
     *
     * @param aboardManagement the instance of AboardManagement
     * @param scanner the scanner for user input
     */
    private static void report(AboardManagement aboardManagement, Scanner scanner) {
        boolean backToMenu = false;
        int choice;
        while (!backToMenu) {
            System.out.println("\n[Report Options]");
            System.out.println("1. Print out the registration by student's ID");
            System.out.println("2. Print out the students that registered more than 2 programs");
            System.out.println("3. Count students that registered the program");
            System.out.println("4. Back to main menu");
            System.out.print("Enter your choice: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice! Please try again.");
                continue;
            }
            switch (choice) {
                case 1:
                    aboardManagement.printRegistration();
                    break;
                case 2:
                    aboardManagement.printRegistrationMore2();
                    break;
                case 3:
                    aboardManagement.countStudentRegisteredProgram();
                    break;
                case 4:
                    System.out.println("");
                    backToMenu = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }
}
