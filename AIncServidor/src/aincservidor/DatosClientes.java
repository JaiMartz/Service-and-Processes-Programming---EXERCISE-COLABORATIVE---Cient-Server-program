/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aincservidor;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Jairo
 */
@Entity
public class DatosClientes implements Serializable{
    
    @Id @GeneratedValue
    private int nif;
    
    private String nombre;
    private String tipo; //Persona física o jurídica
    private int aniosRelacion;
    private int idArticulo;
    private int pedidoMedioProveedor;

    public DatosClientes(String nombre, String tipo, int aniosRelacion, int idArticulo, int pedidoMedioProveedor) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.aniosRelacion = aniosRelacion;
        this.idArticulo = idArticulo;
        this.pedidoMedioProveedor = pedidoMedioProveedor;
    }
    
    public DatosClientes(){
        this.nombre = "";
        this.tipo="";
        this.aniosRelacion = 0;
        this.idArticulo = 0;
        this.pedidoMedioProveedor = 0;
    }

    /**
     * Devuelve el valor de nif;
     * @return  nif
     */
    public int getNif() {
        return nif;
    }
    
    /**
    * 
    * @param nif establecemos el parametro.
    */
    public void setNif(int nif) {
        this.nif = nif;
    }
    /**
     * Devuelve el valor de nombre.
     * @return nombre
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * 
     * @param nombre establecemos el parametro.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Devuelve el valor de tipo.
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * 
     * @param tipo establecemos el parametro
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Devuelve el valor de aniosRelacion
     * @return aniosRelacion
     */
    public int getAniosRelacion() {
        return aniosRelacion;
    }
    /**
     * 
     * @param aniosRelacion establecemos el parametro
     */
    public void setAniosRelacion(int aniosRelacion) {
        this.aniosRelacion = aniosRelacion;
    }
    /**
     * Devuelve el valor  de idAritculo.
     * @return idArticulo
     */
    public int getIdArticulo() {
        return idArticulo;
    }
    /**
     * 
     * @param idArticulo establecemos el parametro
     */
    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }
    /**
     * Devuelve el valor de pedidoMedioProveedor.
     * @return pedidoMedioProveedor
     */
    public int getPedidoMedioProveedor() {
        return pedidoMedioProveedor;
    }
    /**
     * 
     * @param pedidoMedioProveedor establecemos el parametro
     */
    public void setPedidoMedioProveedor(int pedidoMedioProveedor) {
        this.pedidoMedioProveedor = pedidoMedioProveedor;
    }
    
    
    //String nombre, String tipo, int aniosRelacion, int idArticulo, int pedidoMedioProveedor
     /**
      * Sobreescribimos el metodo toStrig para que muestre la informacion del objeto.
      * @return 
      */
    @Override
    public String toString(){
        return String.format("*** NIF: %d - Cliente: '%s' - Tipo:'%s' - Años relacion: %d Id Articulo: %d "
                + "Pedido Medio: %d ",
                this.nif, this.nombre, this.tipo, this.aniosRelacion, 
                this.idArticulo, this.pedidoMedioProveedor);
    }
    
    
}
