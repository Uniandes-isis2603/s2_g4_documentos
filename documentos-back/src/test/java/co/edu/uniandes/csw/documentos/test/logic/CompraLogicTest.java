/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.CompraLogic;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@RunWith(Arquillian.class)
public class CompraLogicTest {
    
  private PodamFactory factory = new PodamFactoryImpl();

      @Inject
    private CompraLogic cursoLogic;

    
    @PersistenceContext
    private EntityManager em;

 
    @Inject
    private UserTransaction utx;
    private List<CompraEntity> datos = new ArrayList<>();

    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraLogic.class.getPackage())
                .addPackage(CursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     private void clearData() {
        em.createQuery("delete from CompraEntity").executeUpdate();
        
    }
     
     private void insertData() {
         
        for (int i = 0; i < 3; i++) {
            CompraEntity curso = factory.manufacturePojo(CompraEntity.class);
         
            curso.setFecha(fechaaux.getTime());
            
            em.persist(curso);
            datos.add(curso);
        }
     }
    
   
    private Calendar fechaaux;
    
    
    @Before
    public void configTest() {
              fechaaux = Calendar.getInstance();
              fechaaux.set(2010, 3, 16);
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
    public void createCompraTest() throws BusinessLogicException {
        
        try{
            
           
            CompraEntity newEntity = factory.manufacturePojo(CompraEntity.class);
          
            newEntity.setFecha(fechaaux.getTime());
          
             CompraEntity resultado = cursoLogic.createCompra(newEntity);
            
             Assert.assertNotNull(resultado);
             
             CompraEntity entity = em.find(CompraEntity.class, resultado.getId());
             
            Assert.assertEquals(newEntity.getCosto(), entity.getCosto());
            Assert.assertEquals(newEntity.getFecha(), entity.getFecha());
            Assert.assertEquals(newEntity.getTipoDeCompra(), entity.getTipoDeCompra());
            Assert.assertEquals(newEntity.getId(), entity.getId());
        }
        catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void getCompraesTest() {
         try{
        List<CompraEntity> list = cursoLogic.getCompras();
        Assert.assertEquals(datos.size(), list.size());
        for (CompraEntity entity : list) {
            boolean found = false;
            for (CompraEntity storedEntity : datos) {
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
    public void getCompraTest() 
    {
        
        try{
            CompraEntity entity = datos.get(0);
            CompraEntity resultEntity = cursoLogic.getCompra(entity.getId());
            
            System.out.println("Resultado:"+resultEntity.getFecha()+"entity"+entity.getFecha());
            
            Assert.assertNotNull(resultEntity);
           Assert.assertEquals(resultEntity.getId(), entity.getId());
           Assert.assertEquals(resultEntity.getId(), entity.getId());   
         Assert.assertEquals(resultEntity.getCosto(), entity.getCosto());
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());
        Assert.assertEquals(resultEntity.getTipoDeCompra(), entity.getTipoDeCompra());
        
        }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
     @Test
    public void deleteCompraTest() {
        try{
        CompraEntity entity = datos.get(0);
        cursoLogic.deleteCompra(entity.getId());
        CompraEntity deleted = em.find(CompraEntity.class, entity.getId());
        Assert.assertNull(deleted);
         }
         catch(BusinessLogicException e)
        {
            Assert.assertNotNull(e);
        } 
    }
    
    
     @Test
    public void updateComprasTest() throws BusinessLogicException {
       
        CompraEntity entity = datos.get(0);
        
        CompraEntity pojoEntity = factory.manufacturePojo(CompraEntity.class);
        
       
        
         
        pojoEntity.setId(entity.getId());
        
        cursoLogic.updateCompra(pojoEntity.getId(), pojoEntity);
         
        CompraEntity resp = em.find(CompraEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getCosto(), resp.getCosto());
        Assert.assertEquals(pojoEntity.getFecha(), resp.getFecha());
        Assert.assertEquals(pojoEntity.getTipoDeCompra(), resp.getTipoDeCompra());
        
        
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
