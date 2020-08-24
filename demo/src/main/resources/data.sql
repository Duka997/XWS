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

