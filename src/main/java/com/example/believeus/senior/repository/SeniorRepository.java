package com.example.believeus.senior.repository;

import com.example.believeus.senior.QSenior;
import com.example.believeus.senior.Senior;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.stereotype.Repository;

@Repository
public interface SeniorRepository extends
        JpaRepository<Senior, Long>,
        QuerydslPredicateExecutor<Senior>,
        QuerydslBinderCustomizer<QSenior> {
    
}
