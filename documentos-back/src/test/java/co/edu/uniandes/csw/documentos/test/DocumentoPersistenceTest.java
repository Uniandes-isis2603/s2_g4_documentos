/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.test;

import co.edu.uniandes.csw.documentos.entities.AreaDeConocimientoEntity;
import co.edu.uniandes.csw.documentos.entities.AutorEntity;
import co.edu.uniandes.csw.documentos.entities.ComentarioEntity;
import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import co.edu.uniandes.csw.documentos.entities.ImagenEntity;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author Ernesto Viana
 */
@RunWith(Arquillian.class)
public class DocumentoPersistenceTest {
    
    /**
     * Deployment por defecto.
     * @return un wrap que contiene todos los elementos a utilizar en la prueba.
     */
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(DocumentoEntity.class.getPackage())
                .addPackage(DocumentoPersistence.class.getPackage())
                .addPackage(ComentarioEntity.class.getPackage())
                .addPackage(ImagenEntity.class.getPackage())
                .addPackage(AutorEntity.class.getPackage())
                .addPackage(AreaDeConocimientoEntity.class.getPackage())
                .addPackage(CursoEntity.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml","beans.xml");
    }
    
    /**
     * Inyeccion de la dependencia a la clase documentoPersistence cuyos metodos 
     * se van a probar.
     */
    @Inject
    private DocumentoPersistence documentoPersistence;
    
