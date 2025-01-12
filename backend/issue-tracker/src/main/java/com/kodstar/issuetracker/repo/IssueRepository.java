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

    @Query(value = "select * from t_issue i where i.issue_title like %:keyword%", nativeQuery = true)
    List<Issue> findALlByTitleKeyword(@Param("keyword") String keyword);

    @Query(value = "select * from t_issue i where  i.issue_description like %:keyword%", nativeQuery = true)
    List<Issue> findALlByDescKeyword(@Param("keyword") String keyword);

    @Query(value = "SELECT DISTINCT  i.*\n" +
            "FROM issue_tracker.t_issue  i\n" +
            "JOIN issue_tracker.t_issue_label il ON i.id = il.issue_id\n" +
            "JOIN t_label l ON il.label_id = l.id\n" +
            "WHERE EXISTS\n" +
            "  (SELECT DISTINCT  issue_id\n" +
            "  FROM issue_tracker.t_issue_label il\n" +
            "  JOIN issue_tracker.t_label l ON il.label_id = l.id\n" +
            "  WHERE l.label_name like %:keyword% \n" +
            "  AND il.issue_id = i.id)", nativeQuery = true)
    List<Issue> findALlIssuesByLabel(@Param("keyword") String keyword);

    List<Issue> findAllByOrderByCreateTime();

    List<Issue> findAllByOrderByCreateTimeDesc();

    List<Issue> findAllByOrderByUpdateTime();

    List<Issue> findAllByOrderByUpdateTimeDesc();


}
