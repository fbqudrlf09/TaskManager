USE task;

CREATE TABLE task
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT "할 일 식별자",
    task_name VARCHAR(20) NOT NULL COMMENT "할 일",
    user_name VARCHAR(20) NOT NULL COMMENT "작성자",
    password VARCHAR(20) NOT NULL COMMENT "비밀번호",
    create_at DATE COMMENT "생성 일",
    update_at DATE COMMENT "수정 일"
);

/* 데이터 삽입 */
INSERT INTO task(task_name, user_name, password, create_at, update_at) Values('spring', 'ryu', '1234','2024-11-07', '2024-11-07');

/* 모든 데이터 조회 */
SELECT * FROM task;

/* 데이터 수정 */
UPDATE task SET task_name = 'spring_java' WHERE id =2;

/* 데이터 삭제 */
DELETE FROM task WHERE id = 1;

/* 패스워드 열 추가 */
ALTER TABLE task ADD COLUMN password VARCHAR(20) NOT NULL AFTER user_name;

/* 컬럼 데이터 타입 변경 */
ALTER TABLE task MODIFY create_at DATETIME;

/* 컬럼 데이터 타입 변경 */
ALTER TABLE task MODIFY update_at DATETIME;