    /**
     * Contexto de persistencia que se va a utilizar para acceder a la Base de datos 
     * por fuera  de los metodos que se estan probando.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * Variable para marcar las transacciones del em anterior cuando se 
     * crean/borran datos para las pruebas.
     */
    @Inject
    UserTransaction utx;
    
    
    /**
     * Constructor por defeco
     */
    public DocumentoPersistenceTest() {
    }
    
    
    /**
     * Configuracion inicial de la prueba.
     */
    @Before
    public void setUp() {
        try {
            utx.begin();
            em.joinTransaction();
            clearData();
            insertData();
            utx.commit();
        } catch (Exception e){
            e.printStackTrace();
            try{
                utx.rollback();
            } catch (Exception e1){
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * Limpia las tablas que estan implicadas en las pruebas.
     */
    private void clearData() {
        em.createQuery("delete from DocumentoEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
        em.createQuery("delete from ImagenEntity").executeUpdate();
        em.createQuery("delete from AutorEntity").executeUpdate();
        em.createQuery("delete from AreaDeConocimientoEntity").executeUpdate();
        em.createQuery("delete from ComentarioEntity").executeUpdate();
    }
    
    /**
     * Lista de documentoentity para probar.
     */
    private List<DocumentoEntity> data = new ArrayList<>();
    
    /**
     * Lista de comentarios para pruebas.
     */
    private List<ComentarioEntity> comentarios = new ArrayList<>();
    
    /**
     * Lista de comentarios para la prueba de crear.
     */
    private List<ComentarioEntity> comentariosCreate = new ArrayList<>();
    
    /**
     * Lista de comentarios para la prueba de actualizar.
     */
    private List<ComentarioEntity> comentariosUpdate = new ArrayList<>();
    
    /**
     * Lista de imagenes para las pruebas.
     */
    private List<ImagenEntity> imagenes = new ArrayList<>();
    
    /**
     * Lista de imagenes para las pruebas de crear.
     */
    private List<ImagenEntity> imagenesCreate = new ArrayList<>();
    
    /**
     * Lista de imagenes para las pruebas de actualizar.
     */
    private List<ImagenEntity> imagenesUpdate = new ArrayList<>();
    
    /**
     * Lista de autores para las pruebas.
     */
    private List<AutorEntity> autores = new ArrayList<>();
    
    /**
     * Lista de autores para la prueba de crear.
     */
    private List<AutorEntity> autoresCreate = new ArrayList<>();
    
    /**
     * Lista de autores para la prueba de actualizar.
     */
    private List<AutorEntity> autoresUpdate = new ArrayList<>();
    
    /**
     * Lista de areas de conocimiento para las pruebas.
     */
    private List<AreaDeConocimientoEntity> areas = new ArrayList<>();
    
    /**
     * Lista de areas de conocimiento  para las pruebas de crear.
     */
    private List<AreaDeConocimientoEntity> areasCreate = new ArrayList<>();
    
    /**
     * Lista de areas de conocimiento para las pruebas de actualizar.
     */
    private List<AreaDeConocimientoEntity> areasUpdate = new ArrayList<>();
    
    /**
     * Lista de cursos para las pruebas.
     */
    private List<CursoEntity> cursos = new ArrayList<>();
    
    /**
     * Lista de cursos para las pruebas de crear.
     */
    private List<CursoEntity> cursosCreate = new ArrayList<>();
    
    /**
     * Lista de cursos para las pruebas de actualizar.
     */
    private List<CursoEntity> cursosUpdate = new ArrayList<>();
    
    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las pruebas.
     */
    private void insertData() {
        PodamFactory factory = new PodamFactoryImpl();
        
        //Creo las relaciones posibles y las agrego tanto a la base de datos como a la lista de pruebas.
        for(int j = 0; j <= 9; j++) {
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            ImagenEntity imagen = factory.manufacturePojo(ImagenEntity.class);
            AutorEntity autor = factory.manufacturePojo(AutorEntity.class);
            AreaDeConocimientoEntity area = factory.manufacturePojo(AreaDeConocimientoEntity.class);
            CursoEntity curso = factory.manufacturePojo(CursoEntity.class);
            
            em.persist(comentario);
            em.persist(imagen);
            em.persist(autor);
            em.persist(area);
            em.persist(curso);
            
            comentarios.add(comentario);
            imagenes.add(imagen);
            autores.add(autor);
            areas.add(area);
            cursos.add(curso);
        }
        
        //Agrego las relaciones al documento.
        for(int i = 0; i < 3; i++) {
            DocumentoEntity entity = factory.manufacturePojo(DocumentoEntity.class);
            
            List<ComentarioEntity> comentariosDocumento = new ArrayList<>();
            List<ImagenEntity> imagenesDocumento = new ArrayList<>();
            List<AutorEntity> autoresDocumento = new ArrayList<>();
            List<AreaDeConocimientoEntity> areasDocumento = new ArrayList<>();
            List<CursoEntity> cursosDocumento = new ArrayList<>();
            
            for(int k = i; k <= 9 ; k = k+3){
            comentariosDocumento.add(comentarios.get(k));
            imagenesDocumento.add(imagenes.get(k));
            autoresDocumento.add(autores.get(k));
            areasDocumento.add(areas.get(k));
            cursosDocumento.add(cursos.get(k));
        }
            //Se las relaciono a el documento y lo persisto todo junto
            entity.setComentarios(comentariosDocumento);
            entity.setImagenes(imagenesDocumento);
            entity.setAutores(autoresDocumento);
            entity.setAreas(areasDocumento);
            entity.setCursos(cursosDocumento);
            
            em.persist(entity);
            data.add(entity);
        }
        //Se pueblan los datos que se van a probar en la prueba de crear.
        //por lo tanto no se agregan a ninguna entidad, solo se persisten y se agregan a las listas.
        for(int l = 0; l < 3; l++){
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            ImagenEntity imagen = factory.manufacturePojo(ImagenEntity.class);
            AutorEntity autor = factory.manufacturePojo(AutorEntity.class);
            AreaDeConocimientoEntity area = factory.manufacturePojo(AreaDeConocimientoEntity.class);
            CursoEntity curso = factory.manufacturePojo(CursoEntity.class);
            
            em.persist(comentario);
            em.persist(imagen);
            em.persist(autor);
            em.persist(area);
            em.persist(curso);
            
            comentariosCreate.add(comentario);
            imagenesCreate.add(imagen);
            autoresCreate.add(autor);
            areasCreate.add(area);
            cursosCreate.add(curso);
        }
        
        //Se pueblan los datos que se van a probar en la prueba de actualizar.
        //por lo tanto no se agregan a ninguna entidad, solo se persisten y se agregan a las listas.
        for(int l = 0; l < 3; l++){
            ComentarioEntity comentario = factory.manufacturePojo(ComentarioEntity.class);
            ImagenEntity imagen = factory.manufacturePojo(ImagenEntity.class);
            AutorEntity autor = factory.manufacturePojo(AutorEntity.class);
            AreaDeConocimientoEntity area = factory.manufacturePojo(AreaDeConocimientoEntity.class);
            CursoEntity curso = factory.manufacturePojo(CursoEntity.class);
            
            em.persist(comentario);
            em.persist(imagen);
            em.persist(autor);
            em.persist(area);
            em.persist(curso);
            
            comentariosUpdate.add(comentario);
            imagenesUpdate.add(imagen);
            autoresUpdate.add(autor);
            areasUpdate.add(area);
            cursosUpdate.add(curso);
        }
    }
    
    /**
     * Prueba para crear un documento.
     */
   @Test
   public void createDocumentoTest(){
      
       PodamFactory factory  = new PodamFactoryImpl();
       DocumentoEntity newEntity = factory.manufacturePojo(DocumentoEntity.class);
       
       
       //Agrego al documento entidades relacionadas a el que existen en la base
       //de datos pero que no es parte de ningun documento.
       newEntity.setComentarios(comentariosCreate);
       newEntity.setImagenes(imagenesCreate);
       newEntity.setAutores(autoresCreate);
       newEntity.setAreas(areasCreate);
       newEntity.setCursos(cursosCreate);
       DocumentoEntity result = documentoPersistence.create(newEntity);
       
       Assert.assertNotNull(result);
       DocumentoEntity entity = em.find(DocumentoEntity.class, result.getId());
       Assert.assertEquals(newEntity.getNombre(),entity.getNombre());
       
       //Compruebo si al persistir si quedan como parte del documento.
       for(int j = 0; j < 3; j++) {
           boolean foundComentario  = false;
           boolean foundImagen = false;
           boolean foundAutor = false;
           boolean foundArea = false;
           boolean foundCurso = false;
           for(int k = 0; k < 3; k++){
               if(newEntity.getComentarios().get(j).getId().equals(entity.getComentarios().get(k).getId())) {
                   foundComentario = true;
               }
               if(newEntity.getImagenes().get(j).getId().equals(entity.getImagenes().get(k).getId())) {
                   foundImagen = true;
               }
               if(newEntity.getAutores().get(j).getId().equals(entity.getAutores().get(k).getId())) {
                   foundAutor = true;
               }
               if(newEntity.getAreas().get(j).getId().equals(entity.getAreas().get(k).getId())) {
                   foundArea = true;
               }
               if(newEntity.getCursos().get(j).getId().equals(entity.getCursos().get(k).getId())) {
                   foundCurso = true;
               }
           }
           Assert.assertTrue(foundComentario);
           Assert.assertTrue(foundImagen);
           Assert.assertTrue(foundAutor);
           Assert.assertTrue(foundArea);
           Assert.assertTrue(foundCurso);
       }
       
   }
   
   /**
    * Prueba para consultar la lista de Documentos.
    */
   @Test
   public void getDocumentosTest() {
       
       List<DocumentoEntity> list = documentoPersistence.findAll();
       Assert.assertEquals(data.size(), list.size());
      
       //Compruebo que obtenga  a todos los documentos.
       for(DocumentoEntity ent : list){
           boolean found = false;
           for(DocumentoEntity entity: data) {
               if(ent.getId().equals(entity.getId())){
                   found = true;
               }
           }
          
           //Compruebo que obtengo los atributos de relacion que son segun la lista de pruebas
           for(ComentarioEntity comentario : ent.getComentarios()){
               boolean foundComentario = false;
               for(ComentarioEntity comentarioData : comentarios) {
                   if(comentario.getId().equals(comentarioData.getId())){
                       foundComentario = true;
                   }
               }
               Assert.assertTrue(foundComentario);
           }
           for(ImagenEntity imagen : ent.getImagenes()){
               boolean foundImagen = false;
               for(ImagenEntity imagenData : imagenes) {
                   if(imagen.getId().equals(imagenData.getId())) {
                       foundImagen = true;
                   }
               }
               Assert.assertTrue(foundImagen);
           }
           for(AutorEntity autor : ent.getAutores()) {
               boolean foundAutor = false;
               for(AutorEntity autorData : autores) {
                   if(autor.getId().equals(autorData.getId())){
                       foundAutor = true;
                   }
               }
               Assert.assertTrue(foundAutor);
           }
           for(AreaDeConocimientoEntity area : ent.getAreas()) {
               boolean foundArea = false;
               for(AreaDeConocimientoEntity areaData : areas) {
                   if(area.getId().equals(areaData.getId())){
                       foundArea = true;
                   }
               }
               Assert.assertTrue(foundArea);
           }
           for(CursoEntity curso : ent.getCursos()) {
               boolean foundCurso = false;
               for(CursoEntity cursoData : cursos) {
                   if(curso.getId().equals(cursoData.getId())){
                       foundCurso = true;
                   }
               }
               Assert.assertTrue(foundCurso);
           }
           
           Assert.assertTrue(found);
       }
               
    }
   
   /**
    * Prueba para consultar un documento.
    */
   @Test
   public void getDocumentoTest(){
       
       //Compruebo de que segun el id consigo los documentos.
       for(int i = 0; i < data.size(); i++){
           DocumentoEntity entity = data.get(i);
           DocumentoEntity newEntity = documentoPersistence.find(entity.getId());
           Assert.assertNotNull(newEntity);
           Assert.assertEquals(entity.getNombre(), newEntity.getNombre());
       
           //Compruebo por cada relacion que en un documento especifico exista en la lista de pruebas
           for(ComentarioEntity comentario : entity.getComentarios()){
               boolean foundComentario = false;
               for(ComentarioEntity comentarioPrueba : comentarios) {
                   if(comentario.getComentario().equals(comentarioPrueba.getComentario())){
                       foundComentario = true;
                   }
               }
               Assert.assertTrue(foundComentario);
           }
           for(ImagenEntity imagen : entity.getImagenes()) {
               boolean foundImagen = false;
               for(ImagenEntity imagenPrueba : imagenes) {
                   if(imagen.getNombre().equals(imagenPrueba.getNombre())){
                       foundImagen = true;
                   }
               }
               Assert.assertTrue(foundImagen);
           }
           for(AutorEntity autor : entity.getAutores()) {
               boolean foundAutor = false;
               for(AutorEntity autorPrueba : autores) {
                   if(autor.getNombre().equals(autorPrueba.getNombre())) {
                       foundAutor = true;
                   }
               }
               Assert.assertTrue(foundAutor);
           }
           for(AreaDeConocimientoEntity area : entity.getAreas()) {
               boolean foundArea = false;
               for(AreaDeConocimientoEntity areaPrueba : areas) {
                   if(area.getTipo().equals(areaPrueba.getTipo())) {
                       foundArea = true;
                   }
               }
               Assert.assertTrue(foundArea);
           }
           for(CursoEntity curso : entity.getCursos()) {
               boolean foundCurso = false;
               for(CursoEntity cursoPrueba : cursos) {
                   if(curso.getNombre().equals(cursoPrueba.getNombre())) {
                       foundCurso = true;
                   }
               }
               Assert.assertTrue(foundCurso);
           }
            
       }
       
   }
   
   
   /**
    * Prueba para actualizar documento.
    */
   @Test
   public void UpdateDocumentoTest() {
       //se obtiene un documento en especifico
       DocumentoEntity entity = data.get(0);
       PodamFactory factory = new PodamFactoryImpl();
       
       //se crea otro documento con podam
       DocumentoEntity newEntity = factory.manufacturePojo(DocumentoEntity.class);
       
       //se asegura que el documento tenga un id que ya existe, y le agrega las relaciones.
       newEntity.setId(entity.getId());
       newEntity.setComentarios(comentariosUpdate);
       newEntity.setImagenes(imagenesUpdate);
       newEntity.setAutores(autoresUpdate);
       newEntity.setAreas(areasUpdate);
       newEntity.setCursos(cursosUpdate);
       
       documentoPersistence.update(newEntity);
       
       //Actualiza el documento y prueba que los cambios se hayan realizado.
       DocumentoEntity resp = em.find(DocumentoEntity.class, entity.getId());
       Assert.assertEquals(newEntity.getNombre(), resp.getNombre());
       
       //prueba que los cambios se realizaron en las relaciones del documento.
       for(ComentarioEntity comentario : resp.getComentarios()){
           boolean foundComentario = false;
           for(ComentarioEntity comentarioPrueba: comentariosUpdate){
               if(comentario.getComentario().equals(comentarioPrueba.getComentario()))
               {
                   foundComentario = true;
               }
           }
           Assert.assertTrue(foundComentario);
       }
       for(ImagenEntity imagen : resp.getImagenes()){
           boolean foundImagen = false;
           for(ImagenEntity imagenPrueba : imagenesUpdate) {
               if(imagen.getNombre().equals(imagenPrueba.getNombre())){
                   foundImagen = true;
               }
           }
           Assert.assertTrue(foundImagen);
       }
       for(AutorEntity autor : resp.getAutores()) {
           boolean foundAutor = false;
           for(AutorEntity autorPrueba : autoresUpdate) {
               if(autor.getNombre().equals(autorPrueba.getNombre())){
                   foundAutor =true;
               }
           }
           Assert.assertTrue(foundAutor);
       }
       for(AreaDeConocimientoEntity area : resp.getAreas()){ 
           boolean foundArea = false;
           for(AreaDeConocimientoEntity areaPrueba : areasUpdate) {
               if(area.getTipo().equals(areaPrueba.getTipo())){
                   foundArea = true;
               }
           }
           Assert.assertTrue(foundArea);
       }
       for(CursoEntity curso : resp.getCursos()) {
           boolean foundCurso = false;
           for(CursoEntity cursoPrueba : cursosUpdate) {
               if(curso.getNombre().equals(cursoPrueba.getNombre())) {
                   foundCurso =true;
               }
           }
           Assert.assertTrue(foundCurso);
       }
   }
   
   /**
    * Prueba para eliminar un documento.
    */
   @Test
   public void deleteDocumentoTest() {
       //Obtengo un documento.
       DocumentoEntity entity = data.get(0);
       //lo elimino.
       documentoPersistence.delete(entity.getId());
       //Pruebo que no exista.
       DocumentoEntity deleted = em.find(DocumentoEntity.class, entity.getId());
       Assert.assertNull(deleted);
       
       //Pruebo que si la relacion es de composicion se debe eliminar todo rastro de la base dedatos
       //Si no lo es, debe seguir existiendo.
       for(ComentarioEntity comentarioPrueba : entity.getComentarios())
       {
           ComentarioEntity deletedComentario = em.find(ComentarioEntity.class, comentarioPrueba.getId());
           Assert.assertNull(deletedComentario);
       }
       for(ImagenEntity imagenPrueba : entity.getImagenes()) {
           ImagenEntity deletedImagen = em.find(ImagenEntity.class,imagenPrueba.getId());
           Assert.assertNull(deletedImagen);
       }
       for(AutorEntity autorPrueba : entity.getAutores()) {
           AutorEntity autor = em.find(AutorEntity.class,autorPrueba.getId());
           Assert.assertNotNull(autor);
       }
       for(AreaDeConocimientoEntity areaPrueba : entity.getAreas()) {
           AreaDeConocimientoEntity area = em.find(AreaDeConocimientoEntity.class,areaPrueba.getId());
           Assert.assertNotNull(area);
       }
       for(CursoEntity cursoPrueba : entity.getCursos()) {
           CursoEntity curso = em.find(CursoEntity.class,cursoPrueba.getId());
           Assert.assertNotNull(curso);
       }
       
   }
}
