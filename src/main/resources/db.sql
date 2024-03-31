CREATE TABLE tbl_synthesis
(
    id         SERIAL PRIMARY KEY,
    user_id    BIGINT,
    win_count  INTEGER,
    lost_count INTEGER
)