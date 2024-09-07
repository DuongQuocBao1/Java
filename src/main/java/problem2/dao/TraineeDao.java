package problem2.dao;

import problem2.entities.Trainee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TraineeDao {
    private static final String FILE_NAME = "trainee-data.xlsx";

    public TraineeDao() throws IOException {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet();
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Trainee ID");
            headerRow.createCell(1).setCellValue("Account");
            headerRow.createCell(2).setCellValue("Full Name");
            headerRow.createCell(3).setCellValue("Birth Date");
            headerRow.createCell(4).setCellValue("Gender");
            headerRow.createCell(5).setCellValue("Status");
            headerRow.createCell(6).setCellValue("GPA");

            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            workbook.close();
            outputStream.close();
        }
    }

    public static void save(Trainee trainee) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();

        Row row = sheet.createRow(++rowNum);
        row.createCell(0).setCellValue(trainee.getTraineeId());
        row.createCell(1).setCellValue(trainee.getAccount());
        row.createCell(2).setCellValue(trainee.getFullName());
        row.createCell(3).setCellValue(trainee.getBirthDate());
        row.createCell(4).setCellValue(trainee.getGender());
        row.createCell(5).setCellValue(trainee.getStatus());
        row.createCell(6).setCellValue(trainee.getGpa());
        excelFile.close();

        FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
    public static void update(String traineeId, Trainee updatedTrainee) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        boolean isUpdated = false;

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() != 0) {
                String currentId = row.getCell(0).getStringCellValue();
                if (currentId.equals(traineeId)) {
                    row.getCell(1).setCellValue(updatedTrainee.getAccount());
                    row.getCell(2).setCellValue(updatedTrainee.getFullName());
                    row.getCell(3).setCellValue(updatedTrainee.getBirthDate());
                    row.getCell(4).setCellValue(updatedTrainee.getGender());
                    row.getCell(5).setCellValue(updatedTrainee.getStatus());
                    row.getCell(6).setCellValue(updatedTrainee.getGpa());
                    isUpdated = true;
                    break;
                }
            }
        }
        if (isUpdated) {
            FileOutputStream outputStream = new FileOutputStream(FILE_NAME);
            workbook.write(outputStream);
            outputStream.close();
            workbook.close();
            excelFile.close();
        } else {
            workbook.close();
            excelFile.close();
            throw new IllegalArgumentException("Trainee with ID " + traineeId + " not found.");
        }
    }
    public static List<Trainee> findIncompletedTrainee() throws IOException {
        List<Trainee> incompletedTrainees = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() != 0) { // Skip header row
                double gpa = row.getCell(6).getNumericCellValue();
                if (gpa < 60) {
                    Trainee trainee = new Trainee(
                            row.getCell(0).getStringCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            (int) row.getCell(3).getNumericCellValue(),
                            (int) row.getCell(4).getNumericCellValue(),
                            gpa
                    );
                    incompletedTrainees.add(trainee);
                }
            }
        }
        workbook.close();
        excelFile.close();
        return incompletedTrainees;
    }

    public static List<Trainee> findExcellentTrainee() throws IOException {
        List<Trainee> excellentTrainees = new ArrayList<>();
        FileInputStream excelFile = new FileInputStream(FILE_NAME);
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() != 0) { // Skip header row
                double gpa = row.getCell(6).getNumericCellValue();
                if (gpa >= 90 && gpa <= 100) {
                    Trainee trainee = new Trainee(
                            row.getCell(0).getStringCellValue(),
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            (int) row.getCell(3).getNumericCellValue(),
                            (int) row.getCell(4).getNumericCellValue(),
                            gpa
                    );
                    excellentTrainees.add(trainee);
                }
            }
        }
        workbook.close();
        excelFile.close();
        return excellentTrainees;
    }
    public Trainee findById(String traineeId) throws IOException {
        FileInputStream excelFile = new FileInputStream(new File(FILE_NAME));
        Workbook workbook = new XSSFWorkbook(excelFile);
        Sheet sheet = workbook.getSheetAt(0);
        Trainee trainee = null;

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() != 0) { // Skip header row
                String currentId = row.getCell(0).getStringCellValue();
                if (currentId.equals(traineeId)) {
// Assuming Trainee has a constructor that takes all these parameters
                    trainee = new Trainee(
                            currentId,
                            row.getCell(1).getStringCellValue(),
                            row.getCell(2).getStringCellValue(),
                            (int) row.getCell(3).getNumericCellValue(),
                            (int) row.getCell(4).getNumericCellValue(),
                            row.getCell(6).getNumericCellValue()
                    );
//                    System.out.println(currentId);

                    break;
                }
            }
        }
        workbook.close();
        excelFile.close();
        return trainee;
    }
}