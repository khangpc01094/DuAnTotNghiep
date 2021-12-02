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

--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.users (userid, username, password, fullname, picture, email, gender, birthday, phone) FROM stdin;
user1	user1	123	my	user.png	myphpc01084@fpt.edu.vn	f	2001-05-12	0947682902
user3	user3	123	khang	user.png	khangvhpc01094@fpt.edu.vn	t	2001-01-05	094744902
user4	user4	123	trung	user.png	trungtmpc01001@fpt.edu.vn	t	2001-02-05	0933327902
user5	user5	123	dinh	user.png	abc123@fpt.edu.vn	t	2001-03-05	0947655902
user6	user6	123	trước	user.png	abc124@fpt.edu.vn	t	2001-04-05	0944482902
user7	user7	123	trường	user.png	abc143@fpt.edu.vn	t	2001-05-05	0947622902
user8	user8	123	tú	user.png	abc423@fpt.edu.vn	t	2001-06-05	0911182902
user9	user9	123	khải	user.png	abc443@fpt.edu.vn	t	2001-07-05	0943222902
user10	user10	123	nhi	user.png	abc144@fpt.edu.vn	f	2001-08-05	0947681234
user2	user2	khangtest	phương	user.png	phuongttypc01043@fpt.edu.vn	f	2000-01-17	0947445902
\.


--
-- Data for Name: address; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.address (id, userid, address, fullname, phone, status) FROM stdin;
1	user1	tổ 1, ấp AAbb, xã BBaa, huyện C, tỉnh D	\N	\N	\N
2	user2	tổ 2, ấp AB, xã BB, huyện C, tỉnh D	\N	\N	\N
3	user3	tổ 3, ấp AA, xã Bb, huyện C, tỉnh D	\N	\N	\N
4	user4	tổ 4, ấp AAB, xã BAA, huyện C, tỉnh D	\N	\N	\N
5	user5	tổ 5, ấp Aa, xã Ba, huyện C, tỉnh D	\N	\N	\N
6	user6	tổ 6, ấp Ab, xã Bb, huyện C, tỉnh D	\N	\N	\N
7	user7	tổ 7, ấp AaBb, xã BaAb, huyện C, tỉnh D	\N	\N	\N
8	user8	tổ 8, ấp AAA, xã BBB, huyện C, tỉnh D	\N	\N	\N
9	user9	tổ 9, ấp BBA, xã BAA, huyện C, tỉnh D	\N	\N	\N
10	user10	đường abc, quận Ninh Kiều, thành phố Cần Thơ	\N	\N	\N
11	user2	tổ 4, ấp 10, xã thành Trung, Bình tân, Vĩnh Long	\N	\N	\N
12	user2	tổ 6, ấp 3 ngày,  nở thành con, gà nhỏ bé	\N	\N	\N
\.


--
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, role) FROM stdin;
1	buyer
2	seller
3	Admin
\.


--
-- Data for Name: authorities; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.authorities (id, userid, roleid) FROM stdin;
1	user1	1
2	user2	1
3	user3	1
4	user4	1
5	user5	1
6	user6	1
7	user7	1
8	user8	1
9	user9	1
10	user10	1
11	user1	2
12	user2	2
13	user3	2
14	user4	2
15	user5	2
16	user6	2
17	user7	2
18	user8	2
19	user9	2
20	user10	2
21	user1	3
22	user2	3
23	user3	3
\.


--
-- Data for Name: category; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.category (id, name, status, picture) FROM stdin;
1	Rau	t	\N
2	Củ	t	\N
3	Quả	t	\N
4	Nấm	t	\N
\.


--
-- Data for Name: store; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.store (id, userid, name, description, status) FROM stdin;
1	user1	My store User1	Cửa hàng bán rau sạch	t
2	user2	My store User2	Cửa hàng bán các loại củ chất lượng	t
3	user3	My store User3	Cửa hàng bán quả, trái cây ngon	t
4	user4	My store User4	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	t
5	user5	My store User5	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
6	user6	My store User6	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
7	user7	My store User7	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
8	user8	My store User8	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
9	user9	My store User9	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
10	user10	My store User10	Cửa hàng bán nấm sạch thân thiện người tiêu dùng	f
\.


