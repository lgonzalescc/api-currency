package com.prueba.apicurrency.domain.repository;

import com.prueba.apicurrency.domain.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository  extends JpaRepository<Conversion, Long> {
}
