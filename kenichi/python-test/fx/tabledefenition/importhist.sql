-- Table: public.importhist

-- DROP TABLE public.importhist;

CREATE TABLE public.importhist
(
    filename character varying(255) COLLATE pg_catalog."default" NOT NULL,
    importtimestamp timestamp with time zone,
    CONSTRAINT importhist_pkey PRIMARY KEY (filename)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.importhist
    OWNER to dbuser;