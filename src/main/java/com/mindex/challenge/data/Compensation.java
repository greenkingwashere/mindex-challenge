package com.mindex.challenge.data;

import java.util.UUID;

public class Compensation {
    private String compensationId;
    private Employee employee;
    private int salary;
    private String effectiveDate;

    public Compensation(){
        this.setCompensationId();
    }

    public String getCompensationId(){
        return this.compensationId;
    }
    public void setCompensationId(String compensationId) {
        this.compensationId = compensationId;
    }
    public void setCompensationId(){this.compensationId = UUID.randomUUID().toString();}

    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }
    public int getSalary(){
        return this.salary;
    }
    public void setSalary(int salary){
        this.salary = salary;
    }
    public String getEffectiveDate(){
        return this.effectiveDate;
    }
    public void setEffectiveDate(String effectiveDate){
        this.effectiveDate = effectiveDate;
    }



}
