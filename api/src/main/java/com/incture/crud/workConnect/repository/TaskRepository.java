package com.incture.crud.workConnect.repository;

import com.incture.crud.workConnect.entity.Task;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TaskRepository extends JpaRepository<Task,Integer>{
	@Query(value = "SELECT e FROM Task e WHERE e.receiver = :email AND e.platform = :platform")
    List<Task> findByEmailPlatform(@Param("email") String email, @Param("platform") String platform);
	
	@Query(value = "SELECT e FROM Task e WHERE e.receiver = :email AND e.isDeleted = 0 ORDER BY e.id DESC")
    List<Task> findByEmail(@Param("email") String email);
	
	@Query(value = "SELECT e.time FROM task e where e.platform='slack' ORDER BY e.time DESC LIMIT 1", nativeQuery=true)
    Date retrieveSlackLast();
	
	@Query(value = "SELECT e FROM Task e WHERE e.receiver = :email AND e.platform = :platform AND e.taskName =:taskName")
    Task checkAsana(@Param("email") String email, @Param("platform") String platform, @Param("taskName") String taskName);
	
	@Modifying
	@Query(value = "UPDATE Task e set e.status = :newStatus where e.id =:id")
    void updateStatus(@Param("id") int id, @Param("newStatus") int newStatus);
}
