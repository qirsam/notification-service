CREATE TABLE IF NOT EXISTS notification
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    task_id BIGINT NOT NULL,
    time    TIME   NOT NULL
);

INSERT INTO notification(user_id, task_id, time)
VALUES
    (1, 1,'23:40'),
    (1, 2,'13:20'),
    (2, 1,'10:30'),
    (2, 2,'00:40'),
    (3, 1,'22:34'),
    (4, 1,'11:22'),
    (4, 2,'09:11');