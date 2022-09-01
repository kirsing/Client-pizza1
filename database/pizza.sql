CREATE TABLE IF NOT EXISTS public.pizza
(
  id integer NOT NULL DEFAULT nextval('pizza_id_seq'::regclass),
  diameter double precision NOT NULL,
  name character varying(255) COLLATE pg_catalog."default",
  weight double precision NOT NULL,
  CONSTRAINT pizza_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;
