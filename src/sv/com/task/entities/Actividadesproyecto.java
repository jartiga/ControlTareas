package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the actividadesproyecto database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="actividadesproyecto")
public class Actividadesproyecto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idActividadesProyecto;

	@Lob
	private String comentario;

	//bi-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name="Tareas_idTareas", nullable=false)
	private Tarea tarea;

	//bi-directional many-to-one association to Tipoactividad
	@ManyToOne
	@JoinColumn(name="TipoActividad_idTipoActividad", nullable=false)
	private Tipoactividad tipoactividad;

	public Actividadesproyecto() {
	}

	public int getIdActividadesProyecto() {
		return this.idActividadesProyecto;
	}

	public void setIdActividadesProyecto(int idActividadesProyecto) {
		this.idActividadesProyecto = idActividadesProyecto;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

	public Tipoactividad getTipoactividad() {
		return this.tipoactividad;
	}

	public void setTipoactividad(Tipoactividad tipoactividad) {
		this.tipoactividad = tipoactividad;
	}

}