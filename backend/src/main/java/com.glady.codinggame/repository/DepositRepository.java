package com.glady.codinggame.repository;

import java.math.BigDecimal;

import com.glady.codinggame.repository.entity.DepositEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;


@NoRepositoryBean
public interface DepositRepository<E extends DepositEntity> extends JpaRepository<E, Long> {

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM #{#entityName} e WHERE e.employeeId = :employeeId AND CURRENT_DATE < e.expirationDate")
    BigDecimal getUserBalance( @Param("employeeId") Long employeeId);

}
