package com.mindex.challenge.data;

import java.util.List;
import java.util.UUID;

public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String position;
    private String department;
    private Employee[] directReports;

    public Employee() {
        this.setEmployeeId();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public void setEmployeeId(){
        this.employeeId = UUID.randomUUID().toString();
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Employee[] getDirectReports() {
        return directReports;
    }

    public void setDirectReports(Employee[] directReports) {
        this.directReports = directReports;
    }

    public int calculateTotalDirectReports(){
        //gets the total sum of all employees reporting under this employee. WARNING: if an employee is mistakenly put into the same reporting structure more than once this can cause this function to go into an infinite loop
        int total = 0;
        for (int i = 0; i < this.directReports.length; i++){
            total = total + this.directReports[i].calculateTotalDirectReports() + 1;
        }
        return total;
    }
}
