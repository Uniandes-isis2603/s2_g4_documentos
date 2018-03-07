package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.DeseadoLogic;
import co.edu.uniandes.csw.documentos.ejb.DeseadoLogic;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.DeseadoEntity;
import co.edu.uniandes.csw.documentos.persistence.DeseadoPersistence;
import co.edu.uniandes.csw.documentos.persistence.DeseadoPersistence;
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author f.marroquin10
 */
@RunWith(Arquillian.class)
public class DeseadoLogicTest {

    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private DeseadoLogic DeseadoLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<DeseadoEntity> data = new ArrayList<DeseadoEntity>();
    private List<DocumentoEntity> documentoData = new ArrayList<DocumentoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DeseadoEntity.class.getPackage())
                .addPackage(DocumentoEntity.class.getPackage())
                .addPackage(DeseadoLogic.class.getPackage())
                .addPackage(DeseadoPersistence.class.getPackage())
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
        em.createQuery("delete from DeseadoEntity").executeUpdate();
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
            DeseadoEntity entity = factory.manufacturePojo(DeseadoEntity.class);
            if (entity.getCantidad() < 0) {
                entity.setCantidad(entity.getCantidad() * (-1));
            }
            em.persist(entity);
            data.add(entity);

        }

    }

    /**
     * Prueba para crear una Deseado no valida
     *
     *
     */
    @Test
    public void createDeseadoTest1() throws ParseException {

        DeseadoEntity newEntity = factory.manufacturePojo(DeseadoEntity.class);
        DeseadoEntity result = DeseadoLogic.createDeseado(newEntity);
        Assert.assertNotNull(result);

        newEntity.setNombre("%$#hs_ñ");
        result = DeseadoLogic.createDeseado(newEntity);
        Assert.assertNull(result);

        newEntity = factory.manufacturePojo(DeseadoEntity.class);
        
        newEntity.setCantidad(-4);
        result = DeseadoLogic.createDeseado(newEntity);
        Assert.assertNull(result);

    }

    /**
     * Prueba para crear una Deseado valida
     *
     *
     */
    @Test
    public void createDeseadoTest2() {

        DeseadoEntity newEntity = factory.manufacturePojo(DeseadoEntity.class);
        DeseadoEntity result = DeseadoLogic.createDeseado(newEntity);
        Assert.assertNotNull(result);

        DeseadoEntity entity = em.find(DeseadoEntity.class, result.getId());
        Assert.assertEquals(newEntity.getId(), entity.getId());
        Assert.assertEquals(newEntity.getCantidad(), entity.getCantidad());
        Assert.assertEquals(newEntity.getNombre(), entity.getNombre());

    }

    /**
     * Prueba para consultar la lista de Deseadoes
     *
     *
     */
    @Test
    public void getDeseadoesTest() {
        List<DeseadoEntity> list = DeseadoLogic.getDeseadoes();
        Assert.assertEquals(data.size(), list.size());
        for (DeseadoEntity entity : list) {
            boolean found = false;
            for (DeseadoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

    /**
     * Prueba para consultar una Deseado no valida
     *
     *
     */
    @Test
    public void getDeseadoTest1() {

        Long id = new Long("1");
        DeseadoEntity resultEntity = DeseadoLogic.getDeseado(id);
        Assert.assertNull(resultEntity);

    }

    /**
     * Prueba para consultar una Deseado valida
     *
     *
     */
    @Test
    public void getDeseadoTest2() {
        DeseadoEntity entity = data.get(0);
        DeseadoEntity resultEntity = DeseadoLogic.getDeseado(entity.getId());
        Assert.assertNotNull(resultEntity);

        Assert.assertEquals(resultEntity.getId(), entity.getId());
        Assert.assertEquals(resultEntity.getCantidad(), entity.getCantidad());
        Assert.assertEquals(resultEntity.getNombre(), entity.getNombre());

    }

    /**
     * Prueba para eliminar una Deseado valido
     *
     *
     */
    @Test
    public void deleteDeseadoTest() {
        DeseadoEntity entity = data.get(0);
        DeseadoLogic.deleteDeseado(entity.getId());
        DeseadoEntity deleted = em.find(DeseadoEntity.class, entity.getId());
        Assert.assertNull(deleted);
    }

    /**
     * Prueba para actualizar una Deseado no valida
     *
     *
     */
    @Test
    public void updateDeseadoTest1() {

        DeseadoEntity pojoEntity = factory.manufacturePojo(DeseadoEntity.class);

        Long id = new Long("1");
        pojoEntity.setId(id);

        DeseadoLogic.updateDeseado(pojoEntity);
        DeseadoEntity encontrar = DeseadoLogic.getDeseado(pojoEntity.getId());
        Assert.assertNull(encontrar);

        pojoEntity.setId(data.get(0).getId());
        pojoEntity.setCantidad(-232134);
        DeseadoLogic.updateDeseado(pojoEntity);
        encontrar = DeseadoLogic.getDeseado(pojoEntity.getId());
        Assert.assertNull(encontrar);

    }

    /**
     * Prueba para actualizar una Deseado valida
     *
     *
     */
    @Test
    public void updateDeseadoTest2() {
        DeseadoEntity entity = data.get(0);
        DeseadoEntity pojoEntity = factory.manufacturePojo(DeseadoEntity.class);

        pojoEntity.setId(entity.getId());
        DeseadoLogic.updateDeseado(pojoEntity);

        DeseadoEntity resp = em.find(DeseadoEntity.class, entity.getId());

        Assert.assertEquals(pojoEntity.getId(), entity.getId());
        Assert.assertEquals(pojoEntity.getCantidad(), entity.getCantidad());
        Assert.assertEquals(pojoEntity.getNombre(), entity.getNombre());
    }
}