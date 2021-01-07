INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (100, 'Description1', 'ACTIVE', 'Title1', '2020-09-04', '2020-10-08');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (200, 'Description2', 'ACTIVE', 'Title2', '2020-03-10', '2020-04-08');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (300, '3Description', 'DELETED', '3Title', '2020-01-01', '2021-01-02');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (400, 'backend project', 'ACTIVE', 'sort', '2020-05-04', '2020-08-08');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (500, 'web project', 'ACTIVE', 'Filter', '2020-09-04', '2020-11-08');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (600, 'My issue', 'ACTIVE', 'Issue1', '2020-02-12', '2020-10-12');
INSERT INTO t_issue (id, issue_description, issue_status, issue_title, create_time, update_time)
VALUES (700, 'New Issue', 'ACTIVE', 'myTitle', '2020-11-11', '2021-01-02');

INSERT INTO t_users (id, username, password)
VALUES (1000, 'Kodstar', '$2a$10$2wjebL7o4No1w3DSfTFtxO4z5C7546BACzUlxSN8vW74v0cgVKLbC');
INSERT INTO t_users (id, username, password)
VALUES (2000, 'admin', '$2a$10$2wjebL7o4No1w3DSfTFtxO4z5C7546BACzUlxSN8vW74v0cgVKLbC');
INSERT INTO t_users (id, username, password)
VALUES (3000, 'user1', '$2a$10$2wjebL7o4No1w3DSfTFtxO4z5C7546BACzUlxSN8vW74v0cgVKLbC');
INSERT INTO t_users (id, username, password)
VALUES (4000, 'user2', '$2a$10$2wjebL7o4No1w3DSfTFtxO4z5C7546BACzUlxSN8vW74v0cgVKLbC');


INSERT INTO t_label (id, label_name, label_color)
VALUES (150, 'backend', '#fbca04');
INSERT INTO t_label (id, label_name, label_color)
VALUES (250, 'web', '#3d155f');
INSERT INTO t_label (id, label_name, label_color)
VALUES (350, 'test', '#1fb1f6');
INSERT INTO t_label (id, label_name, label_color)
VALUES (450, 'control', '#d876e3');
INSERT INTO t_label (id, label_name, label_color)
VALUES (550, 'bug', '#d73a4a');
INSERT INTO t_label (id, label_name, label_color)
VALUES (650, 'advice', '#0075ca');

INSERT INTO t_issue_label (issue_id, label_id)
VALUES (100, 150);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (100, 550);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (100, 350);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (200, 150);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (200, 250);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (300, 250);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (300, 350);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (300, 450);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (400, 150);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (400, 550);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (400, 650);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (500, 350);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (500, 550);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (600, 650);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (700, 250);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (700, 150);
INSERT INTO t_issue_label (issue_id, label_id)
VALUES (700, 550);

INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (155, 'Comment1', '2020-11-11', '2020-12-02');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (255, 'Comment2', '2020-10-04', '2020-11-02');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (355, 'Comment3', '2020-12-03', '2021-01-02');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (455, 'Comment4', '2020-09-02', '2020-11-02');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (555, 'Comment5', '2020-12-11', '2021-01-02');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (655, 'Comment6', '2020-03-11', '2021-01-03');
INSERT INTO t_comment (id, comment, create_time, modify_time)
VALUES (755, 'Comment7', '2020-08-04', '2021-11-05');

INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (100, 155);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (200, 255);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (300, 355);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (400, 455);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (500, 555);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (600, 655);
INSERT INTO t_issue_comment (issue_id, comment_id)
VALUES (700, 755);

INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (100, 1000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (100, 2000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (200, 2000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (200, 3000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (300, 1000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (400, 3000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (500, 3000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (600, 4000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (700, 1000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (700, 2000);
INSERT INTO t_issue_assignee (issue_id, user_id)
VALUES (500, 4000);


