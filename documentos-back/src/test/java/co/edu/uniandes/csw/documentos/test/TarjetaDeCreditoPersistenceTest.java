/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.persistence.TarjetaDeCreditoPersistence;
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
public class TarjetaDeCreditoPersistenceTest {
   
        /**
     *
     * @return Devuelve el jar que Arquillian va a desplegar en el Glassfish
     * embebido. El jar contiene las clases de TDC, el descriptor de la
     * base de datos y el archivo benas.xml para resolver la inyección de
     * dependencias.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyección de la dependencia a la clase EmployeePersistence cuyos métodos
     * se van a probar.
     */
    @Inject
    private TarjetaDeCreditoPersistence TDCPersistance;

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
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    /**
     *
     */
    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

            em.persist(entity);
            data.add(entity);
        }
    }
    
     /**
     * Prueba para crear una nueva TDC.
     *
     *
     */
    @Test
    public void createTarjetaDeCreditoTest() {
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
        TarjetaDeCreditoEntity result = TDCPersistance.create(newEntity);

        Assert.assertNotNull(result);

        TarjetaDeCreditoEntity entity = em.find(TarjetaDeCreditoEntity.class, result.getId());

        Assert.assertEquals(newEntity.getNombreEnLaTarjeta(), entity.getNombreEnLaTarjeta());
        Assert.assertEquals(newEntity.getNroDeLaTarjeta(), entity.getNroDeLaTarjeta());
        Assert.assertEquals(newEntity.getTipoDeTarjeta(), entity.getTipoDeTarjeta());
        Assert.assertEquals(newEntity.getNumeroDeSeguridad(), entity.getNumeroDeSeguridad());
    }

    /**
     * Prueba para consultar la lista de TDC.
     *
     *
     */
    @Test
    public void geTodasTarjetasDeCreditoTest() {
        List<TarjetaDeCreditoEntity> list = TDCPersistance.findAll();
        Assert.assertEquals(data.size(), list.size());
        for (TarjetaDeCreditoEntity ent : list) {
            boolean found = false;
            for (TarjetaDeCreditoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

      /**
     * Prueba para consultar un Employee.
     *
     *
     */
    @Test
    public void getTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TarjetaDeCreditoEntity newEntity = TDCPersistance.find(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombreEnLaTarjeta(), newEntity.getNombreEnLaTarjeta());
        Assert.assertEquals(entity.getNroDeLaTarjeta(), newEntity.getNroDeLaTarjeta());
        Assert.assertEquals(entity.getNumeroDeSeguridad(), newEntity.getNumeroDeSeguridad());
        Assert.assertEquals(entity.getTipoDeTarjeta(), newEntity.getTipoDeTarjeta());
    }

    /**
     * Prueba para eliminar un Employee.
     *
     *
     */
    @Test
    public void deleteTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        TDCPersistance.delete(entity.getId());
        TarjetaDeCreditoEntity deleted = em.find(TarjetaDeCreditoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Employee.
     *
     *
     */
    @Test
    public void updateTarjetaDeCreditoTest() {
        TarjetaDeCreditoEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        TarjetaDeCreditoEntity newEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);

        newEntity.setId(entity.getId());

        TDCPersistance.update(newEntity);

        TarjetaDeCreditoEntity resp = em.find(TarjetaDeCreditoEntity.class, entity.getId());

        Assert.assertEquals(newEntity.getNombreEnLaTarjeta(), resp.getNombreEnLaTarjeta());
        Assert.assertEquals(newEntity.getNroDeLaTarjeta(), resp.getNroDeLaTarjeta());
        Assert.assertEquals(newEntity.getNumeroDeSeguridad(), resp.getNumeroDeSeguridad());
        Assert.assertEquals(newEntity.getTipoDeTarjeta(), resp.getTipoDeTarjeta());
    }
}
