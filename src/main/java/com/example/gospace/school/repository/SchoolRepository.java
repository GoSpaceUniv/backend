package com.example.gospace.school.repository;

import com.example.gospace.school.entity.School;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, String> {

    List<School> findByAtptOfcdcScCodeOrderBySchoolNameAsc(final String code);

}
