package com.kodstar.issuetracker.repo;

import com.kodstar.issuetracker.entity.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment,Long> {
}
