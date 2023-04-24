package miu.edu.pm.project.onlineshoppingcartsystem.report.service;

import miu.edu.pm.project.onlineshoppingcartsystem.transaction.model.Transaction;
import net.sf.jasperreports.engine.JRException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException;
    List<Transaction> findAllTransactions();
}
