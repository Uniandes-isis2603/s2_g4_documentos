/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.documentos.persistence.AreaDeConocimientoPersistence;
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
 * @author Camilojaravila
 */
@RunWith(Arquillian.class)
public class AreaDeConocimientoPersistanceTest {
    
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AreaDeConocimientoEntity.class.getPackage())
                .addPackage(AreaDeConocimientoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private AreaDeConocimientoPersistence areaPersistence;
      
     @PersistenceContext
    private EntityManager em;
      
     @Inject
    UserTransaction utx;
     
    private List<AreaDeConocimientoEntity> data = new ArrayList<AreaDeConocimientoEntity>();
    
    /**
     * Constructor por defecto
     */
    public AreaDeConocimientoPersistanceTest(){
        
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
            AreaDeConocimientoEntity entity = factory.manufacturePojo(AreaDeConocimientoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        
    }

    private void clearData() {
        em.createQuery("delete from AreaDeConocimientoEntity").executeUpdate();
    }
    
    /**
     * Prueba para crear una nueva Area de Conocimiento
     */
    @Test
    public void createAreaDeConocimientoTest(){
        PodamFactory factory = new PodamFactoryImpl();
        AreaDeConocimientoEntity newEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        AreaDeConocimientoEntity result = areaPersistence.create(newEntity);
        
        Assert.assertNotNull(result);

        AreaDeConocimientoEntity entity = em.find(AreaDeConocimientoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
    /**
     * Prueba para encontrar una area de conocimiento
     */
    @Test
    public void getAreaDeConocimientoTest(){
        AreaDeConocimientoEntity entity = data.get(0);
        AreaDeConocimientoEntity newEntity = areaPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
    
    /**
     * Prueba para encontrar todas las areas de conocimiento
     */
    @Test
    public void getAreasDeConocimientoTest(){
        List<AreaDeConocimientoEntity> list = areaPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (AreaDeConocimientoEntity ent : list) {
            boolean encontro = false;
            for (AreaDeConocimientoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
    /**
     * Prueba para verificar si se actualizo un area de conocimiento
     */
    @Test
    public void updateAreaDeConocimientoTest(){
        AreaDeConocimientoEntity entity = data.get(0);
        entity.setTipo("Calculo");
        areaPersistence.update(entity);
        
        Assert.assertNotNull(entity);
        Assert.assertEquals("Calculo", entity.getTipo());
    }
    
    /**
     * Prueba para borrar un area de conocimiento
     */
    @Test
    public void deleteAreaDeConocimientoTest() {
        AreaDeConocimientoEntity entity = data.get(0);
        areaPersistence.delete(entity.getId());
        AreaDeConocimientoEntity borrado = em.find(AreaDeConocimientoEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
    
    /**
     * Prueba para encontrar una area por tipo
     */
    @Test
    public void findAreaDeConocimientoByTipoTest(){
        AreaDeConocimientoEntity entity = data.get(0);
        
        AreaDeConocimientoEntity newEntity = areaPersistence.findByTipo(entity.getTipo()).get(0);
        
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getTipo(), entity.getTipo());
    }
}
