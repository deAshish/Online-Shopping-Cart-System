package miu.edu.pm.project.onlineshoppingcartsystem.report.service.impl;


import lombok.RequiredArgsConstructor;
import miu.edu.pm.project.onlineshoppingcartsystem.email.dto.EmailDto;
import miu.edu.pm.project.onlineshoppingcartsystem.email.service.EmailService;
import miu.edu.pm.project.onlineshoppingcartsystem.email.service.impl.EmailServiceImpl;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.model.ItemList;
import miu.edu.pm.project.onlineshoppingcartsystem.orderline.repository.ItemListRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.report.dto.ReportDto;
import miu.edu.pm.project.onlineshoppingcartsystem.report.service.ReportService;
import miu.edu.pm.project.onlineshoppingcartsystem.transaction.model.Transaction;
import miu.edu.pm.project.onlineshoppingcartsystem.transaction.repository.TransactionRepository;
import miu.edu.pm.project.onlineshoppingcartsystem.user.model.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    @Value("${spring.env.abc.email}")
    private String fivelayersteam;
    @Value("${spring.env.tax}")
    private Double tax;

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    ItemListRepository itemListRepository;

    private JasperPrint getJasperPrint(List<ReportDto> prodCollection, String resourceLocation, String vendorName) throws FileNotFoundException, JRException {
        File file = ResourceUtils.getFile(resourceLocation);
        JasperReport jasperReport = JasperCompileManager
                .compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new
                JRBeanCollectionDataSource(prodCollection);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("author", "Temka");
        parameters.put("vendor", vendorName);

        JasperPrint jasperPrint = JasperFillManager
                .fillReport(jasperReport, parameters, dataSource);

        return jasperPrint;
    }

    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
        String uploadDir = StringUtils.cleanPath("./generated-reports");
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
        }
        //generate the report and save it in the just created folder
        if (fileFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(
                    jasperPrint, uploadPath+fileName
            );
        }

        return uploadPath;
    }

    private String getPdfFileLink(String uploadPath){
        return uploadPath+"/"+"transaction.pdf";
    }

    @Override
    public String generateReport(LocalDate localDate, String fileFormat) throws JRException, IOException {
        List<ItemList> items = itemListRepository.findOrderedItems();
        Map<Long, List<ReportDto>> productMap = new HashMap<>();
        Map<Long, User> vendorMap = new HashMap<>();

        for(ItemList item : items) {
            ReportDto reportDto = new ReportDto();
            reportDto.setId(item.getProduct().getId());
            reportDto.setColor(item.getProduct().getColor());
            reportDto.setName(item.getProduct().getName());
            if(item.getProduct().getDescription() != null && !item.getProduct().getDescription().equals(null)) {
                reportDto.setDescription(item.getProduct().getDescription());
            }
            reportDto.setPrice(item.getProduct().getPrice());
            reportDto.setQuantity(item.getQuantity());
            reportDto.setStatus(item.getPurchaseStatus().name());
            reportDto.setVendor_id(item.getProduct().getVendor().getId());
            reportDto.setVendorName(item.getProduct().getVendor().getUsername());
            reportDto.setOrderDate(LocalDate.now().toString());
            reportDto.setSubTotal(item.getProduct().getPrice() * item.getQuantity());
            reportDto.setTax(tax);
            reportDto.setTaxedPrice(item.getProduct().getPrice() - (item.getProduct().getPrice() * tax / 100));
            reportDto.setTaxedSubTotal((item.getProduct().getPrice() - (item.getProduct().getPrice() * tax / 100)) * item.getQuantity());

            long key = item.getProduct().getVendor().getId();
            if(productMap.containsKey(key)) {
                List<ReportDto> list = productMap.get(key);
                list.add(reportDto);
                productMap.put(key, list);
            } else {
                productMap.put(key, new ArrayList<>(Arrays.asList(reportDto)));
            }

            vendorMap.put(key, item.getProduct().getVendor());
        }

        //load the file and compile it
        String resourceLocation = "classpath:jasper_template.jrxml";

        //create a folder to store the report
        String fileName = "/"+"transaction.pdf";

        for(Long key : productMap.keySet()) {

            JasperPrint jasperPrint = getJasperPrint(productMap.get(key), resourceLocation, vendorMap.get(key).getUsername());
            Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);

            //create a private method that returns the link to the specific pdf file
            String fileLinke = getPdfFileLink(uploadPath.toString());
            // TODO COMMENT WHEN FINAL sending email
             sendEmail(vendorMap.get(key).getEmail(), fileLinke);
        }
        return "";
    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    private void sendEmail(String toAddress, String fileLinke) {
        EmailDto email = new EmailDto();
        email.setFromAddress(fivelayersteam);
        email.setToAddress(toAddress);
        email.setMailSubject("FiveLayers  montly report");
        email.setBodyText("Hi there");
        email.setAttachFileAddress("./"+fileLinke);
        EmailService emailService = new EmailServiceImpl();
        emailService.sendEmail(email);
    }
}
