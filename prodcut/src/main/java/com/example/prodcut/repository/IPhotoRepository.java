package com.example.prodcut.repository;


import com.example.prodcut.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo, Long> {
}
