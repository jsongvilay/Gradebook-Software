import java.util.*;
import java.util.regex.*;

public class Gradebook {
    List<Assignment> assignmentArr = new ArrayList<Assignment>();

    public Gradebook() {
        super();
    }

    // Takes in AssignmentName: String, type: String, description: String, ID: Int,
    // percentage: Double and prints out successful creation
    // Add newly created Assignment to List<Assignment>
    public Assignment createAssignment(String assignmentName, String type, String description, String ID,
            double percentage) {
        System.out.println("\nAssignment Name: " + assignmentName);
        System.out.println("Type: " + type);
        System.out.println("Description: " + description);
        System.out.println("ID: " + ID);
        System.out.println("Percentage: " + percentage);
        Assignment assign = new Assignment(assignmentName, type, description, ID, percentage, 0.0);
        assignmentArr.add(assign);
        System.out.println(assignmentName + " created!");
        return null;
    }

    // Takes in Assignment Name, ID to select for deletion
    public void deleteAssignment(String givenName, String givenID) {
        boolean found = false;
        if (assignmentArr.size() > 0) {

            for (int i = 0; i < assignmentArr.size(); i++) {
                if (assignmentArr.get(i).assignmentName.equals(givenName) && assignmentArr.get(i).ID.equals(givenID)) {
                    System.out.println(assignmentArr.get(i).assignmentName + " was deleted\n");
                    found = true;
                    assignmentArr.remove(i);
                } else {
                    found = false;
                }
            }
            if (found == false) {
                System.out.println("Assignment \"" + givenName + "\" was not found");
            }
            if (assignmentArr.size() > 0) {
                System.out.println("Current assignments:");
                for (int i = 0; i < assignmentArr.size(); i++) {
                    System.out.println(assignmentArr.get(i).assignmentName);
                }
            } else {
                System.out.println("There are currently no assignments");
            }
        } else {
            System.out.println("There are currently no assignments:\nAssignment \"" + givenName
                    + "\" was not found\nPlease type l to see list of assignments");
        }
    }

    // List out existing assignments if any, sort by ascending or alpha order
    public void listAssignments() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Sort by: name or percentage?");
        String choice = scan.nextLine();

        System.out.println("Listing Assignments: ");
        for (int i = 0; i < assignmentArr.size(); i++) {
            if (choice.equals("name")) {
                Comparator<Assignment> compareByName = (Assignment o1, Assignment o2) -> o1.getPercentage()
                        .compareTo(o2.getPercentage());
                Collections.sort(assignmentArr, compareByName);
                System.out.println(assignmentArr.get(i).assignmentName + " " + assignmentArr.get(i).percentage);
            } else if (choice.equals("percentage")) {
                Comparator<Assignment> compareByPercentage = (Assignment o1, Assignment o2) -> o1.getPercentage()
                        .compareTo(o2.getPercentage());
                Collections.sort(assignmentArr, compareByPercentage);
                System.out.println(assignmentArr.get(i).percentage + " " + assignmentArr.get(i).assignmentName);
            } else {
                System.out.println("Sorting field not found. Please try again.");
            }
        }
    }

    // Takes in Assignment Name to select for editing
    public void editAssignment(String givenName) {
        Scanner scan = new Scanner(System.in);
        boolean found = false;
        int index = 0;
        if (assignmentArr.size() > 0) {
            for (int i = 0; i < assignmentArr.size(); i++) {
                if (assignmentArr.get(i).assignmentName.equals(givenName)) {
                    index = i;
                    found = true;
                } else {
                    System.out.println("Assignment not found: please enter an existing assignment");
                }
            }
            while (found == true) {
                System.out.println("Please enter a field to edit or type done to finish editing");
                String fieldToEdit = scan.nextLine();
                if (fieldToEdit.toLowerCase().equals("name")) {
                    System.out.println("Name selected. Please enter a new name");
                    String newName = scan.nextLine();
                    assignmentArr.get(index).setAssignmentName(newName);
                    System.out.println("New name: " + newName);
                } else if (fieldToEdit.toLowerCase().equals("type")) {
                    System.out.println("Type selected. Please enter a new type");
                    String newType = scan.nextLine();
                    assignmentArr.get(index).setType(newType);
                    System.out.println("New type: " + newType);
                } else if (fieldToEdit.toLowerCase().equals("description")) {
                    System.out.println("Description selected. Please enter a new description");
                    String newDescription = scan.nextLine();
                    assignmentArr.get(index).setDescription(newDescription);
                    System.out.println("New description: " + newDescription);
                } else if (fieldToEdit.toLowerCase().equals("id")) {
                    String newID = "";
                    while (true) {
                        System.out.println("ID selected. Please enter a new ID");
                        newID = scan.nextLine();
                        if (!isOnlyDigits(newID)) {
                            System.out.println("Please enter non decimal digits");
                        } else {
                            break;
                        }
                    }
                    assignmentArr.get(index).setID(newID);
                    System.out.println("New ID: " + newID);
                } else if (fieldToEdit.toLowerCase().equals("percentage")) {
                    System.out.println("Percentage selected. Please enter a new percentage");
                    while (!scan.hasNextDouble()) {
                        System.out.println("Invalid input: Please enter a decimal number:");
                        scan.next();
                    }
                    double newPercentage = scan.nextDouble();
                    scan.nextLine();
                    assignmentArr.get(index).setPercentage(newPercentage);
                    System.out.println("New percentage: " + newPercentage);
                } else if (fieldToEdit.toLowerCase().equals("done")) {
                    System.out.println("Finished editing. Updated Assignment values...\nName: "
                            + assignmentArr.get(index).assignmentName);
                    System.out.println("Type: " + assignmentArr.get(index).type);
                    System.out.println("Description: " + assignmentArr.get(index).description);
                    System.out.println("ID: " + assignmentArr.get(index).ID);
                    System.out.println("Percentage: " + assignmentArr.get(index).percentage);
                    found = false;
                }
            }
        } else

        {
            System.out.println("There are currently no assignments:\nAssignment \"" + givenName + "\" was not found");
        }
    }

    public static boolean isOnlyDigits(String str) {
        // check if string is only digits
        String regex = "[0-9]+";
        Pattern p = Pattern.compile(regex);

        if (str == null) {
            return false;
        }
        // check if string matches regex
        Matcher m = p.matcher(str);

        // return true if contains only numbers
        return m.matches();
    }
}
