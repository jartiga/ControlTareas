package sv.com.task.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;

import org.primefaces.event.RowEditEvent;


public class SystemController extends SuperController {

	private Serializable registro;
	private Serializable regSelected;
	private List<? extends Serializable> listado;

	public SystemController(){
		super();
	}
	public SystemController(Serializable registro){
		super();
		setRegistro(registro);
		setRegSelected(null);
	}

	/**
	 * Metodo para ejecutarse al momento de crear nuevos registros, hace uso de
	 * los metodos beforeNew() y afterNew(), cambia el valor de la propiedad
	 * status a "NEW".
	 * 
	 * @author Jose David Artiga.
	 */
	public void onNew() {
		try {
			setRegSelected(null);
			setRegistro(getRegistro().getClass().newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		if (beforeNew()) {
			status = STATUS_NEW;
			afterNew();
		}
	}

	/**
	 * Metodo para ejecutarse al momento de modificar un registros, hace uso de
	 * los metodos beforeEdit() y afterEdit(), cambia el valor de la propiedad
	 * status a "EDIT".
	 * 
	 * @author Jose David Artiga.
	 */
	public void onEdit() {
		if (beforeEdit()) {
			afterEdit();
			status = STATUS_EDIT;
		}
	}

	/**
	 * Metodo para cancelar la edicion de un registro, ya sea nuevo o modificacion, hace uso de
	 * los metodos beforeCancle() y afterCancel().
	 * 
	 * @author Jose David Artiga.
	 */
	public void onCancel() {
		if (beforeCancel()) {
			if (status.equals(STATUS_EDIT)) {
				onCancelEdit();
			} else if (status.equals(STATUS_NEW)) {
				onCancelNew();
			}
			afterCancel();
		}
	}

	/**
	 * Metodo para cancelar la edicion de un nuevo registro, hace uso de
	 * los metodos beforeCancleNew() y afterCancelNew(), cambia la propiedad status a "SEARCH".
	 * 
	 * @author Jose David Artiga.
	 */
	public void onCancelNew() {
		if (beforeCancelNew()) {
			try {
				setRegSelected(null);
				setRegistro(getRegistro().getClass().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status = STATUS_SEARCH;
			afterCancelNew();
		}
	}

	/**
	 * Metodo para cancelar la modificacion de un registro, hace uso de
	 * los metodos beforeCancleEdit() y afterCancelEdit(), cambia la propiedad status a "SEARCH".
	 * 
	 * @author Jose David Artiga.
	 */
	public void onCancelEdit() {
		if (beforeCancelEdit()) {
			try {
				setRegSelected(null);
				setRegistro(getRegistro().getClass().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			status = STATUS_SEARCH;
			afterCancelEdit();
		}
	}

	/**
	 * Metodo para guardar los cambios de un registro, ya sea nuevo o modificacio, hace uso de
	 * los metodos beforeSave() y afterSave().
	 * 
	 * @author Jose David Artiga.
	 */
	public void onSave() {
		FacesMessage message = new FacesMessage();
		try {
			if (beforeSave()) {
				if (status.equals(STATUS_EDIT)) {
					onSaveEdit();
				} else if (status.equals(STATUS_NEW)) {
					onSaveNew();
				}
				afterSave();
			}
		} catch (Exception e) {
			message.setDetail(getStringMessage("msg.error")+" ["
					+ e.getMessage() + "]");
			addError(message);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para guardar un nuevo registro en la base de datos, hace uso de
	 * los metodos beforeSaveNew() y afterSaveNew(), cambia la propiedad status a "EDIT".
	 * 
	 * @author Jose David Artiga.
	 */
	public void onSaveNew() {
		FacesMessage message = new FacesMessage();
		try {
			if (beforeSaveNew()) {
				businessLocal.insert(getRegistro());
				status = STATUS_EDIT;
				message.setDetail(getStringMessage("msg.info.guarda"));
				addInfo(message);
				afterSaveNew();
			}
		} catch (Exception e) {
			message.setDetail(getStringMessage("msg.error")+" ["
					+ e.getMessage() + "]");
			addError(message);
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para guardar las modificaciones hechas a un registro en la base de datos, hace uso de
	 * los metodos beforeSaveEdit() y afterSaveEdit().
	 * 
	 * @author Jose David Artiga.
	 */
	public void onSaveEdit() {
		FacesMessage message = new FacesMessage();
		try {
			if (beforeSaveEdit()) {
				businessLocal.update(getRegistro());
				message.setDetail(getStringMessage("msg.info.guarda"));
				addInfo(message);
				afterSaveEdit();
			}
		} catch (Exception e) {
			message.setDetail(getStringMessage("msg.error")+" ["
					+ e.getMessage() + "]");
			addError(message);
			e.printStackTrace();
		}
	}

	public void onSaveEditRow(RowEditEvent event) {  
		setRegSelected((Serializable)event.getObject());
		setRegistro(getRegSelected());
		onEdit();
		onSaveEdit();
    }
	
	/**
	 * Metodo para eliminar un registro de la base de datos, hace uso de
	 * los metodos beforeDelete() y afterDelete().
	 * 
	 * @author Jose David Artiga.
	 */
	public void onDelete() {
		FacesMessage message = new FacesMessage();
		try {
			if (beforeDelete()) {
				businessLocal.delete(getRegistro());
				message.setDetail(getStringMessage("msg.info.elimina"));
				addInfo(message);
				afterDelete();
				try {
					setRegSelected(null);
					setRegistro(getRegistro().getClass().newInstance());
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			message.setDetail(getStringMessage("msg.error")+" ["
					+ e.getMessage() + "]");
			addError(message);
			e.printStackTrace();
		}
	}

	public void onDeleteRow(){
		if (getRegSelected() != null){
			setRegistro(getRegSelected());
    		onDelete();
		} else {
			FacesMessage message = new FacesMessage();
			message.setDetail(getStringMessage("msg.error.noseleccion"));
			addError(message);
		}
    }
	
	public void onRowSelect(){
		if (beforeRowSelect()){
			setRegistro(getRegSelected());
			afterRowSelect();
		}
	}

	public void onRowUnSelect(){
		if (beforeRowUnSelect()){
			try {
				setRegSelected(null);
				setRegistro(getRegistro().getClass().newInstance());
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			afterRowUnSelect();
		}
	}
	
	public Serializable getRegistro() {
		return registro;
	}

	public void setRegistro(Serializable registro) {
		this.registro = registro;
	}

	public Serializable getRegSelected() {
		return regSelected;
	}

	public void setRegSelected(Serializable regSelected) {
		this.regSelected = regSelected;
	}

	public List<? extends Serializable> getListado() {
		return listado;
	}

	public void setListado(List<? extends Serializable> listado) {
		this.listado = listado;
	}
}
