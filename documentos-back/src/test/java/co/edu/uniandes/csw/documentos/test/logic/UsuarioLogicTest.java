///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.edu.uniandes.csw.documentos.test.logic;
//
//import co.edu.uniandes.csw.documentos.ejb.UsuarioLogic;
//import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
//import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
//import co.edu.uniandes.csw.documentos.persistence.UsuarioPersistence;
//import java.util.ArrayList;
//import java.util.List;
//import javax.inject.Inject;
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.UserTransaction;
//import org.jboss.arquillian.container.test.api.Deployment;
//import org.jboss.arquillian.junit.Arquillian;
//import org.jboss.shrinkwrap.api.ShrinkWrap;
//import org.jboss.shrinkwrap.api.spec.JavaArchive;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import uk.co.jemos.podam.api.PodamFactory;
//import uk.co.jemos.podam.api.PodamFactoryImpl;
//
///**
// *
// * @author f.marroquin10
// */
//@RunWith(Arquillian.class)
//public class UsuarioLogicTest {
//    
//    private PodamFactory factory = new PodamFactoryImpl();
//
//      @Inject
//    private UsuarioLogic UsuarioLogic;
//
//    
//    @PersistenceContext
//    private EntityManager em;
//
// 
//    @Inject
//    private UserTransaction utx;
//
//  
//    private List<UsuarioEntity> data = new ArrayList<UsuarioEntity>();
//
//    private List<BookEntity> dataBook = new ArrayList<BookEntity>();
//
//    private List<EditorialEntity> editorialData = new ArrayList();
//
//    
//    @Deployment
//    public static JavaArchive createDeployment() {
//        return ShrinkWrap.create(JavaArchive.class)
//                .addPackage(UsuarioEntity.class.getPackage())
//                .addPackage(UsuarioLogic.class.getPackage())
//                .addPackage(UsuarioPersistence.class.getPackage())
//                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
//                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
//    }
//
//    /**
//     * Configuración inicial de la prueba.
//     *
//     * 
//     */
//    @Before
//    public void configTest() {
//        try {
//            utx.begin();
//            clearData();
//            insertData();
//            utx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            try {
//                utx.rollback();
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * Limpia las tablas que están implicadas en la prueba.
//     *
//     * 
//     */
//    private void clearData() {
//        em.createQuery("delete from UsuarioEntity").executeUpdate();
//        em.createQuery("delete from BookEntity").executeUpdate();
//        em.createQuery("delete from EditorialEntity").executeUpdate();
//    }
//
//    /**
//     * Inserta los datos iniciales para el correcto funcionamiento de las
//     * pruebas.
//     *
//     * 
//     */
//    private void insertData() {
//        for (int i = 0; i < 3; i++) {
//            EditorialEntity editorial = factory.manufacturePojo(EditorialEntity.class);
//            em.persist(editorial);
//            editorialData.add(editorial);
//        }
//        
//        for (int i = 0; i < 3; i++) {
//            BookEntity entity = factory.manufacturePojo(BookEntity.class);
//            entity.setEditorial(editorialData.get(i));
//
//            em.persist(entity);
//            dataBook.add(entity);
//        }
//        
//        for (int i = 0; i < 3; i++) {
//            UsuarioEntity entity = factory.manufacturePojo(UsuarioEntity.class);
//            
//            entity.setBook(dataBook.get(1));
//            
//            em.persist(entity);
//            data.add(entity);
//        }
//    }
//
//    /**
//     * Prueba para crear un Usuario
//     *
//     * 
//     */
//    @Test
//    public void createUsuarioTest() {
//        UsuarioEntity newEntity = factory.manufacturePojo(UsuarioEntity.class);
//        UsuarioEntity result = UsuarioLogic.createUsuario(data.get(0).getBook().getId(), newEntity);
//        Assert.assertNotNull(result);
//        UsuarioEntity entity = em.find(UsuarioEntity.class, result.getId());
//        Assert.assertEquals(newEntity.getId(), entity.getId());
//        Assert.assertEquals(newEntity.getName(), entity.getName());
//        Assert.assertEquals(newEntity.getSource(), entity.getSource());
//        Assert.assertEquals(newEntity.getDescription(), entity.getDescription());
//    }
//
//    /**
//     * Prueba para consultar la lista de Usuarios
//     *
//     * 
//     */
//    
//    @Test
//    public void getUsuariosTest() throws BusinessLogicException {
//        List<UsuarioEntity> list = UsuarioLogic.getUsuarios(dataBook.get(1).getId());        
//        Assert.assertEquals(data.size(), list.size());
//        for (UsuarioEntity entity : list) {
//            boolean found = false;
//            for (UsuarioEntity storedEntity : data) {
//                if (entity.getId().equals(storedEntity.getId())) {
//                    found = true;
//                }
//            }
//            Assert.assertTrue(found);
//        }
//    }
//     
//    /**
//     * Prueba para consultar un Usuario
//     *
//     * 
//     */
//    
//    @Test
//    public void getUsuarioTest() {
//        UsuarioEntity entity = data.get(0);
//        UsuarioEntity resultEntity = UsuarioLogic.getUsuario(dataBook.get(1).getId(), entity.getId());
//        Assert.assertNotNull(resultEntity);
//        Assert.assertEquals(entity.getId(), resultEntity.getId());
//        Assert.assertEquals(entity.getName(), resultEntity.getName());
//        Assert.assertEquals(entity.getSource(), resultEntity.getSource());
//        Assert.assertEquals(entity.getDescription(), resultEntity.getDescription());
//    }
//
//    /**
//     * Prueba para eliminar un Usuario
//     *
//     * 
//     */
// 
//    @Test
//    public void deleteUsuarioTest() {
//        UsuarioEntity entity = data.get(0);
//        UsuarioLogic.deleteUsuario(dataBook.get(1).getId(), entity.getId());
//        UsuarioEntity deleted = em.find(UsuarioEntity.class, entity.getId());
//        Assert.assertNull(deleted);
//    }
//
//    /**
//     * Prueba para actualizar un Usuario
//     *
//     * 
//     */
// @Test
//    public void updateUsuarioTest() {
//        UsuarioEntity entity = data.get(0);
//        UsuarioEntity pojoEntity = factory.manufacturePojo(UsuarioEntity.class);
//
//        pojoEntity.setId(entity.getId());
//
//        UsuarioLogic.updateUsuario(dataBook.get(1).getId(), pojoEntity);
//
//        UsuarioEntity resp = em.find(UsuarioEntity.class, entity.getId());
//
//        Assert.assertEquals(pojoEntity.getId(), resp.getId());
//        Assert.assertEquals(pojoEntity.getName(), resp.getName());
//        Assert.assertEquals(pojoEntity.getSource(), resp.getSource());
//        Assert.assertEquals(pojoEntity.getDescription(), resp.getDescription());
//    }
//}
