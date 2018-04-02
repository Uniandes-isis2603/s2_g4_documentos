package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.entities.CompraEntity;
import co.edu.uniandes.csw.documentos.entities.PayPalEntity;
import co.edu.uniandes.csw.documentos.entities.ReservaEntity;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class UsuarioLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private UsuarioLogic UsuarioLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();

    private List<ReservaEntity> reservaData = new ArrayList<ReservaEntity>();

    private List<ComentarioEntity> comentarioData = new ArrayList<ComentarioEntity>();

    private List<CompraEntity> compraData = new ArrayList<CompraEntity>();
    private List<PayPalEntity> payPalData = new ArrayList<PayPalEntity>();
    private List<TarjetaDeCreditoEntity> tarjetaData = new ArrayList<TarjetaDeCreditoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(UsuarioEntity.class.getPackage())
                .addPackage(UsuarioLogic.class.getPackage())
                .addPackage(UsuarioPersistence.class.getPackage())
                .addPackage(ReservaEntity.class.getPackage())
                .addPackage(PayPalEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(CompraEntity.class.getPackage())
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
        em.createQuery("delete from UsuarioEntity").executeUpdate();
        em.createQuery("delete from ReservaEntity").executeUpdate();
        em.createQuery("delete from PayPalEntity").executeUpdate();
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from CompraEntity").executeUpdate();

    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {
        for (int i = 0; i < 3; i++) {
            ReservaEntity reserva = factory.manufacturePojo(ReservaEntity.class);
            em.persist(reserva);
            reservaData.add(reserva);
        }

        for (int i = 0; i < 3; i++) {
            CompraEntity entity = factory.manufacturePojo(CompraEntity.class);
            em.persist(entity);
            compraData.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            ComentarioEntity entity = factory.manufacturePojo(ComentarioEntity.class);
            em.persist(entity);
            comentarioData.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            PayPalEntity entity = factory.manufacturePojo(PayPalEntity.class);
            em.persist(entity);
            payPalData.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            em.persist(entity);
            tarjetaData.add(entity);
        }

        for (int i = 0; i < 3; i++) {
            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
            if (entity.getEdad() < 17 || entity.getEdad() > 100) {
                entity.setEdad(20);
            } else if (entity.getGenero() < 0 || entity.getGenero() > 1) {
                entity.setGenero(1);
            } else if (!entity.getCorreo().contains("@")) {
                entity.setCorreo("f.marroquin10@uniandes.edu.co");
            }else if(entity.getNombreUsuario().length() < 7 || entity.getNombreUsuario().length() > 15)
            {
                entity.setNombreUsuario("el ferchis12");
            }
            entity.setNombre("el papa");
            entity.setComentarios(comentarioData);
            entity.setReservas(reservaData);
            entity.setCompras(compraData);
            entity.setPaypal(payPalData);
            entity.setTarjetasCredito(tarjetaData);
            em.persist(entity);
            data.add(entity);
        }
        
        
    }

    /**
     * Prueba para crear un usuario no valida
     *
     *
     */
    @Test
    public void createUsuarioTestInvalido() {

        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
        UsuarioEntity result = null;

        Long id = new Long("111");
        newEntity.setId(id);
        newEntity.setNombre("(#/$6jd-´´");
        try {
            result = UsuarioLogic.createUsuario(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);

        }
        Assert.assertNull(result);

        newEntity.setNombre("maria antonia");
        newEntity.setEdad(-15);
        try {
            result = UsuarioLogic.createUsuario(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

        newEntity.setEdad(18);
        newEntity.setGenero(-15);
        try {
            result = UsuarioLogic.createUsuario(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

        newEntity.setGenero(0);
        newEntity.setCorreo("este no es un correo");
        try {
            result = UsuarioLogic.createUsuario(newEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(result);

    }

    /**
     * Prueba para crear un Usuario
     *
     *
     */
    @Test
    public void createUsuarioTestValido() {

        UsuarioEntity newo = data.get(0);
        Long d= new Long("123");
        newo.setId(d);
        newo.setNombre("carlos florez");
        newo.setNombreUsuario("roger123");
        newo.setCorreo("12jasd@hotmail.com");
        newo.setEdad(32);
        newo.setGenero(1);
        newo.setNombreUsuario("roger123");
        UsuarioEntity result = null;
        newo.setId(new Long("23"));
        try {
            result = UsuarioLogic.createUsuario(newo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(result);

        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertNotNull(entity);
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getNombre(), entity.getNombre());
        Assert.assertEquals(result.getEdad(), entity.getEdad());
        Assert.assertEquals(result.getGenero(), entity.getGenero());
        Assert.assertEquals(result.getNombreUsuario(), entity.getNombreUsuario());
        Assert.assertEquals(result.getCorreo(), entity.getCorreo());
 
    }

    /**
     * Prueba para consultar la lista de Usuarios
     *
     *
     */
    @Test
    public void getUsuariosTest() {
        List<UsuarioEntity> list = UsuarioLogic.getUsuarios();
        Assert.assertEquals(data.size(), list.size());
        for (UsuarioEntity entity : list) {
            boolean found = false;
            for (UsuarioEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar un Usuario no valido
     *
     *
     */
    @Test
    public void getUsuarioTestInvalido() {

        Long id = new Long("1");
        UsuarioEntity resultEntity = null;
        try {
            resultEntity = UsuarioLogic.getUsuario(id);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(resultEntity);

    }

    @Test
    public void getUsuarioByname()
    {
        UsuarioEntity entity = null;
        try {
             entity= UsuarioLogic.getUsuarioByName(data.get(0).getNombreUsuario());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
                Assert.assertNotNull(entity);

    }
    /**
     * Prueba para consultar un Usuario
     *
     *
     */
    @Test
    public void getUsuarioTestValido() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = null;
        try {
            resultEntity = UsuarioLogic.getUsuario(entity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getEdad(), resultEntity.getEdad());
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals(entity.getGenero(), resultEntity.getGenero());
        Assert.assertEquals(entity.getNombreUsuario(), resultEntity.getNombreUsuario());
    }

    /**
     * Prueba para eliminar un Usuario
     *
     *
     */
    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        Long id = new Long("1111");
        entity.setId(id);
        try {
            UsuarioLogic.createUsuario(entity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            UsuarioLogic.deleteUsuario(id);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Usuario
     *
     *
     */
    @Test
    public void updateUsuarioTestValido() {

        UsuarioEntity nuevo = new UsuarioEntity();
        nuevo.setNombre("federico");
        nuevo.setCorreo("f.marroquin10@uniandes.edu.co");
        nuevo.setEdad(21);
        nuevo.setGenero(1);
        nuevo.setNombreUsuario("fedeGeek");
        Long id = new Long("1111");
        nuevo.setId(id);

        UsuarioEntity user = null;
        try {
            user = UsuarioLogic.createUsuario(nuevo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(user);
        nuevo.setNombre("federico mb");
        try {
            user = UsuarioLogic.updateUsuario(nuevo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(user);
        nuevo.setCorreo("f.mquin10@uniandes.edu.co");
        try {
            user = UsuarioLogic.updateUsuario(nuevo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNotNull(user);
        nuevo.setEdad(22);
        try {
            user = UsuarioLogic.updateUsuario(nuevo);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioEntity entity = em.find(UsuarioEntity.class, user.getId());
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getId(), entity.getId());
        Assert.assertEquals(user.getNombre(), entity.getNombre());
        Assert.assertEquals(user.getEdad(), entity.getEdad());
        Assert.assertEquals(user.getCorreo(), entity.getCorreo());
        Assert.assertEquals(user.getGenero(), entity.getGenero());
        Assert.assertEquals(user.getNombreUsuario(), entity.getNombreUsuario());

    }

    /**
     * Prueba para actualizar un usuario no valido
     *
     *
     */
    @Test
    public void updateUsuarioTestInvalido() {

        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);

        Long id = new Long("1");
        pojoEntity.setId(id);

        try {
            UsuarioLogic.updateUsuario(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        UsuarioEntity encontrar = null;
        try {
            encontrar = UsuarioLogic.getUsuario(pojoEntity.getId());
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(encontrar);

        pojoEntity.setId(data.get(0).getId());
        pojoEntity.setNombre("as###$%&/#");
        try {
            encontrar = UsuarioLogic.updateUsuario(pojoEntity);
        } catch (BusinessLogicException ex) {
            Logger.getLogger(UsuarioLogicTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        Assert.assertNull(encontrar);

    }
}
