package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;

public interface CompensationService {
    Compensation read(String id);
    Compensation create(Compensation compensation);
}
