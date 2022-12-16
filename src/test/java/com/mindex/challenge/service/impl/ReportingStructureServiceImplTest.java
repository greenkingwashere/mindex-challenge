package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.CompensationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingUrl;
    private String employeeUrl;



    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        reportingUrl = "http://localhost:" + port + "/reportingstructure/{id}";
        employeeUrl = "http://localhost:" + port + "/employee";
    }

    @Test
    public void testRead(){



        Employee underEmployeeOne = new Employee();
        underEmployeeOne.setEmployeeId("81bbfcfb-eaed-4b30-84df-ac5ee8ed95e2");
        underEmployeeOne.setFirstName("Kim");
        underEmployeeOne.setLastName("Lee");
        underEmployeeOne.setDepartment("Administration");
        underEmployeeOne.setPosition("Intern");
        underEmployeeOne.setDirectReports(new Employee[0]);
        restTemplate.postForEntity(employeeUrl, underEmployeeOne, Employee.class);

        Employee underEmployeeTwo = new Employee();
        underEmployeeTwo.setEmployeeId("7596d445-c358-4e72-80d1-761d80a00c94");
        underEmployeeTwo.setFirstName("Trisha");
        underEmployeeTwo.setLastName("Plough");
        underEmployeeTwo.setDepartment("Administration");
        underEmployeeTwo.setPosition("Intern");
        underEmployeeTwo.setDirectReports(new Employee[0]);
        restTemplate.postForEntity(employeeUrl, underEmployeeTwo, Employee.class);

        Employee underEmployeeThree = new Employee();
        underEmployeeThree.setEmployeeId("66779344-b59f-42fb-8c8c-fcf724fe53de");
        underEmployeeThree.setFirstName("Josh");
        underEmployeeThree.setLastName("Jacobs");
        underEmployeeThree.setDepartment("Administration");
        underEmployeeThree.setPosition("Assistant");
        underEmployeeThree.setDirectReports(new Employee[]{underEmployeeTwo, underEmployeeOne});
        restTemplate.postForEntity(employeeUrl, underEmployeeThree, Employee.class);

        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("ada98a2c-c0f3-431c-b80e-1509f4b41057");
        testEmployee.setFirstName("Joe");
        testEmployee.setLastName("Mama");
        testEmployee.setDepartment("Administration");
        testEmployee.setPosition("CFO");
        testEmployee.setDirectReports(new Employee[]{underEmployeeThree});
        restTemplate.postForEntity(employeeUrl, testEmployee, Employee.class);

        ReportingStructure testReportingStructure = new ReportingStructure(testEmployee);


        ReportingStructure readReportingStructure = restTemplate.getForEntity(reportingUrl, ReportingStructure.class, testEmployee.getEmployeeId()).getBody();
        assertEquals(testReportingStructure.getEmployee().getEmployeeId(), readReportingStructure.getEmployee().getEmployeeId());
        assertEquals(readReportingStructure.getNumberOfReports(), 3); //correct sum of direct reports

    }

}
