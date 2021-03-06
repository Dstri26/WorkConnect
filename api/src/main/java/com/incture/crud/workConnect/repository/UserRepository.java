package com.incture.crud.workConnect.repository;
import com.incture.crud.workConnect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query(value = "SELECT e FROM User e WHERE e.email = :email AND e.password = :password")
    User findByEmailPass(@Param("email") String email, @Param("password") String password);
	
	@Query(value = "SELECT e FROM User e WHERE e.email = :email")
    User findByEmail(@Param("email") String email);
	
	
	@Query(value = "SELECT SUM(is_completed) FROM task WHERE receiver = :email",nativeQuery = true)
	Integer findTaskCompletedByEmail(@Param("email") String email);
	
	@Query(value = "SELECT COUNT(*) FROM task WHERE receiver = :email",nativeQuery = true)
	Integer findTotalTaskByEmail(@Param("email") String email);
	
}
