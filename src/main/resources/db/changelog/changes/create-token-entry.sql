CREATE TABLE token_entry
(
    processor_name VARCHAR(255) NOT NULL,
    segment BIGINT NOT NULL,
    token_type VARCHAR(255),
    timestamp VARCHAR(255) NOT NULL,
    owner VARCHAR(255),
    token text,
    PRIMARY KEY (processor_name, segment)
);
CREATE UNIQUE INDEX UK_k5lt6d2792amknloo7q91njp0v ON token_entry (processor_name, segment);