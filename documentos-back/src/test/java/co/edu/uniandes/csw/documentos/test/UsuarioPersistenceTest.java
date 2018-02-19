/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;


import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
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
import org.junit.Assert.*;
import org.junit.runner.RunWith;
import reactor.util.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
/**
 *
 * @author f.marroquin10
 */
@RunWith(Arquillian.class)
public class UsuarioPersistenceTest {
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
     @Inject
    private UsuarioPersistence UsuarioPersistence;
      
     @PersistenceContext
    private EntityManager entidad;
      
     @Inject
    UserTransaction utx;
     
     private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
     
     
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }
        
        }
        
        private void clearData() 
          {
        entidad.createQuery("delete from UsuarioEntity").executeUpdate();
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
  public void createTest()
  {
      PodamFactory sujeto=new PodamFactoryImpl();   
      UsuarioEntity entidadDePrueba= sujeto.manufacturePojo(UsuarioEntity.class);
      UsuarioEntity usuarioDePrueba= UsuarioPersistence.create(entidadDePrueba);
      
      Assert.notNull(usuarioDePrueba);
  }
  

  
    
   

    @After
    public void tearDown() {
    }
    
}
