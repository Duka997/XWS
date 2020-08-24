-- ROLES
insert into role (name, id) values ('ROLE_ADMIN', 1);
insert into role (name, id) values ('ROLE_AGENT', 2);
insert into role (name, id) values ('ROLE_USER', 3);

insert into users (id, username, name, surname, address, password, enabled, is_admin, last_password_reset_date)
values (1, 'kaca', 'Katarina', 'Prodanovic', 'luke vuklalovica 93', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, true, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled, is_admin, last_password_reset_date)
values (2, 'milica', 'Milica', 'Radovanovic', 'luke vuklalovica 93', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, '2020-06-09T21:39:42.000');
insert into users (id, username, name, surname, address, password, enabled, is_admin, last_password_reset_date, ime_kompanije, poslovniid)
values (3, 'nevena', 'Nevena', 'Djukin', 'luke vuklalovica 93', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, '2020-06-09T21:39:42.000', 'pow wow ltd', '1');


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


