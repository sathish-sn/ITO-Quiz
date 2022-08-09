package com.itoquiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itoquiz.entity.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

}
