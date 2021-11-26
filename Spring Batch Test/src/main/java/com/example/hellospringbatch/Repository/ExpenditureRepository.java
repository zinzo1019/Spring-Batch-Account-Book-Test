package com.example.hellospringbatch.Repository;

import com.example.hellospringbatch.Entity.Expenditure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenditureRepository extends JpaRepository<Expenditure, String> {
}
