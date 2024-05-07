package domain;

import java.util.ArrayList;
import java.util.List;

public class Alumno {
    public String nombre;
    public List<Materia> materiasAprobadas;

    public Alumno(String nombre) {
        this.nombre = nombre;
        this.materiasAprobadas = new ArrayList<>();
    }

    public void agregarMateriaAprobada(Materia materia) {
        this.materiasAprobadas.add(materia);
    }

    public boolean cumpleCorrelatividad(Materia materia) {
        return materiasAprobadas.containsAll(materia.getCorrelativas());
    }
}
