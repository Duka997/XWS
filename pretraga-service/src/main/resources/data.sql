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

insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id)
values (1,  '4', '1200', false, true, '120000', '150', '5', 1, 1, 3, 1);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id)
values (2,  '5', '2400', true, true, '150000', '110', '1', 1, 1, 2, 3);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id)
values (3,  '3', '1000', false, false, '220000', '100', '3', 3, 2, 1, 2);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id)
values (4,  '3', '1000', false, false, '515000', '500', '4', 2, 3, 1, 2);
insert into `vozilo` (id, br_sjedista_za_djecu, cijena, coliision_damage_wavier, ima_android, kilometraza, moze_precikm, ocjena, klasa_automobila_id, marka_automobila_id, tip_goriva_id, tip_mjenjaca_id)
values (5, '3', '1000', false, false, '12000', '1100', '2', 1, 3, 2, 1);

insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust) values (1, 56, 56, 56, 16);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust) values (2, 55, 55, 55, 10);
insert into `cjenovnik` (id, cijenacdw, cijena_po_danu, cijena_pokm, popust) values (3, 57, 57, 57, 17);

insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (1, '2020-09-26 21:58:58', true, '200', 'Novi Sad', '2020-08-11 21:58:58', 1,  1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (2, '2020-09-29 21:58:58', true, '200', 'Beograd', '2020-08-20 21:58:58', 2,  2);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (3, '2020-09-30 21:58:58', true, '200', 'Banja Luka', '2020-08-25 21:58:58', 1, 3);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (4, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-23 21:58:58', 3,  4);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (5, '2020-09-23 21:58:58', true, '200', 'Banja Luka', '2020-08-28 21:58:58', 3,  5);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (6, '2020-09-30 21:58:58', true, '109', 'Zrenjanin', '2020-09-01 21:58:58', 1,  1);
insert into `oglasi` (id, doo, dostupan, dozvoljena_kilometraza, mjesto_preuzimanja, od, cjenovnik_id, vozilo_id)
values (7, '2020-09-15 21:58:58', true, '250', 'Subotica', '2020-08-30 21:58:58', 2,  2);