package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.Course;
import ua.com.alevel.persistence.type.CourseType;

public class CourseResponseDto extends ResponseDto {

    private String courseName;
    private Integer credit;
    private CourseType courseType ;
    private String description;
    private Integer managementCount;
    private Integer studentCount;

    public CourseResponseDto() {
        this.managementCount = 0;
        this.studentCount = 0;
    }

    public CourseResponseDto(Course course) {
        this();
        setId(course.getId());
        setCreated(course.getCreated());
        setUpdated(course.getUpdated());
        setVisible(course.getVisible());
        this.courseName = course.getCourseName();
        this.credit = course.getCredit();
        this.courseType = course.getCourseType();
        this.description = course.getDescription();
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getManagementCount() {
        return managementCount;
    }

    public void setManagementCount(Integer managementCount) {
        this.managementCount = managementCount;
    }

    public Integer getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(Integer studentCount) {
        this.studentCount = studentCount;
    }

    @Override
    public String toString() {
        return "CourseResponseDto{" +
                "courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", courseType=" + courseType +
                ", description='" + description + '\'' +
                ", managementCount=" + managementCount +
                ", studentCount=" + studentCount +
                '}';
    }
}
