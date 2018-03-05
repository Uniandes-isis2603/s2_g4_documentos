/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.ejb;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.LibroPersistence;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.inject.Inject;

/**
 *
 * @author Ernesto Viana
 */
@Stateless
public class LibroLogic {
    
    private static final Logger LOGGER = Logger.getLogger(LibroLogic.class.getName());
    
    @Inject
    private LibroPersistence persistence;
    
    /**
     * Devuelve todos los libros que hay en la base de datos.
     * @return Lista de entidades tipo libro.
     */
    public List<LibroEntity> getLibros()
    {
        LOGGER.info("Inicia proceso de consultar todos los libros");
        List<LibroEntity> libros = persistence.findAll();
        LOGGER.info("Termina proceso de consultar todos los libros");
        return libros;
    }
    
    /**
     * Busca un libro por ID
     * @param id El id del libro a buscar.
     * @return El libro encontrado, null si no lo encuentra.
     */
    public LibroEntity getLibro(Long id){
        LOGGER.log(Level.INFO,"Inicia proceso de consultar libro con id={0}",id);
        LibroEntity libro = persistence.find(id);
        if(libro == null){
            LOGGER.log(Level.SEVERE, "El libro con el id={0} no existe",id);
            
        }
        LOGGER.log(Level.INFO,"Termina proceso de consultar libro con id={0}",id);
        return libro;
    }
    
    /**
     * Busca los libros segun el nombre.
     * @param nombre el nombre del libro a buscar.
     * @return La lista de libros que tienen ese mismo nombre.
     */
    public List<LibroEntity> getLibrosByName(String nombre){
        LOGGER.log(Level.INFO,"Inicia proceso de consultar libro con el nombre={}",nombre);
        List<LibroEntity> libros = persistence.findByName(nombre);
        if(libros.isEmpty()){
            LOGGER.log(Level.SEVERE,"El libro con el id={} no existe",nombre);
        }
        LOGGER.log(Level.INFO,"Termina proceso de consultar libro con nombre={}",nombre);
        return libros;
    }
    
    /**
     * Metodo que crea un libro en la base de datos.
     * @param entity entidad que va a persistir en la base de datos.
     * @return el libro creado.
     * @throws BusinessLogicException si los datos estan incompletos, el precio es incorrecto, la fecha es incorrecta,
     * o el isbn es incorrecto.
     */
    public LibroEntity createLibro(LibroEntity entity) throws BusinessLogicException {
        LOGGER.info("Inicia proceso de creacion de libro");
        if(!validarCompletitud(entity))
        {
            throw new BusinessLogicException("El libro que se va a crear no tiene la informacion completa");
        }
        else if(entity.getPrecio() < 0)
        {
            throw new BusinessLogicException("El precio del libro es negativo");
        }
        else if(!validarFecha(entity.getFechaPublicacion()))
        {
            throw new BusinessLogicException("La fecha de publicacion es incorrecta");
        }
        else if(!validarIsbn13(entity.getIsbn()))
        {
            throw new BusinessLogicException("El codigo isbn del libro no es correcto");
        }
        
        entity.setCalificacionPromedio(-1.0);
        persistence.create(entity);
        
        LOGGER.info("Termina proceso de creacion de libro");
        return entity;
    }
    
    /**
     * Actualiza un libro por el id.
     * @param id El id del libro a actualizar.
     * @param entity la entidad del libro con los cambios.
     * @return la entidad del libro luego de ser actualizada.
     * @throws BusinessLogicException  si los datos estan incompletos, el precio es incorrecto, la fecha es incorrecta,
     * o el isbn es incorrecto.
     */
    public LibroEntity updateLibro(Long id,LibroEntity entity) throws BusinessLogicException{
        LOGGER.log(Level.INFO,"Inicia proceso de actualizar un libro con id={0}", id);
        if(!validarCompletitud(entity))
        {
            throw new BusinessLogicException("El libro que se va a actualizar no tiene la informacion completa");
        }
        else if(entity.getPrecio() <0)
        {
            throw new BusinessLogicException("El precio del libro es negativo");
        }
        else if(!validarFecha(entity.getFechaPublicacion()))
        {
            throw new BusinessLogicException("La fecha de publicacion es incorrecta");
        }
        else if(!validarIsbn13(entity.getIsbn()))
        {
             throw new BusinessLogicException("El codigo isbn del libro no es correcto");
        }
        
        LibroEntity newEntity = persistence.update(entity);        
        LOGGER.log(Level.INFO,"Termina proceso de actualizar libro con id= {0}", entity.getId());
        return newEntity;     
    }
    
    /**
     * Elimina un libro con id.
     * @param id El id del libro que se va a eliminar.
     */
    public void deleteLibro(Long id)
    {
        LOGGER.log(Level.INFO,"Inicia proceso de eliminar un libro con id ={0}",id);
        persistence.delete(id);
        LOGGER.log(Level.INFO,"Termina proceso de eliminar un libro con id={0}",id);
    }
    
    //--------------------------//
    // Metodos de validacion.
    //--------------------------//
    
    
    /**
     * Metodo que valida la completitud de los datos basicos de un libro.
     * @param entity libro que se va a validar, tiene que tener el nombre, precio, caratula y descripcion.
     * @return true, si el libro tiene la informacion completa, de lo contrario false.
     */
    public boolean validarCompletitud(LibroEntity entity) {
        
        return !(entity.getNombre() == null || entity.getPrecio() == null || entity.getCaratula() == null || entity.getDescripcion() == null);
    }
    
    /**
     * Metodo que valida que la fecha de publicacion sea anterior a el dia de hoy.
     * @param fecha fecha que se va a comprobar.
     * @return true, si la fecha es antes que hoy, false de lo contrario.
     */
    public boolean validarFecha(Date fecha)
    {
        Calendar c = Calendar.getInstance();

        
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        
        Date hoy = c.getTime();
        return fecha.before(hoy);
    }
    
    /**
     * Metodo que valida un codigo isbn de 13 digitos.
     * @param isbn el isbn que se va a validar.
     * @return true,si el isbn es valido, false de lo contrario.
     */
    public boolean validarIsbn13(String isbn)
    {
        if ( isbn == null )
        {
            return false;
        }

        isbn = isbn.replaceAll( "-", "" );

        if ( isbn.length() != 13 )
        {
            return false;
        }

        try
        {
            int tot = 0;
            for ( int i = 0; i < 12; i++ )
            {
                int digit = Integer.parseInt( isbn.substring( i, i + 1 ) );
                tot += (i % 2 == 0) ? digit * 1 : digit * 3;
            }

            int checksum = 10 - (tot % 10);
            if ( checksum == 10 )
            {
                checksum = 0;
            }

            return checksum == Integer.parseInt( isbn.substring( 12 ) );
        }
        catch ( NumberFormatException nfe )
        {
            return false;
        }
    }
}
