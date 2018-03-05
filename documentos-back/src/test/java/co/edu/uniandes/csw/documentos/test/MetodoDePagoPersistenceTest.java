/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

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
import co.edu.uniandes.csw.documentos.persistence.MetodoDePagoPersistence;
import co.edu.uniandes.csw.documentos.entities.MetodoDePagoEntity;        

/**
 *
 * @author g.ospinaa
 */
@RunWith(Arquillian.class)
public class MetodoDePagoPersistenceTest 
{
 
     @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(MetodoDePagoEntity.class.getPackage())
                .addPackage(MetodoDePagoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase metodoDePagoPersistence cuyos metodos 
     * se van a probar.
     */
    @Inject
    private MetodoDePagoPersistence MDPPersistence;
    
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
    
    public MetodoDePagoPersistenceTest()
    {
        
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
        em.createQuery("delete from MetodoDePagoEntity").executeUpdate();
    }
    
    private List<MetodoDePagoEntity> data = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++) {
            MetodoDePagoEntity entity = factory.manufacturePojo(MetodoDePagoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    @Test
    public void crearMetodoDePago()
    {
        PodamFactory factory = new PodamFactoryImpl();
        MetodoDePagoEntity entity = factory.manufacturePojo(MetodoDePagoEntity.class);
        MetodoDePagoEntity result = MDPPersistence.create(entity);
        
        Assert.assertNotNull(result);
        
        MetodoDePagoEntity newEntity = em.find(MetodoDePagoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        
    }
 
    @Test
   public void getMetodosDePagoTest() {
       
       List<MetodoDePagoEntity> list = MDPPersistence.findAll();
       Assert.assertEquals(data.size(), list.size());
       
       for(MetodoDePagoEntity ent : list){
           boolean found = false;
           for(MetodoDePagoEntity entity: data) {
               if(ent.getId().equals(entity.getId())){
                   found = true;
               }
           }
           
           Assert.assertTrue(found);
       }
               
    }
   
   @Test
   public void getMetodoDePagoTest(){
       
       MetodoDePagoEntity entity = data.get(0);
       MetodoDePagoEntity newEntity = MDPPersistence.find(entity.getId());
       Assert.assertNotNull(newEntity);
   }
   
   @Test
   public void UpdateMetodoDePagoTest() {
       MetodoDePagoEntity entity = data.get(0);
       PodamFactory factory = new PodamFactoryImpl();
       MetodoDePagoEntity newEntity = factory.manufacturePojo(MetodoDePagoEntity.class);
       
       newEntity.setId(entity.getId());
       
       MDPPersistence.update(newEntity);
       
       MetodoDePagoEntity resp = em.find(MetodoDePagoEntity.class, entity.getId());
       Assert.assertEquals(newEntity.getId(), resp.getId());

       
       
   }
   
    @Test
   public void deleteDocumentoTest() {
       MetodoDePagoEntity entity = data.get(0);
       MDPPersistence.delete(entity.getId());
       MetodoDePagoEntity deleted = em.find(MetodoDePagoEntity.class, entity.getId());
       Assert.assertNull(deleted);
   }
    
}
