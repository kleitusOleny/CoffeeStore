package data.dto;

public class EmployeeDTO {
    private String name, id, phoneNumber, birth, salary;
    private String identifyNumber, address, startingDate, shift;

    public EmployeeDTO(String name, String id, String phoneNumber, String identifyNumber, String address, String birth, String startingDate, String shift, String salary) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.identifyNumber = identifyNumber;
        this.address = address;
        this.birth = birth;
        this.startingDate = startingDate;
        this.shift = shift;
        this.salary = salary;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
