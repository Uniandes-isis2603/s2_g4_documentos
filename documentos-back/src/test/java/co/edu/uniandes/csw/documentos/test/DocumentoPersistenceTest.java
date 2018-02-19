/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.persistence.DocumentoPersistence;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ernesto Viana
 */
@RunWith(Arquillian.class)
public class DocumentoPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DocumentoEntity.class.getPackage())
                .addPackage(DocumentoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase documentoPersistence cuyos metodos 
     * se van a probar.
     */
    @Inject
    private DocumentoPersistence documentoPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de datos 
     * por fuera  de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se 
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    
    
    public DocumentoPersistenceTest() {
    }
    
    
    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from DocumentoEntity").executeUpdate();
    }
    
    private List<DocumentoEntity> data = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++) {
            DocumentoEntity entity = factory.manufacturePojo(DocumentoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear un documento.
     */
   @Test
   public void createDocumentoTest(){
      
       PodamFactory factory  = new PodamFactoryImpl();
       DocumentoEntity newEntity = factory.manufacturePojo(DocumentoEntity.class);
       DocumentoEntity result = documentoPersistence.create(newEntity);
       
       Assert.assertNotNull(result);
       
       DocumentoEntity entity = em.find(DocumentoEntity.class, result.getId());
       
       Assert.assertEquals(newEntity.getNombre(),entity.getNombre());
       
   }
   
   /**
    * Prueba para consultar la lista de Documentos.
    */
   @Test
   public void getDocumentosTest() {
       
       List<DocumentoEntity> list = documentoPersistence.findAll();
       Assert.assertEquals(data.size(), list.size());
       
       for(DocumentoEntity ent : list){
           boolean found = false;
           for(DocumentoEntity entity: data) {
               if(ent.getId().equals(entity.getId())){
                   found = true;
               }
           }
           
           Assert.assertTrue(found);
       }
               
    }
   
   /**
    * Prueba para consultar un documento.
    */
   @Test
   public void getDocumentoTest(){
       
       DocumentoEntity entity = data.get(0);
       DocumentoEntity newEntity = documentoPersistence.find(entity.getId());
       Assert.assertNotNull(newEntity);
       Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
   }
   
   
   /**
    * Prueba para actualizar documento.
    */
   @Test
   public void UpdateDocumentoTest() {
       DocumentoEntity entity = data.get(0);
       PodamFactory factory = new PodamFactoryImpl();
       DocumentoEntity newEntity = factory.manufacturePojo(DocumentoEntity.class);
       
       newEntity.setId(entity.getId());
       
       documentoPersistence.update(newEntity);
       
       DocumentoEntity resp = em.find(DocumentoEntity.class, entity.getId());
       
       Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
   }
   
   /**
    * Prueba para eliminar un documento.
    */
   @Test
   public void deleteDocumentoTest() {
       DocumentoEntity entity = data.get(0);
       documentoPersistence.delete(entity.getId());
       DocumentoEntity deleted = em.find(DocumentoEntity.class, entity.getId());
       Assert.assertNull(deleted);
   }
}
