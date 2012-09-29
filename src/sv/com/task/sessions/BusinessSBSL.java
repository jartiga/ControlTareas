package sv.com.task.sessions;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * Session Bean implementation class SirycSBSL
 */
@Stateless
@LocalBean
public class BusinessSBSL implements BusinessSBSLLocal {

	@PersistenceUnit
	protected EntityManagerFactory emf;
	protected EntityManager em;

	public static final long TRANSACTION_INSERT = 1L;
	public static final long TRANSACTION_UPDATE = 2L;
	public static final long TRANSACTION_DELETE = 3L;

	public BusinessSBSL() {

	}

	/**
	 * Metodo que realiza la busqueda de un registro por medio de la llave
	 * primaria de la tabla.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Entidad del tipo Class<? extends Serializable>, de la cual se
	 *            quiere realizar la busqueda del registro
	 * @param pk
	 *            Recibe el valor de la llave primaria que se desea buscar, el
	 *            tipo de dato debe ser igual al del Id definido en la clase
	 *            entidad
	 * @return La clase Serializable con el registro obtenido en la busqueda
	 * @throws Exception
	 */
	@Override
	public Serializable findByPk(Class<? extends Serializable> entity, Object pk)
			throws Exception {
		Serializable registro = null;
		try {
			em = emf.createEntityManager();
			registro = em.find(entity, pk);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.clear();
			em.close();
		}
		return registro;
	}

	/**
	 * Metodo que realiza la busqueda de todos los registros de una entidad.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Entidad del tipo Class<? extends Serializable>, de la cual se
	 *            quiere realizar la busqueda
	 * @return la lista de los registros encontrados de la entidad proporcionada
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> findAll(Class<? extends Serializable> entity)
			throws Exception {
		List<? extends Serializable> registros = null;
		try {
			em = emf.createEntityManager();
			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<?> q = qb.createQuery(entity);
			registros = (List<? extends Serializable>) em.createQuery(q).getResultList();
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		} finally {
			em.clear();
			em.close();
		}
		return registros;
	}

	/**
	 * Metodo que realiza el update de un registro en la base de datos.
	 * 
	 * @author Jose David Artiga
	 * @param registro
	 *            Entidad del tipo Serializable, con la informacion a actualizar
	 *            en la base de datos
	 * @return La clase Serializable con el registro almacenado en la base de
	 *         datos
	 */
	@Override
	public Serializable update(Serializable registro) throws Exception {
		try {
			em = emf.createEntityManager();
			registro = em.merge(registro);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.clear();
			em.close();
		}

		return registro;
	}

	/**
	 * Metodo que realiza el insert de un registro en la base de datos.
	 * 
	 * @author Jose David Artiga
	 * @param registro
	 *            Entidad del tipo Serializable, con la informacion a insertar en
	 *            la base de datos
	 * @return La clase Serializable con el registro almacenado en la base de
	 *         datos
	 */
	@Override
	public Serializable insert(Serializable registro) throws Exception {
		try {
			em = emf.createEntityManager();
			//em.getTransaction().begin();
			em.persist(registro);
			//em.getTransaction().commit();
			em.flush();
		} catch (Exception e) {
			if (em != null){
				em.getTransaction().rollback();
			}
			throw new Exception(e);
		} finally {
			em.clear();
			em.close();
		}
		return registro;
	}

	/**
	 * Metodo que realiza el delete de un registro en la base de datos.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Entidad del tipo Class<? extends Serializable>, de la cual se
	 *            quiere realizar el delete
	 */
	@Override
	public void delete(Serializable entity) throws Exception {
		try {
			em = emf.createEntityManager();
			PersistenceUnitUtil util = emf.getPersistenceUnitUtil();
			Object pk = util.getIdentifier(entity);
			entity = em.find(entity.getClass(), pk);
			em.remove(entity);
			em.flush();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			em.clear();
			em.close();
		}
	}

