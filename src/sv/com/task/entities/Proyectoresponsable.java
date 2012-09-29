package sv.com.task.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the proyectoresponsables database table.
 * 
 */
@Entity
@Table(schema="task_ow", name="proyectoresponsables")
public class Proyectoresponsable implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int idProyectoResponsables;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//bi-directional many-to-one association to Movimientostarea
	@OneToMany(mappedBy="proyectoresponsable")
	private List<Movimientostarea> movimientostareas;

	//bi-directional many-to-one association to Empleado
	@ManyToOne
	@JoinColumn(name="Empleado_idEmpleado", nullable=false)
	private Empleado empleado;

	//bi-directional many-to-one association to Proyecto
	@ManyToOne
	@JoinColumn(name="Proyecto_idProyecto", nullable=false)
	private Proyecto proyecto;

	//bi-directional many-to-one association to Tareasresponsable
	@OneToMany(mappedBy="proyectoresponsable")
	private List<Tareasresponsable> tareasresponsables;

	public Proyectoresponsable() {
	}

	public int getIdProyectoResponsables() {
		return this.idProyectoResponsables;
	}

	public void setIdProyectoResponsables(int idProyectoResponsables) {
		this.idProyectoResponsables = idProyectoResponsables;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public List<Movimientostarea> getMovimientostareas() {
		return this.movimientostareas;
	}

	public void setMovimientostareas(List<Movimientostarea> movimientostareas) {
		this.movimientostareas = movimientostareas;
	}

	public Empleado getEmpleado() {
		return this.empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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