--
-- Data for Name: orders; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orders (id, storeid, bookingdate, userid, status, totalamount, addressid) FROM stdin;
27	1	2021-12-01	user1	1	95000	12
28	4	2021-12-01	user1	2	2860962	12
\.


--
-- Data for Name: product; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.product (id, name, categoryid, price, description, status, storeid, images) FROM stdin;
1	Cải xà lách	1	20000	Xà lách Đà Lạt đảm bảo chất lượng	t	1	1.png
2	Rau dền	1	10000	Rau dền đảm bảo chất lượng	t	1	2.png
3	Mồng tơi	1	10000	Mồng tơi đảm bảo chất lượng	t	1	3.png\n
4	Củ sắn	2	17580	Củ sắn đảm bảo chất lượng	t	2	4.png
5	Củ cải trắng	2	17470	Củ cải trắng đảm bảo chất lượng	t	2	5.png
6	Củ cải đỏ	2	17320	Củ cải đỏ đảm bảo chất lượng	t	2	6.png
7	Cam sành	3	29457	Cam sành đảm bảo chất lượng	t	3	7.png
8	Quýt đường	3	40010	Quýt đường đảm bảo chất lượng	t	3	8.png
9	Dâu tây	3	104500	Dâu tây đảm bảo chất lượng	t	3	9.png
10	Nấm rơm	4	150580	Nấm rơm đảm bảo chất lượng	t	4	10.png
11	Nấm bào ngư	4	80457	Nấm bào ngư đảm bảo chất lượng	t	4	11.png
\.


--
-- Data for Name: orderdetail; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.orderdetail (id, price, quantity, productid, orderid, totalamount) FROM stdin;
23	10000	4	3	27	40000
24	10000	4	2	27	40000
25	150580	21	10	28	3162180
\.


--
-- Data for Name: productimage; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productimage (id, productid, picture) FROM stdin;
1	3	traicay.png
2	3	traicay2.png
3	3	traicay3.png
4	3	traicay4.png
5	4	traicay.png
6	4	traicay2.png
7	4	traicay3.png
8	4	traicay4.png
9	5	traicay.png
10	5	traicay2.png
11	5	traicay3.png
12	5	traicay4.png
13	6	traicay.png
14	6	traicay2.png
15	6	traicay3.png
16	6	traicay4.png
\.


--
-- Data for Name: shoppingcart; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.shoppingcart (id, userid, productid, quantity, storeid, status) FROM stdin;
77	user1	2	5	1	f
78	user1	3	8	1	f
80	user1	1	2	1	f
81	user1	5	4	2	t
82	user1	4	4	2	t
\.


--
-- Data for Name: total; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.total (storeid, name, tong, userid, giam, thanhtoan) FROM stdin;
\.


--
-- Data for Name: transaction; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.transaction (id, userid, name, createdate, status, money) FROM stdin;
\.


--
-- Data for Name: wallet; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wallet (id, userid, cardnumber, cardbrand, holdername, cvv, money, cardexpiry) FROM stdin;
\.


--
-- Name: address_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.address_id_seq', 12, true);


--
-- Name: authorization_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.authorization_id_seq', 23, true);


--
-- Name: category_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.category_id_seq', 4, true);


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: order_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.order_id_seq', 28, true);


--
-- Name: orderdetail_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.orderdetail_id_seq', 25, true);


--
-- Name: product_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.product_id_seq', 11, true);


--
-- Name: productimage_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.productimage_id_seq', 16, true);


--
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 3, true);


--
-- Name: shoppingcart_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.shoppingcart_id_seq', 83, true);


--
-- Name: store_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.store_id_seq', 10, true);


--
-- Name: transaction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.transaction_id_seq', 1, false);


--
-- Name: wallet_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wallet_id_seq', 1, false);


--
-- PostgreSQL database dump complete
--

