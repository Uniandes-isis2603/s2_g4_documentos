/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.AreaDeConocimientoLogic;
import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.FotocopiaEntity;
import co.edu.uniandes.csw.documentos.entities.LibroEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.AreaDeConocimientoPersistence;
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
public class AreaDeConocimientoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();

    @Inject
    private AreaDeConocimientoLogic areaLogic;

    @PersistenceContext
    private EntityManager em;

    @Inject
    private UserTransaction utx;

    private List<AreaDeConocimientoEntity> data = new ArrayList<>();

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(AreaDeConocimientoEntity.class.getPackage())
                .addPackage(AreaDeConocimientoLogic.class.getPackage())
                .addPackage(AreaDeConocimientoPersistence.class.getPackage())
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
        em.createQuery("delete from AreaDeConocimientoEntity").executeUpdate();
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
            AreaDeConocimientoEntity entity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
            entity.setTipo("Area " + i);
            em.persist(entity);
            data.add(entity);
         
        }

    }
    

     /**
     * Prueba para crear un area no valido
     *
     *
     */
    @Test
    public void createAreaTest1() {
        
        AreaDeConocimientoEntity newEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        newEntity.setTipo("Calculo");
        newEntity.setDocumentos(addDocumentos());
        try
        {
            areaLogic.createArea(newEntity);
        }
        catch(BusinessLogicException e)
        {
            Assert.fail(e.getMessage());
        }
       
        try{     
            newEntity.setTipo("er$$2/34e");
            areaLogic.createArea(newEntity);
            
            //No deberia llegar hasta aca
            Assert.fail("No se generó el error esperado");
        }
        catch(BusinessLogicException e)
        {
            
        }
        
        try{
            AreaDeConocimientoEntity existe = data.get(0);
            newEntity.setId(existe.getId());
            areaLogic.createArea(newEntity);
            
            //No deberia llegar hasta aca
            Assert.fail("No se generó el error esperado");
        }
        catch(BusinessLogicException e)
        {
            
        }
        
    }
    
    /**
     * Prueba para crear un area valido
     *
     *
     */
    @Test
    public void createAreaTest2() {
        
        try{
        
        AreaDeConocimientoEntity newEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        newEntity.setTipo("Calculo");
        AreaDeConocimientoEntity result = areaLogic.createArea(newEntity);
        Assert.assertNotNull(result);
        
        AreaDeConocimientoEntity entity = em.find(AreaDeConocimientoEntity.class, result.getId());
        Assert.assertEquals(result.getId(), entity.getId());
        Assert.assertEquals(result.getTipo(), entity.getTipo());  
        }
        catch(BusinessLogicException e){
            Assert.fail(e.getMessage());
        }
        
    }

    /**
     * Prueba para consultar la lista de areas
     *
     *
     */
    @Test
    public void getAreasTest() {
        List<AreaDeConocimientoEntity> list = areaLogic.getAreas();
        Assert.assertEquals(data.size(), list.size());
        for (AreaDeConocimientoEntity entity : list) {
            boolean found = false;
            for (AreaDeConocimientoEntity storedEntity : data) {
                if (entity.getId().equals(storedEntity.getId())) {
                    found = true;
                }
            }
            Assert.assertTrue(found);
        }
    }

     /**
     * Prueba para consultar una area no valida
     *
     *
     */
    @Test
    public void getAreaTest1() {
        
        Long id = new Long("11111");
        AreaDeConocimientoEntity resultEntity = areaLogic.getArea(id);
        Assert.assertNull(resultEntity);

        
    }
    
    /**
     * Prueba para consultar una area valida
     *
     *
     */
    @Test
    public void getAreaTest2() {
        AreaDeConocimientoEntity entity = data.get(0);
        AreaDeConocimientoEntity resultEntity = areaLogic.getArea(entity.getId());
        Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        
    }

    /**
     * Prueba para eliminar una area valida
     *
     *
     */
    @Test
    public void deleteAreaTest() {
        try{
            AreaDeConocimientoEntity entity = data.get(0);
            areaLogic.deleteArea(entity.getId());
            AreaDeConocimientoEntity deleted = em.find(AreaDeConocimientoEntity.class, entity.getId());
            Assert.assertNull(deleted);
        } 
        catch (BusinessLogicException e) {
            Assert.fail(e.getMessage());
        }

    }
    
     /**
     * Prueba para actualizar una area no valida
     *
     *
     */
    @Test
    public void updateAreaTest1() {

        AreaDeConocimientoEntity pojoEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        pojoEntity.setTipo("Calculo");
        
        try{
            Long id = new Long("11111");
            pojoEntity.setId(id);
            areaLogic.updateArea(pojoEntity);
            areaLogic.getArea(pojoEntity.getId());
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
         
        } catch (BusinessLogicException e) {

        }

        try{
            pojoEntity.setTipo("rt4#$%#fdsgds");
            areaLogic.updateArea(pojoEntity);
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
        } catch (BusinessLogicException e) {
            
        }
        
    }
    

    /**
     * Prueba para actualizar una area valida sin documentos
     *
     *
     */
    @Test
    public void updateAreaTest2() {
        AreaDeConocimientoEntity entity = data.get(0);
        AreaDeConocimientoEntity pojoEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        pojoEntity.setTipo("Calculo");

        try{
            pojoEntity.setId(entity.getId());
            areaLogic.updateArea(pojoEntity);

            AreaDeConocimientoEntity resp = em.find(AreaDeConocimientoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo()); 
        }
        catch (BusinessLogicException e){
            Assert.fail(e.getMessage());
        }

    }

    /**
     * Prueba para actualizar una area valida pero documentos no validos
     *
     *
     */
    @Test
    public void updateAreaTest3() {
        AreaDeConocimientoEntity entity = data.get(0);
        AreaDeConocimientoEntity pojoEntity = factory.manufacturePojo(AreaDeConocimientoEntity.class);
        pojoEntity.setTipo("Calculo");
        pojoEntity.setDocumentos(addDocumentos());

        try{
            pojoEntity.setId(entity.getId());
            areaLogic.updateArea(pojoEntity);

            AreaDeConocimientoEntity resp = em.find(AreaDeConocimientoEntity.class, entity.getId());

            Assert.assertEquals(pojoEntity.getId(), resp.getId());
            Assert.assertEquals(pojoEntity.getTipo(), resp.getTipo()); 
        }
        catch (BusinessLogicException e){
            Assert.fail(e.getMessage());
        }
        
        pojoEntity.setDocumentos(addDocumentosMalos());
        try{
            pojoEntity.setId(entity.getId());
            areaLogic.updateArea(pojoEntity);
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
        }
        catch (BusinessLogicException e){
            //Debe llegar acá
        }

    }
    
    
    /**
     * Test que prueba el retorno de las áreas de conocimiento de manera incorrecta
     */
    @Test
    public void getAreabyTipo1(){
        AreaDeConocimientoEntity entity = data.get(0);
        try{
            AreaDeConocimientoEntity resultEntity = areaLogic.getAreasByTipo("Prb4nd!!").get(0);
            
            //No deberia llegar aca
            Assert.fail("No se generó el error esperado");
        }
      catch(BusinessLogicException e){
          //Debe llegar acá
      }  
        
    }
    
    /**
     * Test que prueba el retorno de las áreas de conocimiento
     */
    @Test
    public void getAreabyTipo2(){
        AreaDeConocimientoEntity entity = data.get(0);
        try{
            AreaDeConocimientoEntity resultEntity = areaLogic.getAreasByTipo(entity.getTipo()).get(0);
            Assert.assertNotNull(resultEntity);
        
        Assert.assertEquals(entity.getId(), resultEntity.getId());
        Assert.assertEquals(entity.getTipo(), resultEntity.getTipo());
        }
      catch(BusinessLogicException e){
          Assert.fail(e.getMessage());
      }  
        
    }
    
       /**
     * Test que prueba el retorno de las áreas de conocimiento vacío
     */
    @Test
    public void getAreabyTipo3(){
        AreaDeConocimientoEntity entity = data.get(0);
        try{
            List<AreaDeConocimientoEntity> resultEntity = areaLogic.getAreasByTipo("Nada");
            Assert.assertEquals(0,resultEntity.size());
        
        }
      catch(BusinessLogicException e){
          Assert.fail(e.getMessage());
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
