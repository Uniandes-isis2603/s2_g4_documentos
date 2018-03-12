package co.edu.uniandes.csw.documentos.test.logic;

import co.edu.uniandes.csw.documentos.ejb.TarjetaDeCreditoLogic;
import co.edu.uniandes.csw.documentos.entities.TarjetaDeCreditoEntity;
import co.edu.uniandes.csw.documentos.entities.UsuarioEntity;
import co.edu.uniandes.csw.documentos.exceptions.BusinessLogicException;
import co.edu.uniandes.csw.documentos.persistence.TarjetaDeCreditoPersistence;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;
import org.junit.runner.RunWith;
import org.jboss.arquillian.junit.Arquillian;

/**
 *
 * @author g.ospinaa
 */
@RunWith(Arquillian.class)
public class TarjetaDeCreditoLogicTest {
    
    private PodamFactory factory = new PodamFactoryImpl();
    
    @Inject
    private TarjetaDeCreditoLogic TDCLogic;
    
    @PersistenceContext
    private EntityManager em;
    
    @Inject
    private UserTransaction utx;
    
    private List<TarjetaDeCreditoEntity> data = new ArrayList<TarjetaDeCreditoEntity>();
    
    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addPackage(TarjetaDeCreditoEntity.class.getPackage())
                .addPackage(TarjetaDeCreditoLogic.class.getPackage())
                .addPackage(TarjetaDeCreditoPersistence.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
    }
    
    /**
     * Configuracion inicial de la prueba.
     * 
     * 
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
        em.createQuery("delete from TarjetaDeCreditoEntity").executeUpdate();
    }

    /**
     * Inserta los datos iniciales para el correcto funcionamiento de las
     * pruebas.
     *
     *
     */
    private void insertData() {

        for (int i = 0; i < 3; i++) 
        {
            TarjetaDeCreditoEntity entity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
            
            em.persist(entity);
            data.add(entity);
        }
    }
    
    
    private TarjetaDeCreditoEntity crearEntity()
    {
        TarjetaDeCreditoEntity entity = new TarjetaDeCreditoEntity();
        entity.setNombreEnLaTarjeta("GREGORIO");
        entity.setNroDeLaTarjeta("4234567890123456");
        entity.setTipoDeTarjeta("Visa");
        entity.setNumeroDeSeguridad(123);
      
        return entity;
    }
    
   
    
    @Test
    public void crearTarjeta()
    {
        TarjetaDeCreditoEntity entity = data.get(0);
        try
        {
            TarjetaDeCreditoEntity result = TDCLogic.createTarjetaDeCredito(entity);
        }
        catch(BusinessLogicException e )
        {
            Assert.assertTrue(e.getMessage().equals("La TDC ya existe"));
        }
   
    }
    
       
    @Test
    public void crearTarjeta2()
    {
        boolean check = true;
        TarjetaDeCreditoEntity entity = crearEntity();
        try
        {
            TarjetaDeCreditoEntity result = TDCLogic.createTarjetaDeCredito(entity);
        }
        catch(BusinessLogicException e )
        {
            System.out.println(e.getMessage());
            check = false;
        }
        Assert.assertTrue(check);
  
    }
    
    @Test
    public void crearTarjeta3()
    {
        TarjetaDeCreditoEntity entity = crearEntity();
        entity.setTipoDeTarjeta("Masdfghjklñ");
         try
        {
            TarjetaDeCreditoEntity result = TDCLogic.createTarjetaDeCredito(entity);
        }
        catch(BusinessLogicException e )
        {
            Assert.assertTrue(e.getMessage().equals("Error creando la tarjeta"));
        }
                
    }

    
    
    @Test
    public void getTarjetaDeCredito()
    {
        List<TarjetaDeCreditoEntity> list = TDCLogic.getTarjetaDeCredito();
        Assert.assertEquals(data.size(), list.size());
        for(TarjetaDeCreditoEntity entity : list)
        {
            boolean found = false;
            for(TarjetaDeCreditoEntity storedEntity : data)
            {
                if(entity.getId().equals(storedEntity.getId()))
                {
                    found = true;
                }    
                }
                    Assert.assertTrue(found);
        }
    }
    
//    @Test
//    public void updateTarjetaDeCredito()
//    {
//        
//        TarjetaDeCreditoEntity pojoEntity = factory.manufacturePojo(TarjetaDeCreditoEntity.class);
//
//       Long id = new Long("11111");
//        pojoEntity.setId(id);
//
//        TDCLogic.updateEntity(pojoEntity);
//        TarjetaDeCreditoEntity encontrar = TDCLogic.getTarjetaDeCredito(pojoEntity.getId());
//        Assert.assertNull(encontrar);
//        
//    }
    
    @Test
    public void getTarjetaDeCredito2()
    {
        Long id = new Long ("1234567890");
        TarjetaDeCreditoEntity resultEntity = TDCLogic.getTarjetaDeCredito(id);
        Assert.assertNull(resultEntity);
    }
}
