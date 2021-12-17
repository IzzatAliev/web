package ua.com.alevel.api.dto.request;

import ua.com.alevel.persistence.type.CourseType;

public class CourseRequestDto extends RequestDto {

    private String courseName;
    private CourseType courseType ;
    private Integer credit;
    private String description;

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "CourseRequestDto{" +
                "courseName='" + courseName + '\'' +
                ", courseType=" + courseType +
                ", credit=" + credit +
                ", description='" + description + '\'' +
                '}';
    }
}
