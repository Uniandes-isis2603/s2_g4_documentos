/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.LibroLogic;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.persistence.LibroPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ernesto Viana.
 */
@RunWith(Arquillian.class)
public class LibroLogicTest {
    
    @Deployment
    public static JavaArchive createDeployment(){
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroLogic.class.getPackage())
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase libroLogic cuyos metodos se van a probar.
     */
    @Inject
    private LibroLogic libroLogic;
    
    /**
     * Inyeccion de la dependencia a la clase de libroPersistence cuyos metodos van a ser de uso para probar.
     */
    @Inject
    private LibroPersistence libroPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de datos
     * por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando
     * se crean/borran datos para las pruebas
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp(){
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch(Exception e) {
            e.printStackTrace();
            try{
                utx.rollback();
            } catch(Exception e1)
            {
                e1.printStackTrace();
            }
        }
        
    }
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData(){
        em.createQuery("delete from LibroEntity").executeUpdate();
    }
    
    private List<LibroEntity> data = new ArrayList<>();
    
    private List<LibroEntity> dataPrueba =new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData(){
         PodamFactory factory = new PodamFactoryImpl();
        for(Integer i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            entity.setId(i.longValue());
            em.persist(entity);
            data.add(entity);
        }
        
        //creando entities para prueba
        LibroEntity entity = new LibroEntity();
        LibroEntity entity1 = new LibroEntity();
        LibroEntity entity2 = new LibroEntity();
        LibroEntity entity3 = new LibroEntity();
        LibroEntity entity4 = new LibroEntity();
        Integer a = 4;
        Integer b = 5;
        Integer c = 6;
        Integer d = 7;
        Integer e = 8;
        Integer f = 9;
        // poblandolos con cada caso
        //Entity con datos incompletos.
        entity.setId(a.longValue());
        entity.setNombre(null);
        entity.setPrecio(1.2);
        entity.setCalificacionPromedio(null);
        entity.setCaratula("algo.png");
        entity.setDescripcion("Libro de prueba 1");
        entity.setIsbn("978-0307387264");
        Date fecha = new GregorianCalendar(2012,02,22).getTime();
        entity.setFechaPublicacion(fecha);
        
        //Entity1 con el precio negativo
        entity1.setId(b.longValue());
        entity1.setNombre("Cien años de hacer codigo en Desarrollo");
        entity1.setPrecio(-1.2);
        entity1.setCalificacionPromedio(null);
        entity1.setCaratula("OtroAlgo.png");
        entity1.setDescripcion("La historia de un estudiando matandose por tratar de hacer algo bueno");
        entity1.setIsbn( "978-0307474728");
        entity1.setFechaPublicacion(fecha);
        
        //Entity2 con el isbn incorrecto
        entity2.setId(c.longValue());
        entity2.setNombre("El general no quiere hacer la tarea de desarrollo");
        entity2.setPrecio(5.4);
        entity2.setCalificacionPromedio(null);
        entity2.setCaratula("ElAlgoreferenciado.png");
        entity2.setDescripcion("El man esta aburrido");
        entity2.setIsbn("978-0307474720");
        entity2.setFechaPublicacion(fecha);
        
        //Entity 3 con la fecha incorrecta
        entity3.setId(d.longValue());
        entity3.setNombre("1984");
        entity3.setPrecio(4.3);
        entity3.setCalificacionPromedio(null);
        entity3.setCaratula("Algo.jpg");
        entity3.setDescripcion("Distopia, wow");
        entity3.setIsbn("978-0451524935");
        Date fechaIncorrecta = new GregorianCalendar(2050,02,22).getTime();
        entity3.setFechaPublicacion(fechaIncorrecta);
        
        //Entity 4 con todos los datos correctos.
        entity4.setId(e.longValue());
        entity4.setNombre("El coronel");
        entity4.setPrecio(5.4);
        entity4.setCalificacionPromedio(4.3);
        entity4.setCaratula("EseMismo.png");
        entity4.setDescripcion("Las cosas de la vida");
        entity4.setIsbn("978-0451524935");
        entity4.setFechaPublicacion(fecha);
        
        //los añadimos   la lista de prueba
        dataPrueba.add(0,entity);
        dataPrueba.add(1,entity1);
        dataPrueba.add(2,entity2);
        dataPrueba.add(3,entity3);
        dataPrueba.add(4,entity4);
        
        entity4.setId(f.longValue());
        em.persist(entity4);
        data.add(entity4);
    }
    
