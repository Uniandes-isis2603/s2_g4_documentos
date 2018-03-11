/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.FotocopiaLogic;
import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
import co.edu.uniandes.csw.documentos.persistence.FotocopiaPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ernesto Viana.
 */
@RunWith(Arquillian.class)
public class FotocopiaLogicTest {
    
    /**
     * Deployment
     * @return deployment
     */
    @Deployment
    public static JavaArchive createDeployment(){
         return ShrinkWrap.create(JavaArchive.class)
                 .addPackage(FotocopiaLogic.class.getPackage())
                 .addPackage(FotocopiaEntity.class.getPackage())
                 .addPackage(FotocopiaPersistence.class.getPackage())
                 .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                 .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase fotocopiaLogic
     * cuyos metodos se van a probar.
     */
    @Inject
    private FotocopiaLogic fotocopiaLogic;
    
    /**
     * inyeccion de la dependencia a la clase de fotocopiaPersistence
     * cuyos metodos van a ser de uso para probar la logica.
     */
    @Inject
    private FotocopiaPersistence fotocopiaPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la base de datos
     * por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando
     * se crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
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
    private void clearData() {
        em.createQuery("delete from FotocopiaEntity").executeUpdate();
    }
    
    /**
     * Lista de datos que se van a persistir.
     */
    private List<FotocopiaEntity> data = new ArrayList<>();
    
    /**
     * Lista de datos para las pruebas.
     */
    private List<FotocopiaEntity> dataPrueba = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for(Integer i = 0; i < 3; i++)
        {
            FotocopiaEntity entity = factory.manufacturePojo(FotocopiaEntity.class);
            entity.setId(i.longValue()+1);
            em.persist(entity);
            data.add(i,entity);
        }
        
        //Creando entities para prueba
        FotocopiaEntity entity = new FotocopiaEntity();
        FotocopiaEntity entity1 = new FotocopiaEntity();
        FotocopiaEntity entity2 = new FotocopiaEntity();
        FotocopiaEntity entity3 = new FotocopiaEntity();
        Integer a = 4;
        Integer b = 5;
        Integer c = 6;
        Integer d = 7;
        Integer e = 8;
        //Pobrandolos con cada caso
        //Entityt con datos incompletos.
        entity.setId(a.longValue());
        entity.setNombre(null);
        entity.setPrecio(3.2);
        entity.setCalificacionPromedio(null);
        entity.setCaratula("algo.nog");
        entity.setDescripcion("Fotocopia de prueba 1");
        entity.setNroPaginas(12);
        entity.setProfesor("Juan Carlos");
        entity.setCapitulo("del 5 al 25");
        
        //Entity1 con el precio negativo
        entity1.setId(b.longValue());
        entity1.setNombre("Calculo y algo mas");
        entity1.setPrecio(-3.2);
        entity1.setCalificacionPromedio(null);
        entity1.setCaratula("OtroAlgo.png");
        entity1.setDescripcion("Una materia mas que va a ser inutil por el resto de la vida");
        entity1.setNroPaginas(22);
        entity1.setProfesor("Alguien mas");
        entity1.setCapitulo("del 2 al 22");
        
        //Entity2 con el numero de paginas es menor o igual a cero.
        entity2.setId(c.longValue());
        entity2.setNombre("Fisica y demas");
        entity2.setPrecio(2.2);
        entity2.setCalificacionPromedio(null);
        entity2.setCaratula("Otra bobada");
        entity2.setDescripcion("Otra de esas que no sirve para nada, nisiquiera para los que la estudian");
        entity2.setNroPaginas(0);
        entity2.setProfesor("Los de siempre");
        entity2.setCapitulo("1,2,3,4,5 los del primer parcial :v");
        
        //Entity3 con los datos completos y correctos.
        entity3.setId(d.longValue());
        entity3.setNombre("Español texto tal");
        entity3.setPrecio(0.0);
        entity3.setCalificacionPromedio(null);
        entity3.setCaratula("Si.jpg");
        entity3.setNroPaginas(22);
        entity3.setDescripcion("Hasta esto sirve mas que las anteriores");
        entity3.setProfesor("no se");
        entity3.setCapitulo("todos");
        
        
        //los añandimos a la lista de prueba
        dataPrueba.add(0,entity);
        dataPrueba.add(1,entity1);
        dataPrueba.add(2,entity2);
        dataPrueba.add(3,entity3);
        
        entity3.setId(e.longValue());
        em.persist(entity3);
        data.add(entity3);
    }
    
    /**
     * Prueba para crear una fotocopia.
     */
    @Test
    public void createFotocopiaTest() {
        
        //caso1 la fotocopia se agrega correctamente.
        FotocopiaEntity newEntity = dataPrueba.get(3);
        try{
            Double calificacionCorrecta = -1.0;
            FotocopiaEntity result = fotocopiaLogic.createFotocopia(newEntity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), dataPrueba.get(3).getNombre());
            Assert.assertEquals(calificacionCorrecta, result.getCalificacionPromedio());
        } catch(Exception e)
        {
            Assert.fail();
        }
        
        //Caso 2 La fotocopia no tiene la informacion completa.
        newEntity = dataPrueba.get(0);
        try{
            FotocopiaEntity result = fotocopiaLogic.createFotocopia(newEntity);
        } catch(Exception e1) {
            
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("La fotocopia que se va a crear no tiene la informacion completa", e1.getMessage());
        }
        
