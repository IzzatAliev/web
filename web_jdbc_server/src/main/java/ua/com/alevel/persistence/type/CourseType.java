package ua.com.alevel.persistence.type;

public enum CourseType {

    IT("IT"),
    MATHEMATICS("MATHEMATICS"),
    LITERATURE("LITERATURE"),
    CHEMISTRY("CHEMISTRY"),
    BIOLOGY("BIOLOGY"),
    PHYSICS("PHYSICS"),
    GEOGRAPHY("GEOGRAPHY");

    private final String type;

    CourseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
