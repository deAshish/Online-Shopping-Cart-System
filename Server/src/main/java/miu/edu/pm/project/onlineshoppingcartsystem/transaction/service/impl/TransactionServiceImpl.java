
package miu.edu.pm.project.onlineshoppingcartsystem.transaction.service.impl;

import miu.edu.pm.project.onlineshoppingcartsystem.report.model.TransactionReport;
import miu.edu.pm.project.onlineshoppingcartsystem.transaction.model.Transaction;
import miu.edu.pm.project.onlineshoppingcartsystem.transaction.repository.TransactionRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.transaction.service.TransactionService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Double getTotalRecAmountByUserRole(String userRole) {
        Double total = transactionRepository.getTotalRecAmountByUserRole(userRole);
        System.out.println("------------------------------"+total+"------------");
        return total;
    }

    @Override
    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = "/Users/temuujintsogt/Desktop/ReportPM//.";
        List<Transaction> transactionReport= transactionRepository.findAll();
        List<TransactionReport> finalReports = new ArrayList<TransactionReport>();

        for (Transaction obj : transactionReport) {
            TransactionReport summary = new TransactionReport( obj.getId(), obj.getConcept(),obj.getAmount(),obj.getDateShipped().toString());
            finalReports.add(summary);
        }
        System.out.println(finalReports);
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:transaction.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(finalReports);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Binod Kathayat");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "transactionReport.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "transactionReport.pdf");
        }
        return "File is downloaded in the following path: " +transactionReport;
    }

}
