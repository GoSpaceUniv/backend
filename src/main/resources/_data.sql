INSERT INTO posts (category, is_anon, title, content, created_at, updated_at)
VALUES ('STUDY_TIPS', FALSE, '첫 번째 글', '이것은 테스트 본문입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (category, is_anon, title, content, created_at, updated_at)
VALUES ('MENTAL', TRUE, '익명 글', '익명 작성자의 테스트 글입니다.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
