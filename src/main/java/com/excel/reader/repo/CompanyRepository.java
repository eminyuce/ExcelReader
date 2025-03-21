package com.excel.reader.repo;

import com.excel.reader.entities.Company;
import com.excel.reader.model.CompanyProjection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    List<CompanyProjection> findAllBy(); // or any custom query you want
}
