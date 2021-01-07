package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.auth.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

     User findByUsername(String username);

     @Modifying
     @Transactional
     @Query(value = "DELETE FROM t_issue_assignee WHERE user_id = :userId and issue_id = :issueId", nativeQuery = true)
     void removeAssigneeFromIssue(@Param("userId") Long userId, @Param("issueId") Long IssueId);
}