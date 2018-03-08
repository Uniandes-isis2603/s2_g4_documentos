/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.ComentarioLogic;
import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
import java.util.ArrayList;
import java.util.Calendar;
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
@RunWith(Arquillian.class )
public class ComentarioLogicTest {
    
   private PodamFactory factory = new PodamFactoryImpl();

      @Inject
    private ComentarioLogic cursoLogic;

    
    @PersistenceContext
    private EntityManager em;

 
    @Inject
    private UserTransaction utx;
    private List<ComentarioEntity> datos = new ArrayList<>();

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioLogic.class.getPackage())
                .addPackage(CursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     private void clearData() {
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        
    }
     
     private void insertData() {
         Date fecha_aux= new Date();
        fecha_aux.setHours(fecha_aux.getHours()-1);
        for (int i = 0; i < 3; i++) {
            ComentarioEntity curso = factory.manufacturePojo(ComentarioEntity.class);
            curso.setFecha(fecha_aux);
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
    
  @Test
    public void createComentarioTest() throws BusinessLogicException {
        Date fecha_aux= new Date();
        fecha_aux.setHours(fecha_aux.getHours()-1);
        try{
            ComentarioEntity newEntity = factory.manufacturePojo(ComentarioEntity.class);
             newEntity.setFecha(fecha_aux);
             ComentarioEntity resultado = cursoLogic.createComentario(newEntity);
             Assert.assertNotNull(resultado);
             ComentarioEntity entity = em.find(ComentarioEntity.class, resultado.getId());
        
        
        Assert.assertEquals(newEntity.getComentario(), entity.getComentario());
        Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        }
        catch(BusinessLogicException e)
        {
            e.printStackTrace();
        } 
    }
     @Test
    public void getComentarioesTest() {
         try{
        List<ComentarioEntity> list = cursoLogic.getComentarios();
        Assert.assertEquals(datos.size(), list.size());
        for (ComentarioEntity entity : list) {
            boolean found = false;
            for (ComentarioEntity storedEntity : datos) {
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
    public void getComentarioTest() 
    {
        
        try{
            ComentarioEntity entity = datos.get(0);
            ComentarioEntity resultEntity = cursoLogic.getComentario(entity.getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        
           Assert.assertEquals(resultEntity.getId(), entity.getId());
        
        
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());
       Assert.assertEquals(resultEntity.getComentario(), entity.getComentario());
        
        Assert.assertEquals(resultEntity.getId(), entity.getId());
        
        }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void deleteComentarioTest() {
        try{
        ComentarioEntity entity = datos.get(0);
        cursoLogic.deleteComentario(entity.getId());
        ComentarioEntity deleted = em.find(ComentarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
         }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void updateComentarioesTest() throws BusinessLogicException {
         Date fecha_aux= new Date();
        fecha_aux.setHours(fecha_aux.getHours()-1);
        ComentarioEntity entity = datos.get(0);
        ComentarioEntity pojoEntity = factory.manufacturePojo(ComentarioEntity.class);
        
pojoEntity.setFecha(fecha_aux);
        pojoEntity.setId(entity.getId());

        cursoLogic.updateComentario(pojoEntity.getId(), pojoEntity);

        ComentarioEntity resp = em.find(ComentarioEntity.class, entity.getId());

      
          
         
       
         Assert.assertEquals(pojoEntity.getComentario(), resp.getComentario());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        
        
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
