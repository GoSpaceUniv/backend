package com.example.gospace.school.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class CsvService {

    public List<String[]> readCsv(String filePath) throws IOException, CsvException {
        try (
            FileInputStream fis = new FileInputStream(filePath);
            InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
            CSVReader reader = new CSVReader(isr)
        ) {
            return reader.readAll();
        }
    }
}
