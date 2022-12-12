--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: reference; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA reference;


ALTER SCHEMA reference OWNER TO postgres;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: adoptions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.adoptions (
                                  name character varying(20) NOT NULL,
                                  species character varying(10) NOT NULL,
                                  adopter_email character varying(100) NOT NULL,
                                  adoption_date date NOT NULL,
                                  adoption_fee smallint NOT NULL,
                                  CONSTRAINT adoptions_adoption_fee_check CHECK ((adoption_fee >= 0))
);


ALTER TABLE public.adoptions OWNER TO postgres;

--
-- Name: animals; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.animals (
                                name character varying(20) NOT NULL,
                                species character varying(10) NOT NULL,
                                primary_color character varying(10) NOT NULL,
                                implant_chip_id character varying(50) NOT NULL,
                                breed character varying(50),
                                gender character(1) NOT NULL,
                                birth_date date NOT NULL,
                                pattern character varying(20) NOT NULL,
                                admission_date date NOT NULL,
                                CONSTRAINT animals_gender_check CHECK ((gender = ANY (ARRAY['F'::bpchar, 'M'::bpchar])))
);


ALTER TABLE public.animals OWNER TO postgres;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.categories (
                                   category_id smallint NOT NULL,
                                   category_name character varying(15) NOT NULL,
                                   description text,
                                   picture bytea
);


ALTER TABLE public.categories OWNER TO postgres;

--
-- Name: company_divisions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_divisions (
                                          department character varying(100) NOT NULL,
                                          company_division character varying(100)
);


ALTER TABLE public.company_divisions OWNER TO postgres;

--
-- Name: company_regions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.company_regions (
                                        region_id integer NOT NULL,
                                        company_regions character varying(20),
                                        country character varying(20)
);


ALTER TABLE public.company_regions OWNER TO postgres;

--
-- Name: customer_customer_demo; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_customer_demo (
                                               customer_id bpchar NOT NULL,
                                               customer_type_id bpchar NOT NULL
);


ALTER TABLE public.customer_customer_demo OWNER TO postgres;

--
-- Name: customer_demographics; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customer_demographics (
                                              customer_type_id bpchar NOT NULL,
                                              customer_desc text
);


ALTER TABLE public.customer_demographics OWNER TO postgres;

--
-- Name: customers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.customers (
                                  customer_id bpchar NOT NULL,
                                  company_name character varying(40) NOT NULL,
                                  contact_name character varying(30),
                                  contact_title character varying(30),
                                  address character varying(60),
                                  city character varying(15),
                                  region character varying(15),
                                  postal_code character varying(10),
                                  country character varying(15),
                                  phone character varying(24),
                                  fax character varying(24)
);


ALTER TABLE public.customers OWNER TO postgres;

--
-- Name: employee_territories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employee_territories (
                                             employee_id smallint NOT NULL,
                                             territory_id character varying(20) NOT NULL
);


ALTER TABLE public.employee_territories OWNER TO postgres;

--
-- Name: employees; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.employees (
                                  employee_id smallint NOT NULL,
                                  last_name character varying(20) NOT NULL,
                                  first_name character varying(10) NOT NULL,
                                  title character varying(30),
                                  title_of_courtesy character varying(25),
                                  birth_date date,
                                  hire_date date,
                                  address character varying(60),
                                  city character varying(15),
                                  region character varying(15),
                                  postal_code character varying(10),
                                  country character varying(15),
                                  home_phone character varying(24),
                                  extension character varying(4),
                                  photo bytea,
                                  notes text,
                                  reports_to smallint,
                                  photo_path character varying(255)
);


ALTER TABLE public.employees OWNER TO postgres;

--
-- Name: order_details; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.order_details (
                                      order_id smallint NOT NULL,
                                      product_id smallint NOT NULL,
                                      unit_price real NOT NULL,
                                      quantity smallint NOT NULL,
                                      discount real NOT NULL
);


ALTER TABLE public.order_details OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
                               order_id smallint NOT NULL,
                               customer_id bpchar,
                               employee_id smallint,
                               order_date date,
                               required_date date,
                               shipped_date date,
                               ship_via smallint,
                               freight real,
                               ship_name character varying(40),
                               ship_address character varying(60),
                               ship_city character varying(15),
                               ship_region character varying(15),
                               ship_postal_code character varying(10),
                               ship_country character varying(15)
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: persons; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.persons (
                                email character varying(100) NOT NULL,
                                first_name character varying(15) NOT NULL,
                                last_name character varying(15) NOT NULL,
                                birth_date date,
                                address character varying(100) NOT NULL,
                                state character varying(20) NOT NULL,
                                city character varying(30) NOT NULL,
                                zip_code character(5) NOT NULL
);


