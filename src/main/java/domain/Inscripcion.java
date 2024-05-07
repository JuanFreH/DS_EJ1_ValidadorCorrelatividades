package domain;

import java.util.ArrayList;
import java.util.List;

public class Inscripcion {
    public Alumno alumno;
    public List<Materia> materias;

    public Inscripcion(Alumno alumno, List<Materia> materias) {
        this.alumno = alumno;
        this.materias = materias;
    }

    public boolean aprobada() {
        return this.materias.stream().allMatch(materia -> this.alumno.cumpleCorrelatividad(materia));
    }
}
