package com.mindurownbusiness.springboottutorial.springboottutorial.repositary;

import com.mindurownbusiness.springboottutorial.springboottutorial.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeReepository extends JpaRepository<EmployeeEntity, Long> {

}
