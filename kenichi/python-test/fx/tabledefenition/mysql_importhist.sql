CREATE TABLE importhist
(
    filename varchar(255) NOT NULL,
    importtimestamp timestamp,
    CONSTRAINT importhist_pkey PRIMARY KEY (filename)
)