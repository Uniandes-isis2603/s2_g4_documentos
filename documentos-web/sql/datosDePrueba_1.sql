
delete from UsuarioEntity;
delete from ReservaEntity;
delete from DeseadoEntity;


insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (45,'gregorio ospina','gregoOspi',20,'g.ospina@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (46,'nicolas sotelo','nico',20,'n.sotelo@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (47,'juan camilo','dobleConSis',20,'j.jaramillo@gmail.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (48,'ernesto v','thePullRequester',20,'e.viera@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (49,'federico marroquin','Faker',30,'f.marroquin10@uniandes.edu.co',1);
insert into UsuarioEntity(id,nombre,nombreUsuario,edad,correo,genero) values (50,'cesar','elProfe',32,'cesar@uniandes.edu.co',1);

insert into ReservaEntity(id, fecha, costo) values (545, '2013-10-28 14:59:59',54000);
insert into ReservaEntity(id, fecha, costo) values (546,'2018-02-15 05:27:20',20000);
insert into ReservaEntity(id, fecha, costo) values (547,'2017-10-28 12:45:05',60000);
insert into ReservaEntity(id, fecha, costo) values (548,'2012-04-05 22:20:13',10000);

insert into DeseadoEntity(id, nombre, cantidad) values (2000, 'mi lista',4.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2001, 'mis documentos',6.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2002, 'mis desesos',10.0);
insert into DeseadoEntity(id, nombre, cantidad) values (2003, 'libros pref',5.0);

