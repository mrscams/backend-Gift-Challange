package com.glady.codinggame.repository;

import com.glady.codinggame.repository.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
