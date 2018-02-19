CREATE TABLE domain_event_entry 
(
    aggregate_identifier VARCHAR(255) NOT NULL,
    sequence_number BIGINT NOT NULL,
    type VARCHAR(255) NOT NULL,
    global_index BIGINT NOT NULL,
    event_identifier VARCHAR(255) NOT NULL,
    payload_revision VARCHAR(255),
    payload_type VARCHAR(255) NOT NULL,
    time_stamp VARCHAR(255) NOT NULL,
    meta_data text NOT NULL,
    payload text   NOT NULL,
    PRIMARY KEY (aggregate_identifier, sequence_number, type)
);
CREATE UNIQUE INDEX UK_k5lt6d2792amnloo7q91njp0v ON domain_event_entry (event_Identifier);