package com.example.prodcut.repository;

import com.example.prodcut.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product , Long> {

    @Query("select p from Product p order by p.created_at desc ")
    public List<Product> findProductsLatest();

    List<Product> findAll(Specification<Product> spec);

    List<Product> findAllByBrandId(Long id);

    @Query("select MONTH(p.created_at) as month, count(p.id) from Product p group by month")
    public List<Object[]> getJobsByMonth();



}
