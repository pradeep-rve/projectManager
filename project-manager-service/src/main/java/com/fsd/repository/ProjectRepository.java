package com.fsd.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fsd.entity.Project;

/**
 * Repository for Project
 * 
 * @author 449418
 *
 */

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

	List<Project> findAll();

	Project findByProjectId(int projectId);

	@Query(value = "SELECT pj FROM Project pj WHERE pj.user.userid = :userId")
	List<Project> getProjectsForUser(@Param("userId") int userId);

	@Modifying
	@Query(value = "delete from project where userid=:userId", nativeQuery = true)
	void deleteProjectForUser(@Param("userId") int userId);

}
