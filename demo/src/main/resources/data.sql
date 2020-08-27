-- ROLES
insert into role (name, id) values ('ROLE_ADMIN', 1);
insert into role (name, id) values ('ROLE_AGENT', 2);
insert into role (name, id) values ('ROLE_USER', 3);

insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (1, 'kaca', 'Katarina', 'Prodanovic', 'Stepe Stepanovica 10', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, true, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (2, 'milica', 'Milica', 'Radovanovic', 'Kilavci 2', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled, deleted, is_admin, last_password_reset_date, ime_kompanije, poslovniid)
values (3, 'nevena', 'Nevena', 'Djukin', 'Padej 3', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 'pow wow ltd', '1');


insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 3);
insert into users_roles (user_id, role_id) values (3, 2);

insert into `tipmjenjaca` (id, naziv, obrisan) values (1, 'manuelni', false);
insert into `tipmjenjaca` (id, naziv,obrisan) values (2, 'automatski', false);
insert into `tipmjenjaca` (id, naziv, obrisan) values (3, 'poluautomatski', false);

insert into `klasaautomobila` (id, naziv, obrisan) values (1, 'klasa1', false);
insert into `klasaautomobila` (id, naziv, obrisan) values (2, 'klasa2', false);
insert into `klasaautomobila` (id, naziv, obrisan) values (3, 'klasa3', false);

insert into `vrstagoriva` (id, naziv, obrisan) values (1, 'benzin', false);
insert into `vrstagoriva` (id, naziv, obrisan) values (2, 'dizel', false);
insert into `vrstagoriva` (id, naziv, obrisan) values (3, 'plin', false);

insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (1, 'BMW', 'model1', false);
insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (2, 'Audi', 'model2', false);
insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (3, 'Wolsvagen', 'model3', false);

INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (1, '0', '1200', false, true, '120000', '100', '4', 2, 1,1,1, false);
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (2, '2', '2200', true, true, '150000', '200', '3',  1, 1,2,2, false);
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena,  marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (3, '0', '2150', false, false, '250000', '600', '5',  3, 3,2,3, false);
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena , marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (4, '1', '2250', true, true, '15000', '150', '5',  1, 3,3,3, false);
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (5, '3', '1000', true, true, '15510', '121', '4',  1, 3,3,3, false);
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (6, '1', '600', false, false, '270000', '500', '2', 2, 2,1,3 , false );
INSERT INTO `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, marka_automobila_id, klasa_automobila_id, tip_goriva_id, tip_mjenjaca_id, bundle) VALUES (7, '0', '5000', false, true, '10000', '1000', '5', 3, 2,1,1, false );



insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (1, 55, 55, 55, 10, 1);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (2, 55, 55, 55, 10, 1);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (3, 55, 55, 55, 10, 1);

insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (1, '2020-09-25 21:58:58', true, '200', 'Novi Sad', '2020-09-01 21:58:58', 1, 1, 1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (2, '2020-09-08 21:58:58', true, '200', 'Beograd', '2020-09-05 21:58:58', 2, 1, 3);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (3, '2020-09-10 21:58:58', true, '200', 'Banja Luka', '2020-09-06 21:58:58', 3, 1, 2);