    /**
     * Prueba para crear un libro.
     */
    @Test
    public void createLibroTest(){
        
        //Caso 1 el libro se agrega correctamente.
        LibroEntity newEntity  = dataPrueba.get(4);
        try{
            Double calificacionCorrecta = -1.0;
            LibroEntity result =libroLogic.createLibro(newEntity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), dataPrueba.get(4).getNombre());
            Assert.assertEquals(calificacionCorrecta,result.getCalificacionPromedio());
        }catch(Exception e)
        {
            Assert.assertNotNull(null);
        }
        
        //Caso 2 El libro no tiene la informacion completa.
        newEntity = dataPrueba.get(0);
        try{
            LibroEntity result = libroLogic.createLibro(newEntity);
        } catch(Exception e1)
        {
            //Si el mensaje de la excepcion es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El libro que se va a crear no tiene la informacion completa",e1.getMessage());
        }
        
        //Caso 3  El Libro tiene precio negativo.
        newEntity = dataPrueba.get(1);
        try{ 
            LibroEntity result = libroLogic.createLibro(newEntity);
        } catch(Exception e2)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El precio del libro es negativo",e2.getMessage());
        }
        
        //Caso4 El libro tiene el isbn incorrecto.
        newEntity = dataPrueba.get(2);
         try{ 
            LibroEntity result = libroLogic.createLibro(newEntity);
        } catch(Exception e3)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El codigo isbn del libro no es correcto",e3.getMessage());
        }
         
        //Caso5 El lubro tiene la fecha incorrecta.
        newEntity = dataPrueba.get(3);
         try{ 
            LibroEntity result = libroLogic.createLibro(newEntity);
        } catch(Exception e4)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("La fecha de publicacion es incorrecta",e4.getMessage());
        }
    }
    
    /**
     * Prueba para consultar la lista de libros.
     */
    @Test 
    public void getLibrosTest() {
        List<LibroEntity> list = libroLogic.getLibros();
        Assert.assertEquals(data.size(),list.size());
        
        for(LibroEntity ent: list){
            boolean found = false;
            for(LibroEntity entity: data) {
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un libro.
     */
    @Test
    public void getLibroTest(){
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroLogic.getLibro(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());
    }
    
    /**
     * Prueba para consultar lista de libros por nombre.
     */
    @Test
    public void getLibroByNameTest(){
        LibroEntity entity = data.get(0);
        List<LibroEntity> newEntity = libroLogic.getLibrosByName(entity.getNombre());
        Assert.assertFalse(newEntity.isEmpty());
        for(LibroEntity ent : newEntity) {
            boolean found =false;
            if(ent.getNombre().equals(entity.getNombre())){
                found =true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar un libro.
     */
    @Test
    public void UpdateLibroTest() {
        
        
        //Caso 1 el libro se actualiza correctamente.
        Integer a = 9;
        LibroEntity newEntity  = dataPrueba.get(4);
        String nuevoNombre = "Amor en los tiempos de jose";
        newEntity.setNombre(nuevoNombre);
        newEntity.setId(a.longValue());
        try{
            LibroEntity result =libroLogic.updateLibro(newEntity.getId(),newEntity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), nuevoNombre);
        }catch(Exception e)
        {
            Assert.fail();
        }
        
        //Caso 2 El libro no tiene la informacion completa.
        newEntity = dataPrueba.get(0);
        try{
            LibroEntity result = libroLogic.updateLibro(newEntity.getId(),newEntity);
        } catch(Exception e1)
        {
            //Si el mensaje de la excepcion es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El libro que se va a actualizar no tiene la informacion completa",e1.getMessage());
        }
        
        //Caso 3  El Libro tiene precio negativo.
        newEntity = dataPrueba.get(1);
        try{ 
            LibroEntity result = libroLogic.updateLibro(newEntity.getId(),newEntity);
        } catch(Exception e2)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El precio del libro es negativo",e2.getMessage());
        }
        
        //Caso4 El libro tiene el isbn incorrecto.
        newEntity = dataPrueba.get(2);
         try{ 
            LibroEntity result = libroLogic.updateLibro(newEntity.getId(),newEntity);
        } catch(Exception e3)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El codigo isbn del libro no es correcto",e3.getMessage());
        }
         
        //Caso5 El lubro tiene la fecha incorrecta.
        newEntity = dataPrueba.get(3);
         try{ 
            LibroEntity result = libroLogic.updateLibro(newEntity.getId(),newEntity);
        } catch(Exception e4)
        {
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("La fecha de publicacion es incorrecta",e4.getMessage());
        }
    }
    
    /**
     * Prueba para eliminar libro.
     */
    @Test
    public void deleteLibroTest() {
        LibroEntity entity = data.get(0);
        libroLogic.deleteLibro(entity.getId());
        LibroEntity deleted = em.find(LibroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
