package com.kodstar.issuetracker.repository;


import com.kodstar.issuetracker.entity.Issue;
import com.kodstar.issuetracker.entity.Label;
import com.kodstar.issuetracker.repo.IssueRepository;
import com.kodstar.issuetracker.service.IssueService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class IssueRepositoryTests {

    private Issue issue;

    @Autowired
    IssueRepository issueRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testUpdateTime() {

        issue = new Issue();
        issue.setTitle("Test");
        issue.setDescription("Test text");

        entityManager.persist(issue);
        entityManager.flush();

        issue.setTitle("newTestTitle");

        entityManager.persist(issue);
        entityManager.flush();

        Assert.assertNotNull(issue.getUpdateDateTime());
    }
}
