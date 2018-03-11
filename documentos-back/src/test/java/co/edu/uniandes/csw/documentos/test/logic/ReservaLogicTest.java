package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.ReservaLogic;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.ReservaPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    private List<ReservaEntity> data2 = new ArrayList<ReservaEntity>();

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
                entity.setFecha(new Date());
            }
            em.persist(entity);
            data.add(entity);

        }
        Integer a = 5;
        Integer b = 6;
        Integer c = 7;
        Integer d = 8;

        //reserva valida
        ReservaEntity entity = new ReservaEntity();
        entity.setCosto(1234);
        entity.setFecha(new Date());
        entity.setId(a.longValue());
        
        //reserva valida
        ReservaEntity entity2 = new ReservaEntity();
        entity2.setCosto(6456);
        entity2.setFecha(new Date());
        entity2.setId(b.longValue());


        //reserva costo invalido
        ReservaEntity entity3 = new ReservaEntity();
        entity3.setCosto(-1234);
        entity3.setFecha(new Date());
        entity3.setId(c.longValue());

        // atributos nulos
        ReservaEntity entity4 = new ReservaEntity();
        entity4.setCosto(0);
        entity4.setFecha(null);
        entity4.setId(d.longValue());

        data2.add(entity);
        //em.persist(entity2);
        data2.add(entity2);
        //em.persist(entity2);
        data2.add(entity3);
        //em.persist(entity3);
        data2.add(entity4);
        // em.persist(entity4);

    }

    /**
     * Prueba para crear una Reserva no valida
     *
     *
     */
    @Test
    public void createReservaTestInvalido() throws ParseException {

        ReservaEntity newEntity = data.get(0);
        ReservaEntity result=null;
        try {
            result = ReservaLogic.createReserva(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

        ReservaEntity newEntity4 = data.get(1);
        newEntity4.setCosto(-12323);
        newEntity4.setFecha(new Date());
        try {
            result = ReservaLogic.createReserva(newEntity4);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

       
        ReservaEntity reserva2 = data2.get(2);
        try {
            result = ReservaLogic.createReserva(reserva2);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);
        
        ReservaEntity reserva3 = data2.get(3);
        try {
            result = ReservaLogic.createReserva(reserva3);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

    }

    /**
     * Prueba para crear una Reserva valida
     *
     *
     */
    @Test
    public void createReservaTestValido() {

        ReservaEntity newEntity = data2.get(0);
        ReservaEntity result=null;
        try {
            result = ReservaLogic.createReserva(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(result);
        result=null;
        //ya hay una entidad con el mismo id
        ReservaEntity newEntity2 = data.get(0);
        newEntity2.setCosto(12323);
        newEntity2.setFecha(new Date());
        try {
            result = ReservaLogic.createReserva(newEntity2);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

        ReservaEntity newEntity3 = data2.get(1);
        try {
            result = ReservaLogic.createReserva(newEntity3);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(result);

        ReservaEntity newEntity4 = data2.get(2);
        newEntity4.setCosto(12323);
        try {
            result = ReservaLogic.createReserva(newEntity4);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(result);

        ReservaEntity newEntity5 = data2.get(3);
        newEntity5.setCosto(1223);
        newEntity5.setFecha(new Date());
        try {
            result = ReservaLogic.createReserva(newEntity5);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(result);

    }

    /**
     * Prueba para consultar la lista de Reservaes
     *
     *
     */
    @Test
    public void getReservaesTest() {
        List<ReservaEntity> list = ReservaLogic.getReservas();
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
        try {
            ReservaLogic.createReserva(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        
        ReservaEntity entity2 = data.get(1);
        ReservaEntity resultEntity2 = ReservaLogic.getReserva(entity2.getId());
        Assert.assertNotNull(resultEntity2);
        Assert.assertEquals(resultEntity2.getId(), entity2.getId());
        Assert.assertEquals(resultEntity2.getFecha(), entity2.getFecha());
        
        ReservaEntity entity3 = data.get(2);
        ReservaEntity resultEntity3 = ReservaLogic.getReserva(entity3.getId());
        Assert.assertNotNull(resultEntity3);
        Assert.assertEquals(resultEntity3.getId(), entity3.getId());
        Assert.assertEquals(resultEntity3.getFecha(), entity3.getFecha());

    }

    /**
     * Prueba para eliminar una Reserva valido
     *
     *
     */
    @Test
    public void deleteReservaTest() {
        ReservaEntity entity = data.get(0);
        try {
            ReservaLogic.deleteReserva(entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
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

        try {
            ReservaLogic.updateReserva(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReservaEntity encontrar = ReservaLogic.getReserva(pojoEntity.getId());
        Assert.assertNull(encontrar);

        pojoEntity.setId(data.get(0).getId());
        pojoEntity.setCosto(-232134);
        try {
            encontrar = ReservaLogic.updateReserva(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

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
        try {
            ReservaLogic.updateReserva(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(ReservaLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        ReservaEntity resp = em.find(ReservaEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), entity.getId());
        Assert.assertNotEquals(pojoEntity.getFecha(), entity.getFecha());
    }
}
