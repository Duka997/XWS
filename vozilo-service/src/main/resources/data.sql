insert into users (id, username, name, surname, address)
values (1, 'kaca', 'Katarina', 'Prodanovic', 'Stepe Stepanovica 10');
insert into users (id, username, name, surname, address)
values (2, 'milica', 'Milica', 'Radovanovic', 'Kilavci 2');
insert into users (id, username, name, surname, address)
values (3, 'nevena', 'Nevena', 'Djukin', 'Padej 3');
insert into users (id, username, name, surname, address)
values (4, 'agent1', 'Nikola', 'Jokic', 'Somborski put 3');
insert into users (id, username, name, surname, address)
values (5, 'user1', 'Blagoje', 'Pantic', 'Kilavci 22');
insert into users (id, username, name, surname, address)
values (6, 'user2', 'Stanoje', 'Stanic', 'Kilavci 12');

insert into `tipmjenjaca` (id, naziv, obrisan) values (1, 'manuelni', false);
insert into `tipmjenjaca` (id, naziv,obrisan) values (2, 'automatski', false);
insert into `tipmjenjaca` (id, naziv, obrisan) values (3, 'poluautomatski', false);

insert into `klasaautomobila` (id, naziv, obrisan) values (1, 'SUV', false);
insert into `klasaautomobila` (id, naziv, obrisan) values (2, 'Old timer', false);
insert into `klasaautomobila` (id, naziv, obrisan) values (3, 'Gradski auto', false);
insert into `klasaautomobila` (id, naziv, obrisan) values (4, 'Hecbek', false);

insert into `vrstagoriva` (id, naziv, obrisan) values (1, 'benzin', false);
insert into `vrstagoriva` (id, naziv, obrisan) values (2, 'dizel', false);
insert into `vrstagoriva` (id, naziv, obrisan) values (3, 'plin', false);

insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (1, 'BMW', 'X3', false);
insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (2, 'Audi', 'A6', false);
insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (3, 'Volkswagen', 'Golf', false);
insert into `markaautomobila` (id, naziv_marke, model, obrisan) values (4, 'Tesla', 'Model X', false);

insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id)
values (1,  '4', '1200', false, true, '120000', '150', '5', 1, 1, 3, 1, 3);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id)
values (2,  '5', '2400', true, true, '150000', '110', '1', 2, 1, 2, 3, 3);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id)
values (3,  '3', '1000', false, false, '220000', '100', '3', 3, 2, 1, 2, 3);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id)
values (4, '3', '1000', false, false, '515000', '500', '4', 2, 3, 1, 2, 4);
insert into `vozilo` (id,  br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id, user_id)
values (5, '3', '1000', false, false, '12000', '1100', '2', 1, 3, 2, 1, 4);

insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (1, 56, 56, 56, 16, 3);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (2, 55, 55, 55, 10, 3);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust, user_id) values (3, 57, 57, 57, 17, 4);

insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (1, '2020-09-16 21:58:58', true, '200', 'Novi Sad', '2020-09-08 21:58:58', 1, 3, 1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (2, '2020-9-10 21:58:58', true, '200', 'Beograd', '2020-09-09 21:58:58', 2, 3, 2);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (3, '2020-09-15 21:58:58', true, '200', 'Banja Luka', '2020-09-10 21:58:58', 1, 3, 3);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (4, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-23 21:58:58', 3, 4, 4);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (5, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-28 21:58:58', 3, 4, 5);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (6, '2020-09-19 21:58:58', true, '109', 'Zrenjanin', '2020-09-17 21:58:58', 1, 3, 1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, user_id, vozilo_id)
values (7, '2020-09-15 21:58:58', true, '250', 'Subotica', '2020-09-11 21:58:58', 2, 3, 2);

insert into `komentar` (id, odobren,tekst, user_id, vozilo_id, oglas_id) values (1, true, 'Vrh auto', 6, 1,1);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id, oglas_id) values (2, true, 'Udobno', 5, 2,2);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id, oglas_id) values (3, true, 'Svidja mi se', 6, 2,2);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id, oglas_id) values (4, false, 'Super', 5, 1,1);
insert into `komentar` (id, odobren,tekst, user_id, vozilo_id, oglas_id) values (5, false, 'Predobro auto', 5, 1,1);

insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (1, '4', 1, 1, 1);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (2, '4', 2, 1, 2);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (3, '5', 2, 1, 2);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (4, '5', 3, 1, 3);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (5, '1', 3, 1, 3);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (6, '4', 4, 6, 4);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (7, '5', 4, 2, 4);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (8, '5', 6, 3, 5);
insert into `ocena` (id, ocjena, oglas_id, user_id, vozilo_id) values (9, '4', 6, 4, 5);