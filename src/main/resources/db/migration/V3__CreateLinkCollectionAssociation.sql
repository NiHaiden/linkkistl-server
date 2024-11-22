CREATE TABLE link_collection_saved_links
(
    link_collection_id UUID NOT NULL,
    saved_links_id     UUID NOT NULL,
    CONSTRAINT pk_linkcollection_savedlinks PRIMARY KEY (link_collection_id, saved_links_id)
);

ALTER TABLE link_collection_saved_links
    ADD CONSTRAINT fk_lincolsavlin_on_link_collection FOREIGN KEY (link_collection_id) REFERENCES link_collection (id);

ALTER TABLE link_collection_saved_links
    ADD CONSTRAINT fk_lincolsavlin_on_saved_link FOREIGN KEY (saved_links_id) REFERENCES savedlinks (id);