MERGE INTO GRANA (ID, IME) VALUES
  (1, 'Grana 1'),
  (2, 'Grana 2');
  
MERGE INTO PODGRANA (ID, IME) VALUES
  (1, 'Podgrana 1'),
  (2, 'Podgrana 2');
  
MERGE INTO KORISNIK(ID, ADMIN, ADRESA, EMAIL, IME, KORISNICKO_IME, OSOBNI_STATUS, PREZIME, TELEFON, ZAPORKA, ZVANJE, GRANA_ID, PODGRANA_ID) VALUES
  (1, 1, 'Ilica 10', 'mirko@gmail.com', 'Mirko', 'mmirkovic', 'Nisam zgodan al sam nezgodan', 'Mirkovic', '091472827', 'mirkec', 'soboslikar', 1, 1),
  (2, 1, 'Ilica 10', 'ankica@gmail.com', 'Ankica', 'aancic', 'Kaj buljis', 'Ancic', '091472827', 'ankica', 'soboslikar', 1, 1);

-- INSERT INTO PODGRANA (IME) VALUES ();

-- INSERT INTO KORISNIK(ADMIN, ADRESA, EMAIL, IME, KORISNICKO_IME, OSOBNI_STATUS, PREZIME, TELEFON, ZAPORKA, ZVANJE, GRANA_ID, PODGRANA_ID) VALUES ();

-- INSERT INTO UMJETNINA (DATUM_NASTANKA, IME, TEHNIKA) VALUES ();