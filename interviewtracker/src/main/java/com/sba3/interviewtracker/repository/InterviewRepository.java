package com.sba3.interviewtracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sba3.interviewtracker.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

	public Interview findByInterviewName(String interviewName);
	public List<Interview> findByInterviewerName(String interviewerName);
}
