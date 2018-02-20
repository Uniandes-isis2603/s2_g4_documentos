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
import org.junit.*;
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

     /**
     * Inyeccion de la dependencia a la clase de UsuarioPersistence cuyos metodos
     * se van a probar.
     */
    @Inject
    private UsuarioPersistence UsuarioPersistence;

        /**
     * Contexto de persitencia que se va a utilizar para acceder a la Base de datos
     * por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager entidad;

     /**
     * Variable para marcar las transacciones del em anterior cuando
     * se crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);

            entidad.persist(entity);
            data.add(entity);
        }

    }
/**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        entidad.createQuery("delete from UsuarioEntity").executeUpdate();
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
     * Prueba para crear un usuario.
     */
    @Test
    public void createTest() {
        PodamFactory sujeto = new PodamFactoryImpl();
        UsuarioEntity entidadDePrueba = sujeto.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity usuarioDePrueba = UsuarioPersistence.create(entidadDePrueba);

        Assert.notNull(usuarioDePrueba);

    }

     /**
     * Prueba para actualizar un usuario.
     */
    @Test
    public void updateTest() {
        UsuarioEntity entity = data.get(0);
        PodamFactory sujeto = new PodamFactoryImpl();
        UsuarioEntity entidadDePrueba = sujeto.manufacturePojo(UsuarioEntity.class);
        entidadDePrueba.setId(entity.getId());
        UsuarioPersistence.update(entidadDePrueba);
        UsuarioEntity resp = entidad.find(UsuarioEntity.class, entity.getId());
        org.junit.Assert.assertEquals(entidadDePrueba.getNombre(), resp.getNombre());

    }

     /**
     * Prueba para encontrar todos los usuarios.
     */
    @Test
    public void findAllTest() {
        List<UsuarioEntity> usuarios = UsuarioPersistence.findAll();
        org.junit.Assert.assertEquals(data.size(), usuarios.size());
        for (UsuarioEntity ent : usuarios) {
            boolean found = false;
            for (UsuarioEntity entity : data) {
                if (ent.getId().equals(entity.getId())) {
                    found = true;
                }
            }
            org.junit.Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para encontrar un usuario.
     */
    @Test
    public void findTest() {

        UsuarioEntity entity = data.get(0);
        UsuarioEntity entidadDePrueba = UsuarioPersistence.find(entity.getId());
        org.junit.Assert.assertNotNull(entidadDePrueba);
        org.junit.Assert.assertEquals(entidadDePrueba.getNombre(), entity.getNombre());
    }

    /**
     * Prueba para eliminar un usuario.
     */
    @Test
    public void deleteTest() {

        UsuarioEntity entity = data.get(0);
        UsuarioPersistence.delete(entity.getId());
        UsuarioEntity deleted = entidad.find(UsuarioEntity.class, entity.getId());
        org.junit.Assert.assertNull(deleted);
    }

    @After
    public void tearDown() {
    }

}
