--
-- PostgreSQL database dump
--

-- Dumped from database version 13.4
-- Dumped by pg_dump version 13.4

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: address; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.address (
    id integer NOT NULL,
    userid character varying(10) NOT NULL,
    address text NOT NULL,
    fullname character varying(50),
    phone character varying(10),
    status boolean
);


ALTER TABLE public.address OWNER TO postgres;

--
-- Name: address_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.address ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.address_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: authorities; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.authorities (
    id integer NOT NULL,
    userid character varying(10) NOT NULL,
    roleid integer NOT NULL
);


ALTER TABLE public.authorities OWNER TO postgres;

--
-- Name: authorization_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.authorities ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.authorization_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: category; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.category (
    id integer NOT NULL,
    name character varying(30) NOT NULL,
    status boolean NOT NULL,
    picture text
);


ALTER TABLE public.category OWNER TO postgres;

--
-- Name: category_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.category ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.category_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: orders; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orders (
    id integer NOT NULL,
    storeid integer NOT NULL,
    bookingdate date NOT NULL,
    userid character varying(10) NOT NULL,
    status integer NOT NULL,
    totalamount double precision NOT NULL,
    addressid integer NOT NULL
);


ALTER TABLE public.orders OWNER TO postgres;

--
-- Name: order_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orders ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.order_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: orderdetail; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.orderdetail (
    id integer NOT NULL,
    price double precision NOT NULL,
    quantity integer NOT NULL,
    productid integer NOT NULL,
    orderid integer NOT NULL,
    totalamount double precision NOT NULL
);


ALTER TABLE public.orderdetail OWNER TO postgres;

--
-- Name: orderdetail_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.orderdetail ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.orderdetail_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: product; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.product (
    id integer NOT NULL,
    name character varying NOT NULL,
    categoryid integer NOT NULL,
    price double precision NOT NULL,
    description text NOT NULL,
    status boolean NOT NULL,
    storeid integer NOT NULL,
    images text
);


ALTER TABLE public.product OWNER TO postgres;

--
-- Name: product_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.product ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.product_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: productimage; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productimage (
    id integer NOT NULL,
    productid integer,
    picture text
);


ALTER TABLE public.productimage OWNER TO postgres;

--
-- Name: productimage_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.productimage ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.productimage_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    role character varying(10) NOT NULL
);


ALTER TABLE public.role OWNER TO postgres;

--
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: shoppingcart; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.shoppingcart (
    id integer NOT NULL,
    userid character varying(10) NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL,
    storeid integer NOT NULL,
    status boolean
);


ALTER TABLE public.shoppingcart OWNER TO postgres;

--
-- Name: shoppingcart_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.shoppingcart ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.shoppingcart_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: store; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.store (
    id integer NOT NULL,
    userid character varying NOT NULL,
    name character varying(30) NOT NULL,
    description text,
    status boolean NOT NULL
);


ALTER TABLE public.store OWNER TO postgres;

--
-- Name: store_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.store ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.store_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: total; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.total (
    storeid integer NOT NULL,
    name character varying(255),
    tong double precision,
    userid character varying(255),
    giam double precision,
    thanhtoan double precision
);


ALTER TABLE public.total OWNER TO postgres;

--
-- Name: transaction; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.transaction (
    id integer NOT NULL,
    userid character varying(10) NOT NULL,
    name character varying(100) NOT NULL,
    createdate date NOT NULL,
    status boolean NOT NULL,
    money double precision NOT NULL
);


ALTER TABLE public.transaction OWNER TO postgres;

--
-- Name: transaction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.transaction ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.transaction_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: users; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.users (
    userid character varying(10) NOT NULL,
    username character varying(15) NOT NULL,
    password character varying(15) NOT NULL,
    fullname character varying(50),
    picture text,
    email character varying(100) NOT NULL,
    gender boolean,
    birthday date,
    phone character varying(10)
);


ALTER TABLE public.users OWNER TO postgres;

