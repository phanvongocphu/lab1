package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The Student class represents a student in a program. It contains information
 * about the student such as ID, name, major, email, phone, passport, address,
 * and registered abroad programs.
 */
public class Student implements Serializable {

    private String studentID;
    private String studentName;
    private String studentMajor;
    private String studentEmail;
    private String studentPhone;
    private String studentPassport;
    private String studentAddress;
    private List<AboardPrograms> registerAboardPrograms;

    /**
     * Constructs a Student object with the specified parameters.
     *
     * @param studentID the ID of the student
     * @param studentName the name of the student
     * @param studentMajor the major of the student
     * @param studentEmail the email of the student
     * @param studentPhone the phone number of the student
     * @param studentPassport the passport number of the student
     * @param studentAddress the address of the student
     */
    public Student(String studentID, String studentName, String studentMajor, String studentEmail, String studentPhone, String studentPassport, String studentAddress) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        this.studentEmail = studentEmail;
        this.studentPhone = studentPhone;
        this.studentPassport = studentPassport;
        this.studentAddress = studentAddress;
    }

    /**
     * Constructs an empty Student object.
     */
    public Student() {
    }

    /**
     * Returns the ID of the student.
     *
     * @return the student ID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Sets the ID of the student.
     *
     * @param studentID the student ID to set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Returns the name of the student.
     *
     * @return the student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * Sets the name of the student.
     *
     * @param studentName the student name to set
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * Returns the major of the student.
     *
     * @return the student major
     */
    public String getStudentMajor() {
        return studentMajor;
    }

    /**
     * Sets the major of the student.
     *
     * @param studentMajor the student major to set
     */
    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    /**
     * Returns the email of the student.
     *
     * @return the student email
     */
    public String getStudentEmail() {
        return studentEmail;
    }

    /**
     * Sets the email of the student.
     *
     * @param studentEmail the student email to set
     */
    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    /**
     * Returns the phone number of the student.
     *
     * @return the student phone number
     */
    public String getStudentPhone() {
        return studentPhone;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param studentPhone the student phone number to set
     */
    public void setStudentPhone(String studentPhone) {
        this.studentPhone = studentPhone;
    }

    /**
     * Returns the passport number of the student.
     *
     * @return the student passport number
     */
    public String getStudentPassport() {
        return studentPassport;
    }

    /**
     * Sets the passport number of the student.
     *
     * @param studentPassport the student passport number to set
     */
    public void setStudentPassport(String studentPassport) {
        this.studentPassport = studentPassport;
    }

    /**
     * Returns the address of the student.
     *
     * @return the student address
     */
    public String getStudentAddress() {
        return studentAddress;
    }

    /**
     * Sets the address of the student.
     *
     * @param studentAddress the student address to set
     */
    public void setStudentAddress(String studentAddress) {
        this.studentAddress = studentAddress;
    }

    /**
     * Returns the list of registered abroad programs for the student.
     *
     * @return the list of registered abroad programs
     */
    public List<AboardPrograms> getRegisterAboardPrograms() {
        return registerAboardPrograms;
    }

    /**
     * Sets the registered abroad program for the student. If the list of
     * registered abroad programs is null, a new list is created. The student is
     * added to the registered abroad program, and the registered abroad program
     * is set for the student.
     *
     * @param aboardPrograms the abroad program to register
     */
    public void setRegisterAboardPrograms(AboardPrograms aboardPrograms) {
        if (registerAboardPrograms == null) {
            registerAboardPrograms = new ArrayList<>();
        }
        registerAboardPrograms.add(aboardPrograms);
        aboardPrograms.setRegisterStudent(this);
    }

    /**
     * Returns a string representation of the student object.
     *
     * @return a string representation of the student
     */
    @Override
    public String toString() {
        return String.format("| %-10s | %-20s | %-7s | %-30s | %-12s | %-10s | %-20s |",
                studentID.toUpperCase(), studentName, studentMajor, studentEmail.toLowerCase(),
                studentPhone, studentPassport, studentAddress);
    }

}
