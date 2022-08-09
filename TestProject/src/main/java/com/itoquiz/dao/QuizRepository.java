package com.itoquiz.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.itoquiz.entity.Candidate;
import com.itoquiz.entity.Question;

@Repository
public interface QuizRepository extends JpaRepository<Question, Integer> {// We need to specify <Model Name , Datatype of the prirmary key>

}
