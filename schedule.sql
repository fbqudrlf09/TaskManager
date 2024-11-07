USE task;

CREATE TABLE task
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT "할 일 식별자",
    task_name VARCHAR(20) NOT NULL COMMENT "할 일",
    user_name VARCHAR(20) NOT NULL COMMENT "작성자",
    create_at DATE COMMENT "생성 일",
    update_at DATE COMMENT "수정 일"
);

/* 데이터 삽입 */
INSERT INTO task(task_name, user_name, create_at, update_at) Values('spring', 'ryu', '2024-11-07', '2024-11-07');

/* 모든 데이터 조회 */
SELECT * FROM task;

/* 데이터 수정 */
UPDATE task SET task_name = 'spring_java' WHERE id =2;

/* 데이터 삭제 */
DELETE FROM task WHERE id = 1;