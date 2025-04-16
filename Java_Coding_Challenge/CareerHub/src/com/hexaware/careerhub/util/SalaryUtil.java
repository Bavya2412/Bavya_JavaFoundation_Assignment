package com.hexaware.careerhub.util;

import com.hexaware.careerhub.entity.JobListing;
import com.hexaware.careerhub.exception.NegativeSalaryException;
import java.util.List;

public class SalaryUtil {
    public static double calculateAverageSalary(List<JobListing> jobListings) throws NegativeSalaryException {
        double totalSalary = 0;
        int count = 0;

        for (JobListing job : jobListings) {
            if (job.getSalary() < 0) {
                throw new NegativeSalaryException("Negative salary found in Job ID: " + job.getJobID());
            }
            totalSalary += job.getSalary();
            count++;
        }

        return (count > 0) ? totalSalary / count : 0;
    }
}
