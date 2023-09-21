-- Table: public.swipe_in_out_event_details

DROP TABLE IF EXISTS public.swipe_in_out_event_details;
DROP TABLE IF EXISTS EMPLOYEE;

CREATE TABLE EMPLOYEE(
   ID INT PRIMARY KEY NOT NULL,
   NAME TEXT NOT NULL,
   EMAIL TEXT NOT NULL
);

INSERT INTO EMPLOYEE(ID, NAME, EMAIL) values(1,'Emp A', 'A@email.com');
INSERT INTO EMPLOYEE(ID, NAME, EMAIL) values(2,'Emp B', 'B@email.com');
INSERT INTO EMPLOYEE(ID, NAME, EMAIL) values(3,'Emp C', 'C@email.com');
INSERT INTO EMPLOYEE(ID, NAME, EMAIL) values(4,'Emp D', 'D@email.com');
INSERT INTO EMPLOYEE(ID, NAME, EMAIL) values(5,'Emp E', 'E@email.com');


CREATE TABLE IF NOT EXISTS public.swipe_in_out_event_details
(
    employee_id integer NOT NULL,
    in_out_sequence integer,
    swipe_date date,
    swipe_time time(6) without time zone,
    id bigint NOT NULL,
    swipe_type character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT swipe_in_out_event_details_pkey PRIMARY KEY (id),
    CONSTRAINT swipe_in_out_event_details_fkey FOREIGN KEY (employee_id)
        REFERENCES public.employee (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT swipe_in_out_event_details_swipe_type_check CHECK (swipe_type::text = ANY (ARRAY['IN'::character varying, 'OUT'::character varying]::text[]))
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.swipe_in_out_event_details
    OWNER to postgres;