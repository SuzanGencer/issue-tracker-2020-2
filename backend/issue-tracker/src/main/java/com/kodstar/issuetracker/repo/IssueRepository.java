package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Issue;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Moon on 12/20/2020
 */

@Repository
public interface IssueRepository extends CrudRepository<Issue,Long> {

}
