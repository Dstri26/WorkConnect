package com.incture.crud.workConnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.incture.crud.workConnect.entity.Admin;



public interface AdminRepository extends JpaRepository<Admin,Integer>{
	
	@Query(value = "SELECT e FROM Admin e WHERE e.email = :email AND e.password = :password")
    Admin findByEmailPass(@Param("email") String email, @Param("password") String password);
	
	@Query(value = "SELECT e FROM Admin e WHERE e.email = :email")
    Admin findByEmail(@Param("email") String email);

}
