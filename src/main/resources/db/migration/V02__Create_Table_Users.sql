-- Table: public.task_tb

-- DROP TABLE IF EXISTS public.task_tb;

CREATE TABLE Users (
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login TEXT NOT NULL UNIQUE,
    password TEXT NOT NULL,
    role TEXT NOT NULL
);