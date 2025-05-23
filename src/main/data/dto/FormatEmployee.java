package data.dto;

public class FormatEmployee {
    private String name, id, phoneNumber, birth, salary;

    public FormatEmployee(String name, String id, String phoneNumber, String birth, String salary) {
        this.name = name;
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
        this.salary = salary;
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
