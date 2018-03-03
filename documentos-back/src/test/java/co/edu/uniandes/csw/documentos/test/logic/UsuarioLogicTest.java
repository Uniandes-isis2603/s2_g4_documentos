/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        em.createQuery("delete from ComprasEntity").executeUpdate();

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
     * Prueba para crear un Usuario
     *
     *
     */
    @Test
    public void createUsuarioTest() {
        UsuarioEntity result = UsuarioLogic.createUsuario(data.get(0));
        Assert.assertNotNull(result);
        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getNombre(), entity.getNombre());
        Assert.assertEquals(result.getEdad(), entity.getEdad());
        Assert.assertEquals(result.getGenero(), entity.getGenero());
        Assert.assertEquals(result.getUserName(), entity.getUserName());
        Assert.assertEquals(result.getCorreo(), entity.getCorreo());
        for (int i = 0; i < result.getComentarios().size(); i++) {
            Assert.assertEquals(result.getComentarios().get(i).getId(), entity.getComentarios().get(i).getId());

        }
        for (int i = 0; i < result.getReservas().size(); i++) {
            Assert.assertEquals(result.getReservas().get(i).getId(), entity.getReservas().get(i).getId());

        }
        for (int i = 0; i < result.getCompras().size(); i++) {
            Assert.assertEquals(result.getCompras().get(i).getId(), entity.getCompras().get(i).getId());

        }
        for (int i = 0; i < result.getPaypal().size(); i++) {
            Assert.assertEquals(result.getPaypal().get(i).getId(), entity.getPaypal().get(i).getId());

        }
        for (int i = 0; i < result.getTarjetasCredito().size(); i++) {
            Assert.assertEquals(result.getTarjetasCredito().get(i).getId(), entity.getTarjetasCredito().get(i).getId());

        }

    }

    /**
     * Prueba para consultar la lista de Usuarios
     *
     *
     */
    @Test
    public void getUsuariosTest() throws BusinessLogicException {
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
     * Prueba para consultar un Usuario
     *
     *
     */
    @Test
    public void getUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity resultEntity = UsuarioLogic.getUsuario(data.get(0).getId());
        Assert.assertNotNull(resultEntity);
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        Assert.assertEquals(entity.getEdad(), resultEntity.getEdad());
        Assert.assertEquals(entity.getCorreo(), resultEntity.getCorreo());
        Assert.assertEquals(entity.getGenero(), resultEntity.getGenero());
        Assert.assertEquals(entity.getUserName(), resultEntity.getUserName());
        for (int i = 0; i < resultEntity.getComentarios().size(); i++) {
            Assert.assertEquals(resultEntity.getComentarios().get(i).getId(), entity.getComentarios().get(i).getId());

        }
        for (int i = 0; i < resultEntity.getReservas().size(); i++) {
            Assert.assertEquals(resultEntity.getReservas().get(i).getId(), entity.getReservas().get(i).getId());

        }
        for (int i = 0; i < resultEntity.getCompras().size(); i++) {
            Assert.assertEquals(resultEntity.getCompras().get(i).getId(), entity.getCompras().get(i).getId());

        }
        for (int i = 0; i < resultEntity.getPaypal().size(); i++) {
            Assert.assertEquals(resultEntity.getPaypal().get(i).getId(), entity.getPaypal().get(i).getId());

        }
        for (int i = 0; i < resultEntity.getTarjetasCredito().size(); i++) {
            Assert.assertEquals(resultEntity.getTarjetasCredito().get(i).getId(), entity.getTarjetasCredito().get(i).getId());

        }
    }

    /**
     * Prueba para eliminar un Usuario
     *
     *
     */
    @Test
    public void deleteUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioLogic.deleteUsuario(data.get(1).getId());
        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar un Usuario
     *
     *
     */
    @Test
    public void updateUsuarioTest() {
        UsuarioEntity entity = data.get(0);
        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);

        pojoEntity.setId(entity.getId());

        UsuarioLogic.updateUsuario(pojoEntity);

        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), resp.getId());
        Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre());
        Assert.assertEquals(pojoEntity.getUserName(), resp.getUserName());
        Assert.assertEquals(pojoEntity.getEdad(), resp.getEdad());
        Assert.assertEquals(pojoEntity.getGenero(), resp.getGenero());
        Assert.assertEquals(pojoEntity.getCorreo(), resp.getCorreo());

    }
}
