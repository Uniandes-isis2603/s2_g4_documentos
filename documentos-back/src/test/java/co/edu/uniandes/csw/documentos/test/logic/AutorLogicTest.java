/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.AutorLogic;
import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.AutorPersistence;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
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
 * @author Camilojaravila
 */
@RunWith(Arquillian.class)
public class AutorLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AutorLogic autorLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AutorEntity> data = new ArrayList<AutorEntity>();
    
    private List<DocumentoEntity> dataDocumentos = new ArrayList<DocumentoEntity>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AutorEntity.class.getPackage())
                .addPackage(AutorLogic.class.getPackage())
                .addPackage(AutorPersistence.class.getPackage())
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
        em.createQuery("delete from AutorEntity").executeUpdate();
        em.createQuery("delete from DocumentoEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from FotocopiaEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) {
            AutorEntity entity = factory.manufacturePojo(AutorEntity.class);
            entity.setNombre("Autor "+i);
            em.persist(entity);
            entity.setDocumentos(dataDocumentos);
            data.add(entity);
         
        }

    }

     /**
     * Prueba para crear un Autor no valido
     *
     *
     */
    @Test
    public void createAutorTest1() {
        
        AutorEntity newEntity = factory.manufacturePojo(AutorEntity.class);
        newEntity.setNombre("Un Nombre");
        try{
             autorLogic.createAutor(newEntity);
        } catch (BusinessLogicException e) {
            Assert.fail(e.getMessage());
        }

        try{
            newEntity.setNombre("er$$2/34e");
            autorLogic.createAutor(newEntity);
            
            //No deberia llegar hasta aca
            Assert.fail("No se generó el error esperado");            
        } catch (BusinessLogicException e) {
            
        }
        
        try{
            newEntity = factory.manufacturePojo(AutorEntity.class);
            newEntity.setNombre("Otro Nombre");
            AutorEntity existe = data.get(0);
            newEntity.setId(existe.getId());
            autorLogic.createAutor(newEntity);
            
            //No deberia llegar hasta aca
            Assert.fail("No se generó el error esperado");            
        } catch (BusinessLogicException e) {
            
        }
        

    }
    
    /**
     * Prueba para crear un Autor valido
     *
     *
     */
    @Test
    public void createAutorTest2() {
        
        try{
            AutorEntity newEntity = factory.manufacturePojo(AutorEntity.class);
            newEntity.setNombre("Un Nombre");
            AutorEntity result = autorLogic.createAutor(newEntity);          
            AutorEntity entity = em.find(AutorEntity.class, result.getId());
            Assert.assertEquals(result.getId(), entity.getId());
            Assert.assertEquals(result.getNombre(), entity.getNombre());
        } catch (BusinessLogicException e) {
            Assert.fail(e.getMessage());
        }
    }

    /**
     * Prueba para consultar la lista de Autores
     *
     *
     */
    @Test
    public void getAutoresTest() {
        List<AutorEntity> list = autorLogic.getAutores();
        Assert.assertEquals(data.size(), list.size());
        for (AutorEntity entity : list) {
            boolean found = false;
            for (AutorEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para consultar un Autor no valido
     *
     *
     */
    @Test
    public void getAutorTest1() {
        
        Long id = new Long("11111");
        AutorEntity resultEntity = autorLogic.getAutor(id);
        Assert.assertNull(resultEntity);

        
    }
    
    /**
     * Prueba para consultar un Autor valido
     *
     *
     */
    @Test
    public void getAutorTest2() {
        AutorEntity entity = data.get(0);
        AutorEntity resultEntity = autorLogic.getAutor(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getNombre(), resultEntity.getNombre());
        
    }

    /**
     * Prueba para eliminar un Autor valido
     *
     *
     */
    @Test
    public void deleteAutorTest() {
        try{
            AutorEntity entity = data.get(0);
            autorLogic.deleteAutor(entity.getId());
            AutorEntity deleted = em.find(AutorEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } catch (BusinessLogicException e) {
           Assert.fail(e.getMessage());
        }

    }
    
     /**
     * Prueba para actualizar un Autor no valido
     *
     *
     */
    @Test
    public void updateAutorTest1() {

        AutorEntity pojoEntity = factory.manufacturePojo(AutorEntity.class);
        pojoEntity.setNombre("Un Nombre");
        
        try{
            Long id = new Long("11111");
            pojoEntity.setId(id);
            autorLogic.updateAutor(pojoEntity);
            autorLogic.getAutor(pojoEntity.getId());
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
         
        } catch (BusinessLogicException e) {

        }

        try{
            pojoEntity.setNombre("rt4#$%#fdsgds");
            autorLogic.updateAutor(pojoEntity);
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
        } catch (BusinessLogicException e) {
            
        }
        
    }
    

    /**
     * Prueba para actualizar un Autor valido sin Documentos
     *
     *
     */
    @Test
    public void updateAutorTest2() {
        AutorEntity entity = data.get(0);
        AutorEntity pojoEntity = factory.manufacturePojo(AutorEntity.class);
        pojoEntity.setNombre("Juan");

        try{
            pojoEntity.setId(entity.getId());
            autorLogic.updateAutor(pojoEntity);

            AutorEntity resp = em.find(AutorEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre()); 
        }
        catch (BusinessLogicException e){
            Assert.fail(e.getMessage());
        }

    }
    
    /**
     * Prueba para actualizar un Autor valido pero Documentos no validos
     *
     *
     */
    @Test
    public void updateAutorTest3() {
        AutorEntity entity = data.get(0);
        AutorEntity pojoEntity = factory.manufacturePojo(AutorEntity.class);
        pojoEntity.setNombre("Juan");
        pojoEntity.setDocumentos(addDocumentos());

        try{
            pojoEntity.setId(entity.getId());
            autorLogic.updateAutor(pojoEntity);

            AutorEntity resp = em.find(AutorEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getNombre(), resp.getNombre()); 
        }
        catch (BusinessLogicException e){
            Assert.fail(e.getMessage());
        }
        
        pojoEntity.setDocumentos(addDocumentosMalos());
        try{
            pojoEntity.setId(entity.getId());
            autorLogic.updateAutor(pojoEntity);
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
        }
        catch (BusinessLogicException e){
            //Debe llegar acá
        }


    }
    /**
     * Metodo que permite agregar documentos validos
     * @return documentos. Lista de documentos validos, entre Libros y Fotocopias
     */
    private List<DocumentoEntity> addDocumentos() {
        List<DocumentoEntity> documentos = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            LibroEntity entity1 = factory.manufacturePojo(LibroEntity.class);
            FotocopiaEntity entity2 = factory.manufacturePojo(FotocopiaEntity.class);
            entity1.setPrecio(5.4);
            entity1.setCalificacionPromedio(4.3);
            entity1.setIsbn("978-0451524935");
            Date fecha = new GregorianCalendar(2012,02,22).getTime();
            entity1.setFechaPublicacion(fecha);
            entity2.setNroPaginas(i+40);
            entity2.setPrecio(5.4);
            entity2.setProfesor("Profesor " + i);           

            documentos.add(entity1);
            documentos.add(entity2);
        }
        return documentos;
    }

    /**
     * Metodo que retorna Documentos no vlaidos
     * @return documentos. Lista donde los primeros 3 son validos y el resto no son validos
     */
    private List<DocumentoEntity> addDocumentosMalos() {
        List<DocumentoEntity> documentos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if(i<3){
                LibroEntity entity1 = factory.manufacturePojo(LibroEntity.class);
                FotocopiaEntity entity2 = factory.manufacturePojo(FotocopiaEntity.class);
                entity1.setPrecio(5.4);
                entity1.setCalificacionPromedio(4.3);
                entity1.setIsbn("978-0451524935");
                Date fecha = new GregorianCalendar(2012,02,22).getTime();
                entity1.setFechaPublicacion(fecha);
                entity2.setNroPaginas(i+40);
                entity2.setPrecio(5.4);
                entity2.setProfesor("Profesor " + i);

                documentos.add(entity1);
                documentos.add(entity2);
            }
            else{
                LibroEntity entity1 = factory.manufacturePojo(LibroEntity.class);
                FotocopiaEntity entity2 = factory.manufacturePojo(FotocopiaEntity.class);
                entity1.setPrecio(5.4);
                entity1.setCalificacionPromedio(4.3);
                Date fecha = new GregorianCalendar(2012,02,22).getTime();
                entity1.setFechaPublicacion(fecha);
                entity2.setNroPaginas(i-40);
                entity2.setPrecio(5.4);
                entity2.setProfesor("Profesor " + i);

                documentos.add(entity1);
                documentos.add(entity2);
            }

        }
        return documentos;
    }
    
}
