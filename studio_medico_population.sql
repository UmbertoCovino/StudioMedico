insert into proprietari (nome, cognome, email, password)
values ('admin', 'admin', 'admin@sm.com', 'admin');

insert into pazienti
values ('XXXXXXXXXXXXXXXX', 'Marco', 'Cortese', 'mc@sm.com', 'mc'),
	   ('CVNMRT96A18A783J', 'Umberto', 'Covino', 'uc@sm.com', 'uc');

insert into specializzazioni
values ('cardiologo'),
	   ('dentista'),
	   ('ortopedico');

insert into medici (nome, cognome, email, password, nome_specializzazione)
values ('Mario', 'Rossi', 'mr@sm.com', 'mr', 'cardiologo'),
	   ('Antonio', 'Bianchi', 'ab@sm.com', 'ab', 'dentista'),
	   ('Giuseppe', 'Fermi', 'gf@sm.com', 'gf', 'ortopedico');

insert into calendario_disponibilita (anno, codice_medico)
values (2019, 1),
	   (2019, 2),
	   (2019, 3);

insert into disponibilita (giorno, ora_inizio, ora_fine, max_num_visite, presenza, id_calendario_disponibilita)
values ('2019-07-03', '16:00', '19:00', 6, 0, 1),
	   ('2019-07-05', '17:00', '20:00', 6, 0, 1),
	   ('2019-07-07', '16:00', '19:00', 6, 0, 1),
	   ('2019-07-04', '9:00', '12:00', 6, 0, 2),
	   ('2019-07-06', '9:00', '12:00', 6, 0, 2),
	   ('2019-07-02', '16:00', '19:00', 6, 0, 3),
	   ('2019-07-09', '9:00', '12:00', 6, 0, 3);

insert into tipologie_visite (nome, prezzo_fisso, costo_manodopera, costo_esercizio)
values ('Elettrocardiogramma', '50.00', '5.00', '5.00'),
	   ('Visita odontoiatrica generica', '85.00', '10.00', '5.00'),
	   ('Radiografia', '55.00', '8.00', '15.00');

insert into tipologie_visite_specializzazioni
values (1, 'cardiologo'),
	   (2, 'dentista'),
	   (3, 'ortopedico');

insert into prenotazioni (giorno, ora, id_tipologia_visita, codice_medico, codice_fiscale_paziente)
values ('2019-05-05', '17:30', 1, 1, 'CVNMRT96A18A783J');

insert into visite (diagnosi, terapia, id_prenotazione)
values ('Elettrocardiogramma negativo', 'Nessuna', 1);

insert into fatture (importo, id_visita, codice_fiscale_paziente)
values ('60.00', 1, 'CVNMRT96A18A783J');

insert into pagamenti (data, metodo_pagamento, id_fattura)
values ('2019-05-15', 'Bonifico bancario', 1);

insert into 
values ('', '', '', '', '');