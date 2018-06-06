DELETE FROM VOTES;
DELETE FROM USER_ROLES;
DELETE FROM USERS;
DELETE FROM MENU_ENTRIES;
DELETE FROM RESTAURANTS;

ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;

INSERT INTO RESTAURANTS ("NAME", "ADDRESS", "DESCRIPTION", "ENABLED") VALUES
  ('Roll with the dishes', '23, Baker st.', 'Japanese cousins', true),
  ('Wild boar', '13, Middle of nowhere st.', 'Medieval classical cousins', true),
  ('inactive', '', '', false);

INSERT INTO MENU_ENTRIES ("REST_ID", "DATE", "NAME", "PRICE", "ENABLED") VALUES
  (100000, '2018-04-07', 'Philadelphia roll', 899, true),
  (100000, '2018-04-07', 'Tuna fish susi', 655, true),
  (100000, '2018-04-08', 'Beef steak', 1250, true),
  (100000, '2018-04-07', 'Chicken soup', 125, false);

INSERT INTO USERS ("NAME", "EMAIL", "PASSWORD") VALUES
  ('Admin', 'admin@dustnfox.com', '123'),
  ('User', 'user@dustnfox.com', '456');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 100007),
  ('ROLE_USER', 100007),
  ('ROLE_USER', 100008);

INSERT INTO VOTES ("DATE", "USER_ID", "REST_ID") VALUES
  ('2018-04-07', 100007, 100000),
  ('2018-04-07', 100008, 100000);