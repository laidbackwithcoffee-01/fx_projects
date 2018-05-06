CREATE TABLE pairsprice
(
    timestamp bigint NOT NULL,
    symbolcode varchar(2) NOT NULL,
    price numeric(12,6),
    bid numeric(12,6),
    ask numeric(12,6),
    CONSTRAINT pairsprice_pkey PRIMARY KEY (timestamp, symbolcode)
)