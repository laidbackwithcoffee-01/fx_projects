-- Table: public.pairsprice

-- DROP TABLE public.pairsprice;

CREATE TABLE public.pairsprice
(
    "timestamp" bigint NOT NULL,
    symbolcode character varying(2) COLLATE pg_catalog."default" NOT NULL,
    price numeric(12,6),
    bid numeric(12,6),
    ask numeric(12,6),
    CONSTRAINT pairsprice_pkey PRIMARY KEY ("timestamp", symbolcode)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.pairsprice
    OWNER to dbuser;