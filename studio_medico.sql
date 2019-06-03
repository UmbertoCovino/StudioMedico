create database studio_medico;

create table proprietari (
	id int(10) primary key auto_increment,
	nome varchar(80) not null,
	cognome varchar(80) not null,
	email varchar(80) not null,
	password varchar(20) not null
);

create table pazienti (
	codice_fiscale varchar(16) primary key,
	nome varchar(80) not null,
	cognome varchar(80) not null,
	email varchar(80) not null,
	password varchar(20) not null
);

create table specializzazioni (
	nome varchar(80) primary key
);

create table medici (
	codice int(10) primary key auto_increment,
	nome varchar(80) not null,
	cognome varchar(80) not null,
	email varchar(80) not null,
	password varchar(20) not null,
	nome_specializzazione varchar(80),
	foreign key (nome_specializzazione) references specializzazioni(nome)
);

create table calendario_disponibilita (
	id int(10) primary key auto_increment,
	anno int(4) not null,
	codice_medico int(10),
	foreign key (codice_medico) references medici(codice)
);

create table disponibilita (
	id int(10) primary key auto_increment,
	giorno date not null,
	ora_inizio time not null,
	ora_fine time not null,
	max_num_visite int(2) not null check (max_num_visite > 0),
	presenza bit not null,
	id_calendario_disponibilita int(10),
	foreign key (id_calendario_disponibilita) references calendario_disponibilita(id)
);

create table tipologie_visite (
	id int(10) primary key auto_increment,
	nome varchar(80) not null,
	prezzo_fisso float(5,2) not null check (prezzo_fisso > 0),
	costo_manodopera float(5,2) not null check (costo_manodopera > 0),
	costo_esercizio float(5,2) not null check (costo_esercizio > 0)
);

create table tipologie_visite_specializzazioni (
	id_tipologia_visita int(10),
	nome_specializzazione varchar(80),
	primary key (id_tipologia_visita, nome_specializzazione),
	foreign key (id_tipologia_visita) references tipologie_visite(id),
	foreign key (nome_specializzazione) references specializzazioni(nome)
);

create table prenotazioni (
	id int(10) primary key auto_increment,
	giorno date not null,
	ora time not null,
	id_tipologia_visita int(10),
	codice_medico int(10),
	codice_fiscale_paziente varchar(16),
	foreign key (id_tipologia_visita) references tipologie_visite(id),
	foreign key (codice_medico) references medici(codice),
	foreign key (codice_fiscale_paziente) references pazienti(codice_fiscale)
);

create table visite (
	id int(10) primary key auto_increment,
	diagnosi varchar(200) not null,
	terapia varchar(200) not null,
	id_prenotazione int(10),
	foreign key (id_prenotazione) references prenotazioni(id)
);

create table fatture (
	id int(10) primary key auto_increment,
	importo float(5, 2) not null,
	id_visita int(10),
	codice_fiscale_paziente varchar(16),
	foreign key (id_visita) references visite(id),
	foreign key (codice_fiscale_paziente) references pazienti(codice_fiscale)
);

create table pagamenti (
	id int(10) primary key auto_increment,
	data date not null,
	metodo_pagamento varchar(80) not null,
	id_fattura int(10),
	foreign key (id_fattura) references fatture(id)
);
