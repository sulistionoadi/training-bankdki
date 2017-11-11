--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.8
-- Dumped by pg_dump version 9.5.8

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: balance_customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE balance_customer (
    id character varying(255) NOT NULL,
    balance numeric(19,2) NOT NULL,
    id_customer character varying(255) NOT NULL
);


ALTER TABLE balance_customer OWNER TO postgres;

--
-- Name: mst_customer; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE mst_customer (
    id character varying(255) NOT NULL,
    address character varying(255) NOT NULL,
    birth_date date NOT NULL,
    customer_id character varying(20) NOT NULL,
    firstname character varying(25) NOT NULL,
    lastname character varying(25)
);


ALTER TABLE mst_customer OWNER TO postgres;

--
-- Name: sec_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sec_permission (
    id character varying(255) NOT NULL,
    permission_label character varying(255) NOT NULL,
    permission_value character varying(255) NOT NULL
);


ALTER TABLE sec_permission OWNER TO postgres;

--
-- Name: sec_role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sec_role (
    id character varying(255) NOT NULL,
    role_desc character varying(255) NOT NULL,
    role_name character varying(255) NOT NULL
);


ALTER TABLE sec_role OWNER TO postgres;

--
-- Name: sec_role_permission; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sec_role_permission (
    id_role character varying(255) NOT NULL,
    id_permission character varying(255) NOT NULL
);


ALTER TABLE sec_role_permission OWNER TO postgres;

--
-- Name: sec_user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE sec_user (
    id character varying(255) NOT NULL,
    is_active boolean NOT NULL,
    fullname character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    username character varying(255) NOT NULL,
    id_role character varying(255) NOT NULL
);


ALTER TABLE sec_user OWNER TO postgres;

--
-- Name: transaction_detail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE transaction_detail (
    id character varying(255) NOT NULL,
    amount numeric(19,2) NOT NULL,
    description character varying(255) NOT NULL,
    id_header character varying(255) NOT NULL
);


ALTER TABLE transaction_detail OWNER TO postgres;

--
-- Name: transaction_header; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE transaction_header (
    id character varying(255) NOT NULL,
    transaction_amount numeric(19,2) NOT NULL,
    transaction_date timestamp without time zone NOT NULL,
    id_customer character varying(255) NOT NULL
);


ALTER TABLE transaction_header OWNER TO postgres;

--
-- Data for Name: balance_customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY balance_customer (id, balance, id_customer) FROM stdin;
7453487d-c924-4884-b695-b51fa7158ae8	17000.00	11c6cc65-801a-456e-a8e2-8f57a8abf449
\.


--
-- Data for Name: mst_customer; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY mst_customer (id, address, birth_date, customer_id, firstname, lastname) FROM stdin;
6c6e9c6d-bfda-453e-b7d1-3fdb651a9c52	JAKARTA TIMUR	2017-10-14	35781313000933	ADI	SULISTIONO
11c6cc65-801a-456e-a8e2-8f57a8abf449	BANDUNG	2017-10-14	35781313000944	RAHADI	RACHMAT
\.


--
-- Data for Name: sec_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sec_permission (id, permission_label, permission_value) FROM stdin;
delcust	Delete Customer	ROLE_ACT_DEL_CUSTOMER
deltrx	Delete Transaksi	ROLE_ACT_DEL_TRANSAKSI
formcust	Tampilan Form Customer	ROLE_MENU_FORM_CUSTOMER
formtrx	Tampilan Form Transaksi	ROLE_MENU_FORM_TRANSAKSI
listcust	Tampilan List Customer	ROLE_MENU_LIST_CUSTOMER
listtrx	Tampilan List Transaksi	ROLE_MENU_LIST_TRANSAKSI
\.


--
-- Data for Name: sec_role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sec_role (id, role_desc, role_name) FROM stdin;
adm	Admin Aplikasi	ADMINISTRATOR
opr	Operator Aplikasi	OPERATOR
\.


--
-- Data for Name: sec_role_permission; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sec_role_permission (id_role, id_permission) FROM stdin;
adm	listcust
adm	formcust
adm	delcust
adm	listtrx
adm	formtrx
adm	deltrx
opr	listtrx
opr	formtrx
\.


--
-- Data for Name: sec_user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY sec_user (id, is_active, fullname, password, username, id_role) FROM stdin;
admin	t	administrator	$2a$13$xi32XgP5q.4CxSY8VyDLnemMjaLYTvkRah8/IGEOD9yu/YWCySmAi	admin	adm
operator	t	operator	$2a$13$xi32XgP5q.4CxSY8VyDLnemMjaLYTvkRah8/IGEOD9yu/YWCySmAi	operator	opr
\.


