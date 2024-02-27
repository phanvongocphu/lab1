package Business;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * This class handles the registration of students for aboard programs.
 */
public class RegisterPrograms implements Serializable {

    /**
     * Registers a student for an aboard program.
     *
     * @param aboardProgramsManagement An instance of AboardProgramsManagement.
     * @param studentManagement An instance of StudentManagement.
     * @param studentID The ID of the student.
     * @param aboardID The ID of the aboard program.
     * @param registrationDate The registration date.
     * @param parentEmail The email of the student's parent.
     * @param parentPhone The phone number of the student's parent.
     */
    public void registerProgram(AboardProgramsManagement aboardProgramsManagement, StudentManagement studentManagement, String studentID, String aboardID, String registrationDate, String parentEmail, String parentPhone) {

        if (parentEmail.isEmpty() || parentPhone.isEmpty()) {
            System.out.println("Parent email and phone cannot be empty.");
            return;
        }

        // Save registration to file
        String fileName = studentID + "_" + aboardID + ".doc";
        String filePath = "RegistrationForm/" + fileName;
        try (PrintWriter writer = new PrintWriter(filePath)) {
            writer.println("\t\t\t  Aboard Program Registration Form");
            writer.println("\nInformation Student:");
            writer.format("Student ID: %-10s \tStudent name: %-15s\n", studentID, studentManagement.get(studentID).getStudentName());
            writer.format("Major: %-15s \tEmail: %-20s\n", studentManagement.get(studentID).getStudentMajor(), studentManagement.get(studentID).getStudentEmail());
            writer.format("Phone: %-15s \tPassport: %-10s\n", studentManagement.get(studentID).getStudentPhone(), studentManagement.get(studentID).getStudentPassport());
            writer.format("Address: %-30s\n", studentManagement.get(studentID).getStudentAddress());
            writer.format("Email of the parents: %-25s \nPhone of the parents: %-12s\n", parentEmail, parentPhone);
            writer.println("\nInformation of the aboard program:");
            writer.format("Program's ID: %-10s \tProgram's name: %-20s\n", aboardID, aboardProgramsManagement.get(aboardID).getAboardName());
            writer.format("Time: %-12s      \tDays: %-2d \nLocation: %-12s      \tCost: %-10.2f\n", aboardProgramsManagement.get(aboardID).getAboardTime(),
                    aboardProgramsManagement.get(aboardID).getAboardDays(), aboardProgramsManagement.get(aboardID).getLocation(), aboardProgramsManagement.get(aboardID).getCost());
            writer.println("\nInformation of the registration:");
            writer.println("Registration Date: " + registrationDate);
            System.out.println("Registration details saved to file: " + filePath);
        } catch (FileNotFoundException e) {
            System.out.println("Error saving registration details to file: " + e.getMessage());
        }
    }
}
