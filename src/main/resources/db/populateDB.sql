DELETE FROM RESTAURANTS;
DELETE FROM DISHES;
ALTER SEQUENCE global_seq RESTART WITH 100000;


INSERT INTO RESTAURANTS ("NAME", "ADDRESS", "DESCRIPTION") VALUES
  ('Roll with the dishes', '23, Baker st.', 'Japanese cousin'),
  ('Wild boar', '13, Middle of nowhere st.', 'Medival classic cousin');

INSERT INTO DISHES ("REST_ID", "CREATION_DATE", "NAME", "PRICE") VALUES
  ('100000', '2018-04-07', 'Philadelphia roll', 899),
  ('100000', '2018-04-07', 'Tuna fish susi', 655),
  ('100001', '2018-04-07', 'Beef steak', 1250),
  ('100001', '2018-04-07', 'BBQ with vegetables', 990),
  ('100001', '2018-04-07', 'Chicken soup', 125);