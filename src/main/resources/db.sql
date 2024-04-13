CREATE TABLE tbl_synthesis
(
    id         SERIAL PRIMARY KEY,
    user_id    BIGINT,
    win_count  INTEGER,
    lost_count INTEGER
);

CREATE TABLE tbl_synthesis_line
(
    id                   BIGSERIAL PRIMARY KEY,
    synthesis_id         BIGINT REFERENCES tbl_synthesis (id),
    is_win               BOOLEAN,
    your_number_of_moves INTEGER,
    bot_number_moves     INTEGER,
    level                INTEGER,
    you_first            BOOLEAN,
    created_at           TIMESTAMP
)