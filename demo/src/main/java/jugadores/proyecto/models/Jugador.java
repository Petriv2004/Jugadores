package jugadores.proyecto.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "EDAD")
    private int edad;

    @Column(name = "POSICION")
    private String posicion;

    @ManyToOne
    @JoinColumn(name = "equipo_id")
    //@JsonIgnore
    private Equipo equipo;

    @ManyToMany
    @JoinTable(
            name = "CAMPEONATO_JUGADOR",
            joinColumns = @JoinColumn(name = "jugador_id"),
            inverseJoinColumns = @JoinColumn(name = "campeonato_id")
    )
    private List<Campeonato> campeonatoList;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public int getEdad() {return edad;}

    public void setEdad(int edad) {this.edad = edad;}

    public String getPosicion() {return posicion;}

    public void setPosicion(String posicion) {this.posicion = posicion;}

    public Equipo getEquipo() {
        return equipo;
    }

    public List<Campeonato> getCampeonatoList() {
        return campeonatoList;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    public void setCampeonatoList(List<Campeonato> campeonatoList) {
        this.campeonatoList = campeonatoList;
    }
}