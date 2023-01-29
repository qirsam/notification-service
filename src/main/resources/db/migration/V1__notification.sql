CREATE TABLE IF NOT EXISTS notification
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT    NOT NULL,
    task_id BIGINT    NOT NULL,
    time    TIME NOT NULL
);