DELETE FROM RESTAURANTS;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;


INSERT INTO RESTAURANTS ("NAME", "ADDRESS", "DESCRIPTION", "ENABLED") VALUES
  ('Roll with the dishes', '23, Baker st.', 'Japanese cousins', true),
  ('Wild boar', '13, Middle of nowhere st.', 'Medieval classical cousins', true),
  ('inactive', '', '', false);

/*INSERT INTO DISHES ("REST_ID", "CREATION_DATE", "NAME", "PRICE") VALUES
  ('100000', '2018-04-07', 'Philadelphia roll', 899),
  ('100000', '2018-04-07', 'Tuna fish susi', 655),
  ('100001', '2018-04-07', 'Beef steak', 1250),
  ('100001', '2018-04-07', 'BBQ with vegetables', 990),
  ('100001', '2018-04-07', 'Chicken soup', 125);*/