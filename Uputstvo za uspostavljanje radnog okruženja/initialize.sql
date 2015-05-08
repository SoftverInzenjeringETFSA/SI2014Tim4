create database tim4;
use tim4;
create user 'EtfSI2014'@'localhost' identified by '2014SIEtf';
GRANT ALL PRIVILEGES ON *.* TO 'EtfSI2014'@'localhost' IDENTIFIED BY '2014SIEtf' WITH GRANT OPTION;

create table komitenti(
	id integer primary key auto_increment,
	tip_komitenta varchar(20),
	adresa varchar(100),
	broj_telefona varchar(15),
	email varchar(40),
	ime varchar(50),
	prezime varchar(50),
	JMB varchar(13),
	broj_licne_karte varchar(10),
	nazivFirme varchar(100),
	pdvBroj varchar(20)
);

create table korisnici(

	id integer primary key auto_increment,
	tip varchar(30),
	username varchar(100),
	lozinka varchar(100),
	ime varchar(50),
	prezime varchar(50),
	broj_licne_karte varchar(10),
	adresa varchar(100),
	broj_telefona varchar(15),
	datum_zaposljavanja date
);

create table skladiste_plinskih_boca(
	id integer primary key auto_increment,
	kapacitet integer NOT NULL,
	kolicina integer NOT NULL,
	cijena real NOT NULL
);

create table plinski_rezervoari(
	id integer primary key auto_increment,
	serijski_broj varchar(20) unique,
	kapacitet integer NOT NULL,
	tezina integer NOT NULL,
	napunjenost integer NOT NULL,
	tip varchar(15),
	datum_zadnjeg_bazdarenja date,
	lokacija varchar(100),
	trenutni_status varchar(100)
);

create table sifarnik_promjena(
	id integer primary key auto_increment,
	sifra_promjene integer unique,
	naziv_promjene varchar(100)
);

create table promjene_na_rezervoarima(
	id integer primary key auto_increment,
	serijski_broj varchar(20),
	foreign key(serijski_broj) references plinski_rezervoari(serijski_broj),
	datum_promjene date,
	tip_promjene integer,
	foreign key(tip_promjene) references sifarnik_promjena(id)
);

create table fakture_prodaja(
	id integer primary key auto_increment,
	broj_fakture varchar(20),
	komitent integer,
	foreign key(komitent) references komitenti(id)
);

create table stavkefakture_prodaja(
	id integer primary key auto_increment,
	faktura integer,
	foreign key(faktura) references fakture_prodaja(id),
	rezervoar varchar(20),
	foreign key(rezervoar) references plinski_rezervoari(serijski_broj),
	kolicina integer,
	tip_rezervoara varchar(20),
	cijena real
);

create table fakture_iznajmljivanje(
	id integer primary key auto_increment,
	broj_fakture varchar(20),
	komitent integer,
	foreign key(komitent) references komitenti(id)
);

create table stavkefakture_iznajmljivanje(
	id integer primary key auto_increment,
	faktura integer,
	foreign key(faktura) references fakture_iznajmljivanje(id),
	rezervoar varchar(20),
	foreign key(rezervoar) references plinski_rezervoari(serijski_broj),
	kolicina integer,
	tip_rezervoara varchar(20),
	iznajmljeno_do date,
	cijena real
);

create table izvjestaji(
	id integer primary key auto_increment,
	tip_izvjestaja varchar(20),
	broj_izvjestaja varchar(10),
	datum_izvjestaja date,
	parametar_izvjestaja varchar(30)
);

create table izvjestaji_stanja_skladiste(
	id integer primary key auto_increment,
	tip_izvjestaja varchar(20),
	broj_izvjestaja varchar(10),
	datum_izvjestaja date
);

create table izvjestaji_stanja_stavke(
	id integer primary key auto_increment,
	izvjestaj integer,
	foreign key(izvjestaj) references izvjestaji_stanja_skladista(id),
	rezervoar varchar(20),
	foreign key(rezervoar) references plinski_rezervoari(serijski_broj),
	kapacitet integer,
	kolicina integer
);

INSERT INTO sifarnik_promjena(sifra_promjene, naziv_promjene) VALUES('1', 'Bazdarenje');
INSERT INTO sifarnik_promjena(sifra_promjene, naziv_promjene) VALUES('2', 'Promjena lokacije');
INSERT INTO sifarnik_promjena(sifra_promjene, naziv_promjene) VALUES('3', 'Punjenje');
INSERT INTO sifarnik_promjena(sifra_promjene, naziv_promjene) VALUES('4', 'Iznajmljivanje');