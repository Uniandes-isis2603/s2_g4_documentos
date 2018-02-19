/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import co.edu.uniandes.csw.documentos.persistence.ImagenPersistence;
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
public class ImagenPersistanceTest {
        
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ImagenEntity.class.getPackage())
                .addPackage(ImagenPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private ImagenPersistence imagenesPersistence;
      
     @PersistenceContext
    private EntityManager em;
      
     @Inject
    UserTransaction utx;
     
    private List<ImagenEntity> data = new ArrayList<ImagenEntity>();
    
    /**
     * Constructor por defecto
     */
    public ImagenPersistanceTest(){
        
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
            ImagenEntity entity = factory.manufacturePojo(ImagenEntity.class);

            em.persist(entity);
            data.add(entity);
        }
        
    }

    private void clearData() {
        em.createQuery("delete from ImagenEntity").executeUpdate();
    }
    
    /**
     * Prueba para crear un nuevo autor
     */
    @Test
    public void createAutorTest(){
        PodamFactory factory = new PodamFactoryImpl();
        ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
        ImagenEntity result = imagenesPersistence.create(newEntity);
        
        Assert.assertNotNull(result);

        ImagenEntity entity = em.find(ImagenEntity.class, result.getId());

        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getImg(), entity.getImg());
    }
    
    /**
     * Prueba para encontrar una imagen
     */
    @Test
    public void getAutorTest(){
        ImagenEntity entity = data.get(0);
        ImagenEntity newEntity = imagenesPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getImg(), entity.getImg());
    }
    
    /**
     * Prueba para encontrar todas las imagenes
     */
    @Test
    public void getAutoresTest(){
        List<ImagenEntity> list = imagenesPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (ImagenEntity ent : list) {
            boolean encontro = false;
            for (ImagenEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    encontro = true;
                }
            }
            Assert.assertTrue(encontro);
        }
    }
    
    /**
     * Prueba para verificar si se actualizo una imagen
     */
    @Test
    public void updateAreaDeConocimientoTest(){
        ImagenEntity entity = data.get(0);
        entity.setNombre("Yo");
        entity.setImg("/ruta/imagen");
        imagenesPersistence.update(entity);
        
        Assert.assertNotNull(entity);
        Assert.assertEquals("Portada", entity.getNombre());
        Assert.assertEquals("/ruta/imagen", entity.getImg());
    }
    
    /**
     * Prueba para borrar una Imagen
     */
    @Test
    public void deleteAreaDeConocimientoTest() {
        ImagenEntity entity = data.get(0);
        imagenesPersistence.delete(entity.getId());
        ImagenEntity borrado = em.find(ImagenEntity.class, entity.getId());
        Assert.assertNull(borrado);
    }
}
