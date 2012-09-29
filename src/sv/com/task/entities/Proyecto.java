package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proyecto database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="proyecto")
public class Proyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idProyecto;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;

	@Column(length=75)
	private String nombre;

	//bi-directional many-to-one association to Proyectoresponsable
	@OneToMany(mappedBy="proyecto")
	private List<Proyectoresponsable> proyectoresponsables;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="proyecto")
	private List<Tarea> tareas;

	public Proyecto() {
	}

	public int getIdProyecto() {
		return this.idProyecto;
	}

	public void setIdProyecto(int idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaIngreso() {
		return this.fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Proyectoresponsable> getProyectoresponsables() {
		return this.proyectoresponsables;
	}

	public void setProyectoresponsables(List<Proyectoresponsable> proyectoresponsables) {
		this.proyectoresponsables = proyectoresponsables;
	}

	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

}