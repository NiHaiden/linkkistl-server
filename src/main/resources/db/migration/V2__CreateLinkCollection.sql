CREATE TABLE link_collection
(
    id                 UUID NOT NULL,
    name               VARCHAR(255),
    description        VARCHAR(255),
    external_user_id   VARCHAR(255),
    created_date       TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    last_modified_date TIMESTAMP WITHOUT TIME ZONE,
    visibility_status  VARCHAR(10),
    CONSTRAINT pk_linkcollection PRIMARY KEY (id)
);

ALTER TABLE link_collection
    ADD CONSTRAINT FK_LINKCOLLECTION_ON_EXTERNAL_USER FOREIGN KEY (external_user_id) REFERENCES external_user (id);