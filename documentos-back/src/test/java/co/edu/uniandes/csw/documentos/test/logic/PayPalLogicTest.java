/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.PayPalLogic;
import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
import co.edu.uniandes.csw.documentos.persistence.PayPalPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;



/**
 *
 * @author g.ospinaa
 */
@RunWith(Arquillian.class)
public class PayPalLogicTest {
 
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private PayPalLogic PPLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<PayPalEntity> data = new ArrayList<PayPalEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(PayPalEntity.class.getPackage())
                .addPackage(PayPalLogic.class.getPackage())
                .addPackage(PayPalPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuracion inicial de la prueba.
     * 
     * 
     * 
     * 
     */
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
    
     /**
     * Limpia las tablas que están implicadas en la prueba.
     *
     *
     */
    private void clearData() {
        em.createQuery("delete from PayPalEntity").executeUpdate();
        em.createQuery("delete from UsuarioEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    @Test
    public void createPayPal()
    {
        PayPalEntity newEntity = factory.manufacturePojo(PayPalEntity.class);
        PayPalEntity result = PPLogic.createPayPal(newEntity);
        Assert.assertNotNull(result);
        
        newEntity.setCorreoElectronico("gregorio@hotmail.com");
        result = PPLogic.createPayPal(newEntity);
        PayPalEntity nEntity2 = factory.manufacturePojo(PayPalEntity.class);
        nEntity2.setCorreoElectronico("gregorio@hotmail.com");
        PayPalEntity result2 = PPLogic.createPayPal(nEntity2);
        Assert.assertNull(result2);
        
        
        
    }
    
    @Test
    public void crearPayPal2()
    {
        
       PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);
       PayPalEntity existe  = data.get(0);
       entity.setId(existe.getId());
       PayPalEntity result = PPLogic.createPayPal(entity);
       Assert.assertNull(result);              
    }
    
    @Test
    public void getPayPal()
    {
        List<PayPalEntity> list = PPLogic.getPayPal();
        Assert.assertEquals(data.size(), list.size());
        for(PayPalEntity entity : list)
        {
            boolean found = false;
            for(PayPalEntity storedEntity : data)
            {
                if(entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }    
                }
                    Assert.assertTrue(found);
        }
    }
    
    @Test
    public void getPayPal2()
    {
        Long id = new Long ("1234567890");
        PayPalEntity resultEntity = PPLogic.getPayPal(id);
        Assert.assertNull(resultEntity);
    }
    
    @Test
    public void updatePayPal()
    {
        PayPalEntity newEntity = factory.manufacturePojo(PayPalEntity.class);
        
        Long id  = new Long ("1234567890");
        newEntity.setId(id);
        
        PPLogic.updatePayPal(newEntity);
        PayPalEntity result = PPLogic.getPayPal(newEntity.getId());
        Assert.assertNull(result);
        
        PayPalEntity entity2 = factory.manufacturePojo(PayPalEntity.class);
        newEntity.setCorreoElectronico("gregoriohotmail.com");
        result = PPLogic.updatePayPal(entity2);
        Assert.assertNull(result);
                
        PayPalEntity nEntity2 = factory.manufacturePojo(PayPalEntity.class);
        nEntity2.setCorreoElectronico("gregorio@hotmail.com");
        PayPalEntity result2 = PPLogic.updatePayPal(nEntity2);
        Assert.assertNotNull(result2);
    }
    
    
    @Test
    public void deletePayPalTest() {
        PayPalEntity entity = data.get(0);
        PPLogic.deletePayPal(entity.getId());
        PayPalEntity deleted = em.find(PayPalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
            
}