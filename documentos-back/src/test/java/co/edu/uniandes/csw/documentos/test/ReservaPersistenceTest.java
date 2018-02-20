/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.persistence.ReservaPersistence;
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
import org.junit.runner.RunWith;
import reactor.util.Assert;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author f.marroquin10
 */
@RunWith(Arquillian.class)
public class ReservaPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyeccion de la dependencia a la clase de ReservaPersistence cuyos
     * metodos se van a probar.
     */
    @Inject
    private ReservaPersistence ReservaPersistence;

    /**
     * Contexto de persitencia que se va a utilizar para acceder a la Base de
     * datos por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager entidad;

    /**
     * Variable para marcar las transacciones del em anterior cuando se
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        entidad.createQuery("delete from ReservaEntity").executeUpdate();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
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

    /**
     * Prueba para crear una reserva.
     */
    @Test
    public void createTest() {
        PodamFactory sujeto = new PodamFactoryImpl();
        ReservaEntity entidadDePrueba = sujeto.manufacturePojo(ReservaEntity.class);
        ReservaEntity usuarioDePrueba = ReservaPersistence.create(entidadDePrueba);

        Assert.notNull(usuarioDePrueba);

    }

    /**
     * Prueba para actualizar una reserva.
     */
    @Test
    public void updateTest() {
        ReservaEntity entity = data.get(0);
        PodamFactory sujeto = new PodamFactoryImpl();
        ReservaEntity entidadDePrueba = sujeto.manufacturePojo(ReservaEntity.class);
        entidadDePrueba.setId(entity.getId());
        ReservaPersistence.update(entidadDePrueba);
        ReservaEntity resp = entidad.find(ReservaEntity.class, entity.getId());
        org.junit.Assert.assertEquals(entidadDePrueba.getId(), resp.getId());

    }

    /**
     * Prueba para encontrar todos las reservas.
     */
    @Test
    public void findAllTest() {
        List<ReservaEntity> usuarios = ReservaPersistence.findAll();
        org.junit.Assert.assertEquals(data.size(), usuarios.size());
        for (ReservaEntity ent : usuarios) {
            boolean found = false;
            for (ReservaEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            org.junit.Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para encontrar una reserva.
     */
    @Test
    public void findTest() {

        ReservaEntity entity = data.get(0);
        ReservaEntity entidadDePrueba = ReservaPersistence.find(entity.getId());
        org.junit.Assert.assertNotNull(entidadDePrueba);
        org.junit.Assert.assertEquals(entidadDePrueba.getId(), entity.getId());
    }

    /**
     * Prueba para eliminar una reserva.
     */
    @Test
    public void deleteTest() {

        ReservaEntity entity = data.get(0);
        ReservaPersistence.delete(entity.getId());
        ReservaEntity deleted = entidad.find(ReservaEntity.class, entity.getId());
        org.junit.Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {
    }
}
