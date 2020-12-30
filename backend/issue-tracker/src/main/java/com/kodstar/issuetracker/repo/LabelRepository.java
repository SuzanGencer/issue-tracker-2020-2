package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM t_issue_label WHERE label_id = :labelId and issue_id = :issueId", nativeQuery = true)
    void removeLabelFromIssue(@Param("labelId") Long labelId, @Param("issueId") Long IssueId);
}
