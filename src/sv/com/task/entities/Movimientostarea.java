package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the movimientostareas database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="movimientostareas")
public class Movimientostarea implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idMovimientosTareas;

	@Lob
	private String comentario;

	//bi-directional many-to-one association to Estado
	@ManyToOne
	@JoinColumn(name="Estados_idEstado", nullable=false)
	private Estado estado;

	//bi-directional many-to-one association to Proyectoresponsable
	@ManyToOne
	@JoinColumn(name="ProyectoResponsables_idProyectoResponsables", nullable=false)
	private Proyectoresponsable proyectoresponsable;

	public Movimientostarea() {
	}

	public int getIdMovimientosTareas() {
		return this.idMovimientosTareas;
	}

	public void setIdMovimientosTareas(int idMovimientosTareas) {
		this.idMovimientosTareas = idMovimientosTareas;
	}

	public String getComentario() {
		return this.comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Proyectoresponsable getProyectoresponsable() {
		return this.proyectoresponsable;
	}

	public void setProyectoresponsable(Proyectoresponsable proyectoresponsable) {
		this.proyectoresponsable = proyectoresponsable;
	}

}