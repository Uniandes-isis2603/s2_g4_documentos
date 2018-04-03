/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.PayPalLogic;
import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
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
        PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);
        entity.setCorreoElectronico("correoINVALIDO");
        boolean check = true;
        try
        {
        PayPalEntity result = PPLogic.createPayPal(entity);
        }
        catch(BusinessLogicException e)
        {
            Assert.assertTrue(e.getMessage().equals("El correo no es valido"));
        }
        
        PayPalEntity entity2 = factory.manufacturePojo(PayPalEntity.class);
        entity.setCorreoElectronico("correo@VALIDO");
        try
        {
            PayPalEntity result = PPLogic.createPayPal(entity2);
        }
        catch(BusinessLogicException d)
        {
            System.out.println("Error grave en Logica");
        }
    }
    
    @Test
    public void crearPayPal2()
    {
        
       PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);
       PayPalEntity existe  = data.get(0);
       entity.setCorreoElectronico(existe.getCorreoElectronico());
        try {
                PayPalEntity result = PPLogic.createPayPal(entity);
        } catch (BusinessLogicException e) {
            System.out.println(e.getMessage());
            Assert.assertTrue(e.getMessage().equals("El correo no es valido"));
        }
   
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
    public void getPayPal3()
    {
        Long id = data.get(0).getId();
        PayPalEntity result = PPLogic.getPayPal(id);
        Assert.assertNotNull(result);
    }
    
    @Test
    public void updatePayPal()
    {
        PayPalEntity newEntity = factory.manufacturePojo(PayPalEntity.class);
        data.get(0).setCorreoElectronico("nofuncionagmail");
        try
        {
         PPLogic.updatePayPal(data.get(0));
        }
        catch(BusinessLogicException e)
        {
            Assert.assertTrue(e.getMessage().equals("El correo no es valido"));
        }
        
        boolean check = true;
        PayPalEntity nEntity2 = data.get(0);
        nEntity2.setCorreoElectronico("gregorio@hotmail.com");
        try{
        PayPalEntity result2 = PPLogic.updatePayPal(nEntity2);
        }
        catch(BusinessLogicException e)
        {
        check = false;
        }
        Assert.assertTrue(check);

                
    }
    
    
    @Test
    public void deletePayPalTest() {
        boolean check = true;
        PayPalEntity entity = data.get(0);
        try{
        PPLogic.deletePayPal(entity.getId());
        }
        catch(BusinessLogicException e)
        {
            check = false;
        }
        Assert.assertTrue(check);
        PayPalEntity deleted = em.find(PayPalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
            
}