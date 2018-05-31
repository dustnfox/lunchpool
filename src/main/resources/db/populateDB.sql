DELETE FROM RESTAURANTS;
ALTER SEQUENCE GLOBAL_SEQ RESTART WITH 100000;


INSERT INTO RESTAURANTS ("NAME", "ADDRESS", "DESCRIPTION", "ENABLED") VALUES
  ('Roll with the dishes', '23, Baker st.', 'Japanese cousins', true),
  ('Wild boar', '13, Middle of nowhere st.', 'Medieval classical cousins', true),
  ('inactive', '', '', false);

INSERT INTO MENU_ENTRIES ("REST_ID", "DATE", "NAME", "PRICE", "ENABLED") VALUES
  ('100000', '2018-04-07', 'Philadelphia roll', 899, true),
  ('100000', '2018-04-07', 'Tuna fish susi', 655, true),
  ('100000', '2018-04-08', 'Beef steak', 1250, true),
  ('100000', '2018-04-07', 'Chicken soup', 125, false);