--
-- Data for Name: transaction_detail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transaction_detail (id, amount, description, id_header) FROM stdin;
a588df66-d479-4ee0-8f37-c2cfea9c38f6	100000.00	Transfer antar bank	da236a8c-1944-478c-99ad-19ed34d3eabe
5e0e557f-7dd4-4930-a68c-61a72e901313	6500.00	biaya admin	da236a8c-1944-478c-99ad-19ed34d3eabe
6d5efa99-4a1f-4e2b-80f0-0878e31cf5d9	20000.00	Transfer antar bank	1395d0f6-90d0-4875-b96e-0761e57b8570
f11143e4-72e5-4037-bee6-2cd52ab1f86a	6500.00	biaya admin	1395d0f6-90d0-4875-b96e-0761e57b8570
\.


--
-- Data for Name: transaction_header; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY transaction_header (id, transaction_amount, transaction_date, id_customer) FROM stdin;
da236a8c-1944-478c-99ad-19ed34d3eabe	106500.00	2017-10-21 13:37:58.795	11c6cc65-801a-456e-a8e2-8f57a8abf449
1395d0f6-90d0-4875-b96e-0761e57b8570	26500.00	2017-10-21 14:20:23.162	11c6cc65-801a-456e-a8e2-8f57a8abf449
\.


--
-- Name: balance_customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY balance_customer
    ADD CONSTRAINT balance_customer_pkey PRIMARY KEY (id);


--
-- Name: mst_customer_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY mst_customer
    ADD CONSTRAINT mst_customer_pkey PRIMARY KEY (id);


--
-- Name: sec_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_permission
    ADD CONSTRAINT sec_permission_pkey PRIMARY KEY (id);


--
-- Name: sec_role_permission_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_role_permission
    ADD CONSTRAINT sec_role_permission_pkey PRIMARY KEY (id_role, id_permission);


--
-- Name: sec_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_role
    ADD CONSTRAINT sec_role_pkey PRIMARY KEY (id);


--
-- Name: sec_user_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_user
    ADD CONSTRAINT sec_user_pkey PRIMARY KEY (id);


--
-- Name: transaction_detail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction_detail
    ADD CONSTRAINT transaction_detail_pkey PRIMARY KEY (id);


--
-- Name: transaction_header_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction_header
    ADD CONSTRAINT transaction_header_pkey PRIMARY KEY (id);


--
-- Name: uk_5ctbdrlf3eismye20vsdtk8w8; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_user
    ADD CONSTRAINT uk_5ctbdrlf3eismye20vsdtk8w8 UNIQUE (username);


--
-- Name: uk_8ege6kp5muamlbeasrhfd7sc5; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_role
    ADD CONSTRAINT uk_8ege6kp5muamlbeasrhfd7sc5 UNIQUE (role_name);


--
-- Name: fk2gja2ln01u340w3epdpf2gam8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction_detail
    ADD CONSTRAINT fk2gja2ln01u340w3epdpf2gam8 FOREIGN KEY (id_header) REFERENCES transaction_header(id);


--
-- Name: fk50no5kynlxtiuq8fu665t46n8; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_user
    ADD CONSTRAINT fk50no5kynlxtiuq8fu665t46n8 FOREIGN KEY (id_role) REFERENCES sec_role(id);


--
-- Name: fk96pn5rd5nfvpe4r0waigfwwim; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_role_permission
    ADD CONSTRAINT fk96pn5rd5nfvpe4r0waigfwwim FOREIGN KEY (id_role) REFERENCES sec_role(id);


--
-- Name: fkoc1wnthl4q3mdmgt0guk9210v; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY balance_customer
    ADD CONSTRAINT fkoc1wnthl4q3mdmgt0guk9210v FOREIGN KEY (id_customer) REFERENCES mst_customer(id);


--
-- Name: fkre4xghqxr9llv60ds5d4jpl5r; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY transaction_header
    ADD CONSTRAINT fkre4xghqxr9llv60ds5d4jpl5r FOREIGN KEY (id_customer) REFERENCES mst_customer(id);


--
-- Name: fks6iqh8nhx5qcqal9k339jli3d; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY sec_role_permission
    ADD CONSTRAINT fks6iqh8nhx5qcqal9k339jli3d FOREIGN KEY (id_permission) REFERENCES sec_permission(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

