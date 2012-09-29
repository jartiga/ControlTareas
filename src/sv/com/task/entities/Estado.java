package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estados database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="estados")
public class Estado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idEstado;

	@Column(length=75)
	private String nombre;

	//bi-directional many-to-one association to Movimientostarea
	@OneToMany(mappedBy="estado")
	private List<Movimientostarea> movimientostareas;

	public Estado() {
	}

	public int getIdEstado() {
		return this.idEstado;
	}

	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Movimientostarea> getMovimientostareas() {
		return this.movimientostareas;
	}

	public void setMovimientostareas(List<Movimientostarea> movimientostareas) {
		this.movimientostareas = movimientostareas;
	}

}