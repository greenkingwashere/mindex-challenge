package com.mindex.challenge.data;

public class ReportingStructure {
    private Employee employee;
    private int numberOfReports;

    public ReportingStructure(){

    }

    public ReportingStructure(Employee employee){
        this.employee = employee;
        this.setNumberOfReports();
    }

    public Employee getEmployee(){
        return this.employee;
    }
    public void setEmployee(Employee employee){
        this.employee = employee;
    }


    public int getNumberOfReports(){return this.numberOfReports;}
    public void setNumberOfReports(int numberOfReports){
        this.numberOfReports = numberOfReports;
    }
    public void setNumberOfReports(){
        this.numberOfReports = this.employee.calculateTotalDirectReports();
    }


}
