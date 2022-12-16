package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
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
public class CompensationServiceImplTest {
    private String compensationUrl;
    private String compensationIdUrl;


    @Autowired
    private CompensationService compensationService;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Before
    public void setup() {
        compensationUrl = "http://localhost:" + port + "/compensation";
        compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
    }
    @Test
    public void testCreateRead() {
        Employee testEmployee = new Employee();
        testEmployee.setEmployeeId("46ab14b3-7548-4c96-a73f-2f2077992ffb");
        testEmployee.setFirstName("Larry");
        testEmployee.setLastName("Smith");
        testEmployee.setDepartment("Administration");
        testEmployee.setPosition("Intern");

        Compensation testCompensation = new Compensation();
        testCompensation.setEmployee(testEmployee);
        testCompensation.setSalary(25000);
        testCompensation.setEffectiveDate("12/25/2022");



        //verify create
        Compensation createdCompensation = restTemplate.postForEntity(compensationUrl, testCompensation, Compensation.class).getBody();
        assertNotNull(createdCompensation.getCompensationId());
        assertCompensationEquals(testCompensation, createdCompensation);

        // Read checks
        Compensation readCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, createdCompensation.getCompensationId()).getBody();
        assertEquals(createdCompensation.getCompensationId(), readCompensation.getCompensationId());
        assertCompensationEquals(createdCompensation, readCompensation);


    }



    private static void assertCompensationEquals(Compensation first, Compensation second){
        EmployeeServiceImplTest.assertEmployeeEquivalence(first.getEmployee(), second.getEmployee());
        assertEquals(first.getSalary(), second.getSalary());
        assertEquals(first.getEffectiveDate(), second.getEffectiveDate());
    }

}