	/**
	 * Metodo que realiza la busqueda de objetos por una propiedad de la clase.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Class de la entidad de la que se realizara la gusqueda.
	 * @param property
	 *            Nombre del atributo por el cual se realizara la busqueda.
	 * @param value
	 *            Valor que se desea buscar.
	 * @return Lista de objetos que cumple la condicion proporcionada.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> findByProperty(Class<? extends Serializable> entity,
			String property, Object value) {
		List<? extends Serializable> lst = null;
		try {
			em = emf.createEntityManager();

			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<?> q = qb.createQuery(entity);

			Root<?> registro = q.from(entity);

			q.where(qb.equal(registro.get(property), value));
			lst = (List<? extends Serializable>) em.createQuery(q).getResultList();
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return lst;
	}

	/**
	 * Metodo que realiza la busqueda de un objeto por una propiedad de la
	 * clase.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Class de la entidad de la que se realizara la gusqueda.
	 * @param property
	 *            Nombre del atributo por el cual se realizara la busqueda.
	 * @param value
	 *            Valor que se desea buscar.
	 * @return Objeto que cumple la condicion proporcionada.
	 */
	@Override
	public Serializable findByPropertyUnique(
			Class<? extends Serializable> entity, String property, Object value) {
		Serializable registros = null;
		try {
			em = emf.createEntityManager();

			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<?> q = qb.createQuery(entity);

			Root<?> registro = q.from(entity);
			q.where(qb.equal(registro.get(property), value));

			registros = (Serializable) em.createQuery(q).getSingleResult();
			em.flush();
		} catch (NoResultException e) {
			// e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return registros;
	}

	/**
	 * Metodo que realiza la busqueda de objetos por medio de una condicion.
	 * 
	 * @author Jose David Artiga
	 * @param entity
	 *            Class de la entidad de la que se realizara la gusqueda.
	 * @param where
	 *            Condicion para realizar la busqueda, cada propiedad de la
	 *            clase puesta en la condicion debe anteponerse el alias por
	 *            defecto que es "o." Ej:
	 *            "o.campo1 = valor1 and o.campo2 = valor2 and o.campoN = valorN"
	 *            .
	 * @param order
	 *            Atributos por los cual se quiere ordenar la lista, cada
	 *            atributo de la clase puesta en el orden debe anteponerse el
	 *            alias por defecto que es "o." Ej:
	 *            "o.campo1, o.campo2 asc, o.campo2 desc, o.campoN".
	 * @return Lista de objetos que cumple la condicion proporcionada.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> findByCondition(Class<? extends Serializable> entity,
			String where, String order) {
		em = emf.createEntityManager();
		List<? extends Serializable> lista = null;
		try {
			String sql = "select object(o) from " + entity.getSimpleName()
					+ " as o";
			if (where != null) {
				sql += " where " + where;
			}

			if (order != null) {
				sql += " order by " + order;
			}
			Query q = em.createQuery(sql);
			lista = q.getResultList();
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> findByNativeQuery(Class<? extends Serializable> entity,
			String query, Object[] params) {
		em = emf.createEntityManager();
		List<? extends Serializable> lista = null;
		try {
			Query q = em.createNativeQuery(query, entity);
			for (int i = 0; i < params.length; i++) {
				q.setParameter(i + 1, params[i]);
			}
			lista = q.getResultList();
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return lista;
	}

	@SuppressWarnings("all")
	public String getEntityToString(Serializable obj) {
		String stringSalida = "";
		if (obj != null) {
			String suffix = "";
			String methodName = "";
			String excepciones = "selected|serialVersionUID|";
			try {
				Field f[] = obj.getClass().getDeclaredFields();
				for (int i = 0; i < f.length; i++) {
					try {

						// Valida que la propiedad no sea ni Set ni List
						if ((f[i].getType().getName().indexOf("java.util.Set") < 0)
								&& (f[i].getType().getName()
										.indexOf("java.util.List") < 0)) {
							// Valida que las propiedad no este dentro de las
							// excepciones
							if ((excepciones.indexOf(f[i].getName()) < 0)) {
								// Valida si es boolean para invocarlo mediante
								// isPropiedad, sino con getPropiedad
								if (f[i].getType().getName()
										.equals("java.lang.Boolean")
										|| f[i].getType().getName()
												.equals("boolean")) {
									suffix = "is";
								} else {
									suffix = "get";
								}

								methodName = suffix
										+ f[i].getName().substring(0, 1)
												.toUpperCase()
										+ f[i].getName().substring(1);
								Method m = obj.getClass().getMethod(methodName,
										null);

								Object metodo = m.invoke(obj, null);

								if (metodo == null) {
									stringSalida += "[" + f[i].getName() + "=]";
								} else {
									boolean isSerializable = ((metodo.getClass()
											.getSuperclass().toString() != null) && (metodo
											.getClass()
											.getSuperclass()
											.toString()
											.indexOf(
													"sv.gob.cnr.cnrapps.entities.Serializable") > 0))
											&& ((metodo.getClass()
													.getSuperclass().getSuperclass().toString() != null) && (metodo
													.getClass()
													.getSuperclass()
													.toString()
													.indexOf(
															"sv.gob.cnr.cnrapps.entities.Serializable") > 0));

									if (isSerializable) {
										stringSalida += "["
												+ f[i].getName()
												+ "="
												+ getEntityPk((Serializable) metodo)
												+ "]";
									} else {
										stringSalida += "[" + f[i].getName()
												+ "=" + metodo.toString() + "]";
									}
								}
							}
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		return stringSalida;
	}

	/**
	 * Devuelve el PK de una Entidad.
	 * <p>
	 * El PK de una entidad corresponde a su identificador �nico y permite
	 * establecer relaciones con otras entidades.
	 * <p>
	 * 
	 * @return El id de la entidad.
	 * @since 1.0
	 */

	public Object getEntityPk(Serializable entity) {
		try {
			Object pk = emf.getPersistenceUnitUtil().getIdentifier(entity);
			return pk;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<? extends Serializable> menuByUser(Class<? extends Serializable> entity,  long usuId, long sisId) {
		em = emf.createEntityManager();
		try {
			//Query q = em.createQuery("select object(o) from GimAdmMenu as o where o.mnuPadre = :mnuPadre and o.mnuActivo = 'A'  order by o.mnuPadre, o.mnuOrden");
			Query q = em.createNativeQuery("select a.* from cnrapps_ow.apps_menus a " +
					"where exists ( select 1 from cnrapps_ow.apps_menus_roles b, cnrapps_ow.apps_usuarios_permisos c " +
					"where a.mnu_id = b.mnr_mnu_id and b.mnr_rol_id = c.upr_rol_id and b.reg_activo = 1 " +
					"and c.reg_activo = 1 and c.upr_usu_id = ? and c.upr_sis_id = a.mnu_sis_id) " +
					"and a.mnu_sis_id = ? and a.reg_activo = 1 order by a.mnu_id_padre, a.mnu_orden", entity);
			q.setParameter(1,usuId);
			q.setParameter(2,sisId);
			List<? extends Serializable> lista = q.getResultList();
			return lista;
		} finally {
			em.close();
		}
	}

	@Override
	public int yaExiste(String tablas, String condicion) {
		int count = 0;
		try {
			em = emf.createEntityManager();
			String sql = "select count(1) from " + tablas;
			if (condicion != null)
				sql += " where " + condicion;
			Query q = em.createNativeQuery(sql);
			count = Integer.parseInt(q.getSingleResult().toString());
			em.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.clear();
			em.close();
		}
		return count;
	}
}
