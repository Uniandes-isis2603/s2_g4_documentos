/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.AutorLogic;
import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
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
public class AutorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AutorLogic autorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AutorEntity> data = new ArrayList<AutorEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutorEntity.class.getPackage())
                .addPackage(AutorLogic.class.getPackage())
                .addPackage(AutorPersistence.class.getPackage())
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
        em.createQuery("delete from AutorEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            AutorEntity entity = factory.manufacturePojo(AutorEntity.class);
            em.persist(entity);
            data.add(entity);
         
        }

    }

        /**
     * Prueba para crear un Autor no valido
     *
     *
     */
    @Test
    public void createAutorTest1() {
        
        AutorEntity newEntity = factory.manufacturePojo(AutorEntity.class);
        AutorEntity result = autorLogic.createAutor(newEntity);
        Assert.assertNotNull(result);
        
        newEntity.setNombre("er$$2/34e");
        result = autorLogic.createAutor(newEntity);
        Assert.assertNull(result);
        
        newEntity = factory.manufacturePojo(AutorEntity.class);
        AutorEntity existe = data.get(0);
        newEntity.setId(existe.getId());
        result = autorLogic.createAutor(newEntity);
        Assert.assertNull(result);
    }
    
    /**
     * Prueba para crear un Autor valido
     *
     *
     */
    @Test
    public void createAutorTest2() {
        
        AutorEntity newEntity = factory.manufacturePojo(AutorEntity.class);
        AutorEntity result = autorLogic.createAutor(newEntity);
        Assert.assertNotNull(result);
        
        AutorEntity entity = em.find(AutorEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        
    }

    /**
     * Prueba para consultar la lista de Autores
     *
     *
     */
    @Test
    public void getAutoresTest() {
        List<AutorEntity> list = autorLogic.getAutores();
        Assert.assertEquals(data.size(), list.size());
        for (AutorEntity entity : list) {
            boolean found = false;
            for (AutorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para consultar un Autor no valido
     *
     *
     */
    @Test
    public void getAutorTest1() {
        
        Long id = new Long("11111");
        AutorEntity resultEntity = autorLogic.getAutor(id);
        Assert.assertNull(resultEntity);

        
    }
    
    /**
     * Prueba para consultar un Autor valido
     *
     *
     */
    @Test
    public void getAutorTest2() {
        AutorEntity entity = data.get(0);
        AutorEntity resultEntity = autorLogic.getAutor(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        
    }

    /**
     * Prueba para eliminar un Autor valido
     *
     *
     */
    @Test
    public void deleteAutorTest() {
        AutorEntity entity = data.get(0);
        autorLogic.deleteAutor(entity.getId());
        AutorEntity deleted = em.find(AutorEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
     /**
     * Prueba para actualizar un Autor no valido
     *
     *
     */
    @Test
    public void updateAutorTest1() {

        AutorEntity pojoEntity = factory.manufacturePojo(AutorEntity.class);

       Long id = new Long("11111");
        pojoEntity.setId(id);

        autorLogic.updateAutor(pojoEntity);
        AutorEntity encontrar = autorLogic.getAutor(pojoEntity.getId());
        Assert.assertNull(encontrar);
        
        pojoEntity.setNombre("rt4#$%#fdsgds");
        autorLogic.updateAutor(pojoEntity);
        encontrar = autorLogic.getAutor(pojoEntity.getId());
        Assert.assertNull(encontrar);
        
    }
    

    /**
     * Prueba para actualizar un Autor valido
     *
     *
     */
    @Test
    public void updateAutorTest2() {
        AutorEntity entity = data.get(0);
        AutorEntity pojoEntity = factory.manufacturePojo(AutorEntity.class);

        pojoEntity.setId(entity.getId());

        autorLogic.updateAutor(pojoEntity);

        AutorEntity resp = em.find(AutorEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
    }
    
}
