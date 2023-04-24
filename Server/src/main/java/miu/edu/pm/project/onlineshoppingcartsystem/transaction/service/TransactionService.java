package miu.edu.pm.project.onlineshoppingcartsystem.transaction.service;


import net.sf.jasperreports.engine.JRException;

import java.io.FileNotFoundException;

public interface TransactionService {

    Double getTotalRecAmountByUserRole(String userRole);
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException;

}
