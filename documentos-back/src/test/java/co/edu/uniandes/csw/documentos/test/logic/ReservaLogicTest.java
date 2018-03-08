package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.ReservaLogic;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.persistence.ReservaPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author f.marroquin10
 */
@RunWith(Arquillian.class)
public class ReservaLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private ReservaLogic ReservaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<ReservaEntity> data = new ArrayList<ReservaEntity>();
    private List<DocumentoEntity> documentoData = new ArrayList<DocumentoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(DocumentoEntity.class.getPackage())
                .addPackage(ReservaLogic.class.getPackage())
                .addPackage(ReservaPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }

    /**
     * Configuración inicial de la prueba.
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
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from DocumentoEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            DocumentoEntity entity = factory.manufacturePojo(DocumentoEntity.class);

            em.persist(entity);
            documentoData.add(entity);

        }

        for (int i = 0; i < 3; i++) {
            ReservaEntity entity = factory.manufacturePojo(ReservaEntity.class);
            if (entity.getCosto() < 0) {
                entity.setCosto(entity.getCosto() * (-1));
            }
            if (entity.getFecha().before(new Date())) {
                entity.setDocumentos(documentoData);
            }
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear una Reserva no valida
     *
     *
     */
    @Test
    public void createReservaTestInvalido() throws ParseException {

        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setCosto(123);
        newEntity.setFecha(new Date());
        ReservaEntity result = ReservaLogic.createReserva(newEntity);

        Assert.assertNotNull(result);

        ReservaEntity newEntity2 = factory.manufacturePojo(ReservaEntity.class);

        newEntity2.setCosto(-23123);
        result = ReservaLogic.createReserva(newEntity2);
        Assert.assertNull(result);

        ReservaEntity newEntity3 = factory.manufacturePojo(ReservaEntity.class);
        SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
        newEntity3.setFecha(fecha.parse("1999-05-12"));
        result = ReservaLogic.createReserva(newEntity3);
        Assert.assertNull(result);

    }

    /**
     * Prueba para crear una Reserva valida
     *
     *
     */
    @Test
    public void createReservaTestValido() {

        ReservaEntity newEntity = factory.manufacturePojo(ReservaEntity.class);
        newEntity.setCosto(123);
        newEntity.setFecha(new Date());
        ReservaEntity result = ReservaLogic.createReserva(newEntity);
        Assert.assertNotNull(result);

        ReservaEntity entity = em.find(ReservaEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getFecha().toString(), entity.getFecha().toString());

    }

    /**
     * Prueba para consultar la lista de Reservaes
     *
     *
     */
    @Test
    public void getReservaesTest() {
        List<ReservaEntity> list = ReservaLogic.getReservaes();
        Assert.assertEquals(data.size(), list.size());
        for (ReservaEntity entity : list) {
            boolean found = false;
            for (ReservaEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Reserva no valida
     *
     *
     */
    @Test
    public void getReservaTestInvalido() {

        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);
        pojoEntity.setCosto(-32123);
        ReservaLogic.createReserva(pojoEntity);
        ReservaEntity resultEntity = ReservaLogic.getReserva(pojoEntity.getId());
        Assert.assertNull(resultEntity);

    }

    /**
     * Prueba para consultar una Reserva valida
     *
     *
     */
    @Test
    public void getReservaTestValido() {
        ReservaEntity entity = data.get(0);
        ReservaEntity resultEntity = ReservaLogic.getReserva(entity.getId());
        Assert.assertNotNull(resultEntity);

        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getFecha(), entity.getFecha());

    }

    /**
     * Prueba para eliminar una Reserva valido
     *
     *
     */
    @Test
    public void deleteReservaTest() {
        ReservaEntity entity = data.get(0);
        ReservaLogic.deleteReserva(entity.getId());
        ReservaEntity deleted = em.find(ReservaEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Reserva no valida
     *
     *
     */
    @Test
    public void updateReservaTestInvalido() {

        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);

        Long id = new Long("1");
        pojoEntity.setId(id);

        ReservaLogic.updateReserva(pojoEntity);
        ReservaEntity encontrar = ReservaLogic.getReserva(pojoEntity.getId());
        Assert.assertNull(encontrar);

        pojoEntity.setId(data.get(0).getId());
        pojoEntity.setCosto(-232134);
        encontrar = ReservaLogic.updateReserva(pojoEntity);

        Assert.assertNull(encontrar);

    }

    /**
     * Prueba para actualizar una Reserva valida
     *
     *
     */
    @Test
    public void updateReservaTestValido() {
        ReservaEntity entity = data.get(0);
        ReservaEntity pojoEntity = factory.manufacturePojo(ReservaEntity.class);

        pojoEntity.setId(entity.getId());
        ReservaLogic.updateReserva(pojoEntity);

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), entity.getId());
        Assert.assertNotEquals(pojoEntity.getFecha(), entity.getFecha());
    }
}
