/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.documentos.dtos;

import co.edu.uniandes.csw.documentos.entities.CursoEntity;
import co.edu.uniandes.csw.documentos.entities.DocumentoEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Clase que extiende de {@link CursoDTO} para manejar la transformacion entre
 * los objetos JSON y las Entidades de la base de datos.Para conocer el
 * contenido del documento vaya a la documentacion de {@link CursoDTO}
 * 
 * @author n.sotelo
 */
public class CursoDetailedDTO  extends CursoDTO{
    
    List<DocumentoDTO> bibliografiaDelCurso;
/**
 * 
 * @return la lista que representa la bibliografia del curso 
 */
    public List<DocumentoDTO> getBibliografiaDelCurso() {
        return bibliografiaDelCurso;
    }
 /**
  * 
  * @param bibliografiaDelCurso nueva lista que representa la bibliografia del curso. 
  */
    public void setBibliografiaDelCurso(List<DocumentoDTO> bibliografiaDelCurso) {
        this.bibliografiaDelCurso = bibliografiaDelCurso;
    }
  /**
   * Constructor por defecto 
   */
    public CursoDetailedDTO() 
    {
        
    }
     public CursoDetailedDTO(CursoEntity entidad)
    {
       
        super(entidad);

       if(entidad!=null )
       {
           List<DocumentoDTO> lista = new ArrayList<>();
           for(DocumentoEntity anadido: entidad.getBibliografiaDelCurso())
           {
               lista.add(new DocumentoDTO(anadido));
           }
           this.setBibliografiaDelCurso(lista);
       }
     
    }
    public CursoEntity toEntity()
    {
       CursoEntity rta= super.toEntity();
  if (bibliografiaDelCurso != null) {
            List<DocumentoEntity> listaEntity = new ArrayList<>();
            for (DocumentoDTO dtoCurso : bibliografiaDelCurso) 
            {
                listaEntity.add(dtoCurso.toEntity());
            }
            rta.setBibliografiaDelCurso(listaEntity);
        }
       return rta;
    }
    
}
