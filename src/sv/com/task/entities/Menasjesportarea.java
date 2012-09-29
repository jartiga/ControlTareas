package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the menasjesportarea database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="menasjesportarea")
public class Menasjesportarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idMenasjesPorTarea;

	@Column(length=45)
	private String comentario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Tarea
	@ManyToOne
	@JoinColumn(name="Tareas_idTareas", nullable=false)
	private Tarea tarea;

	public Menasjesportarea() {
	}

	public int getIdMenasjesPorTarea() {
		return this.idMenasjesPorTarea;
	}

	public void setIdMenasjesPorTarea(int idMenasjesPorTarea) {
		this.idMenasjesPorTarea = idMenasjesPorTarea;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}