-- USERS
insert into users (id, username) values (1, 'kaca');
insert into users (id, username) values (2, 'milica');
insert into users (id, username) values (3, 'nevena');
insert into users (id, username) values (4, 'user1');
insert into users (id, username) values (5, 'user2');
insert into users (id, username) values (6, 'agent1');

-- ADS
insert into `oglasi` (id, user_id) values (1, 3);
insert into `oglasi` (id, user_id) values (2, 3);
insert into `oglasi` (id, user_id) values (3, 3);
insert into `oglasi` (id, user_id) values (4, 4);
insert into `oglasi` (id, user_id) values (5, 4);
insert into `oglasi` (id, user_id) values (6, 3);
insert into `oglasi` (id, user_id) values (7, 3);


-- RENT REQUESTS
insert into `zahtjev_za_iznajmljivanje` (id, datum_kreiranja, doo, mjesto_preuzimanja, od, potvrdjen, status, bundle_id, oglas_id, user_id)
values (1, '2020-08-06 21:58:58', '2020-09-26 21:58:58', 'Novi Sad', '2020-09-11 21:58:58', false, 'PENDING', null, 1, 2);
insert into `zahtjev_za_iznajmljivanje` (id, datum_kreiranja, doo, mjesto_preuzimanja, od, potvrdjen, status, bundle_id, oglas_id, user_id)
values (2, '2020-08-12 21:58:58', '2020-09-29 21:58:58', 'Novi Sad', '2020-09-20 21:58:58', false, 'PENDING', null, 2, 2);
insert into `zahtjev_za_iznajmljivanje` (id, datum_kreiranja, doo, mjesto_preuzimanja, od, potvrdjen, status, bundle_id, oglas_id, user_id)
values (3, '2020-08-12 21:58:58', '2020-09-29 21:58:58', 'Novi Sad', '2020-08-20 21:58:58', false, 'PAID', null, 2, 2);
insert into `zahtjev_za_iznajmljivanje` (id, datum_kreiranja, doo, mjesto_preuzimanja, od, potvrdjen, status, bundle_id, oglas_id, user_id)
values (4, '2020-08-12 21:58:58', '2020-09-01 21:58:58', 'Novi Sad', '2020-08-20 21:58:58', false, 'PAID', null, 2, 2);
