/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.persistence.DeseadoPersistence;
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
public class DeseadoPersistenceTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DeseadoEntity.class.getPackage())
                .addPackage(DeseadoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Inyeccion de la dependencia a la clase de DeseadoPersistence cuyos
     * metodos se van a probar.
     */
    @Inject
    private DeseadoPersistence DeseadoPersistence;

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

    private List<DeseadoEntity> data = new ArrayList<DeseadoEntity>();

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            DeseadoEntity entity = factory.manufacturePojo(DeseadoEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }

    }

    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        entidad.createQuery("delete from DeseadoEntity").executeUpdate();
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
     * Prueba para crear un nuevo deseado.
     */
    @Test
    public void createTest() {
        PodamFactory sujeto = new PodamFactoryImpl();
        DeseadoEntity entidadDePrueba = sujeto.manufacturePojo(DeseadoEntity.class);
        DeseadoEntity usuarioDePrueba = DeseadoPersistence.create(entidadDePrueba);

        Assert.notNull(usuarioDePrueba);

    }

    /**
     * Prueba para actualizar un deseado.
     */
    @Test
    public void updateTest() {
        DeseadoEntity entity = data.get(0);
        PodamFactory sujeto = new PodamFactoryImpl();
        DeseadoEntity entidadDePrueba = sujeto.manufacturePojo(DeseadoEntity.class);
        entidadDePrueba.setId(entity.getId());
        DeseadoPersistence.update(entidadDePrueba);
        DeseadoEntity resp = entidad.find(DeseadoEntity.class, entity.getId());
        org.junit.Assert.assertEquals(entidadDePrueba.getNombre(), resp.getNombre());

    }

    /**
     * Prueba para encontrar todos los deseados de todos los usuarios.
     */
    @Test
    public void findAllTest() {
        List<DeseadoEntity> usuarios = DeseadoPersistence.findAll();
        org.junit.Assert.assertEquals(data.size(), usuarios.size());
        for (DeseadoEntity ent : usuarios) {
            boolean found = false;
            for (DeseadoEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            org.junit.Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para encontrar un deseado.
     */
    @Test
    public void findTest() {

        DeseadoEntity entity = data.get(0);
        DeseadoEntity entidadDePrueba = DeseadoPersistence.find(entity.getId());
        org.junit.Assert.assertNotNull(entidadDePrueba);
        org.junit.Assert.assertEquals(entidadDePrueba.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para eliminar un deseado.
     */
    @Test
    public void deleteTest() {

        DeseadoEntity entity = data.get(0);
        DeseadoPersistence.delete(entity.getId());
        DeseadoEntity deleted = entidad.find(DeseadoEntity.class, entity.getId());
        org.junit.Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {
    }

}
