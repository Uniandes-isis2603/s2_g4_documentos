/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.DocumentoLogic;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.persistence.DocumentoPersistence;
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
public class DocumentoLogicTest {
    
    /**
     * Deployment
     * @return deployment.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DocumentoLogic.class.getPackage())
                .addPackage(DocumentoEntity.class.getPackage())
                .addPackage(DocumentoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml","persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase documentoLogic
     * cuyos metodos se van a probar.
     */
    @Inject
    private DocumentoLogic documentoLogic;
    
    /**
     * Inyeccion de la dependencia a la clase de documentoPersistence
     * cuyos metodos van a ser de uso para probar la logic.
     */
    @Inject
    private DocumentoPersistence documentoPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la base de datos
     * por fuera de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;
    
    /**
     * Variable para marcar las transacciones del em anterior.
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
        } catch(Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch(Exception e1) {
                e1.printStackTrace();
            }
        }
    } 
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from FotocopiaEntity").executeUpdate();
        em.createQuery("delete from LibroEntity").executeUpdate();
        em.createQuery("delete from DocumentoEntity").executeUpdate();
    }
    
    /**
     * Lista de ddatos que se van a persistir.
     */
    private List<DocumentoEntity> data = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        for(Integer i = 0; i< 3; i++){
            DocumentoEntity entity = factory.manufacturePojo(DocumentoEntity.class);
            entity.setId(i.longValue()+1);
            em.persist(entity);
            data.add(i,entity);
        }
    }
    
    /**
     * Prueba para consultar la lista de documentos.
     */
    @Test
    public void getDocumentosTest() {
        List<DocumentoEntity> list = documentoLogic.getDocumentos();
        Assert.assertFalse(list.isEmpty());
        
        for(DocumentoEntity ent: list){
            boolean found = false;
            for(DocumentoEntity entity : data) {
                if(ent.getId().equals(entity.getId())){
                    found  =true;
                }
            }
            Assert.assertTrue(found);
        }
    }
    
    /**
     * Prueba para consultar un documento.
     */
    @Test
    public void getDocumentoTest() {
        DocumentoEntity entity = data.get(0);
        DocumentoEntity newEntity = documentoLogic.getDocumento(entity.getId());
        Assert.assertNotNull(newEntity);
        Assert.assertEquals(entity.getNombre(),newEntity.getNombre());
    }
}
