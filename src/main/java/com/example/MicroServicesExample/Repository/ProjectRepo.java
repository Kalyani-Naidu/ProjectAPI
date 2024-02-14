package com.example.MicroServicesExample.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MicroServicesExample.Entity.ProjectEntity;

public interface ProjectRepo extends JpaRepository<ProjectEntity, Integer>{

}
