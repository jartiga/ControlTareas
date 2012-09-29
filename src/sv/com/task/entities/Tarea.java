package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the tareas database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="tareas")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTareas;

	@Column(length=250)
	private String descripcion;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaTarea;

	//bi-directional many-to-one association to Actividadesproyecto
	@OneToMany(mappedBy="tarea")
	private List<Actividadesproyecto> actividadesproyectos;

	//bi-directional many-to-one association to Menasjesportarea
	@OneToMany(mappedBy="tarea")
	private List<Menasjesportarea> menasjesportareas;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne
	@JoinColumn(name="Proyecto_idProyecto", nullable=false)
	private Proyecto proyecto;

	//bi-directional many-to-one association to Tareasresponsable
	@OneToMany(mappedBy="tarea")
	private List<Tareasresponsable> tareasresponsables;

	public Tarea() {
	}

	public int getIdTareas() {
		return this.idTareas;
	}

	public void setIdTareas(int idTareas) {
		this.idTareas = idTareas;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaTarea() {
		return this.fechaTarea;
	}

	public void setFechaTarea(Date fechaTarea) {
		this.fechaTarea = fechaTarea;
	}

	public List<Actividadesproyecto> getActividadesproyectos() {
		return this.actividadesproyectos;
	}

	public void setActividadesproyectos(List<Actividadesproyecto> actividadesproyectos) {
		this.actividadesproyectos = actividadesproyectos;
	}

	public List<Menasjesportarea> getMenasjesportareas() {
		return this.menasjesportareas;
	}

	public void setMenasjesportareas(List<Menasjesportarea> menasjesportareas) {
		this.menasjesportareas = menasjesportareas;
	}

	public Proyecto getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}

	public List<Tareasresponsable> getTareasresponsables() {
		return this.tareasresponsables;
	}

	public void setTareasresponsables(List<Tareasresponsable> tareasresponsables) {
		this.tareasresponsables = tareasresponsables;
	}

}