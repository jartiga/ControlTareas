package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the empleado database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="empleado")
public class Empleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idEmpleado;

	@Column(length=100)
	private String apellido;

	@Column(length=45)
	private String clave;

	@Column(length=100)
	private String nombre;

	@Column(length=45)
	private String usuario;

	//bi-directional many-to-one association to Perfil
	@ManyToOne
	@JoinColumn(name="Perfil_idPerfil", nullable=false)
	private Perfil perfil;

	//bi-directional many-to-one association to Proyectoresponsable
	@OneToMany(mappedBy="empleado")
	private List<Proyectoresponsable> proyectoresponsables;

	public Empleado() {
	}

	public int getIdEmpleado() {
		return this.idEmpleado;
	}

	public void setIdEmpleado(int idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Perfil getPerfil() {
		return this.perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public List<Proyectoresponsable> getProyectoresponsables() {
		return this.proyectoresponsables;
	}

	public void setProyectoresponsables(List<Proyectoresponsable> proyectoresponsables) {
		this.proyectoresponsables = proyectoresponsables;
	}

}