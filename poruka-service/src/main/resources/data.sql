

insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date, num_cancelled)
values (1, 'kaca', 'Katarina', 'Prodanovic', 'Stepe Stepanovica 10', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, true, '2020-06-09T21:39:42.000', 1);
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date, num_cancelled)
values (2, 'milica', 'Milica', 'Radovanovic', 'Kilavci 2', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 1);
insert into users (id, username, name, surname, address, password, enabled, deleted, is_admin, last_password_reset_date, ime_kompanije, poslovniid, num_cancelled)
values (3, 'nevena', 'Nevena', 'Djukin', 'Padej 3', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 'pow wow ltd', '1', 0);
insert into users (id, username, name, surname, address, password, enabled, deleted, is_admin, last_password_reset_date, ime_kompanije, poslovniid, num_cancelled)
values (4, 'agent1', 'Nikola', 'Jokic', 'Somborski put 3', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 'Burekdzinica', '2', 0);
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date, num_cancelled)
values (5, 'user1', 'Blagoje', 'Pantic', 'Kilavci 22', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 0);
insert into users (id, username, name, surname, address, password, enabled,  deleted, is_admin, last_password_reset_date, num_cancelled)
values (6, 'user2', 'Stanoje', 'Stanic', 'Kilavci 12', '$2a$10$U9jvaVCEV.48aHuR2vck/emgRLXJ3d5jleYyCTwdO/X9fmDtZ0bgG', true, false, false, '2020-06-09T21:39:42.000', 0);

insert into `poruka` (id, datum_slanja, sadrzaj, posiljalac_id, primalac_id) values (1, '2020-06-06 21:58:58', 'Hi :D', 2, 3 );
insert into `poruka` (id, datum_slanja, sadrzaj, posiljalac_id, primalac_id) values (2, '2020-06-06 21:59:58', 'Hey :)', 3, 2 );

