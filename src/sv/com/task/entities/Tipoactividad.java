package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tipoactividad database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="tipoactividad")
public class Tipoactividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idTipoActividad;

	@Column(length=45)
	private String nombre;

	//bi-directional many-to-one association to Actividadesproyecto
	@OneToMany(mappedBy="tipoactividad")
	private List<Actividadesproyecto> actividadesproyectos;

	public Tipoactividad() {
	}

	public int getIdTipoActividad() {
		return this.idTipoActividad;
	}

	public void setIdTipoActividad(int idTipoActividad) {
		this.idTipoActividad = idTipoActividad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividadesproyecto> getActividadesproyectos() {
		return this.actividadesproyectos;
	}

	public void setActividadesproyectos(List<Actividadesproyecto> actividadesproyectos) {
		this.actividadesproyectos = actividadesproyectos;
	}

}