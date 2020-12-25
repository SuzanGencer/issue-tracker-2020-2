package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends CrudRepository<Label, Long> {
}
