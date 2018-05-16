delete from DocumentoEntity_AreaDeConocimientoEntity;
delete from DocumentoEntity_AutorEntity;
delete from DocumentoEntity_ComentarioEntity;
delete from FacturaEntity_DocumentoEntity;
delete from DocumentoEntity_CursoEntity;
delete from DocumentoEntity_ImagenEntity;
delete from UsuarioEntity_TarjetaDeCreditoEntity;
delete from UsuarioEntity_PayPalEntity;
delete from UsuarioEntity_ReservaEntity;
delete from UsuarioEntity_DeseadoEntity;
delete from CursoEntity_DocumentoEntity;
delete from FacturaEntity;
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
delete from CompraEntity;
delete from PayPalEntity;
delete from TarjetaDeCreditoEntity;
delete from ReservaEntity;
delete from DeseadoEntity;
delete from ComentarioEntity;


-- Ernesto
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (100,'LibroEntity','El Amor en los Tiempos del Cólera','https://imagessl1.casadellibro.com/a/l/t0/51/9788497592451.jpg','una novela',3.2,25000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (200,'FotocopiaEntity','Calculo diferencial','https://imagessl1.casadellibro.com/a/l/t0/71/9788499690971.jpg','libro de matematicas',3.4,32000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (700,'LibroEntity','Cien Años de Soledad','https://imagessl9.casadellibro.com/a/l/t1/39/9788420471839.jpg','novela de Gabriel',2.3,24000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (800,'FotocopiaEntity', 'Fisica','https://http2.mlstatic.com/fisica-universitaria-sears-zemansky-solucionario-13a-edicion-D_NQ_NP_614007-MCO26189892253_102017-F.jpg','fotocopias de fisica',3.3,50000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (900,'LibroEntity','Como Agua Para Chocolate','https://cloud10.todocoleccion.online/libros-clasicos-segunda-mano/tc/2017/04/30/00/85176408.jpg','novela',5,120000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (1000,'LibroEntity','Bajo la misma estrella','https://images-na.ssl-images-amazon.com/images/I/817tHNcyAgL.jpg','novela',3.4,12000);


-- Juan Camilo
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (300,'LibroEntity','Mantenimiento de computadores para ingenieros de sistemas idiotas','http://facultades.unicauca.edu.co/editorial/sites/default/files/imagenesPortada/mantenimiento_de_computadores.jpg','una novela',3.2,200000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (400,'FotocopiaEntity','fotocopia2','https://i.pinimg.com/originals/4a/cd/be/4acdbed94f131b5d95f76eb47293d8c1.jpg','libro de fisica',3.4,250400);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (500,'LibroEntity','Harry Poter y la piedra filosofal','https://4.bp.blogspot.com/-Pm4fx2oIF0I/WMIz7CR6qZI/AAAAAAAAAr4/W7hF85KQYV0AA6O_NHhi6J5_ov_Z6k_TgCLcB/s1600/hp1.jpg','una novela',3.2,12000);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (600,'FotocopiaEntity','fotocopia3','https://www.virginmegastore.ae/medias/sys_master/root/h4e/h76/8818752389150/A-Game-of-Thrones-Book-1-of-A-Song-of-Ice-and-Fire-81498-Detail.jpg','libro de quimica',3.4,254250);

-- Juan Camilo
insert into LibroEntity (id,fechapublicacion,isbn) values (300,'10/20/2012','978-0307387264');
insert into LibroEntity (id,fechapublicacion,isbn) values (500,'12/26/2012','978-0307387264');

-- Ernesto
insert into EditorialEntity(id,nombre) values (11,'Vintage espaniol');
insert into EditorialEntity(id,nombre) values (12,'UNAM');

-- Ernesto
insert into LibroEntity (id,fechapublicacion,isbn,editorial_id) values (100,'09/20/2012','978-0307387264',11);
insert into LibroEntity (id,fechapublicacion,isbn,editorial_id) values (700,'02/20/1995','978-0307474728',11);
insert into LibroEntity (id,fechapublicacion,isbn,editorial_id) values (900,'02/25/1996','978-8466329088',12);
insert into LibroEntity (id,fechapublicacion,isbn,editorial_id) values (1000,'02/25/2013','978-0142424179',12);

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

insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (45,'gregorio ospina','Grego777',20,'g.ospina@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (46,'nicolas sotelo','nico',20,'n.sotelo@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (47,'juan camilo','juanCa',20,'j.jaramillo@gmail.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (48,'ernesto v','thePullRequester',20,'e.viera@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (49,'ana duarte','thePrincesAna',30,'ana@uniandes.edu.co',0);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (50,'cesar','yonoloetiendos',32,'cesar@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (51,'Federico Marroquin','Faker',20,'f.marroquin10@uniandes.edu.co',1);

-- Federico
insert into ReservaEntity(id, fecha, costo) values (545, '2013-10-28 14:59:59',54000);
insert into ReservaEntity(id, fecha, costo) values (546,'2018-02-15 05:27:20',20000);
insert into ReservaEntity(id, fecha, costo) values (547,'2017-10-28 12:45:05',60000);
insert into ReservaEntity(id, fecha, costo) values (548,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (549,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (550,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (551,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (552,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (553,'2012-04-05 22:20:13',10000);
insert into ReservaEntity(id, fecha, costo) values (554,'2012-04-05 22:20:13',10000);


-- Federico
insert into DeseadoEntity(id, nombre, cantidad) values (2000, 'mi lista',1);
insert into DeseadoEntity(id, nombre, cantidad) values (2001, 'mis documentos',2);
insert into DeseadoEntity(id, nombre, cantidad) values (2002, 'mis desesos',3);
insert into DeseadoEntity(id, nombre, cantidad) values (2003, 'regalos para navidad',4);
insert into DeseadoEntity(id, nombre, cantidad) values (2004, 'libritos',5);
insert into DeseadoEntity(id, nombre, cantidad) values (2005, ' !los quiero',2);
insert into DeseadoEntity(id, nombre, cantidad) values (2006, ':3 ñom ñom',3);


-- Gregorio
insert into PayPalEntity (id, usuario, correoElectronico, idusuario) values (65000, 'gregorio','gregorio@ospina', 45);
insert into PayPalEntity (id, usuario, correoElectronico, idusuario) values (65001,'andres','andres@ospina', 45);
insert into PayPalEntity (id, usuario, correoElectronico, idusuario) values (65002,'jeronimpo','jeronimo@ospina', 46);
insert into PayPalEntity (id, usuario, correoElectronico, idusuario) values (65003,'juan','juan@ospina', 47);

-- Gregorio
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7500, 'Visa','4234567890123456', 'gregorio', 123);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7501, 'MasterCard','5134567890123456', 'andres', 124);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7502, 'Visa','4234567870123456', 'jeronimo', 125);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7503, 'Visa','4234567880123456', 'raquel', 130);
insert into TarjetaDeCreditoEntity (id, tipoDeTarjeta, nroDeLaTarjeta, nombreEnLaTarjeta, numeroDeSeguridad) values (7504, 'MasterCard','5134567890153456','jaime', 198);


insert into FacturaEntity (id, fecha, costo, usuario_id, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) values (1, '2018-02-22 20:38:54.973', 3000,45, NULL, 7500);
insert into FacturaEntity (id, fecha, costo, usuario_id, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) values (2, '2018-03-22 20:38:54.973', 6000,45, NULL, 7500);
insert into FacturaEntity (id, fecha, costo, usuario_id, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) values (3, '2018-04-22 20:38:54.973', 5000,45, NULL, 7500);
insert into FacturaEntity (id, fecha, costo, usuario_id, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) values (4, '2018-05-22 20:38:54.973', 4000,45, NULL, 7500);

insert into FacturaEntity_DocumentoEntity(FacturaEntity_ID, Documentos_ID) values (1, 100 );
insert into FacturaEntity_DocumentoEntity(FacturaEntity_ID, Documentos_ID) values (1, 700 );
insert into FacturaEntity_DocumentoEntity(FacturaEntity_ID, Documentos_ID) values (2, 900 );


-- Ernesto
insert into EditorialEntity(id,nombre) values (11,'Vintage espaniol');
insert into EditorialEntity(id,nombre) values (12,'UNAM');

-- nicolas
insert into ComentarioEntity (id, comentario, fecha ) values (20000, 'Buen libro','2018-02-22 20:38:54.973');
insert into ComentarioEntity (id, comentario, fecha ) values (20001, 'Mal Libro','2018-02-22 20:38:54.973');
insert into ComentarioEntity(id,comentario,fecha) values (11000,'es malo','2015-10-28 14:12:59');

--Nicolas
insert into CursoEntity(id,codigo,departamento,nombre) values (3000,'Ma205','Matematicas','Matematica basica');

INSERT INTO COMPRAENTITY (id,COSTO, FECHA, TIPODECOMPRA, METODODEPAGOPAYPAL_ID, METODODEPAGOTDC_ID) VALUES (10000,300, '2018-02-22 20:38:54.973', 'efectivo', NULL, 7500);


--------------------------------------------------------------------------
----------------------------- RELACIONES ---------------------------------
--------------------------------------------------------------------------

-- Federico
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (45, 2000);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (46, 2001);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (47, 2002);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (48, 2003);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (49, 2004);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (50, 2005);
insert into UsuarioEntity_DeseadoEntity (UsuarioEntity_ID, Deseado_ID) values (51, 2006);


-- Federico
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (45, 545);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (45, 546);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (46, 547);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (47, 548);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (45, 549);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (45, 550);
insert into UsuarioEntity_ReservaEntity (UsuarioEntity_ID, Reservas_ID) values (45, 551);


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







