--
-- PostgreSQL database dump
--

-- Dumped from database version 9.6.5
-- Dumped by pg_dump version 10.0

-- Started on 2018-01-14 23:22:10 WIB

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- TOC entry 8 (class 2615 OID 24798)
-- Name: common; Type: SCHEMA; Schema: -; Owner: footnoter
--

CREATE SCHEMA common;


ALTER SCHEMA common OWNER TO footnoter;

--
-- TOC entry 7 (class 2615 OID 16428)
-- Name: content; Type: SCHEMA; Schema: -; Owner: footnoter
--

CREATE SCHEMA content;


ALTER SCHEMA content OWNER TO footnoter;

--
-- TOC entry 1 (class 3079 OID 12655)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2483 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = common, pg_catalog;

SET default_tablespace = footnote_space;

SET default_with_oids = false;

--
-- TOC entry 196 (class 1259 OID 24825)
-- Name: role; Type: TABLE; Schema: common; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE role (
    id integer NOT NULL,
    role character varying(50)
);


ALTER TABLE role OWNER TO footnoter;

--
-- TOC entry 195 (class 1259 OID 24821)
-- Name: role_id_seq; Type: SEQUENCE; Schema: common; Owner: footnoter
--

CREATE SEQUENCE role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE role_id_seq OWNER TO footnoter;

--
-- TOC entry 2484 (class 0 OID 0)
-- Dependencies: 195
-- Name: role_id_seq; Type: SEQUENCE OWNED BY; Schema: common; Owner: footnoter
--

ALTER SEQUENCE role_id_seq OWNED BY role.id;


--
-- TOC entry 199 (class 1259 OID 24908)
-- Name: user_roles; Type: TABLE; Schema: common; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE user_roles (
    id bigint NOT NULL,
    role_id bigint NOT NULL,
    user_id character varying(100)
);


ALTER TABLE user_roles OWNER TO footnoter;

--
-- TOC entry 197 (class 1259 OID 24902)
-- Name: user_roles_id_seq; Type: SEQUENCE; Schema: common; Owner: footnoter
--

CREATE SEQUENCE user_roles_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_roles_id_seq OWNER TO footnoter;

--
-- TOC entry 2485 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_roles_id_seq; Type: SEQUENCE OWNED BY; Schema: common; Owner: footnoter
--

ALTER SEQUENCE user_roles_id_seq OWNED BY user_roles.id;


--
-- TOC entry 198 (class 1259 OID 24906)
-- Name: user_roles_role_id_seq; Type: SEQUENCE; Schema: common; Owner: footnoter
--

CREATE SEQUENCE user_roles_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE user_roles_role_id_seq OWNER TO footnoter;

--
-- TOC entry 2486 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_roles_role_id_seq; Type: SEQUENCE OWNED BY; Schema: common; Owner: footnoter
--

ALTER SEQUENCE user_roles_role_id_seq OWNED BY user_roles.role_id;


--
-- TOC entry 194 (class 1259 OID 24804)
-- Name: users; Type: TABLE; Schema: common; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE users (
    id character varying(100) NOT NULL,
    password character varying(150),
    enable boolean,
    token text,
    username character varying(100)
);


ALTER TABLE users OWNER TO footnoter;

SET search_path = content, pg_catalog;

--
-- TOC entry 192 (class 1259 OID 16552)
-- Name: attachment; Type: TABLE; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE attachment (
    id_attachment bigint NOT NULL,
    item_id integer NOT NULL,
    file text,
    type character varying,
    name_file text
);


ALTER TABLE attachment OWNER TO footnoter;

--
-- TOC entry 189 (class 1259 OID 16525)
-- Name: item; Type: TABLE; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE item (
    title character varying(100),
    description text,
    create_date date,
    update_date date,
    create_by character varying(100),
    update_by character varying(100),
    type character varying,
    id_item bigint NOT NULL,
    information character varying(100)
);


ALTER TABLE item OWNER TO footnoter;

