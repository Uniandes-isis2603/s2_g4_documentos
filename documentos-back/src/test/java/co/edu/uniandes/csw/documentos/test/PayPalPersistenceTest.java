/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
import co.edu.uniandes.csw.documentos.persistence.PayPalPersistence;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.junit.Assert;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author g.ospinaa
 */
@RunWith(Arquillian.class)
public class PayPalPersistenceTest {
    /**
     * 
    * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de Employee, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     * 
     */
    @Deployment
    public static JavaArchive createDeplyment()
    {
        return ShrinkWrap.create(JavaArchive.class)
               .addPackage(PayPalEntity.class.getPackage())
               .addPackage(PayPalPersistence.class.getPackage())
               .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
               .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase PayPalPersistence cuyos metodos 
     * se van a probar.
     */
    @Inject
    private PayPalPersistence PPPersistence;
    
    /**
     * Contexto de Persostencia que se va autilizar para acceder a la Base de
     * datos por fuera de los métodos que se están probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para martcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject 
    UserTransaction utx;

    /**
     * Configuración inicial de la prueba.
     *
     *
     */
    @Before
    public void setUp() {
        try {
            utx.begin(); 
            em.joinTransaction();
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
     * 
     */
    private List<PayPalEntity> data = new ArrayList<PayPalEntity>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
    /**
     * Prueba para crear una cuenta PayPal.
     */
    
    @Test
    public void createPayPalTest()
    {
        PodamFactory factory = new PodamFactoryImpl();
        PayPalEntity newEntity = factory.manufacturePojo(PayPalEntity.class);
        PayPalEntity result = PPPersistence.create(newEntity);
        
        Assert.assertNotNull(result);
        
        PayPalEntity entity = em.find(PayPalEntity.class, result.getId());
        
        Assert.assertEquals(newEntity.getCorreoElectronico(), entity.getCorreoElectronico());
        Assert.assertEquals(newEntity.getUsuario(), entity.getUsuario());
    }
    
    /**
     * Prueba para consultar la lista de cuentas PayPal.
     */
    @Test
    public void getPayPalsTest()
    {
        List<PayPalEntity> list = PPPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (PayPalEntity ent : list)
        {
            boolean found  = false; 
            for (PayPalEntity entity : data)
            {
                if (ent.getId().equals(entity.getId()))
                {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar una unica cuenta PayPal
     */
    @Test
    public void getPayPalTest()
    {
        PayPalEntity entity = data.get(0);
        PayPalEntity newEntity = PPPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getCorreoElectronico(), newEntity.getCorreoElectronico());
        Assert.assertEquals(entity.getUsuario(), newEntity.getUsuario());
        Assert.assertEquals(entity.getId(), newEntity.getId());
    }
    
    /**
     * Prueba para eliminar una cuenta PayPal.
     */
    @Test
    public void deletePayPalTest()
    {
        PayPalEntity entity = data.get(0);
        PPPersistence.delete(entity.getId());
        PayPalEntity deleted = em.find(PayPalEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }
    
    /**
     * Prueba para actualizar una cuenta PayPal.
     */
    @Test
    public void updatePayPalTest()
    {
        PayPalEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        PayPalEntity newEntity = factory.manufacturePojo(PayPalEntity.class);

        newEntity.setId(entity.getId());

        PPPersistence.update(newEntity);

        PayPalEntity resp = em.find(PayPalEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getCorreoElectronico(), resp.getCorreoElectronico());
        Assert.assertEquals(newEntity.getUsuario(), resp.getUsuario());
    }   
}

