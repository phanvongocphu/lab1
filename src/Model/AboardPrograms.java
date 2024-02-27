package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an Abroad Program. It stores information about the
 * program, including ID, name, time, dates, location, cost, and content. It
 * also keeps track of the students who have registered for the program.
 */
public class AboardPrograms implements Serializable {

    private String aboardID;
    private String aboardName;
    private String aboardTime;
    private String beginDate;
    private String endDate;
    private int aboardDays;
    private String location;
    private double cost;
    private String content;
    private List<Student> registerStudent;

    //Constructors
    /**
     * Creates an instance of the AbroadPrograms class with specified
     * parameters.
     *
     * @param aboardID The unique identifier for the abroad program.
     * @param aboardName The name of the abroad program.
     * @param aboardTime The time of the abroad program (e.g., summer, winter).
     * @param beginDate The start date of registration for the program.
     * @param endDate The end date of registration for the program.
     * @param aboardDays The number of days for the program.
     * @param location The location of the abroad program.
     * @param cost The cost of the abroad program.
     * @param content The content or description of the abroad program.
     */
    public AboardPrograms(String aboardID, String aboardName, String aboardTime, String beginDate, String endDate, int aboardDays, String location, double cost, String content) {
        this.aboardID = aboardID;
        this.aboardName = aboardName;
        this.aboardTime = aboardTime;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.aboardDays = aboardDays;
        this.location = location;
        this.cost = cost;
        this.content = content;
    }

    /**
     * Creates an instance of the AbroadPrograms class with default values.
     */
    public AboardPrograms() {
    }

    //Getter and setter
    /**
     * Get the unique identifier for the abroad program.
     *
     * @return The abroad program ID.
     */
    public String getAboardID() {
        return aboardID;
    }

    /**
     * Set the unique identifier for the abroad program.
     *
     * @param aboardID The abroad program ID to set.
     */
    public void setAboardID(String aboardID) {
        this.aboardID = aboardID;
    }

    /**
     * Get the name of the abroad program.
     *
     * @return The abroad program name.
     */
    public String getAboardName() {
        return aboardName;
    }

    /**
     * Set the name of the abroad program.
     *
     * @param aboardName The abroad program name to set.
     */
    public void setAboardName(String aboardName) {
        this.aboardName = aboardName;
    }

    /**
     * Get the time of the abroad program (e.g., summer, winter).
     *
     * @return The abroad program time.
     */
    public String getAboardTime() {
        return aboardTime;
    }

    /**
     * Set the time of the abroad program.
     *
     * @param aboardTime The abroad program time to set.
     */
    public void setAboardTime(String aboardTime) {
        this.aboardTime = aboardTime;
    }

    /**
     * Get the start date of registration for the program.
     *
     * @return The start date of registration.
     */
    public String getBeginDate() {
        return beginDate;
    }

    /**
     * Set the start date of registration for the program.
     *
     * @param beginDate The start date of registration to set.
     */
    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    /**
     * Get the end date of registration for the program.
     *
     * @return The end date of registration.
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * Set the end date of registration for the program.
     *
     * @param endDate The end date of registration to set.
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * Get the number of days for the program.
     *
     * @return The number of days for the program.
     */
    public int getAboardDays() {
        return aboardDays;
    }

    /**
     * Set the number of days for the program.
     *
     * @param aboardDays The number of days for the program to set.
     */
    public void setAboardDays(int aboardDays) {
        this.aboardDays = aboardDays;
    }

    /**
     * Get the location of the abroad program.
     *
     * @return The abroad program location.
     */
    public String getLocation() {
        return location;
    }

    /**
     * Set the location of the abroad program.
     *
     * @param location The abroad program location to set.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Get the cost of the abroad program.
     *
     * @return The abroad program cost.
     */
    public double getCost() {
        return cost;
    }

    /**
     * Set the cost of the abroad program.
     *
     * @param cost The abroad program cost to set.
     */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
     * Get the content or description of the abroad program.
     *
     * @return The abroad program content.
     */
    public String getContent() {
        return content;
    }

    /**
     * Set the content or description of the abroad program.
     *
     * @param content The abroad program content to set.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Get the list of students registered for the program.
     *
     * @return The list of registered students.
     */
    public List<Student> getRegisterStudent() {
        return registerStudent;
    }

    /**
     * Set a student as registered for the program. If the registerStudent list
     * is null, it creates a new ArrayList. Then adds the student to the list.
     *
     * @param student The student to set as registered for the program.
     */
    public void setRegisterStudent(Student student) {
        if (registerStudent == null) {
            registerStudent = new ArrayList<>();
        }
        registerStudent.add(student);
    }

    // toString method
    /**
     * Returns a string representation of the AbroadPrograms object.
     *
     * @return A formatted string representation of the object.
     */
    @Override
    public String toString() {
        System.out.println("______________________________________________________________");
        System.out.format("|          %-16s |           %-20s |\n", "Fields", "Constraints");
        System.out.println("--------------------------------------------------------------");
        System.out.format("| %-25s | %-30s |\n", "Aboard ID", aboardID.toUpperCase());
        System.out.format("| %-25s | %-30s |\n", "Aboard Name", aboardName);
        System.out.format("| %-25s | %-30s |\n", "Aboard Time", aboardTime);
        System.out.format("| %-25s | %-30s |\n", "From Registration Date", beginDate);
        System.out.format("| %-25s | %-30s |\n", "End Registration Date", endDate);
        System.out.format("| %-25s | %-30d |\n", "Aboard Days", aboardDays);
        System.out.format("| %-25s | %-30s |\n", "Location", location);
        System.out.format("| %-25s | %-30.2f |\n", "Cost", cost);
        System.out.format("| %-25s | %-30s |\n", "Content", content);
        return "______________________________________________________________\n";
    }

}
