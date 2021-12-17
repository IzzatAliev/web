package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.Student;

public class StudentResponseDto extends ResponseDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer courseCount;

    public StudentResponseDto() {

    }

    public StudentResponseDto(Student student) {
       setId(student.getId());
       setCreated(student.getCreated());
       setUpdated(student.getUpdated());
       setVisible(student.getVisible());
       this.firstName = student.getFirstName();
       this.lastName = student.getLastName();
       this.age = student.getAge();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    @Override
    public String toString() {
        return "StudentResponseDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", courseCount=" + courseCount +
                '}';
    }
}
