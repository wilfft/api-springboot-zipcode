package com.wine.api.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wine.api.Entity.ZipcodeEntity;

@Repository
public interface ZipcodeRepository extends JpaRepository<ZipcodeEntity, Long> {
    List<ZipcodeEntity> findByOrderByFaixaInicioAsc();

}
