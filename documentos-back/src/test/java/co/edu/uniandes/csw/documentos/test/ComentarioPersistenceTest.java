/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.persistence.ComentarioPersistence;
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
public class ComentarioPersistenceTest {
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ComentarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private ComentarioPersistence comentarioPersistence;
      
     @PersistenceContext
    private EntityManager entidad;
      
     @Inject
    UserTransaction utx;
     
     private List<ComentarioEntity> data = new ArrayList<ComentarioEntity>();
     
     
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }
        
        }
        
        private void clearData() 
          {
        entidad.createQuery("delete from ComentarioEntity").executeUpdate();
    }
    public ComentarioPersistenceTest() {
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
  public void createComentarioTest()
  {
      PodamFactory sujeto=new PodamFactoryImpl();
      ComentarioEntity entidadDePrueba= sujeto.manufacturePojo(ComentarioEntity.class);
      ComentarioEntity compraDePrueba= comentarioPersistence.create(entidadDePrueba);
      
      Assert.assertNotNull(compraDePrueba);
      ComentarioEntity laEntidad=entidad.find(ComentarioEntity.class, compraDePrueba.getId());
       Assert.assertEquals(entidadDePrueba.getId(), laEntidad.getId());
         
  }
  
    
   @Test
   public void getComentarioTest()
   {
       ComentarioEntity prueba= data.get(0);
       ComentarioEntity marcoDeComparacion =comentarioPersistence.find(prueba.getId());
        Assert.assertNotNull(marcoDeComparacion);
        Assert.assertEquals(marcoDeComparacion.getId(),prueba.getId());
 
   }
   @Test 
   public void getTodasLasComentarios()
   {
       List<ComentarioEntity> listaDeComentarios= comentarioPersistence.findAll();
       Assert.assertEquals(data.size(), listaDeComentarios.size());
        
        for (ComentarioEntity entidad : listaDeComentarios) {
            boolean found = false;
            for (ComentarioEntity entidad2 : data) {
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
        ComentarioEntity entity = data.get(0);
        comentarioPersistence.delete(entity);
        ComentarioEntity deleted = comentarioPersistence.find(entity.getId());
        Assert.assertEquals(deleted,null);
    }
            
    @Test
    public void actualizar()
    {
        
           ComentarioEntity LaEntidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        ComentarioEntity nuevaEntidad = factory.manufacturePojo(ComentarioEntity.class);
        //Cambio un dato de la nuevaentidad para subirlo a la tabla como la entidad
         nuevaEntidad.setId(LaEntidad.getId());
          comentarioPersistence.update(nuevaEntidad);
          
           ComentarioEntity resp = entidad.find(ComentarioEntity.class, LaEntidad.getId());

        Assert.assertEquals(nuevaEntidad.getId(), resp.getId());
        
    }
    
    
    @After
    public void tearDown() {
    }

   
}
