/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.persistence.EditorialPersistence;
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
public class EditorialPersistenceTest {
       @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(EditorialEntity.class.getPackage())
                .addPackage(EditorialPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    @Inject
    private EditorialPersistence editorialPersistence;
      
     @PersistenceContext
    private EntityManager entidad;
      
     @Inject
    UserTransaction utx;
     
     private List<EditorialEntity> data = new ArrayList<EditorialEntity>();
     
     
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            EditorialEntity entity = factory.manufacturePojo(EditorialEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }
        
        }
        
        private void clearData() 
          {
        entidad.createQuery("delete from EditorialEntity").executeUpdate();
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
  public void createEditorialTest()
  {
      PodamFactory sujeto=new PodamFactoryImpl();
      EditorialEntity entidadDePrueba= sujeto.manufacturePojo(EditorialEntity.class);
      EditorialEntity compraDePrueba= editorialPersistence.create(entidadDePrueba);
      
      Assert.assertNotNull(compraDePrueba);
      EditorialEntity laEntidad=entidad.find(EditorialEntity.class, compraDePrueba.getId());
       Assert.assertEquals(entidadDePrueba.getId(), laEntidad.getId());
        Assert.assertEquals(entidadDePrueba.getNombre(), laEntidad.getNombre());
        ;
         
  }
  
    
   @Test
   public void getEditorialTest()
   {
       EditorialEntity prueba= data.get(0);
       EditorialEntity marcoDeComparacion =editorialPersistence.find(prueba.getId());
        Assert.assertNotNull(marcoDeComparacion);
        Assert.assertEquals(marcoDeComparacion.getId(),prueba.getId());
        Assert.assertEquals(marcoDeComparacion.getNombre(),prueba.getNombre());
        
      
        
        
   }
   @Test 
   public void getTodasLasEditorials()
   {
       List<EditorialEntity> listaDeEditorials= editorialPersistence.findAll();
       Assert.assertEquals(data.size(), listaDeEditorials.size());
        
        for (EditorialEntity entidad : listaDeEditorials) {
            boolean found = false;
            for (EditorialEntity entidad2 : data) {
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
        EditorialEntity entity = data.get(0);
        editorialPersistence.delete(entity);
        EditorialEntity deleted = editorialPersistence.find(entity.getId());
        Assert.assertEquals(deleted,null);
    }
            
    @Test
    public void actualizar()
    {
        
           EditorialEntity LaEntidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        EditorialEntity nuevaEntidad = factory.manufacturePojo(EditorialEntity.class);
        //Cambio un dato de la nuevaentidad para subirlo a la tabla como la entidad
         nuevaEntidad.setId(LaEntidad.getId());
          editorialPersistence.update(nuevaEntidad);
          
           EditorialEntity resp = entidad.find(EditorialEntity.class, LaEntidad.getId());

        Assert.assertEquals(nuevaEntidad.getId(), resp.getId());
        Assert.assertEquals(nuevaEntidad.getNombre(), resp.getNombre());
        
        
    }
    
    
   

    
}
