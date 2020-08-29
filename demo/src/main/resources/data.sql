-- ROLES
insert into role (name, id) values ('ROLE_ADMIN', 1);
insert into role (name, id) values ('ROLE_AGENT', 2);
insert into role (name, id) values ('ROLE_USER', 3);

insert into privilege  (name, id) values ('POST_ADS', 1);

insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (1, 'kaca', 'Katarina', 'Prodanovic', 'Stepe Stepanovica 10', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, true, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (2, 'milica', 'Milica', 'Radovanovic', 'Kilavci 2', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled, deleted, is_admin, last_password_reset_date, ime_kompanije, poslovniid)
values (3, 'nevena', 'Nevena', 'Djukin', 'Padej 3', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 'pow wow ltd', '1');
insert into users (id, username, name, surname, address, password, enabled, deleted, is_admin, last_password_reset_date, ime_kompanije, poslovniid)
values (4, 'agent1', 'Nikola', 'Jokic', 'Somborski put 3', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 'Burekdzinica', '2');
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (5, 'user1', 'Blagoje', 'Pantic', 'Kilavci 22', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date)
values (6, 'user2', 'Stanoje', 'Stanic', 'Kilavci 12', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000');


insert into users_roles (user_id, role_id) values (1, 1);
insert into users_roles (user_id, role_id) values (2, 3);
insert into users_roles (user_id, role_id) values (3, 2);
insert into users_roles (user_id, role_id) values (4, 2);
insert into users_roles (user_id, role_id) values (5, 3);
insert into users_roles (user_id, role_id) values (6, 3);

insert into users_privileges (user_id, privilege_id) values (1, 1);
insert into users_privileges (user_id, privilege_id) values (2, 1);
insert into users_privileges (user_id, privilege_id) values (3, 1);
insert into users_privileges (user_id, privilege_id) values (4, 1);
insert into users_privileges (user_id, privilege_id) values (5, 1);
insert into users_privileges (user_id, privilege_id) values (6, 1);

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

insert into `vozilo` (id, slika, info, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id, bundle)
values (1, null, null, '4', '1200', false, true, '120000', '150', '5', 1, 1, 3, 1, 3, false);
insert into `vozilo` (id, slika, info, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id, bundle)
values (2, null, null, '5', '2400', true, true, '150000', '110', '1', 1, 1, 2, 3, 3, false);
insert into `vozilo` (id, slika, info, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id, bundle)
values (3, null, null, '3', '1000', false, false, '220000', '100', '3', 3, 2, 1, 2, 3, false);
insert into `vozilo` (id, slika, info, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id, bundle)
values (4, null, null, '3', '1000', false, false, '515000', '500', '4', 2, 3, 1, 2, 4, false);
insert into `vozilo` (id, slika, info, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id, bundle)
values (5, null, null, '3', '1000', false, false, '12000', '1100', '2', 1, 3, 2, 1, 4, false);

insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (1, 56, 56, 56, 16, 3);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (2, 55, 55, 55, 10, 3);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (3, 57, 57, 57, 17, 4);

insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (1, '2020-09-26 21:58:58', true, '200', 'Novi Sad', '2020-08-11 21:58:58', 1, 3, 1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (2, '2020-09-29 21:58:58', true, '200', 'Beograd', '2020-08-20 21:58:58', 2, 3, 3);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (3, '2020-09-30 21:58:58', true, '200', 'Banja Luka', '2020-08-25 21:58:58', 1, 3, 2);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (4, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-23 21:58:58', 3, 4, 4);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (5, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-28 21:58:58', 3, 4, 5);

insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (1, '4', 1, 3, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (2, '4', 1, 3, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (3, '5', 1, 3, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (4, '5', 1, 3, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (5, '1', 1, 3, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (6, '4', 1, 3, 2);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (7, '5', 1, 3, 2);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (8, '5', 1, 3, 2);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (9, '4', 1, 3, 3);


insert into `komentar` (id, odobren,tekst, user_id, vozilo_id) values (1, true, 'Vrh auto', 6, 1);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id) values (2, true, 'Udobno', 5, 2);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id) values (3, true, 'Svidja mi se', 6, 2);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id) values (4, false, 'Super', 5, 1);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id) values (5, false, 'Predobro auto', 5, 1);

insert into `ocena` (id, ocjena, user_id, oglas_id, vozilo_id ) values (10,4,6,1,1);
insert into `ocena` (id, ocjena, user_id, oglas_id, vozilo_id ) values (11,4.5,5,1,1);
insert into `ocena` (id, ocjena, user_id, oglas_id, vozilo_id ) values (12,4.6,6,2,2);


insert into `poruka` (id, datum_slanja, sadrzaj, posiljalac_id, primalac_id) values (1, '2020-06-06 21:58:58', 'Hi :D', 2, 3 );
insert into `poruka` (id, datum_slanja, sadrzaj, posiljalac_id, primalac_id) values (2, '2020-06-06 21:59:58', 'Hey :)', 3, 2 )
