delete from DocumentoEntity_AreaDeConocimientoEntity;
delete from DocumentoEntity_AutorEntity;
delete from DocumentoEntity_ComentarioEntity;
delete from DocumentoEntity_CursoEntity;
delete from DocumentoEntity_ImagenEntity;
delete from UsuarioEntity_TarjetaDeCreditoEntity;
delete from UsuarioEntity_PayPalEntity;
delete from CursoEntity_DocumentoEntity;
delete from UsuarioEntity;
delete from LibroEntity;
delete from FotocopiaEntity;
delete from DocumentoEntity;
delete from ComentarioEntity;
delete from CursoEntity;
delete from AreaDeConocimientoEntity;
delete from ImagenEntity;
delete from AutorEntity;
delete from EditorialEntity;
delete from PayPalEntity;
delete from TarjetaDeCreditoEntity;
delete from ReservaEntity;
delete from DeseadoEntity;
delete from ComentarioEntity;
delete from CompraEntity;


-- Ernesto
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (100,'LibroEntity','amor en los tiempos del colera','abc.jph','una novela',3.2,100.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (200,'FotocopiaEntity','calculo diferencial','calculo.jpg','libro de matematicas',3.4,200.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (700,'LibroEntity','Cien anios de soledad','imagen.png','novela de Gabriel',2.3,24);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (800,'FotocopiaEntity', 'Fisica','fisica.png','fotocopias de fisica',3.3,50);

-- Juan Camilo
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (300,'LibroEntity','libro2','abc.jpg','una novela',3.2,110.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (400,'FotocopiaEntity','fotocopia2','calculo.jpg','libro de fisica',3.4,250.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (500,'LibroEntity','libro3','abc.jpg','una novela',3.2,130.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (600,'FotocopiaEntity','fotocopia3','calculo.jpg','libro de quimica',3.4,254.2);

-- Juan Camilo
insert into LibroEntity (id,fechapublicacion,isbn) values (300,'10/20/2012','978-0307387264');
insert into LibroEntity (id,fechapublicacion,isbn) values (500,'12/26/2012','978-0307387264');

-- Ernesto
insert into LibroEntity (id,fechapublicacion,isbn) values (100,'09/20/2012','978-0307387264');
insert into LibroEntity (id,fechapublicacion,isbn) values (700,'02/20/1995','978-0307474728');

-- Juan Camilo
insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(400,'del 3 al 20',14,'Profe 2');
insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(600,'del 4 al 15',22,'Profe 3');

-- Ernesto
insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(200,'del 2 al 25',12,'Juan Carlos');
insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(800,'3,4,5,6',22,'Carlos Avila');

-- Juan Camio
insert into ImagenEntity (id,nombre,img) values (10001,'Portada','portada.png');
insert into ImagenEntity (id,nombre,img) values (10002,'Pagina1','pag1.png');
insert into ImagenEntity (id,nombre,img) values (10003,'Contraportada','contraportada.png');

-- Ernesto
insert into ImagenEntity(id,img,nombre) values (11,'amor.png','parte trasera');
insert into ImagenEntity(id,img,nombre) values (12,'pagina2.jpg','pagina 2 de la fotocopias.');
insert into ImagenEntity(id,img,nombre) values (13,'pagina24.jpg','pagina 5 de la fotocopia.');
insert into ImagenEntity(id,img,nombre) values (14,'fotoGabriel.ngg','foto de gabriel');


-- Juan Camilo
insert into AutorEntity (id,nombre) values (10001,'Autor1');
insert into AutorEntity (id,nombre) values (10002,'Autor2');
insert into AutorEntity (id,nombre) values (10003,'Autor3');
insert into AutorEntity (id,nombre) values (10004,'Autor4');
insert into AutorEntity (id,nombre) values (10005,'Autor5');

-- Ernesto
insert into AutorEntity(id,nombre) values (11,'Gabriel Garcia Marques');
insert into AutorEntity(id,nombre) values (12,'Ullman');
insert into AutorEntity(id,nombre) values (13,'Gates');

-- Juan Camilo
insert into AreaDeConocimientoEntity (id,tipo) values (10001,'Area1');
insert into AreaDeConocimientoEntity (id,tipo) values (10002,'Area2');
insert into AreaDeConocimientoEntity (id,tipo) values (10003,'Area3');
insert into AreaDeConocimientoEntity (id,tipo) values (10004,'Area4');
insert into AreaDeConocimientoEntity (id,tipo) values (10005,'Area5');

-- Ernesto
insert into AreaDeConocimientoEntity(id,tipo) values (11,'Romanticismo');
insert into AreaDeConocimientoEntity(id,tipo) values (12,'Literatura');
insert into AreaDeConocimientoEntity(id,tipo) values (13,'Fisica');
insert into AreaDeConocimientoEntity(id,tipo) values (14,'Matematicas');

-- Ernesto
insert into ComentarioEntity(id,comentario,fecha) values (11,'es malo','2015-10-28 14:12:59');
insert into ComentarioEntity(id,comentario,fecha) values (12,'a cuanto lo deja','2015-04-29 14:25:59');
insert into ComentarioEntity(id,comentario,fecha) values (13,'tienes el 7','2012-11-30 14:30:59');
insert into ComentarioEntity(id,comentario,fecha) values (14,'que profesor tan malo','2015-10-29 14:54:59');

-- Ernesto
insert into CursoEntity(id,codigo,departamento,nombre) values (11,'FISI1018','Fisica','Fisica1');
insert into CursoEntity(id,codigo,departamento,nombre) values (12,'MATE1214','Matematicas','Calculo integral');

-- Federico

insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (45,'gregorio ospina','gregoOspi',20,'g.ospina@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (46,'nicolas sotelo','nico',20,'n.sotelo@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (47,'juan camilo','dobleConSis',20,'j.jaramillo@gmail.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (48,'ernesto v','thePullRequester',20,'e.viera@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (49,'federico marroquin','Faker',30,'f.marroquin10@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (50,'cesar','elProfe',32,'cesar@uniandes.edu.co',1);

-- Federico
insert into ReservaEntity(id, fecha, costo) values (545, '2013-10-28 14:59:59',54000);
insert into ReservaEntity(id, fecha, costo) values (546,'2018-02-15 05:27:20',20000);
insert into ReservaEntity(id, fecha, costo) values (547,'2017-10-28 12:45:05',60000);
insert into ReservaEntity(id, fecha, costo) values (548,'2012-04-05 22:20:13',10000);

-- Federico
insert into DeseadoEntity(id, nombre, cantidad) values (2000, 'mi lista',4.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2001, 'mis documentos',6.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2002, 'mis desesos',10.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2003, 'libros pref',5.0);

-- Gregorio
insert into PayPalEntity (id, usuario, correoElectronico) values (65000, 'gregorio','gregorio@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (65001,'andres','andres@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (65002,'jeronimpo','jeronimo@ospina');
insert into PayPalEntity (id, usuario, correoElectronico) values (65003,'juan','juan@ospina');

-- Gregorio
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7500, 'Visa','4234567890123456', 'gregorio', 123);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7501, 'MasterCard','5134567890123456', 'andres', 124);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7503, 'Visa','4234567870123456', 'jeronimo', 125);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7502, 'Visa','4234567880123456', 'raquel', 130);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7504, 'MasterCard','5134567890153456','jaime', 198);

-- Ernesto
insert into EditorialEntity(id,nombre) values (11,'Vintage espaniol');
insert into EditorialEntity(id,nombre) values (12,'UNAM');

-- nicolas
insert into ComentarioEntity (id, comentario, fecha ) values (20000, 'Buen libro','2018-02-22 20:38:54.973');
insert into ComentarioEntity (id, comentario, fecha ) values (20001, 'Mal Libro','2018-02-22 20:38:54.973');
insert into ComentarioEntity(id,comentario,fecha) values (11000,'es malo','2015-10-28 14:12:59');

--Nicolas
insert into CursoEntity(id,codigo,departamento,nombre) values (3000,'Ma205','Matematicas','Matematica basica');

INSERT INTO COMPRAENTITY (id,COSTO, FECHA, TIPODECOMPRA, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) 
	VALUES (10000,300, '2018-02-22', 'efectivo', NULL, 7500);

--------------------------------------------------------------------------
----------------------------- RELACIONES ---------------------------------
--------------------------------------------------------------------------

-- Gregorio
insert into UsuarioEntity_PayPalEntity (UsuarioEntity_ID, PayPal_ID) values (45, 65000);
insert into UsuarioEntity_PayPalEntity (UsuarioEntity_ID, PayPal_ID) values (45, 65001);
insert into UsuarioEntity_PayPalEntity (UsuarioEntity_ID, PayPal_ID) values (46, 65002);
insert into UsuarioEntity_PayPalEntity (UsuarioEntity_ID, PayPal_ID) values (47, 65003);

-- Gregorio
insert into UsuarioEntity_TarjetaDeCreditoEntity (UsuarioEntity_id, tarjetasCredito_id) values (45, 7501);
insert into UsuarioEntity_TarjetaDeCreditoEntity (UsuarioEntity_id, tarjetasCredito_id) values (46, 7502);
insert into UsuarioEntity_TarjetaDeCreditoEntity (UsuarioEntity_id, tarjetasCredito_id) values (47, 7503);
insert into UsuarioEntity_TarjetaDeCreditoEntity (UsuarioEntity_id, tarjetasCredito_id) values (45, 7500);
insert into UsuarioEntity_TarjetaDeCreditoEntity (UsuarioEntity_id, tarjetasCredito_id) values (45, 7504);

-- Ernesto
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (100,11);
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (700,11);
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (100,12);
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (700,12);
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (800,13);
insert into DocumentoEntity_AreaDeConocimientoEntity(documentos_id,areas_id) values (200,14);

-- Ernesto
insert into DocumentoEntity_CursoEntity(documentoentity_id,cursos_id) values (800,11);
insert into DocumentoEntity_CursoEntity(documentoentity_id,cursos_id) values (200,12);

-- Nicolas
insert into CursoEntity_DocumentoEntity(cursoentity_id,bibliografiadelcurso_id) values (3000,300);


-- Ernesto
insert into DocumentoEntity_ComentarioEntity(documentoentity_id,comentarios_id) values (100,11);
insert into DocumentoEntity_ComentarioEntity(documentoentity_id,comentarios_id) values (200,12);
insert into DocumentoEntity_ComentarioEntity(documentoentity_id,comentarios_id) values (700,13);
insert into DocumentoEntity_ComentarioEntity(documentoentity_id,comentarios_id) values (800,14);

-- Juan Camilo
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10001,100);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10001,200);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10001,400);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10003,100);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10003,300);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10005,500);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10005,100);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10004,600);
insert into DocumentoEntity_AreaDeConocimientoEntity (Areas_id,Documentos_id) values (10002,400);

-- Ernesto
insert into DocumentoEntity_AutorEntity(documentos_id,autores_id) values (100,11);
insert into DocumentoEntity_AutorEntity(documentos_id,autores_id) values (700,11);
insert into DocumentoEntity_AutorEntity(documentos_id,autores_id) values (200,12);
insert into DocumentoEntity_AutorEntity(documentos_id,autores_id) values (800,13);


-- Juan Camilo
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (100,10001);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (200,10001);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (300,10002);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (400,10003);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (300,10004);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (600,10004);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (500,10004);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (100,10002);
insert into DocumentoEntity_AutorEntity (Documentos_id, Autores_id) values (200,10003);







