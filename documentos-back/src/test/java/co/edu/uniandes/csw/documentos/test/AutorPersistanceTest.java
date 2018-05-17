/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
import java.util.ArrayList;
import java.util.Date;
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
 * @author Camilojaravila
 */
@RunWith(Arquillian.class)
public class AutorPersistanceTest {
        
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutorEntity.class.getPackage())
                .addPackage(AutorPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private AutorPersistence autoresPersistence;
      
     @PersistenceContext
    private EntityManager em;
      
     @Inject
    UserTransaction utx;
     
    private List<AutorEntity> data = new ArrayList<AutorEntity>();
    
    /**
     * Constructor por defecto
     */
    public AutorPersistanceTest(){
        
    }
    
     /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Inserta los datos para la prueba
     */
    private void insertData(){
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            AutorEntity entity = factory.manufacturePojo(AutorEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        
    }

    private void clearData() {
        em.createQuery("delete from AutorEntity").executeUpdate();
    }
    
    /**
     * Prueba para crear un nuevo autor
     */
    @Test
    public void createAutorTest(){
        PodamFactory factory = new PodamFactoryImpl();
        AutorEntity newEntity = factory.manufacturePojo(AutorEntity.class);
        AutorEntity result = autoresPersistence.create(newEntity);
        
        Assert.assertNotNull(result);

        AutorEntity entity = em.find(AutorEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para encontrar un autor
     */
    @Test
    public void getAutorTest(){
        AutorEntity entity = data.get(0);
        AutorEntity newEntity = autoresPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
    
    /**
     * Prueba para encontrar todos los autores
     */
    @Test
    public void getAutoresTest(){
        List<AutorEntity> list = autoresPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AutorEntity ent : list) {
            boolean encontro = false;
            for (AutorEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
    /**
     * Prueba para verificar si se actualizo un autor
     */
    @Test
    public void updateAreaDeConocimientoTest(){
        AutorEntity entity = data.get(0);
        entity.setNombre("Yo");
        autoresPersistence.update(entity);
        
        Assert.assertNotNull(entity);
        Assert.assertEquals("Yo", entity.getNombre());
    }
    
    /**
     * Prueba para borrar un autor
     */
    @Test
    public void deleteAreaDeConocimientoTest() {
        AutorEntity entity = data.get(0);
        autoresPersistence.delete(entity.getId());
        AutorEntity borrado = em.find(AutorEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
    
        
    /**
     * Prueba para encontrar un autor por nombre
     */
    @Test
    public void findAutorByNombreTest(){
        AutorEntity entity = data.get(0);
        
        AutorEntity newEntity = autoresPersistence.findByNombre(entity.getNombre()).get(0);
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
    }
}
