CREATE TABLE IF NOT EXISTS notification
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT    NOT NULL,
    task_id BIGINT    NOT NULL,
    time    TIME NOT NULL
);

INSERT INTO notification(user_id, task_id, time)
VALUES
(1, 1, '16:05'),
(2, 2, '12:05'),
(3, 3, '13:05'),
(4, 4, '14:05'),
(5, 5, '15:05');