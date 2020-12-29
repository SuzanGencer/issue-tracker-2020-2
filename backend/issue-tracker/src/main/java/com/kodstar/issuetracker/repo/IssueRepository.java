package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Issue;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {
    List<Issue> findAll();

    @Query(value="select * from t_issue i where i.issue_title like %:keyword%", nativeQuery=true)
    List<Issue> findALlByKeyword(@Param("keyword") String keyword);
}
