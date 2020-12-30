package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Issue;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends CrudRepository<Issue, Long> {
    List<Issue> findAll();

    @Query(value = "select * from t_issue i where i.issue_title like %:keyword% or i.issue_description like %:keyword%", nativeQuery = true)
    List<Issue> findALlByTitleKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from t_issue i where  i.issue_description like %:keyword%", nativeQuery = true)
    List<Issue> findALlByDescKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from t_issue i inner JOIN t_issue_label il ON i.id=il.issue_id where il.label_id= :labelId", nativeQuery = true)
    List<Issue> findALlIssuesByLabel(@Param("labelId") Long labelId);

    List<Issue> findAll(Sort sort);
}
