package co.edu.uniandes.csw.documentos.test;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.persistence.CompraPersistence;
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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.junit.Assert;
import org.junit.runner.RunWith;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author n.sotelo
 */

@RunWith(Arquillian.class)
public class CompraPersitenceTest {
      @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CompraEntity.class.getPackage())
                .addPackage(CompraPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private CompraPersistence compraPersistence;
      
     @PersistenceContext
    private EntityManager entidad;
      
     @Inject
    UserTransaction utx;
     
     private List<CompraEntity> data = new ArrayList<CompraEntity>();
     
     
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CompraEntity entity = factory.manufacturePojo(CompraEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }
        
        }
        
        private void clearData() 
          {
        entidad.createQuery("delete from CompraEntity").executeUpdate();
    }
    
     
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        try
        {  utx.begin();
            entidad.joinTransaction();
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
  public void createCompraTest()
  {
      PodamFactory sujeto=new PodamFactoryImpl();
      CompraEntity entidadDePrueba= sujeto.manufacturePojo(CompraEntity.class);
      CompraEntity compraDePrueba= compraPersistence.create(entidadDePrueba);
      
      Assert.assertNotNull(compraDePrueba);
      CompraEntity laEntidad=entidad.find(CompraEntity.class, compraDePrueba.getId());
       Assert.assertEquals(entidadDePrueba.getId(), laEntidad.getId());
        Assert.assertEquals(entidadDePrueba.getCosto(), laEntidad.getCosto());
         Assert.assertEquals(entidadDePrueba.getFecha(), laEntidad.getFecha());
          Assert.assertEquals(entidadDePrueba.getTipoDeCompra(), laEntidad.getTipoDeCompra());
  }
  
    
   @Test
   public void getCompraTest()
   {
       CompraEntity prueba= data.get(0);
       CompraEntity marcoDeComparacion =compraPersistence.find(prueba.getId());
        Assert.assertNotNull(marcoDeComparacion);
        Assert.assertEquals(marcoDeComparacion.getId(),prueba.getId());
        Assert.assertEquals(marcoDeComparacion.getFecha(),prueba.getFecha());
        Assert.assertEquals(marcoDeComparacion.getCosto(),prueba.getCosto());
        Assert.assertEquals(marcoDeComparacion.getTipoDeCompra(),prueba.getTipoDeCompra());
        
        
   }
   @Test 
   public void getTodasLasCompras()
   {
       List<CompraEntity> listaDeCompras= compraPersistence.findAll();
       Assert.assertEquals(data.size(), listaDeCompras.size());
        
        for (CompraEntity entidad : listaDeCompras) {
            boolean found = false;
            for (CompraEntity entidad2 : data) {
                if (entidad.getId().equals(entidad2.getId())) 
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
   }

    @Test 
    public void borrarEntidad()
    {
        //Acabo cooregir
        CompraEntity entity = data.get(0);
        compraPersistence.delete(entity);
        CompraEntity deleted = compraPersistence.find(entity.getId());
        Assert.assertEquals(deleted,null);
    }
            
    @Test
    public void actualizar()
    {
        
           CompraEntity LaEntidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CompraEntity nuevaEntidad = factory.manufacturePojo(CompraEntity.class);
        //Cambio un dato de la nuevaentidad para subirlo a la tabla como la entidad
         nuevaEntidad.setId(LaEntidad.getId());
          compraPersistence.update(nuevaEntidad);
          
           CompraEntity resp = entidad.find(CompraEntity.class, LaEntidad.getId());

        Assert.assertEquals(nuevaEntidad.getId(), resp.getId());
        Assert.assertEquals(nuevaEntidad.getCosto(), resp.getCosto());
        Assert.assertEquals(nuevaEntidad.getFecha(), resp.getFecha());
        Assert.assertEquals(nuevaEntidad.getTipoDeCompra(), resp.getTipoDeCompra());
    }
    @After
    public void tearDown() 
    {
        
    }

}
    

