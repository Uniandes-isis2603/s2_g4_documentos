/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.persistence.LibroPersistence;
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
 * @author Ernesto Viana
 */
@RunWith(Arquillian.class)
public class LibroPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
               .addPackage(LibroPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase de libroPersistence cuyos metodos
     * se van a probar.
     */
    @Inject
    private LibroPersistence libroPersistence;
    
    /**
     * Contexto de persitencia que se va a utilizar para acceder a la Base de datos
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
            utx.commit();
        } catch (Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from LibroEntity").executeUpdate();
    }
    
    private List<LibroEntity> data = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un libro.
     */
    @Test
    public void createLibroTest() {
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        LibroEntity result = libroPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        LibroEntity entity = em.find(LibroEntity.class,result.getId());
        
        Assert.assertEquals(newEntity.getNombre(),entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de Libros.
     */
    @Test
    public void getLibrosTest() {
        
        List<LibroEntity> list = libroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(LibroEntity ent : list) {
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
     * Prueba para consultar un documento.
     */
    @Test
    public void getLibroTest(){
        
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());
    }
    
    /**
     * Prueba para consultar lista de libros por nombre.
     */
    @Test
    public void getLibroByNameTest(){
        
        LibroEntity entity = data.get(0);
        List<LibroEntity> newEntity = libroPersistence.findByName(entity.getNombre());
        
        for(LibroEntity ent : newEntity) {
            boolean found = false;
            if(ent.getNombre().equals(entity.getNombre())){
                found = true;
            }
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar libro.
     */
    @Test
    public void UpdateLibroTest() {
        LibroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        
        newEntity.setId(entity.getId());
        
        libroPersistence.update(newEntity);
        
        LibroEntity resp = em.find(LibroEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
    }
    
    /**
     * Prueba para eliminar libro.
     */
    @Test
    public void deleteLibroTest() {
        LibroEntity entity = data.get(0);
        libroPersistence.delete(entity.getId());
        LibroEntity deleted = em.find(LibroEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
}