--
-- TOC entry 201 (class 1259 OID 24942)
-- Name: content_id_content_seq; Type: SEQUENCE; Schema: content; Owner: footnoter
--

CREATE SEQUENCE content_id_content_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE content_id_content_seq OWNER TO footnoter;

--
-- TOC entry 2487 (class 0 OID 0)
-- Dependencies: 201
-- Name: content_id_content_seq; Type: SEQUENCE OWNED BY; Schema: content; Owner: footnoter
--

ALTER SEQUENCE content_id_content_seq OWNED BY item.id_item;


--
-- TOC entry 186 (class 1259 OID 16433)
-- Name: guest_book; Type: TABLE; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE guest_book (
    username character varying(100),
    create_date date,
    id_guest_book integer NOT NULL
);


ALTER TABLE guest_book OWNER TO footnoter;

--
-- TOC entry 193 (class 1259 OID 16564)
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE; Schema: content; Owner: footnoter
--

CREATE SEQUENCE guest_book_id_guest_book_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE guest_book_id_guest_book_seq OWNER TO footnoter;

--
-- TOC entry 2488 (class 0 OID 0)
-- Dependencies: 193
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE OWNED BY; Schema: content; Owner: footnoter
--

ALTER SEQUENCE guest_book_id_guest_book_seq OWNED BY guest_book.id_guest_book;


--
-- TOC entry 190 (class 1259 OID 16544)
-- Name: images_id_image_seq; Type: SEQUENCE; Schema: content; Owner: footnoter
--

CREATE SEQUENCE images_id_image_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE images_id_image_seq OWNER TO footnoter;

--
-- TOC entry 2489 (class 0 OID 0)
-- Dependencies: 190
-- Name: images_id_image_seq; Type: SEQUENCE OWNED BY; Schema: content; Owner: footnoter
--

ALTER SEQUENCE images_id_image_seq OWNED BY attachment.id_attachment;


--
-- TOC entry 191 (class 1259 OID 16548)
-- Name: images_news_and_article_di_seq; Type: SEQUENCE; Schema: content; Owner: footnoter
--

CREATE SEQUENCE images_news_and_article_di_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE images_news_and_article_di_seq OWNER TO footnoter;

--
-- TOC entry 2490 (class 0 OID 0)
-- Dependencies: 191
-- Name: images_news_and_article_di_seq; Type: SEQUENCE OWNED BY; Schema: content; Owner: footnoter
--

ALTER SEQUENCE images_news_and_article_di_seq OWNED BY attachment.item_id;


--
-- TOC entry 187 (class 1259 OID 16484)
-- Name: profile; Type: TABLE; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE profile (
    address text,
    email character varying(50),
    create_date date NOT NULL,
    update_date date,
    phone character varying(15),
    lat character varying(100),
    lon character varying(100)
);


ALTER TABLE profile OWNER TO footnoter;

--
-- TOC entry 188 (class 1259 OID 16492)
-- Name: testimonial; Type: TABLE; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

CREATE TABLE testimonial (
    subject character varying(100),
    description text,
    age integer,
    create_date date,
    update_date date,
    id_testimonial bigint NOT NULL
);


ALTER TABLE testimonial OWNER TO footnoter;

--
-- TOC entry 200 (class 1259 OID 24929)
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE; Schema: content; Owner: footnoter
--

CREATE SEQUENCE testimonial_id_testimonial_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE testimonial_id_testimonial_seq OWNER TO footnoter;

--
-- TOC entry 2491 (class 0 OID 0)
-- Dependencies: 200
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE OWNED BY; Schema: content; Owner: footnoter
--

ALTER SEQUENCE testimonial_id_testimonial_seq OWNED BY testimonial.id_testimonial;


SET search_path = common, pg_catalog;

--
-- TOC entry 2320 (class 2604 OID 24828)
-- Name: role id; Type: DEFAULT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY role ALTER COLUMN id SET DEFAULT nextval('role_id_seq'::regclass);


