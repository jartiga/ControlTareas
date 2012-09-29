package sv.com.task.controller;

import java.util.ResourceBundle;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

//import sv.gob.cnr.cnrapps.security.ObjAppsSession;
import sv.com.task.sessions.BusinessSBSLLocal;
import sv.com.task.utils.SystemProperties;

public class SuperController {

	@EJB(mappedName = "ejb/BusinessSBSL")
	protected BusinessSBSLLocal businessLocal;

	public static final String AUTH_KEY = "app.user.name";
	public static final String AUTH_SESSION = "app.session";

	public static final String STATUS_NEW = "NEW";
	public static final String STATUS_EDIT = "EDIT";
	public static final String STATUS_SEARCH = "SEARCH";
	public static final String STATUS_DELETE = "DELETE";

	protected String status; //[NEW, EDIT, SEARCH, DELETE]
	protected String outcome;
	protected String bundleName;
//	protected ObjAppsSession objAppsSession;
	
	private SuperController mb;
	
	
	public SuperController(){
		outcome = null;
		status = STATUS_SEARCH;
	}

	/**
	 * Metodo que agrega los mesajes al contexto JSF para que puedan ser mostrados en pantalla.
	 * 
	 * @author Jose David Artiga
	 * @param message
	 *            Objeto de tipo FacesMessage, con el mensaje que se agregara al contexto JSF.
	 */
	protected void addMessage(FacesMessage message) {  
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);  
    }
	
	/**
	 * Metodo que agrega los mesajes SEVERITY_INFO al contexto JSF para que puedan ser mostrados en pantalla.
	 * 
	 * @author Jose David Artiga
	 * @param message
	 *            Objeto de tipo FacesMessage, con el mensaje que se agregara al contexto JSF.
	 */
	protected void addInfo(FacesMessage message) {  
		message.setSeverity(FacesMessage.SEVERITY_INFO);
		message.setSummary("INFORMACION:");
		addMessage(message);  
    }  

	/**
	 * Metodo que agrega los mesajes SEVERITY_ERROR al contexto JSF para que puedan ser mostrados en pantalla.
	 * 
	 * @author Jose David Artiga
	 * @param message
	 *            Objeto de tipo FacesMessage, con el mensaje que se agregara al contexto JSF.
	 */
	protected void addError(FacesMessage message) {  
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setSummary("ERROR:");
		addMessage(message);  
    }  

	/**
	 * Metodo que agrega los mesajes SEVERITY_WARN al contexto JSF para que puedan ser mostrados en pantalla.
	 * 
	 * @author Jose David Artiga
	 * @param message
	 *            Objeto de tipo FacesMessage, con el mensaje que se agregara al contexto JSF.
	 */
	protected void addWarn(FacesMessage message) {  
		message.setSeverity(FacesMessage.SEVERITY_WARN);
		message.setSummary("ADVERTENCIA:");
		addMessage(message);  
    }  

	/**
	 * Metodo que agrega los mesajes SEVERITY_FATAL al contexto JSF para que puedan ser mostrados en pantalla.
	 * 
	 * @author Jose David Artiga
	 * @param message
	 *            Objeto de tipo FacesMessage, con el mensaje que se agregara al contexto JSF.
	 */
	protected void addFatal(FacesMessage message) {  
		message.setSeverity(FacesMessage.SEVERITY_FATAL);
		message.setSummary("FATAL:");
		addMessage(message);  
    } 

	protected boolean beforeNew(){
		return true;
	}

	protected void afterNew(){
	}

	protected boolean beforeEdit(){
		return true;
	}

	protected void afterEdit(){
	}

	protected boolean beforeCancel(){
		return true;
	}

	protected void afterCancel(){
	}

	protected boolean beforeCancelNew(){
		return true;
	}

	protected void afterCancelNew(){
	}

	protected boolean beforeCancelEdit(){
		return true;
	}

	protected void afterCancelEdit(){
	}

	protected boolean beforeSave(){
		return true;
	}

	protected void afterSave(){
	}
	
	protected boolean beforeSaveNew(){
		return true;
	}

	protected void afterSaveNew(){
	}

	protected boolean beforeSaveEdit(){
		return true;
	}

	protected void afterSaveEdit(){
	}
	
	protected boolean beforeDelete(){
		return true;
	}

	protected void afterDelete(){
	}

	protected boolean beforeReport(){
		return true;
	}

	protected void afterReport(){
	}
	
	protected boolean beforeRowSelect(){
		return true;
	}
	
	protected void afterRowSelect(){
	}
	
	protected boolean beforeRowUnSelect(){
		return true;
	}
	
	protected void afterRowUnSelect(){
	}

	public String getStringMessage(String key) {
		return SystemProperties.getInstance().getProperty(key);
	}
	
	public String getStringMessage(String key, String bundleName) {
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(
				context, bundleName);
		return bundle.getString(key);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

//	public ObjAppsSession getObjAppsSession() {
//		if (objAppsSession == null){
//			objAppsSession = (ObjAppsSession) FacesContext.getCurrentInstance().
//					getExternalContext().getSessionMap().get(AUTH_SESSION);
//		}
//		return objAppsSession;
//	}
//
//	public void setObjAppsSession(ObjAppsSession objAppsSession) {
//		this.objAppsSession = objAppsSession;
//	}

	public BusinessSBSLLocal getBusinessLocal() {
		return businessLocal;
	}

	public void setBusinessLocal(BusinessSBSLLocal businessLocal) {
		this.businessLocal = businessLocal;
	}

	public SuperController getMb() {
		return mb;
	}

	public void setMb(SuperController mb) {
		this.mb = mb;
	}
}
