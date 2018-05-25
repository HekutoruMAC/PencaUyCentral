package entidades;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Equipos_Grupo_Identidad implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Equipo equipo;
	private Grupo grupo;	
	
	public Equipos_Grupo_Identidad() {		
	}
	
		
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public Grupo getGrupo() {
		return grupo;
	}
	
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Equipos_Grupo_Identidad that = (Equipos_Grupo_Identidad) o;

        if (!grupo.equals(that.grupo)) return false;
        return equipo.equals(that.equipo);
    }

    @Override
    public int hashCode() {
        int result = grupo.hashCode();
        result = 31 * result + equipo.hashCode();
        return result;
    }
	
	
	

}
