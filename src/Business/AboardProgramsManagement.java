package Business;

import Model.AboardPrograms;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * This class represents the management of aboard programs.
 */
public class AboardProgramsManagement extends HashMap<String, AboardPrograms> implements Serializable {

    /**
     * Adds a new aboard program.
     */
    public void addAboardProgram() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("\n[Adding new aboard program]");
            String aboardID = isAboardIdExist("Enter aboard ID: ");
            System.out.print("Enter aboard name: ");
            String aboardName = scanner.nextLine();
            String aboardTime = checkAboardTime("Aboard time[January, March, May, July, September, November]: ");
            String fromDate = null;
            String endDate = null;
            boolean dateCheck;
            do {
                fromDate = getDate("From registration date [DD/MM/YYYY]: ");
                endDate = getDate("End registration date [DD/MM/YYYY]: ");
                dateCheck = dateAfterBefore(fromDate, endDate);
            } while (!dateCheck);
            int days = checkDays();
            System.out.print("Enter location: ");
            String location = scanner.nextLine();
            double aboardCost = checkCost();
            System.out.print("Enter content: ");
            String content = scanner.nextLine();
            if (aboardID.isEmpty() || aboardName.isEmpty() || aboardTime.isEmpty() || location.isEmpty() || content.isEmpty()) {
                System.out.println("All fields are required!");
                System.out.println("Add aboard program failed!");
            } else {
                AboardPrograms aboardPrograms = new AboardPrograms();
                aboardPrograms.setAboardID(aboardID);
                aboardPrograms.setAboardName(aboardName);
                aboardPrograms.setAboardTime(aboardTime);
                aboardPrograms.setBeginDate(fromDate);
                aboardPrograms.setEndDate(endDate);
                aboardPrograms.setAboardDays(days);
                aboardPrograms.setLocation(location);
                aboardPrograms.setCost(aboardCost);
                aboardPrograms.setContent(content);
                this.put(aboardID, aboardPrograms);
                System.out.println("Add aboard program successfully!");
            }
        } catch (Exception e) {
            System.out.println("Add aboard program failed!");
        }
        if (continueAddAboardProgram()) {
            addAboardProgram();
        }
    }

    /**
     * Displays all aboard programs.
     */
    public void displayAboardProgram() {
        System.out.println("\n[All Aboard Programs]\n");
        for (AboardPrograms aboardPrograms : this.values()) {
            System.out.println(aboardPrograms);
        }
    }

    /**
     * Edits an existing aboard program.
     */
    public void editAboardProgram() {
        Scanner scanner = new Scanner(System.in);
        String aboardID = checkAboardIdExist("Enter aboard ID to edit: ");
        for (AboardPrograms aboardPrograms : this.values()) {
            if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                try {
                    System.out.print("Update aboard name: ");
                    String aboardName = scanner.nextLine();
                    String aboardTime = checkAboardTime("Aboard time[January, March, May, July, September, November]: ");
                    String fromDate = null;
                    String endDate = null;
                    boolean dateCheck;
                    do {
                        fromDate = getDate("From registration date [DD/MM/YYYY]: ");
                        endDate = getDate("End registration date [DD/MM/YYYY]: ");
                        dateCheck = dateAfterBefore(fromDate, endDate);
                    } while (!dateCheck);
                    int days = checkDays();
                    System.out.print("Update location: ");
                    String location = scanner.nextLine();
                    double aboardCost = checkCost();
                    System.out.print("Update content: ");
                    String content = scanner.nextLine();
                    if (aboardID.isEmpty() || aboardName.isEmpty() || aboardTime.isEmpty() || location.isEmpty() || content.isEmpty()) {
                        System.out.println("All fields are required!");
                    } else {
                        aboardPrograms.setAboardName(aboardName);
                        aboardPrograms.setAboardTime(aboardTime);
                        aboardPrograms.setBeginDate(fromDate);
                        aboardPrograms.setEndDate(endDate);
                        aboardPrograms.setAboardDays(days);
                        aboardPrograms.setLocation(location);
                        aboardPrograms.setCost(aboardCost);
                        aboardPrograms.setContent(content);
                        this.put(aboardID, aboardPrograms);
                        System.out.println("Update aboard program successfully!");
                    }
                } catch (Exception e) {
                    System.out.println("Update aboard program failed!");
                }
            }
        }
    }

    /**
     * Searches for an aboard program based on the entered aboard ID.
     */
    public void searchAboardProgram() {
        String aboardID = checkAboardIdExist("Enter aboard ID to find: ");
        for (AboardPrograms aboardPrograms : this.values()) {
            if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                System.out.println(aboardPrograms);
            }
        }
    }

    //=====================================================
    /**
     * Validates and retrieves the number of days.
     *
     * @return The number of days entered by the user.
     */
    public int checkDays() {
        int input = 0;
        boolean formatDays = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Number of days [Min 30/Max 40]: ");
            String scanInput = sc.nextLine();
            try {
                input = Integer.parseInt(scanInput);
                if (input >= 30 && input <= 40) {
                    formatDays = true;
                } else {
                    System.out.println("Day must be in range [30 ~ 40]! Please try again.");
                    formatDays = false;

                }
            } catch (NumberFormatException e) {
                System.out.println("Day must be a number! Please try again.");
            }
        } while (!formatDays);
        return input;
    }

    /**
     * Validates and retrieves the cost of the aboard program.
     *
     * @return The cost entered by the user.
     */
    public double checkCost() {
        double input = 0;
        boolean formatCost = false;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Enter aboard cost: ");
            String scanInput = sc.nextLine();
            try {
                input = Double.parseDouble(scanInput);
                if (input > 0) {
                    formatCost = true;
                } else {
                    System.out.println("Cost must be a positive number! Please try again.");
                    formatCost = false;

                }
            } catch (NumberFormatException e) {
                System.out.println("Cost must be a number! Please try again.");
            }
        } while (!formatCost);
        return input;
    }

    /**
     * Asks the user if they want to continue adding aboard programs.
     *
     * @return True if the user wants to continue, False otherwise.
     */
    public boolean continueAddAboardProgram() {
        String yn;
        boolean go = false;
        boolean choice = false;
        Scanner sc = new Scanner(System.in);
        while (!choice) {
            System.out.print("Continue to add aboard program? [Y/N]: ");
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
     * Checks if the entered aboard ID exists and prompts the user to enter a
     * valid one.
     *
     * @param requestEnterID The message to display when requesting the user to
     * enter the ID.
     * @return The valid aboard ID entered by the user.
     */
    public String isAboardIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String aboardID = null;
        boolean check = true;
        do {
            try {
                System.out.print(requestEnterID);
                aboardID = sc.nextLine();
                for (AboardPrograms aboardPrograms : this.values()) {
                    if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                        System.out.println("Aboard ID has exist! Please try again.");
                    } else {
                        check = false;
                        return aboardID;
                    }
                }
            } catch (Exception ex) {
                System.out.println("Aboard ID doesn't exist!");
            }
        } while (!check);
        return aboardID;
    }

    /**
     * Checks if the entered aboard ID exists.
     *
     * @param requestEnterID The message to display when requesting the user to
     * enter the ID.
     * @return The valid aboard ID entered by the user, or null if it doesn't
     * exist.
     */
    public String checkAboardIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String aboardID = null;
        boolean idExists = false;
        try {
            System.out.print(requestEnterID);
            aboardID = sc.nextLine();
            for (AboardPrograms aboardPrograms : this.values()) {
                if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                    idExists = true;
                    break;
                }
            }
            if (!idExists) {
                System.out.println("Aboard ID doesn't exist!");
            }
        } catch (Exception ex) {
            System.out.println("Aboard ID doesn't exist!");
        }
        return idExists ? aboardID : null;
    }

    /**
     * Prompts the user to enter an aboard ID and checks if it exists.
     *
     * @param requestEnterID The message to display when requesting the user to
     * enter the ID.
     * @return The valid aboard ID entered by the user.
     */
    public String inputAboardIdExist(String requestEnterID) {
        Scanner sc = new Scanner(System.in);
        String aboardID = null;
        boolean idExists = false;
        boolean check = false;
        do {
            try {
                System.out.print(requestEnterID);
                aboardID = sc.nextLine();
                for (AboardPrograms aboardPrograms : this.values()) {
                    if (aboardPrograms.getAboardID().equalsIgnoreCase(aboardID)) {
                        idExists = true;
                        check = true;
                        return aboardID;
                    }
                }
                if (!idExists) {
                    System.out.println("Aboard ID doesn't exist!");
                    check = false;
                }

            } catch (Exception ex) {
                System.out.println("Aboard ID doesn't exist!");
            }
        } while (!check);
        return aboardID;
    }

    /**
     * Validates the entered time.
     *
     * @param time The message to display when requesting the user to enter the
     * time.
     * @return The valid time entered by the user.
     */
    public String checkAboardTime(String time) {
        String inputTime = null;
        boolean choice = false;
        Scanner sc = new Scanner(System.in);
        while (!choice) {
            System.out.print(time);
            inputTime = sc.nextLine();
            if (inputTime.equalsIgnoreCase("January") || inputTime.equalsIgnoreCase("March") || inputTime.equalsIgnoreCase("May")
                    || inputTime.equalsIgnoreCase("July") || inputTime.equalsIgnoreCase("September") || inputTime.equalsIgnoreCase("November")) {
                choice = true;
            } else {
                System.out.println("Invalid time! Please try again.");
            }
        }
        return inputTime;
    }

    /**
     * Date format.
     *
     */
    private final String dateFormat = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)[0-9]{2,2}$";

    /**
     * Retrieves a valid date from the user based on the given input message.
     *
     * @param dateInput The message to display when requesting the user to enter
     * the date.
     * @return The valid date entered by the user.
     */
    public String getDate(String dateInput) {
        Scanner sc = new Scanner(System.in);
        String date = null;
        boolean validDate;
        do {
            System.out.print(dateInput);
            date = sc.nextLine();
            Pattern pt = Pattern.compile(dateFormat);
            try {
                if (pt.matcher(date).find() && checkValidDate(date)) {
                    return date;
                } else {
                    System.out.println("Invalid date! Please try again.");
                    validDate = false;
                }
            } catch (Exception ex) {
                System.out.println("Invalid date! Please try again.");
                validDate = false;
            }
        } while (!validDate);
        return date;
    }

    /**
     * Checks if the given date is valid.
     *
     * @param date The date to check in the format "dd/MM/yyyy".
     * @return True if the date is valid, False otherwise.
     */
    public boolean checkValidDate(String date) {
        String[] split = date.split("[-/. ]");
        int day = Integer.parseInt(split[0]);
        int month = Integer.parseInt(split[1]);
        int year = Integer.parseInt(split[2]);
        int maxDay = 30;
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            maxDay = 31;
        }
        if (month == 2) {
            if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                maxDay = 29;
            } else {
                maxDay = 28;
            }
        }

        return day <= maxDay;
    }

    /**
     * Checks if the end date is after the start date.
     *
     * @param startDate The start date in the format "dd/MM/yyyy".
     * @param endDate The end date in the format "dd/MM/yyyy".
     * @return True if the end date is after the start date, False otherwise.
     */
    public boolean dateAfterBefore(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate rangeStartDate;
        LocalDate rangeEndDate;

        try {
            rangeStartDate = LocalDate.parse(startDate, formatter);
            rangeEndDate = LocalDate.parse(endDate, formatter);

            if (rangeEndDate.isBefore(rangeStartDate)) {
                System.out.println("End date must be after the start date.");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Invalid date format! Please try again.");
            return false;
        }
        return true;
    }
}
