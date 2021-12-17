package ua.com.alevel.api.dto.request;

public class ManagementRequestDto extends RequestDto {

    private String managementName;
    private Integer staffCount;

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

    @Override
    public String toString() {
        return "ManagementRequestDto{" +
                "managementName='" + managementName + '\'' +
                ", staffCount=" + staffCount +
                '}';
    }
}
