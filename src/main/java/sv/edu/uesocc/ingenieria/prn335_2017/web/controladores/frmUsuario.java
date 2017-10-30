/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.uesocc.ingenieria.prn335_2017.web.controladores;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.event.SelectEvent;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.UsuarioFacadeLocal;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.definiciones.Usuario;
import sv.edu.uesocc.ingenieria.prn335_2017.datos.acceso.GenericFacadeLocal;

/**
 *
 * @author jonathanhdz
 */
@Named(value = "frmUsuario")
@ViewScoped
public class frmUsuario extends GenericManagedBean<Usuario> implements Serializable{

    public frmUsuario() {
    }

    @EJB
    UsuarioFacadeLocal facade;
    Usuario usuarioEntity;
    boolean btnVisible=false;
    
    boolean boton1 = false;
    boolean boton2 = false;
    boolean formulario = false;

    // <editor-fold defaultstate="collapsed" desc="Getters y Setters">
    public UsuarioFacadeLocal getFacade() {
        return facade;
    }

    public boolean isBtnVisible() {
        return btnVisible;
    }

    public void setBtnVisible(boolean btnVisible) {
        this.btnVisible = btnVisible;
    }
    
    public void setFacade(UsuarioFacadeLocal facade) {
        this.facade = facade;
    }

    public Usuario getUsuarioEntity() {
        return usuarioEntity;
    }

    public void setUsuarioEntity(Usuario usuarioEntity) {
        this.usuarioEntity = usuarioEntity;
    }

    public List<Usuario> getListaDatos() {
        return listaDatos;
    }

    public void setListaDatos(List<Usuario> listaDatos) {
        this.listaDatos = listaDatos;
    }

    public boolean isBoton1() {
        return boton1;
    }

    public void setBoton1(boolean boton1) {
        this.boton1 = boton1;
    }

    public boolean isBoton2() {
        return boton2;
    }

    public void setBoton2(boolean boton2) {
        this.boton2 = boton2;
    }

    public boolean isFormulario() {
        return formulario;
    }

    public void setFormulario(boolean formulario) {
        this.formulario = formulario;
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Overrrides">
    @Override
    public Usuario getEntity() {
        return usuarioEntity;
    }

    @Override
    protected GenericFacadeLocal<Usuario> getFacadeLocal() {
        return facade;
    }

    @Override
    public void editar() {
        super.editar(); 
        reiniciarValores();
    }

    @Override
    public void eliminar() {
        super.eliminar(); 
        reiniciarValores();
    }

    @Override
    public void crear() {
        super.crear(); 
        reiniciarValores();
    }
    // </editor-fold>
    
    @PostConstruct
    public void init(){
        llenarLista();
    }
    
    /**
     * Metodo sirve para el momento de seleccionar en la lista y mostrar 
     * los datos en el formulario con sus respectivos botones
     * @param event 
     */
    public void onRowSelect(SelectEvent event) {
        btnVisible = true;
        this.boton2 = true;
        this.boton1 = false;
        this.formulario=true;
    }
    
    /**
     * Este Metodo se encarga de inicializar a valores nulos los campos del formulario
     */
    public void reiniciarValores(){
        usuarioEntity.setActivo(false);
        usuarioEntity.setApellidos(null);
        usuarioEntity.setComentarios(null);
        usuarioEntity.setFechaNacimiento(null);
        usuarioEntity.setNombres(null);
        usuarioEntity.setPassword(null);
        usuarioEntity.setUsername(null);
    }
    
    /**
     * Este metodo sirve como funcion cancelar el cual evita la visivilidad de los botones
     * especificados al momento de hacer uso de el
     */
    public void btnCancelar() {
        usuarioEntity = new Usuario();
        btnVisible = false;
        this.formulario=false;
        this.boton1=false;
        this.boton2=false;
    }
    
    /**
     * Este metodo habilita el fromulario y el boton para la opcion de guardar un nuevo registro
     */
    public void btnNuevo(){
        usuarioEntity = new Usuario();
        this.formulario=true;
        this.boton1=true;
    }
}