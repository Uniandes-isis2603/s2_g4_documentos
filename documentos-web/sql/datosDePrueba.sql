/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  
 * Created: 4/03/2018
 */

delete from LibroEntity;
delete from FotocopiaEntity;
delete from DocumentoEntity;

insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (100,'LibroEntity','amor en los tiempos del colera','abc.jph','una novela',3.2,100.2);
insert into DocumentoEntity (id,dtype,nombre,caratula,descripcion,calificacionpromedio,precio) values (200,'FotocopiaEntity','calculo diferencial','calculo.jpg','libro de matematicas',3.4,200.2);

insert into LibroEntity (id,fechapublicacion,isbn) values (100,'09/20/2012','978-0307387264');

insert into FotocopiaEntity(id,capitulo,nropaginas,profesor) values(200,'del 2 al 25',12,'juan carlos');