--
-- TOC entry 2321 (class 2604 OID 24911)
-- Name: user_roles id; Type: DEFAULT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY user_roles ALTER COLUMN id SET DEFAULT nextval('user_roles_id_seq'::regclass);


--
-- TOC entry 2322 (class 2604 OID 24913)
-- Name: user_roles role_id; Type: DEFAULT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY user_roles ALTER COLUMN role_id SET DEFAULT nextval('user_roles_role_id_seq'::regclass);


SET search_path = content, pg_catalog;

--
-- TOC entry 2318 (class 2604 OID 16555)
-- Name: attachment id_attachment; Type: DEFAULT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY attachment ALTER COLUMN id_attachment SET DEFAULT nextval('images_id_image_seq'::regclass);


--
-- TOC entry 2319 (class 2604 OID 24969)
-- Name: attachment item_id; Type: DEFAULT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY attachment ALTER COLUMN item_id SET DEFAULT nextval('images_news_and_article_di_seq'::regclass);


--
-- TOC entry 2315 (class 2604 OID 16566)
-- Name: guest_book id_guest_book; Type: DEFAULT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY guest_book ALTER COLUMN id_guest_book SET DEFAULT nextval('guest_book_id_guest_book_seq'::regclass);


--
-- TOC entry 2317 (class 2604 OID 24961)
-- Name: item id_item; Type: DEFAULT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY item ALTER COLUMN id_item SET DEFAULT nextval('content_id_content_seq'::regclass);


--
-- TOC entry 2316 (class 2604 OID 24938)
-- Name: testimonial id_testimonial; Type: DEFAULT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY testimonial ALTER COLUMN id_testimonial SET DEFAULT nextval('testimonial_id_testimonial_seq'::regclass);


SET search_path = common, pg_catalog;

--
-- TOC entry 2472 (class 0 OID 24825)
-- Dependencies: 196
-- Data for Name: role; Type: TABLE DATA; Schema: common; Owner: footnoter
--

COPY role (id, role) FROM stdin;
2	ROLE_VUE_ADMIN
1	ROLE_SUPER_ADMIN
\.


--
-- TOC entry 2475 (class 0 OID 24908)
-- Dependencies: 199
-- Data for Name: user_roles; Type: TABLE DATA; Schema: common; Owner: footnoter
--

COPY user_roles (id, role_id, user_id) FROM stdin;
4	1	israj.haliri@gmail.com
5	2	israj.haliri@gmail.com
6	2	vue@admin.com
\.


--
-- TOC entry 2470 (class 0 OID 24804)
-- Dependencies: 194
-- Data for Name: users; Type: TABLE DATA; Schema: common; Owner: footnoter
--

COPY users (id, password, enable, token, username) FROM stdin;
vue@admin.com	$2a$12$3aJYGZNTDKXUeDruTBxYVeBVyTBcM0mpG/BF/.DezaUSI/N4yzMIO	t	\N	vue admin
israj.haliri@gmail.com	$2a$12$3aJYGZNTDKXUeDruTBxYVeBVyTBcM0mpG/BF/.DezaUSI/N4yzMIO	t	eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJpc3Jhai5oYWxpcmlAZ21haWwuY29tIiwiYXVkIjoiaXNyYWpIYWxpcmlTaXRlIiwiZXhwIjoxNTE1OTQ4NTc4fQ.RkZ1XYF85kZK8kHbRFPq_ZO2mouMiPKQs4S0Vci0BTA	israj haliri
\.


SET search_path = content, pg_catalog;

--
-- TOC entry 2468 (class 0 OID 16552)
-- Dependencies: 192
-- Data for Name: attachment; Type: TABLE DATA; Schema: content; Owner: footnoter
--

COPY attachment (id_attachment, item_id, file, type, name_file) FROM stdin;
\.


--
-- TOC entry 2462 (class 0 OID 16433)
-- Dependencies: 186
-- Data for Name: guest_book; Type: TABLE DATA; Schema: content; Owner: footnoter
--

