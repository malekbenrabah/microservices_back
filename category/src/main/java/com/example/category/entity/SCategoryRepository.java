package com.example.category.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SCategoryRepository extends JpaRepository<SCategory, Long> {
    List<SCategory> findByCategory_Id(Long id);

    Optional<SCategory> findById(Long id);
}
