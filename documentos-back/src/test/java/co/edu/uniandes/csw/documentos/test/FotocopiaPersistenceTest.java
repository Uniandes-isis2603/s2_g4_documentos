/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

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
 * @author Ernesto Viana
 */
@RunWith(Arquillian.class)
public class FotocopiaPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(FotocopiaEntity.class.getPackage())
                .addPackage(FotocopiaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");           
    }
    
    /**
     * Inyeccion de la dependencia a la clase de fotocopiaPersistence cuyos metodos
     * se van a probar.
     */
    @Inject
    private FotocopiaPersistence fotocopiaPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base
     * de datos por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior
     * cuando se crean/borran datos para las pruebas.
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
        } catch (Exception e) {
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
        em.createQuery("delete from FotocopiaEntity").executeUpdate();
    }
    
    private List<FotocopiaEntity> data = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento
     * de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i< 3; i++) {
            FotocopiaEntity entity = factory.manufacturePojo(FotocopiaEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una fotocopia.
     */
    @Test
    public void createFotocopiaTest() {
        PodamFactory factory = new PodamFactoryImpl();
        FotocopiaEntity newEntity = factory.manufacturePojo(FotocopiaEntity.class);
        FotocopiaEntity result = fotocopiaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        FotocopiaEntity entity = em.find(FotocopiaEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para consultar la lista de fotocopias.
     */
    @Test
    public void getFotocopiasTest() {
        List<FotocopiaEntity> list = fotocopiaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(FotocopiaEntity ent : list) {
            boolean found = false;
            for(FotocopiaEntity entity : data) {
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
    public void getFotocopiaTest() {
        
        FotocopiaEntity entity = data.get(0);
        FotocopiaEntity newEntity = fotocopiaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCaratula(),newEntity.getCaratula());
    }
    
    /**
     * Prueba para consultar lista de fotocopias por el profesor.
     */
    @Test
    public void getFotocopiaByProfesorTest() {
        
        FotocopiaEntity entity = data.get(0);
        List<FotocopiaEntity> newEntity = fotocopiaPersistence.findByProfesor(entity.getProfesor());
        
        for(FotocopiaEntity ent : newEntity) {
            boolean found = false;
            if(ent.getProfesor().equals(entity.getProfesor())){
                found =true;
            }
            
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para actualizar fotocopia.
     */
    @Test
    public void UpdateFotocopiaTest(){
        FotocopiaEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        FotocopiaEntity newEntity = factory.manufacturePojo(FotocopiaEntity.class);
        
        newEntity.setId(entity.getId());
        
        fotocopiaPersistence.update(newEntity);
        
        FotocopiaEntity resp = em.find(FotocopiaEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
    }
    
    /**
     * Prueba para eliminar fotocopia.
     */
    @Test
    public void deleteFotocopiaTest() {
        FotocopiaEntity entity =data.get(0);
        fotocopiaPersistence.delete(entity.getId());
        FotocopiaEntity deleted = em.find(FotocopiaEntity.class,entity.getId());
        Assert.assertNull(deleted);
    }
}
