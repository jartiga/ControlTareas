package sv.com.task.sessions;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Local;

@Local
public interface BusinessSBSLLocal {
	public Serializable findByPk(Class<? extends Serializable> entity, Object pk) throws Exception;
	public List<? extends Serializable> findAll(Class<? extends Serializable> entity) throws Exception;;
	public Serializable update(Serializable registro) throws Exception;
	public Serializable insert(Serializable registro) throws Exception;
	public void delete(Serializable entity) throws Exception;
	public List<? extends Serializable> findByProperty(Class<? extends Serializable> entity, String property, Object value);
	public Serializable findByPropertyUnique(Class<? extends Serializable> entity, String property, Object value);
	public List<? extends Serializable> findByCondition(Class<? extends Serializable> entity, String where, String order);
	public List<? extends Serializable> findByNativeQuery(Class<? extends Serializable> entity,String query, Object[] params);
	public List<? extends Serializable> menuByUser(Class<? extends Serializable> entity,  long usuId, long sisId);
	public int yaExiste(String tablas, String condicion);
}
