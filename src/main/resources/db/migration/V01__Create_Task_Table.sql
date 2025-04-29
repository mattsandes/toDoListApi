-- Table: public.task_tb

-- DROP TABLE IF EXISTS public.task_tb;

CREATE TABLE IF NOT EXISTS public.task_tb
(
    id uuid NOT NULL,
    titulo character varying(80) COLLATE pg_catalog."default" NOT NULL,
    descricao character varying(100) COLLATE pg_catalog."default" NOT NULL,
    data_criacao timestamp(6) without time zone,
    concluida boolean,
    CONSTRAINT task_tb_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;