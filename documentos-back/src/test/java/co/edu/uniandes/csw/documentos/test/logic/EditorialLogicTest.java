/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.EditorialLogic;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.sotelo
 */
@RunWith(Arquillian.class)
public class EditorialLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private EditorialLogic cursoLogic;
    
    
    @PersistenceContext
    private EntityManager em;
    
    
    @Inject
    private UserTransaction utx;
    private List<EditorialEntity> datos = new ArrayList<>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EditorialEntity.class.getPackage())
                .addPackage(EditorialLogic.class.getPackage())
                .addPackage(CursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    private void clearData() {
        em.createQuery("delete from EditorialEntity").executeUpdate();
        
    }
    
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            EditorialEntity curso = factory.manufacturePojo(EditorialEntity.class);
            em.persist(curso);
            datos.add(curso);
        }
    }
    
    
    
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
     * Test para crear una editorial
     * @throws BusinessLogicException si no se cumple con las reglas de negocio
     */
    @Test
    public void createEditorialTest() throws BusinessLogicException {
        
        try{
            EditorialEntity newEntity = factory.manufacturePojo(EditorialEntity.class);
            EditorialEntity resultado = cursoLogic.createEditorial(newEntity);
            Assert.assertNotNull(resultado);
            EditorialEntity entity = em.find(EditorialEntity.class, resultado.getId());
            
            
            Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        }
    }
    /**
     * Test para obtener todas las editoriales y validar que sea correcta
     */
    @Test
    public void getEditorialesTest() {
        try{
            List<EditorialEntity> list = cursoLogic.getEditorials();
            Assert.assertEquals(datos.size(), list.size());
            for (EditorialEntity entity : list) {
                boolean found = false;
                for (EditorialEntity storedEntity : datos) {
                    if (entity.getId().equals(storedEntity.getId())) {
                        found = true;
                    }
                }
                Assert.assertTrue(found);
            }
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        }
    }
    /**
     * Test para obtener una editorial
     */
    @Test
    public void getEditorialTest()
    {
        
        try{
            EditorialEntity entity = datos.get(0);
            EditorialEntity resultEntity = cursoLogic.getEditorial(entity.getId());
            Assert.assertNotNull(resultEntity);
            Assert.assertEquals(entity.getId(), resultEntity.getId());
            
            Assert.assertEquals(resultEntity.getId(), entity.getId());
            
            Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());
            
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        }
    }
    /**
     * Test para borrar de manera correcta una entidad de tipo editorial
     */
    @Test
    public void deleteEditorialTest() {
        try{
            EditorialEntity entity = datos.get(0);
            cursoLogic.deleteEditorial(entity.getId());
            EditorialEntity deleted = em.find(EditorialEntity.class, entity.getId());
            Assert.assertNull(deleted);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        }
    }
    /**
     *  Test para actualizar la informacion de una enitdad.
     * @throws BusinessLogicException
     */
    @Test
    public void updateEditorialesTest() throws BusinessLogicException {
        EditorialEntity entity = datos.get(0);
        EditorialEntity pojoEntity = factory.manufacturePojo(EditorialEntity.class);
        
        pojoEntity.setId(entity.getId());
        
        cursoLogic.updateEditorial(pojoEntity.getId(), pojoEntity);
        
        EditorialEntity resp = em.find(EditorialEntity.class, entity.getId());
        
        
        
        
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        
    }
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    
}