import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/* Class Driver to run software */
public class Driver {
    public static void main(String[] args) {
        try (Scanner scan = new Scanner(System.in)) {
            Gradebook gb = new Gradebook();
            // Read in credentials from text file
            File fp = new File("login.txt");
            FileReader filereader = new FileReader(fp);
            BufferedReader buff = new BufferedReader(filereader);
            // login validation variables
            boolean loggedIn = false;
            int attempts = 0;

            // first sign in header to prompt user and password - need to save to JSON
            System.out.println("Welcome to Gradebook Software V1.0. Please login below.");
            System.out.println("Username: ");
            String username = scan.nextLine();
            System.out.println("Password: ");
            String password = scan.nextLine();
            String readLine = buff.readLine();

            // while input exists, user is not logged in, and number of attempts is not 3
            while (readLine != null && loggedIn == false && attempts != 3) {
                String[] split = readLine.split("\\s"); // eliminate spaces for reading
                // successful login attempt -> show options
                if (username.equals(split[0]) && password.equals(split[1])) { // credentials stored as: user pass
                    System.out.println("Login Successful!" + "\n");
                    System.out.println("***Welcome to Gradebook Software v1.0. What would you like to do today?***");
                    loggedIn = true;
                    buff.close();
                } else {
                    // prompt login credentials 3 more times before exiting program
                    System.out.println("attempts credentials. Please try again. You have " + (3 - attempts)
                            + " attempts remaining.");
                    System.out.println("Username: ");
                    username = scan.nextLine();
                    System.out.println("Password: ");
                    password = scan.nextLine();
                    attempts++;
                    if (attempts == 3) {
                        System.out.println(
                                "Too many attempts: Please contact IT Support to reset your login information");
                        break;
                    }
                }
            }
            gb.createAssignment("f", "f", "f", "1", 20.0); // test assignment creation
            // user is logged in
            while (loggedIn) {
                // keyboard input options
                System.out.println("\n>Create Assignment (c)"); // type c to create new Assignment
                System.out.println(">Delete Assignment (d)"); // type d to delete Assignment
                System.out.println(">Show Assignments (l)"); // type l (L) to list Assignments
                System.out.println(">Edit Assignment (e)"); // type e to edit fields of an Assignment
                System.out.println(">Save and exit (exit)"); // type x to exit program
                System.out.print("Selection: "); // command line input
                String option = scan.nextLine();
                // Assignment creation - must enter the correct non empty fields to create a new
                // assignment
                if (option.toLowerCase().equals("c")) {
                    System.out.println("Create an Assignment: Please fill in the required fields:");
                    String assignmentName = "";
                    String assignmentType = "";
                    String assignmentDescription = "";
                    String assignmentID = "";
                    while (true) {
                        System.out.println("Name: ");
                        assignmentName = scan.nextLine();
                        if (assignmentName.isEmpty() || assignmentName.equals(" ")) {
                            System.out.println("Please enter non empty inputs");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("Type: ");
                        assignmentType = scan.nextLine();
                        if (assignmentType.isEmpty() || assignmentType.equals(" ")) {
                            System.out.println("Please enter non empty inputs");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("Description: ");
                        assignmentDescription = scan.nextLine();
                        if (assignmentDescription.isEmpty() || assignmentDescription.equals(" ")) {
                            System.out.println("Please enter non empty inputs");
                        } else {
                            break;
                        }
                    }
                    while (true) {
                        System.out.println("ID Number: ");
                        assignmentID = scan.nextLine();
                        if (assignmentID.isEmpty() || assignmentID.equals(" ")) {
                            System.out.println("Please enter non empty inputs");
                        } else {
                            break;
                        }
                    }
                    System.out.println("percentage: ");
                    while (!scan.hasNextDouble()) {
                        System.out.println("Invalid input: Please enter a decimal number:");
                        scan.next();
                    }
                    double assignmentPercentage = scan.nextDouble();
                    scan.nextLine(); // clear buffer
                    String nameToStr = assignmentName.toString();
                    gb.createAssignment(nameToStr, assignmentType, assignmentDescription, assignmentID,
                            assignmentPercentage);
                    // Assignment deletion - must type Assignment name, ID to select for deletion
                } else if (option.toLowerCase().equals("d")) {
                    System.out.println("Please type the name of the assignment to delete:");
                    String givenName = scan.nextLine();
                    System.out.println("Please type the ID of the assignment to delete:");
                    String givenID = scan.nextLine();
                    gb.deleteAssignment(givenName, givenID);
                    // Assignment listing - no input required
                } else if (option.toLowerCase().equals("l")) {
                    gb.listAssignments();
                    // Assignment editing - must type name and ID of assignment before being able to
                    // edit
                } else if (option.toLowerCase().equals("e")) {
                    System.out.println("Please type the name of the assignment to edit:");
                    String givenName = scan.nextLine();
                    gb.editAssignment(givenName);
                    // Exit and end program with successful logout, persist data
                } else if (option.toLowerCase().equals("exit")) {
                    System.out.println("Exiting... \nlogged out successfully");
                    break;
                } else {
                    System.out.println("Option not available. Please choose a valid option: c, d, l, e, exit");
                }
            }
            // Command exceptions: please contact IT
        } catch (Exception e) {
            System.out.println("Error occurred. Please contact IT for assistance.");
        }
    }
}
