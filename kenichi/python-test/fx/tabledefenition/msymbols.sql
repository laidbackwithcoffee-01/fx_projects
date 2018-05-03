-- Table: public.msymbols

-- DROP TABLE public.msymbols;

CREATE TABLE public.msymbols
(
    code character varying(2) COLLATE pg_catalog."default" NOT NULL,
    "codeName" character varying(10) COLLATE pg_catalog."default",
    CONSTRAINT msymbols_pkey PRIMARY KEY (code)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.msymbols
    OWNER to dbuser;