package com.kodstar.issuetracker.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.Modifying;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@SQLDelete(sql = "UPDATE t_issue SET issue_status = 'DELETED' WHERE id = ?")
@Where(clause = "issue_status <> 'DELETED'") // '<>' meaning is NOT
@Table(name = "t_issue")
@EntityListeners(AuditingEntityListener.class)
public class Issue implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "issue_title", unique = true, columnDefinition = "varchar(250)")
    @NotBlank(message = "invalid input, Title can't be null")
    private String title;

    @Column(name = "issue_description", columnDefinition = "varchar(1500)")
    private String description;

    @Column(name = "issue_status")
    @Enumerated(EnumType.STRING)
    private IssueStatus issueStatus = IssueStatus.ACTIVE;//default

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "t_issue_label", joinColumns = @JoinColumn(name = "issue_id"), inverseJoinColumns = @JoinColumn(name = "label_id"))
    private Set<Label> labels = new HashSet<>();

    @OneToMany
    @JoinTable(
            name="IssueComments",
            joinColumns = @JoinColumn( name="issue_id"),
            inverseJoinColumns = @JoinColumn( name="comment_id")
    )
    private List<Comment> comments;

    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime createTime;


}
