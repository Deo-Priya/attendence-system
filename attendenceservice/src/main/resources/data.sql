-- Table: public.attendence

-- DROP TABLE IF EXISTS public.attendence;

CREATE TABLE IF NOT EXISTS public.attendence
(
    employee_id integer,
    id integer NOT NULL,
    swipe_date date,
    total_time_in_office numeric(21,0),
    attendence character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT attendence_pkey PRIMARY KEY (id),
    CONSTRAINT attendence_attendence_check CHECK (attendence::text = ANY (ARRAY['PRESENT'::character varying, 'ABSENT'::character varying, 'HALF_DAY'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.attendence
    OWNER to postgres;