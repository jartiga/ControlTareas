package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tareasresponsable database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="tareasresponsable")
public class Tareasresponsable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTareasResponsable;

	//bi-directional many-to-one association to Proyectoresponsable
	@ManyToOne
	@JoinColumn(name="ProyectoResponsables_idProyectoResponsables", nullable=false)
	private Proyectoresponsable proyectoresponsable;

	//bi-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name="Tareas_idTareas", nullable=false)
	private Tarea tarea;

	public Tareasresponsable() {
	}

	public int getIdTareasResponsable() {
		return this.idTareasResponsable;
	}

	public void setIdTareasResponsable(int idTareasResponsable) {
		this.idTareasResponsable = idTareasResponsable;
	}

	public Proyectoresponsable getProyectoresponsable() {
		return this.proyectoresponsable;
	}

	public void setProyectoresponsable(Proyectoresponsable proyectoresponsable) {
		this.proyectoresponsable = proyectoresponsable;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}