import java.util.*;

public class Assignment{
    String assignmentName;
    String type;
    String description;
    String ID;
    Double percentage;
    Double score;

    public Assignment() {
    };

    public Assignment(String tmpAssignmentName, String tmpType, String tmpDescription, String tmpID,
            double tmpPercentage, double tmpScore) {
        super();
        assignmentName = tmpAssignmentName;
        type = tmpType;
        description = tmpDescription;
        ID = tmpID;
        percentage = tmpPercentage;
        score = tmpScore;
    }

    public String getAssignmentName() {
        return this.assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Double getPercentage() {
        return this.percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }

    public Double getScore() {
        return this.score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
