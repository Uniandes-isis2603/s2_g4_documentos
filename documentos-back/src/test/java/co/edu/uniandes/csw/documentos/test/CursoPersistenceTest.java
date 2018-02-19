/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.persistence.CursoPersistence;
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
public class CursoPersistenceTest {
       @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(CursoEntity.class.getPackage())
                .addPackage(CursoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
               
                }
    
     @Inject
    private CursoPersistence cursoPersistence;
      
     @PersistenceContext
    private EntityManager entidad;
      
     @Inject
    UserTransaction utx;
     
     private List<CursoEntity> data = new ArrayList<CursoEntity>();
     
     
        private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            CursoEntity entity = factory.manufacturePojo(CursoEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }
        
        }
        
        private void clearData() 
          {
        entidad.createQuery("delete from CursoEntity").executeUpdate();
    }
    
    public CursoPersistenceTest() {
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
    public void crearCursoTest()
            
    {
      PodamFactory sujeto=new PodamFactoryImpl();
      CursoEntity entidadDePrueba= sujeto.manufacturePojo(CursoEntity.class);
       
      CursoEntity compraDePrueba= cursoPersistence.create(entidadDePrueba);
       Assert.assertNotNull(compraDePrueba);
      
      CursoEntity laEntidad=entidad.find(CursoEntity.class, compraDePrueba.getId());
       Assert.assertEquals(entidadDePrueba.getId(), laEntidad.getId());
        Assert.assertEquals(entidadDePrueba.getCodigo(), laEntidad.getCodigo());
         Assert.assertEquals(entidadDePrueba.getDepartamento(), laEntidad.getDepartamento());
          Assert.assertEquals(entidadDePrueba.getNombre(), laEntidad.getNombre());
     
       
        
    }
    @Test
    public void getCursoTest()
   {
         CursoEntity prueba= data.get(0);
       CursoEntity marcoDeComparacion =cursoPersistence.find(prueba.getId());
        Assert.assertNotNull(marcoDeComparacion);
        Assert.assertEquals(marcoDeComparacion.getId(),prueba.getId());
        Assert.assertEquals(marcoDeComparacion.getNombre(),prueba.getNombre());
        Assert.assertEquals(marcoDeComparacion.getDepartamento(),prueba.getDepartamento());
        Assert.assertEquals(marcoDeComparacion.getCodigo(),prueba.getCodigo());
    }
    @Test
    public void getTodosLosCuros()
    {
         List<CursoEntity> listaDeCursos= cursoPersistence.findAll();
       Assert.assertEquals(data.size(), listaDeCursos.size());
        
        for (CursoEntity entidad : listaDeCursos) {
            boolean found = false;
            for (CursoEntity entidad2 : data) {
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
        CursoEntity entity = data.get(0);
        cursoPersistence.delete(entity);
        CursoEntity deleted = cursoPersistence.find(entity.getId());
        Assert.assertEquals(deleted,null);
    }
     @Test
    public void actualizar()
    {
        
           CursoEntity LaEntidad = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        CursoEntity nuevaEntidad = factory.manufacturePojo(CursoEntity.class);
        //Cambio un dato de la nuevaentidad para subirlo a la tabla como la entidad
         nuevaEntidad.setId(LaEntidad.getId());
          cursoPersistence.update(nuevaEntidad);
          
           CursoEntity resp = entidad.find(CursoEntity.class, LaEntidad.getId());

        Assert.assertEquals(nuevaEntidad.getId(), resp.getId());
      
        Assert.assertEquals(nuevaEntidad.getNombre(),resp.getNombre());
        Assert.assertEquals(nuevaEntidad.getDepartamento(),resp.getDepartamento());
        Assert.assertEquals(nuevaEntidad.getCodigo(),resp.getCodigo());
    }
         
    @After
    public void tearDown() {
    }

    
}
