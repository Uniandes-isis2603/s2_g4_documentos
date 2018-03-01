/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.ImagenLogic;
import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
import co.edu.uniandes.csw.documentos.persistence.ImagenPersistence;
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
public class ImagenLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ImagenLogic imagenLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ImagenEntity> data = new ArrayList<ImagenEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ImagenEntity.class.getPackage())
                .addPackage(ImagenLogic.class.getPackage())
                .addPackage(ImagenPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void configTest() {
        try {
            utx.begin();
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
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from ImagenEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            ImagenEntity entity = factory.manufacturePojo(ImagenEntity.class);
            entity.setImg(entity.getImg() + ".jpg");
            em.persist(entity);
            data.add(entity);
         
        }

    }

        /**
     * Prueba para crear una imagen no valida
     *
     *
     */
    @Test
    public void createImagenTest1() {
        
        ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
        newEntity.setImg(newEntity.getImg() + ".png");
        ImagenEntity result = imagenLogic.createImagen(newEntity);
        Assert.assertNotNull(result);
        
        newEntity.setNombre("er$$2/34e");
        result = imagenLogic.createImagen(newEntity);
        Assert.assertNull(result);
        
        newEntity = factory.manufacturePojo(ImagenEntity.class);
        ImagenEntity existe = data.get(0);
        newEntity.setId(existe.getId());
        result = imagenLogic.createImagen(newEntity);
        Assert.assertNull(result);
    }
    
    /**
     * Prueba para crear una Imagen valida
     *
     *
     */
    @Test
    public void createImagenTest2() {
        
        ImagenEntity newEntity = factory.manufacturePojo(ImagenEntity.class);
        newEntity.setImg(newEntity.getImg() + ".jpg");
        ImagenEntity result = imagenLogic.createImagen(newEntity);
        Assert.assertNotNull(result);
        
        ImagenEntity entity = em.find(ImagenEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        Assert.assertEquals(newEntity.getImg(), entity.getImg());
    }

    /**
     * Prueba para consultar la lista de Imagenes
     *
     *
     */
    @Test
    public void getImagenesTest() {
        List<ImagenEntity> list = imagenLogic.getImagenes();
        Assert.assertEquals(data.size(), list.size());
        for (ImagenEntity entity : list) {
            boolean found = false;
            for (ImagenEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para consultar una Imagen no valida
     *
     *
     */
    @Test
    public void getImagenTest1() {
        
        Long id = new Long("11111");
        ImagenEntity resultEntity = imagenLogic.getImagen(id);
        Assert.assertNull(resultEntity);

        
    }
    
    /**
     * Prueba para consultar una Imagen valida
     *
     *
     */
    @Test
    public void getImagenTest2() {
        ImagenEntity entity = data.get(0);
        ImagenEntity resultEntity = imagenLogic.getImagen(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getImg(), resultEntity.getImg());
        
        
    }

    /**
     * Prueba para eliminar una Imagen valido
     *
     *
     */
    @Test
    public void deleteImagenTest() {
        ImagenEntity entity = data.get(0);
        imagenLogic.deleteImagen(entity.getId());
        ImagenEntity deleted = em.find(ImagenEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar una Imagen no valida
     *
     *
     */
    @Test
    public void updateImagenTest1() {

        ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);

        Long id = new Long("111111");
        pojoEntity.setId(id);

        imagenLogic.updateImagen(pojoEntity);
        ImagenEntity encontrar = imagenLogic.getImagen(pojoEntity.getId());
        Assert.assertNull(encontrar);
        
        pojoEntity.setNombre("rt4#$%#fdsgds");
        imagenLogic.updateImagen(pojoEntity);
        encontrar = imagenLogic.getImagen(pojoEntity.getId());
        Assert.assertNull(encontrar);
        
    }
    

    /**
     * Prueba para actualizar una Imagen valida
     *
     *
     */
    @Test
    public void updateImagenTest2() {
        ImagenEntity entity = data.get(0);
        ImagenEntity pojoEntity = factory.manufacturePojo(ImagenEntity.class);

        pojoEntity.setId(entity.getId());
        pojoEntity.setImg(pojoEntity.getImg()+".jpg");

        imagenLogic.updateImagen(pojoEntity);

        ImagenEntity resp = em.find(ImagenEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getImg(), resp.getImg());
    }
   
}