COPY guest_book (username, create_date, id_guest_book) FROM stdin;
jono	2017-12-22	3
haliri	2017-12-21	4
jono	2017-12-22	2
new jono	2017-12-19	5
\.


--
-- TOC entry 2465 (class 0 OID 16525)
-- Dependencies: 189
-- Data for Name: item; Type: TABLE DATA; Schema: content; Owner: footnoter
--

COPY item (title, description, create_date, update_date, create_by, update_by, type, id_item, information) FROM stdin;
aprod 1	1	2018-01-10	\N	israj.haliri@gmail.com	\N	PRODUCT	22	
\.


--
-- TOC entry 2463 (class 0 OID 16484)
-- Dependencies: 187
-- Data for Name: profile; Type: TABLE DATA; Schema: content; Owner: footnoter
--

COPY profile (address, email, create_date, update_date, phone, lat, lon) FROM stdin;
jln goal para	israj.haliri@gmail.com	2017-12-23	2018-01-10	085862624149	-6.14309978	106.721001
\.


--
-- TOC entry 2464 (class 0 OID 16492)
-- Dependencies: 188
-- Data for Name: testimonial; Type: TABLE DATA; Schema: content; Owner: footnoter
--

COPY testimonial (subject, description, age, create_date, update_date, id_testimonial) FROM stdin;
jono	dba	23	2017-12-09	\N	9
anjas	dba	20	2017-12-09	\N	10
michael	dba	40	2017-12-09	\N	12
jhone doe	-	0	2017-12-09	\N	20
jhone doe	-	0	2017-12-09	\N	21
jhone doe	-	0	2017-12-09	\N	22
jhone doe	-	0	2017-12-09	\N	23
jhone doe	-	0	2017-12-09	\N	24
jhone doe	-	0	2017-12-09	\N	25
jhone doe	\N	\N	\N	\N	27
\.


SET search_path = common, pg_catalog;

--
-- TOC entry 2492 (class 0 OID 0)
-- Dependencies: 195
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: common; Owner: footnoter
--

SELECT pg_catalog.setval('role_id_seq', 4, true);


--
-- TOC entry 2493 (class 0 OID 0)
-- Dependencies: 197
-- Name: user_roles_id_seq; Type: SEQUENCE SET; Schema: common; Owner: footnoter
--

SELECT pg_catalog.setval('user_roles_id_seq', 6, true);


--
-- TOC entry 2494 (class 0 OID 0)
-- Dependencies: 198
-- Name: user_roles_role_id_seq; Type: SEQUENCE SET; Schema: common; Owner: footnoter
--

SELECT pg_catalog.setval('user_roles_role_id_seq', 1, true);


SET search_path = content, pg_catalog;

--
-- TOC entry 2495 (class 0 OID 0)
-- Dependencies: 201
-- Name: content_id_content_seq; Type: SEQUENCE SET; Schema: content; Owner: footnoter
--

SELECT pg_catalog.setval('content_id_content_seq', 22, true);


--
-- TOC entry 2496 (class 0 OID 0)
-- Dependencies: 193
-- Name: guest_book_id_guest_book_seq; Type: SEQUENCE SET; Schema: content; Owner: footnoter
--

SELECT pg_catalog.setval('guest_book_id_guest_book_seq', 5, true);


--
-- TOC entry 2497 (class 0 OID 0)
-- Dependencies: 190
-- Name: images_id_image_seq; Type: SEQUENCE SET; Schema: content; Owner: footnoter
--

SELECT pg_catalog.setval('images_id_image_seq', 28, true);


--
-- TOC entry 2498 (class 0 OID 0)
-- Dependencies: 191
-- Name: images_news_and_article_di_seq; Type: SEQUENCE SET; Schema: content; Owner: footnoter
--

SELECT pg_catalog.setval('images_news_and_article_di_seq', 1, false);


--
-- TOC entry 2499 (class 0 OID 0)
-- Dependencies: 200
-- Name: testimonial_id_testimonial_seq; Type: SEQUENCE SET; Schema: content; Owner: footnoter
--

