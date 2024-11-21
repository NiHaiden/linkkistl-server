CREATE TABLE external_user
(
    id                VARCHAR(255) NOT NULL,
    profile_image_url VARCHAR(255),
    CONSTRAINT pk_external_user PRIMARY KEY (id)
);

CREATE TABLE savedlinks
(
    id          UUID NOT NULL,
    linkurl     VARCHAR(255),
    title       VARCHAR(255),
    description VARCHAR(255),
    saved_at    TIMESTAMP WITHOUT TIME ZONE,
    user_id     VARCHAR(255),
    CONSTRAINT pk_savedlinks PRIMARY KEY (id)
);

ALTER TABLE savedlinks
    ADD CONSTRAINT FK_SAVEDLINKS_ON_USER FOREIGN KEY (user_id) REFERENCES external_user (id);