ALTER TABLE public.persons OWNER TO postgres;

--
-- Name: products; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.products (
                                 product_id smallint NOT NULL,
                                 product_name character varying(40) NOT NULL,
                                 supplier_id smallint,
                                 category_id smallint,
                                 quantity_per_unit character varying(20),
                                 unit_price real,
                                 units_in_stock smallint,
                                 units_on_order smallint,
                                 reorder_level smallint,
                                 discontinued integer NOT NULL
);


ALTER TABLE public.products OWNER TO postgres;

--
-- Name: region; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.region (
                               region_id smallint NOT NULL,
                               region_description bpchar NOT NULL
);


ALTER TABLE public.region OWNER TO postgres;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.roles (
                              id integer NOT NULL,
                              role character varying(5) NOT NULL
);


ALTER TABLE public.roles OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.roles_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_id_seq OWNER TO postgres;

--
-- Name: roles_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.roles_id_seq OWNED BY public.roles.id;


--
-- Name: shippers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shippers (
                                 shipper_id smallint NOT NULL,
                                 company_name character varying(40) NOT NULL,
                                 phone character varying(24)
);


ALTER TABLE public.shippers OWNER TO postgres;

--
-- Name: staff; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff (
                              email character varying(100) NOT NULL,
                              hire_date date NOT NULL
);


ALTER TABLE public.staff OWNER TO postgres;

--
-- Name: staff_assignments; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff_assignments (
                                          email character varying(100) NOT NULL,
                                          role character varying(20) NOT NULL,
                                          assigned date NOT NULL
);


ALTER TABLE public.staff_assignments OWNER TO postgres;

--
-- Name: staff_roles; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.staff_roles (
    role character varying(20) NOT NULL
);


ALTER TABLE public.staff_roles OWNER TO postgres;

--
-- Name: suppliers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.suppliers (
                                  supplier_id smallint NOT NULL,
                                  company_name character varying(40) NOT NULL,
                                  contact_name character varying(30),
                                  contact_title character varying(30),
                                  address character varying(60),
                                  city character varying(15),
                                  region character varying(15),
                                  postal_code character varying(10),
                                  country character varying(15),
                                  phone character varying(24),
                                  fax character varying(24),
                                  homepage text
);


ALTER TABLE public.suppliers OWNER TO postgres;

--
-- Name: territories; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.territories (
                                    territory_id character varying(20) NOT NULL,
                                    territory_description bpchar NOT NULL,
                                    region_id smallint NOT NULL
);


ALTER TABLE public.territories OWNER TO postgres;

--
-- Name: us_states; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.us_states (
                                  state_id smallint NOT NULL,
                                  state_name character varying(100),
                                  state_abbr character varying(2),
                                  state_region character varying(50)
);


ALTER TABLE public.us_states OWNER TO postgres;

--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
                              id integer NOT NULL,
                              login character varying(10) NOT NULL,
                              password character varying(10) NOT NULL,
                              role integer NOT NULL
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.users_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO postgres;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: vaccinations; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.vaccinations (
                                     name character varying(20) NOT NULL,
                                     species character varying(10) NOT NULL,
                                     vaccination_time timestamp without time zone NOT NULL,
                                     vaccine character varying(50) NOT NULL,
                                     batch character varying(20) NOT NULL,
                                     comments character varying(500),
                                     email character varying(100) NOT NULL
);


ALTER TABLE public.vaccinations OWNER TO postgres;

--
-- Name: colors; Type: TABLE; Schema: reference; Owner: postgres
--

CREATE TABLE reference.colors (
    color character varying(10) NOT NULL
);


ALTER TABLE reference.colors OWNER TO postgres;

--
-- Name: species; Type: TABLE; Schema: reference; Owner: postgres
--

CREATE TABLE reference.species (
    species character varying(10) NOT NULL
);


ALTER TABLE reference.species OWNER TO postgres;

--
-- Name: roles id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.roles ALTER COLUMN id SET DEFAULT nextval('public.roles_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);