SELECT pg_catalog.setval('testimonial_id_testimonial_seq', 27, true);


SET search_path = common, pg_catalog;

SET default_tablespace = '';

--
-- TOC entry 2337 (class 2606 OID 24831)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 2341 (class 2606 OID 24915)
-- Name: user_roles user_roles_pkey; Type: CONSTRAINT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_roles_pkey PRIMARY KEY (id);


--
-- TOC entry 2335 (class 2606 OID 24808)
-- Name: users users_pkey; Type: CONSTRAINT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


SET search_path = content, pg_catalog;

SET default_tablespace = footnote_space;

--
-- TOC entry 2332 (class 2606 OID 16563)
-- Name: attachment attachment_pkey; Type: CONSTRAINT; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT attachment_pkey PRIMARY KEY (id_attachment);


SET default_tablespace = '';

--
-- TOC entry 2330 (class 2606 OID 24963)
-- Name: item content_pkey; Type: CONSTRAINT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY item
    ADD CONSTRAINT content_pkey PRIMARY KEY (id_item);


SET default_tablespace = footnote_space;

--
-- TOC entry 2326 (class 2606 OID 24989)
-- Name: profile create_date_constraint; Type: CONSTRAINT; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

ALTER TABLE ONLY profile
    ADD CONSTRAINT create_date_constraint UNIQUE (create_date);


--
-- TOC entry 2324 (class 2606 OID 16571)
-- Name: guest_book guest_book_pkey; Type: CONSTRAINT; Schema: content; Owner: footnoter; Tablespace: footnote_space
--

ALTER TABLE ONLY guest_book
    ADD CONSTRAINT guest_book_pkey PRIMARY KEY (id_guest_book);


SET default_tablespace = '';

--
-- TOC entry 2328 (class 2606 OID 24940)
-- Name: testimonial testimonial_pkey; Type: CONSTRAINT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY testimonial
    ADD CONSTRAINT testimonial_pkey PRIMARY KEY (id_testimonial);


SET search_path = common, pg_catalog;

--
-- TOC entry 2338 (class 1259 OID 24922)
-- Name: fki_role_fkey; Type: INDEX; Schema: common; Owner: footnoter
--

CREATE INDEX fki_role_fkey ON user_roles USING btree (role_id);


--
-- TOC entry 2339 (class 1259 OID 24928)
-- Name: fki_user_fkey; Type: INDEX; Schema: common; Owner: footnoter
--

CREATE INDEX fki_user_fkey ON user_roles USING btree (user_id);


SET search_path = content, pg_catalog;

--
-- TOC entry 2333 (class 1259 OID 24970)
-- Name: fki_news_and_article_fkey; Type: INDEX; Schema: content; Owner: footnoter
--

CREATE INDEX fki_news_and_article_fkey ON attachment USING btree (item_id);


SET search_path = common, pg_catalog;

--
-- TOC entry 2343 (class 2606 OID 24917)
-- Name: user_roles role_fkey; Type: FK CONSTRAINT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT role_fkey FOREIGN KEY (role_id) REFERENCES role(id);


--
-- TOC entry 2344 (class 2606 OID 24923)
-- Name: user_roles user_fkey; Type: FK CONSTRAINT; Schema: common; Owner: footnoter
--

ALTER TABLE ONLY user_roles
    ADD CONSTRAINT user_fkey FOREIGN KEY (user_id) REFERENCES users(id);


SET search_path = content, pg_catalog;

--
-- TOC entry 2342 (class 2606 OID 24971)
-- Name: attachment item_fkey; Type: FK CONSTRAINT; Schema: content; Owner: footnoter
--

ALTER TABLE ONLY attachment
    ADD CONSTRAINT item_fkey FOREIGN KEY (item_id) REFERENCES item(id_item);


-- Completed on 2018-01-14 23:22:11 WIB

--
-- PostgreSQL database dump complete
--

