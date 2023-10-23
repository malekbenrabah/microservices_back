package com.example.category.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(unique = true )
    private String name;

   // @OneToMany(fetch = FetchType.EAGER,mappedBy = "category")
    //private List<SCategory> scategories;



    public Category(String name) {
        this.name = name;
    }
}
