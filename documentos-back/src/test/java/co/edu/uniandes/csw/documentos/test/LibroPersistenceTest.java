/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.EditorialEntity;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.persistence.LibroPersistence;
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
 * @author Ernesto Viana
 */
@RunWith(Arquillian.class)
public class LibroPersistenceTest {
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(LibroEntity.class.getPackage())
                .addPackage(LibroPersistence.class.getPackage())
                .addPackage(EditorialEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase de libroPersistence cuyos metodos
     * se van a probar.
     */
    @Inject
    private LibroPersistence libroPersistence;
    
    /**
     * Contexto de persitencia que se va a utilizar para acceder a la Base de datos
     * por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior cuando
     * se crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp() {
        try{
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from EditorialEntity").executeUpdate();
    }
    
    /**
     * Lista de libros que se van a probar.
     */
    private List<LibroEntity> data = new ArrayList<>();
    
    /**
     * Lista de editoriales que se van a probar par el libro.
     */
    private List<EditorialEntity> editoriales = new ArrayList<>();
    
    /**
     * Editorial que se va a probrar en el metodo create.
     */
    private EditorialEntity editorialCreate = new EditorialEntity();
    
    /**
     * Editorial que se va a probar en el metodo update.
     */
    private EditorialEntity editorialUpdate = new EditorialEntity();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(int i = 0; i < 3; i++) {
            LibroEntity entity = factory.manufacturePojo(LibroEntity.class);
            EditorialEntity editorial = factory.manufacturePojo(EditorialEntity.class);
            
            entity.setEditorial(editorial);
            
            em.persist(entity);
            em.persist(editorial);
            
            data.add(entity);
            editoriales.add(editorial);
            
            //Se crean nuevas editoriales en la base de datos, pero no al libro.
            //Que se utilizaran en los metodos create y update.
            editorialCreate = factory.manufacturePojo(EditorialEntity.class);
            editorialUpdate = factory.manufacturePojo(EditorialEntity.class);
            em.persist(editorialCreate);
            em.persist(editorialUpdate);
        }
        
    }
    
    /**
     * Prueba para crear un libro.
     */
    @Test
    public void createLibroTest() {
        
        // Se crea una entidad documento
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        
        //Se le agrega una editorial que ya existe.
        newEntity.setEditorial(editorialCreate);
        LibroEntity result = libroPersistence.create(newEntity);
        //Se prueba que no haya error creando el libro.
        Assert.assertNotNull(result);
 
        LibroEntity entity = em.find(LibroEntity.class,result.getId());
        
        //Se prueba que el libro tiene la editorial correcta.
        Assert.assertEquals(newEntity.getNombre(),entity.getNombre());
        Assert.assertEquals(newEntity.getEditorial().getNombre(), entity.getEditorial().getNombre());
    }
    
    /**
     * Prueba para consultar la lista de Libros.
     */
    @Test
    public void getLibrosTest() {
        
        List<LibroEntity> list = libroPersistence.findAll();
        Assert.assertEquals(data.size(), list.size());
        
        for(LibroEntity ent : list) {
            boolean found = false;
            boolean foundEditorial = false;
            for(LibroEntity entity: data) {
                if(ent.getId().equals(entity.getId())){
                    found = true;
                }
                if(ent.getEditorial().getNombre().equals(entity.getEditorial().getNombre())){
                    foundEditorial = true;
                }
            }
            Assert.assertTrue(found);
            Assert.assertTrue(foundEditorial);
        }
    }
    
    /**
     * Prueba para consultar un documento.
     */
    @Test
    public void getLibroTest(){
        
        LibroEntity entity = data.get(0);
        LibroEntity newEntity = libroPersistence.find(entity.getId());
        Assert.assertNotNull(newEntity);
        
        //Se prueba que si existe un libro y su editorial.
        Assert.assertEquals(entity.getIsbn(), newEntity.getIsbn());
        Assert.assertEquals(entity.getEditorial().getNombre(), newEntity.getEditorial().getNombre());
    }
    
    /**
     * Prueba para consultar lista de libros por nombre.
     */
    @Test
    public void getLibroByNameTest(){
        
        LibroEntity entity = data.get(0);
        List<LibroEntity> newEntity = libroPersistence.findByName(entity.getNombre());
        
        for(LibroEntity ent : newEntity) {
            boolean found = false;
            boolean foundEditorial = false;
            if(ent.getNombre().equals(entity.getNombre())){
                found = true;
            }
            if(ent.getEditorial().getNombre().equals(entity.getEditorial().getNombre())){
                foundEditorial =true;
            }
            
            Assert.assertTrue(found);
            Assert.assertTrue(foundEditorial);
        }
    }
    
    /**
     * Prueba para actualizar libro.
     */
    @Test
    public void UpdateLibroTest() {
        LibroEntity entity = data.get(0);
        PodamFactory factory = new PodamFactoryImpl();
        LibroEntity newEntity = factory.manufacturePojo(LibroEntity.class);
        
        //Le agrego el id para que sea igual, y una editorial diferente.
        newEntity.setId(entity.getId());
        newEntity.setEditorial(editorialUpdate);
        
        libroPersistence.update(newEntity);
        
        LibroEntity resp = em.find(LibroEntity.class, entity.getId());
        
        Assert.assertEquals(newEntity.getNombre(),resp.getNombre());
        Assert.assertEquals(newEntity.getEditorial().getNombre(), resp.getEditorial().getNombre());
    }
    
    /**
     * Prueba para eliminar libro.
     */
    @Test
    public void deleteLibroTest() {
        LibroEntity entity = data.get(0);
        libroPersistence.delete(entity.getId());
        LibroEntity deleted = em.find(LibroEntity.class, entity.getId());
        Assert.assertNull(deleted);
        
        //Se comprueba que la editorial asociada no se elimino.
        EditorialEntity editorial = entity.getEditorial();
        EditorialEntity editorialPrueba = em.find(EditorialEntity.class,entity.getEditorial().getId());
        Assert.assertNotNull(editorialPrueba);
    }
}