        //Caso 3 La fotocopia tiene precio negativo.
        newEntity = dataPrueba.get(1);
        try{
            FotocopiaEntity result = fotocopiaLogic.createFotocopia(newEntity);
        }catch(Exception e2) {
            
            //Si el mensaje de la excepcion es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El precio de la fotocopia es negativo", e2.getMessage());
        }
        
        // caso 4 La fotocopia tiene un numero de paginas menor o igual a cero.
        newEntity = dataPrueba.get(2);
        try{
            FotocopiaEntity result = fotocopiaLogic.createFotocopia(newEntity);
        } catch(Exception e2) {
            
            //Si el mensaje de la exception es incorrecto tiene que fallar la prueba.
            Assert.assertEquals("El numero de paginas tiene que ser mayor a cero", e2.getMessage());
        }
        
        // Caso 5 El profesor es null, deberia devolver 'N/A'
        newEntity = dataPrueba.get(3);
        try{
            newEntity.setProfesor(null);
            FotocopiaEntity result = fotocopiaLogic.createFotocopia(newEntity);
            Assert.assertEquals("N/A", result.getProfesor());
        } catch(Exception e4){
            Assert.fail();
        }
    }
    
    /**
     * Prueba para consultar la lista de fotocopias.
     */
    @Test
    public void getFotocopiasTest() {
        List<FotocopiaEntity> list = fotocopiaLogic.getFotocopias();
        Assert.assertFalse(list.isEmpty());
        
        for(FotocopiaEntity ent: list){
            boolean found = false;
            for(FotocopiaEntity entity :data) {
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una fotocopia.
     */
    @Test
    public void getFotocopiaTest(){
        FotocopiaEntity entity = data.get(0);
        FotocopiaEntity newEntity = fotocopiaLogic.getFotocopia(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getProfesor(), newEntity.getProfesor());
    }
    
    /**
     * Prueba para consultar lista de fotocopias por profesor.
     */
    @Test
    public void getFotocopiaByProfesorTest() {
        FotocopiaEntity entity = data.get(0);
        List<FotocopiaEntity> newEntity = fotocopiaLogic.getFotocopiaByProfesor(entity.getProfesor());
        Assert.assertFalse(newEntity.isEmpty());
        for(FotocopiaEntity ent : newEntity) {
            boolean found = false;
            if(ent.getProfesor().equals(entity.getProfesor())){
                found =true;
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar una fotocopia.
     */
    @Test
    public void UpdateFotocopiaTest() {
        
        
        //Caso 1 La fotocopia se actualiza correctamente.
        String nuevoNombre = "No es calculo";
        FotocopiaEntity newEntity = dataPrueba.get(3);
        Integer a= 8;
        newEntity.setNombre(nuevoNombre);
        newEntity.setId(a.longValue());
        try{
            FotocopiaEntity result = fotocopiaLogic.updateFotocopia(newEntity.getId(), newEntity);
            Assert.assertNotNull(result);
            Assert.assertEquals(result.getNombre(), nuevoNombre);
        } catch(Exception e) {
            Assert.fail();
        }
        
        //Caso 2  La fotocopia no tiene la informacion completa.
        newEntity = dataPrueba.get(0);
        try {
            FotocopiaEntity result = fotocopiaLogic.updateFotocopia(newEntity.getId(), newEntity);
        } catch(Exception e1) {
            
            Assert.assertEquals("La fotocopia que se va a actualizar no tiene la informacion completa", e1.getMessage());
        }
        
        //Caso 3 la fotocopia tiene el precio negativo.
        newEntity = dataPrueba.get(1);
        try {
            FotocopiaEntity result = fotocopiaLogic.updateFotocopia(newEntity.getId(), newEntity);
            
        } catch (Exception e2) {
            Assert.assertEquals("El precio tiene que ser positivo", e2.getMessage());
        }
        
        //Caso 4 la fotocopia tiene un numero de paginas que no es positivo.
        newEntity = dataPrueba.get(2);
        try {
            FotocopiaEntity result = fotocopiaLogic.updateFotocopia(newEntity.getId(), newEntity);
        } catch(Exception e3) {
            Assert.assertEquals("El numero de paginas tiene que ser mayor a cero", e3.getMessage());
        }
        
        // Caso 5 El profesor es null, deberia devolver 'N/A'
        newEntity = dataPrueba.get(3);
        try{
            newEntity.setProfesor(null);
            FotocopiaEntity result = fotocopiaLogic.updateFotocopia(newEntity.getId(),newEntity);
            Assert.assertEquals("N/A", result.getProfesor());
        } catch(Exception e4){
            Assert.fail();
        }
    }
    
    /**
     * Prueba para eliminar fotocopia.
     */
    @Test
    public void deleteFotocopiaTest() {
        FotocopiaEntity entity = data.get(0);
        fotocopiaLogic.deleteFotocopia(entity.getId());
        FotocopiaEntity deleted = em.find(FotocopiaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    //-----------------------------------
    //Test de los metodos de validacion
    //-----------------------------------
    
    /**
     * Prueba que verifica que la completitud de la fotocopia funcione.
     */
    @Test
    public void completitudFotocopiaTest() {
        
        FotocopiaEntity entity = dataPrueba.get(0);
        Assert.assertFalse(fotocopiaLogic.validarCompletitud(entity));
        FotocopiaEntity entity3 = dataPrueba.get(3);
        Assert.assertTrue(fotocopiaLogic.validarCompletitud(entity3));
        
    }
    
}
