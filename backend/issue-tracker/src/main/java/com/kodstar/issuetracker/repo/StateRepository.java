package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.State;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends CrudRepository<State,Long> {
}
