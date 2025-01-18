package ch.heigvd.dai.model.entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Quest {

    private String name;
    private String description;
    private double requiredLevel;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private QuestType type;
    private Quest requiredQuest; // Dépendance

    public Quest(String name, String description, double requiredLevel,
                 LocalDateTime startDate, LocalDateTime endDate, String type) {
        this.name = name;
        this.description = description;
        this.requiredLevel = requiredLevel;
        this.startDate = startDate;
        this.endDate = endDate;
        QuestType questType = QuestType.valueOf(type);
    }

    public Quest(ResultSet rs) throws SQLException {
        this.name = rs.getString("nom");
        this.description = rs.getString("description");
        this.requiredLevel = rs.getDouble("niveauRequis");
        this.startDate = rs.getTimestamp("dateDebut") == null ? null : rs.getTimestamp("dateDebut").toLocalDateTime();
        this.endDate = rs.getTimestamp("dateFin") != null ? rs.getTimestamp("dateFin").toLocalDateTime() : null;
        String questTypeString = rs.getString("type");
        this.type = QuestType.valueOf(questTypeString);
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(double requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public QuestType getType() {
        return type;
    }

    public void setType(QuestType type) {
        this.type = type;
    }

    public Quest getRequiredQuest() {
        return requiredQuest;
    }

    public void setRequiredQuest(Quest requiredQuest) {
        this.requiredQuest = requiredQuest;
    }

    @Override
    public String toString() {
        return "Quest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type='" + type + '\'' +
                ", requiredQuest='" + requiredQuest + '\'' +
                '}';
    }
}
