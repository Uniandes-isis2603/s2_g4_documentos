/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.ejb.CursoLogic;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
import com.sun.webkit.CursorManager;
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
public class CursoLogicTest {
     private PodamFactory factory = new PodamFactoryImpl();

      @Inject
    private CursoLogic cursoLogic;

    
    @PersistenceContext
    private EntityManager em;

 
    @Inject
    private UserTransaction utx;
    private List<CursoEntity> datos = new ArrayList<>();

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CursoEntity.class.getPackage())
                .addPackage(CursoLogic.class.getPackage())
                .addPackage(CursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     private void clearData() {
        em.createQuery("delete from CursoEntity").executeUpdate();
        
    }
     
     private void insertData() {
        for (int i = 0; i < 3; i++) {
            CursoEntity curso = factory.manufacturePojo(CursoEntity.class);
            em.persist(curso);
            datos.add(curso);
        }
     }
    public CursoLogicTest()
    {
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
    
  @Test
    public void createCursoTest() throws BusinessLogicException {
        CursoEntity newEntity = factory.manufacturePojo(CursoEntity.class);
        try{
             CursoEntity resultado = cursoLogic.createCurso(newEntity);
             Assert.assertNotNull(resultado);
             CursoEntity entity = em.find(CursoEntity.class, resultado.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCodigo(), entity.getCodigo());
        Assert.assertEquals(newEntity.getDepartamento(), entity.getDepartamento());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void getCursosTest() {
         try{
        List<CursoEntity> list = cursoLogic.getCursos();
        Assert.assertEquals(datos.size(), list.size());
        for (CursoEntity entity : list) {
            boolean found = false;
            for (CursoEntity storedEntity : datos) {
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
    
      @Test
    public void getCursoTest() 
    {
        
        try{
            CursoEntity entity = datos.get(0);
            CursoEntity resultEntity = cursoLogic.getCurso(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        
          Assert.assertEquals(entity.getCodigo(), resultEntity.getCodigo());
        Assert.assertEquals(entity.getDepartamento(), resultEntity.getDepartamento());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        
        }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void deleteCursoTest() {
        try{
        CursoEntity entity = datos.get(0);
        cursoLogic.deleteCurso(entity.getId());
        CursoEntity deleted = em.find(CursoEntity.class, entity.getId());
        Assert.assertNull(deleted);
         }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void updateCursoTest() throws BusinessLogicException {
        CursoEntity entity = datos.get(0);
        CursoEntity pojoEntity = factory.manufacturePojo(CursoEntity.class);

        pojoEntity.setId(entity.getId());

        cursoLogic.updateCurso(pojoEntity.getId(), pojoEntity);

        CursoEntity resp = em.find(CursoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
         Assert.assertEquals(pojoEntity.getCodigo(), resp.getCodigo());
        Assert.assertEquals(pojoEntity.getDepartamento(), resp.getDepartamento());
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

