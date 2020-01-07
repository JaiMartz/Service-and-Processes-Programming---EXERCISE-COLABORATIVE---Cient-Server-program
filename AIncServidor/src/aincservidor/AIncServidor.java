/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aincservidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author Jairo
 */
public class AIncServidor {

    private ServerSocket servidor;
    private static int puerto = 9090;
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    
    /**
     * Builder of AIncServidor.
     * Constructor de AIncServidor
     */
    public AIncServidor(){
        try{
        servidor = new ServerSocket(puerto);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    public static void main(String[] args) {
            AIncServidor programa = new AIncServidor();
            programa.iniciar();
    }

    /**
     * Iniciamos el programa y creamos nuevos hilos para cada peticion
     * de conexion al servidor.
     */
    public void iniciar(){
        while(true){
            try{
                Socket cliente = servidor.accept();
                new HiloServidor(cliente, this).start();
                System.out.println("Creada nueva conexion");
                this.getEntityManager();
                this.insertarDatosClientes(entityManager);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    /**
     * Obtain the EntityManager for access to the database.
     * Obtenemos los EntityManager para acceder a la base de datos.
     */
    private synchronized void getEntityManager(){
        entityManagerFactory = Persistence.createEntityManagerFactory("datosClientes.odb");
        entityManager = entityManagerFactory.createEntityManager();
    }
    /**
     * Insert the objects in the database.
     * Insertamos los objetos en la base de datos.
     * @param entityManager parametro que recoge el entityManager para iniciar la conexion.
     */
    public void insertarDatosClientes(EntityManager entityManager){
        
        //Orden constructor: nombre, tipo, aniosRelacion,SE GENERA AUTOMATICAMENE = idArticulo,pedidoMedioProveedor
        entityManager.getTransaction().begin();
        entityManager.persist(new DatosClientes("Jairo", "Persona fisica",1,9,4));
        entityManager.persist(new DatosClientes("David", "Persona fisica",3,7,2));
        entityManager.persist(new DatosClientes("Fomento", "Persona juridica", 10,4,7));
        entityManager.persist(new DatosClientes("Mercadona", "Persona juridica", 8,10, 60));
        entityManager.persist(new DatosClientes("Gabriel", "Persona fisica", 6,6,21 ));
        entityManager.persist(new DatosClientes("Gema", "Persona fisica", 2,5,6 ));
        entityManager.persist(new DatosClientes("Carrefour", "Persona juridica", 15,3,56 ));
        entityManager.persist(new DatosClientes("Maria", "Persona fisica", 11,1,9 ));
        entityManager.persist(new DatosClientes("Tecnogia+", "Persona juridica", 4,8,10 ));
        entityManager.persist(new DatosClientes("Lomas", "Persona juridica", 1, 2,4 ));
        entityManager.getTransaction().commit();
        
    }

    /**
     * Obtain all the clients data
     * Obtenemos todos los clientes
     * @return parametro que devuelve un objeto List<DatosClientes>.
     */
    public List <DatosClientes> getDatosClientes(){
        //Cargamos el entityManager
        getEntityManager();
    
        TypedQuery<DatosClientes> consulta = (TypedQuery<DatosClientes>)
            entityManager.createQuery("SELECT datosclientes FROM DatosClientes datosclientes",
                    DatosClientes.class);
    
        List<DatosClientes> Clientes = consulta.getResultList();
    
        entityManager.close();
        entityManagerFactory.close();
        return Clientes;
    }
    /**
     * Realize a client search by NIF
     * Realizamos la busqueda de un cliente por su NIF.
     * @param nif parametro que recibe para la busqueda.
     * @return parametro que devuelve un objeto DatosClientes.
     */
    private DatosClientes getDatosClientesByNif(String nif){
        getEntityManager();
        
        TypedQuery<DatosClientes> consulta = (TypedQuery<DatosClientes>)
                entityManager.createQuery("SELECT datosclientes FROM DatosClientes datosclientes"
                        +" WHERE datosclientes.nif = '"+nif+"'");
        
        DatosClientes Cliente = consulta.getSingleResult();
        
        entityManager.close();
        entityManagerFactory.close();
        return Cliente;
    }
    /**
     * Use the method getDatosClientesByNif to get a client.
     * Hace uso del metodo getDatosClientesByNif.
     * @param nombre parametro que utilizamos para la busqueda.
     * @return Cadena de texto que almacena la informacion
     */
    public synchronized String getCliente(String nif){
        try{
            String datos = getDatosClientesByNif(nif).toString();
            return datos;
        }catch(NoResultException  e){
            return "No hay resultados para esa consulta";
        }
    }
    
    /**
     * Get the clients data list using the method getDatosClientes.
    * Recogemos una lista de datos de clientes llamando al método getDatosClientes
    * y la recorre
    * @return clientes que es una variable donde se almacena la lista de resultados.
    */
    public synchronized List<DatosClientes> getListado(){
        getEntityManager();
        TypedQuery<DatosClientes> consulta = (TypedQuery<DatosClientes>) 
                entityManager.createQuery("SELECT datosclientes FROM DatosClientes datosclientes",
                        DatosClientes.class);
        
        List<DatosClientes> clientes = consulta.getResultList();
        
        entityManager.close();
        entityManagerFactory.close();
        return clientes;
    }
}

