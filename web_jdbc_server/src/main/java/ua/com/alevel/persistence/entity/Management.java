package ua.com.alevel.persistence.entity;

public class Management extends BaseEntity {

    private String managementName;
    private Integer staffCount;

    public Management() {
        super();
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
}
