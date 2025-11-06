package edu.upc.dsa;
import edu.upc.dsa.util.Manager;
import edu.upc.dsa.util.ManagerImpl;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import edu.upc.dsa.models.*;
import static org.junit.Assert.*;

public class ManagerImpTest {
    private Manager manager;

    @Before
    public void setUp(){
        this.manager = new ManagerImpl();
        manager = ManagerImpl.getInstance();
        manager.clear();


        manager.añadirDron("1", "Dron 1", "Fabrica 1", "Modelo 1");
        manager.añadirDron("2", "Dron 2", "Fabrica 2", "Modelo 2");
        manager.añadirDron("3", "Dron 3", "Fabrica 3", "Modelo 3");

        manager.añadirPiloto("1", "Nombre 1", "Apellido 1");
        manager.añadirPiloto("2", "Nombre 2", "Apellido 2");
        manager.añadirPiloto("3", "Nombre 3", "Apellido 3");
    }

    @After
    public void tearDown(){
        manager.clear();
    }
    @Test
    public void testAñadirDron() throws Exception{
        Dron dron = manager.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        assertNotNull(dron);
        assertEquals("4", dron.getId());
        assertEquals("Dron 4", dron.getNombre());
        assertEquals("Fabrica 4", dron.getFabricante());
        assertEquals("Modelo 4", dron.getModelo());
    }
    @Test
    public void testAñadirPiloto() throws Exception{
        Piloto piloto = manager.añadirPiloto("4", "Nombre 4", "Apellido 4");
        assertNotNull(piloto);
        assertEquals("4", piloto.getId());
        assertEquals("Nombre 4", piloto.getNombre());
        assertEquals("Apellido 4", piloto.getApellidos());
    }
    @Test
    public void testGuardarDronEnAlmacen() throws Exception{
        Dron dron = manager.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        manager.guardarDronAlmacen(dron.getId());
        assertTrue(dron.isMantenimiento());
    }
    @Test
    public void testRepararDronEnAlmacen() throws Exception{
        Dron dron = manager.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
        manager.guardarDronAlmacen(dron.getId());
        manager.repararDron();
        assertFalse(dron.isMantenimiento());
    }
}
