package ua.com.alevel.api.dto.response;

import ua.com.alevel.persistence.entity.Management;

public class ManagementResponseDto extends ResponseDto {

    private String managementName;
    private Integer staffCount;
    private Integer courseCount;

    public ManagementResponseDto() { }

    public ManagementResponseDto(Management management) {
        setId(management.getId());
        setCreated(management.getCreated());
        setUpdated(management.getUpdated());
        setVisible(management.getVisible());
        this.managementName = management.getManagementName();
        this.staffCount = management.getStaffCount();
    }

    public String getManagementName() {
        return managementName;
    }

    public void setManagementName(String managementName) {
        this.managementName = managementName;
    }

    public Integer getStaffCount() {
        return staffCount;
    }

    public void setStaffCount(Integer staffCount) {
        this.staffCount = staffCount;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    @Override
    public String toString() {
        return "ManagementResponseDto{" +
                "managementName='" + managementName + '\'' +
                ", staffCount=" + staffCount +
                ", courseCount=" + courseCount +
                '}';
    }
}
