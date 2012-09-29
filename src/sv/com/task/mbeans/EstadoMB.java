package sv.com.task.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import sv.com.task.controller.SystemController;
import sv.com.task.entities.Estado;

@ManagedBean
@ViewScoped
public class EstadoMB extends SystemController {
	

	public EstadoMB() {
		super(new Estado());
	}
    
	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init(){
	    	try {
				setListado((List<Estado>)businessLocal.findAll(Estado.class));
			} catch (Exception e) {
				e.printStackTrace();
				addError(new FacesMessage(null, "Se ha generado un error. Consulte con el administrador. [ERROR]["
						+ e.getMessage() + "]"));
			}
	}
	
	@Override
    public void afterSaveNew(){
		getListado().add((Estado)getRegistro());
		beforeNew();
    }
	
	@Override
    public void afterDelete(){
		getListado().remove(getRegistro());
    }
    
    @Override
	@SuppressWarnings("unchecked")
	public List<Estado> getListado() {
		return (List<Estado>)super.getListado();
	}

    @Override
    public Estado getRegistro() {
    	return (Estado)super.getRegistro();
    }
    
}
