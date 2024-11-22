CREATE TABLE link_tags
(
    id    UUID NOT NULL,
    name  VARCHAR(255),
    color VARCHAR(255),
    CONSTRAINT pk_link_tags PRIMARY KEY (id)
);

CREATE TABLE link_tags_saved_links
(
    link_tag_id    UUID NOT NULL,
    saved_links_id UUID NOT NULL,
    CONSTRAINT pk_link_tags_savedlinks PRIMARY KEY (link_tag_id, saved_links_id)
);

ALTER TABLE link_tags_saved_links
    ADD CONSTRAINT fk_lintagsavlin_on_link_tag FOREIGN KEY (link_tag_id) REFERENCES link_tags (id);

ALTER TABLE link_tags_saved_links
    ADD CONSTRAINT fk_lintagsavlin_on_saved_link FOREIGN KEY (saved_links_id) REFERENCES savedlinks (id);