--
-- Name: wallet; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wallet (
    id integer NOT NULL,
    userid character varying(10) NOT NULL,
    cardnumber character varying(16) NOT NULL,
    cardbrand character varying(30) NOT NULL,
    holdername character varying(30) NOT NULL,
    cvv integer NOT NULL,
    money double precision NOT NULL,
    cardexpiry timestamp without time zone
);


ALTER TABLE public.wallet OWNER TO postgres;

--
-- Name: wallet_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.wallet ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.wallet_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- Name: address address_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT address_pkey PRIMARY KEY (id);


--
-- Name: authorities authorization_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT authorization_pkey PRIMARY KEY (id);


--
-- Name: category category_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.category
    ADD CONSTRAINT category_pkey PRIMARY KEY (id);


--
-- Name: orderdetail orderDetail_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT "orderDetail_pkey" PRIMARY KEY (id);


--
-- Name: orders order_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT order_pkey PRIMARY KEY (id);


--
-- Name: productimage productImage_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productimage
    ADD CONSTRAINT "productImage_pkey" PRIMARY KEY (id);


--
-- Name: product product_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);


--
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- Name: shoppingcart shoppingCart_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoppingcart
    ADD CONSTRAINT "shoppingCart_pkey" PRIMARY KEY (id);


--
-- Name: store store_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store
    ADD CONSTRAINT store_pkey PRIMARY KEY (id);


--
-- Name: total total_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.total
    ADD CONSTRAINT total_pkey PRIMARY KEY (storeid);


--
-- Name: transaction transaction_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (userid);


--
-- Name: wallet wallet_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT wallet_pkey PRIMARY KEY (id);


--
-- Name: address address_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.address
    ADD CONSTRAINT "address_userId_fkey" FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: authorities authorization_roleId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT "authorization_roleId_fkey" FOREIGN KEY (roleid) REFERENCES public.role(id);


--
-- Name: authorities authorization_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.authorities
    ADD CONSTRAINT "authorization_userId_fkey" FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: wallet fkg0q3mx355wce5elguuu8j0hun; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wallet
    ADD CONSTRAINT fkg0q3mx355wce5elguuu8j0hun FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: orderdetail orderDetail_orderId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT "orderDetail_orderId_fkey" FOREIGN KEY (orderid) REFERENCES public.orders(id);


--
-- Name: orderdetail orderDetail_productId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orderdetail
    ADD CONSTRAINT "orderDetail_productId_fkey" FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- Name: orders order_addressid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT order_addressid_fkey FOREIGN KEY (addressid) REFERENCES public.address(id) NOT VALID;


--
-- Name: orders order_storeid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT order_storeid_fkey FOREIGN KEY (storeid) REFERENCES public.store(id) NOT VALID;


--
-- Name: orders order_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.orders
    ADD CONSTRAINT "order_userId_fkey" FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: productimage productImage_productId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productimage
    ADD CONSTRAINT "productImage_productId_fkey" FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- Name: product product_categoryId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT "product_categoryId_fkey" FOREIGN KEY (categoryid) REFERENCES public.category(id);


--
-- Name: product product_storeId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.product
    ADD CONSTRAINT "product_storeId_fkey" FOREIGN KEY (storeid) REFERENCES public.store(id);


--
-- Name: shoppingcart shoppingCart_productId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoppingcart
    ADD CONSTRAINT "shoppingCart_productId_fkey" FOREIGN KEY (productid) REFERENCES public.product(id);


--
-- Name: shoppingcart shoppingCart_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.shoppingcart
    ADD CONSTRAINT "shoppingCart_userId_fkey" FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: store store_userId_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.store
    ADD CONSTRAINT "store_userId_fkey" FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- Name: transaction transaction_userid_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.transaction
    ADD CONSTRAINT transaction_userid_fkey FOREIGN KEY (userid) REFERENCES public.users(userid);


--
-- PostgreSQL database dump complete
--

