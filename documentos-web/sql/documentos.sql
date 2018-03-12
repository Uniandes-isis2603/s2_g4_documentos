delete from LibroEntity;
delete from FotocopiaEntity;
delete from DocumentoEntity;
delete from PayPalEntity;
delete from TarjetaDeCreditoEntity;



insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (100,'LibroEntity','amor en los tiempos del colera','abc.jph','una novela',3.2,100.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (200,'FotocopiaEntity','calculo diferencial','calculo.jpg','libro de matematicas',3.4,200.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (300,'FotocopiaEntity','fisica','fisica.jpg','libro de fisica',4,300.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (400,'LibroEntity','Ojos de fuego','king.png','de stephen king',2.5,50.3);

insert into LibroEntity (id,fechapublicacion,isbn) values (100,'09/20/2012','978-0307387264');
insert into LibroEntity (id,fechapublicacion,isbn) values (400,'12/10/1985','978-8497593779');

insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(200,'del 2 al 25',12,'juan carlos');
insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(300,'el primer parcial',25,'carlos florez');

insert into PayPalEntity (id, usuario, correoElectronico) values (10000, 'gregorio','gregorio@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (10001,'andres','andres@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (10002,'jeronimpo','jeronimo@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (10003,'juan','juan@ospina');

insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (10001, 'Visa','4234567890123456', 'gregorio', 123);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (10002, 'MasterCard','5134567890123456', 'andres', 124);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (10003, 'Visa','4234567870123456', 'jeronimo', 125);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (10004, 'Visa','4234567880123456', 'raquel', 130);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (10005, 'MasterCard','5134567890153456','jaime', 198);



