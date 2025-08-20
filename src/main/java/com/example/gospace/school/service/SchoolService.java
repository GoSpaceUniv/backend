package com.example.gospace.school.service;

import com.example.gospace.common.error.ErrorCode;
import com.example.gospace.common.exception.BusinessException;
import com.example.gospace.school.dto.SchoolResponseDto;
import com.example.gospace.school.entity.School;
import com.example.gospace.school.repository.SchoolRepository;
import com.opencsv.exceptions.CsvException;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolService {

    private static final String SCHOOL = "고등학교";
    private final CsvService csvService;
    private final SchoolRepository schoolRepository;
    @Value("${school.csv.file_path}")
    private String FILE_PATH;

    @PostConstruct
    private void init() {
        try {
            importCsv(FILE_PATH);
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
    }

    private void importCsv(final String filePath) throws IOException, CsvException {
        final List<String[]> rows = csvService.readCsv(filePath);

        rows.remove(0);
        final List<School> schools = rows.stream()
            .map(School::mapToEntity).
            filter(school -> school.getSchoolKindName().equals(SCHOOL))
            .collect(Collectors.toList());
        schoolRepository.saveAll(schools);
    }

    public ResponseEntity<?> findMySchool(final String schoolType) {
        final List<School> schools = schoolRepository.findByAtptOfcdcScCodeOrderBySchoolNameAsc(
            schoolType);
        if (schools.isEmpty()) {
            throw new BusinessException(ErrorCode.SCHOOL_CODE_NOT_FOUND);
        }
        List<SchoolResponseDto> response = schools.stream()
            .map(School::toResponse)
            .toList();

        if (response.isEmpty()) {
            throw new BusinessException(ErrorCode.SCHOOL_CODE_NOT_FOUND);
        }
        return ResponseEntity.ok(response);
    }
}
