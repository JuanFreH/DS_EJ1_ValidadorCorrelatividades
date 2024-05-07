package domain;

import java.util.ArrayList;
import java.util.List;

public class Materia {
    public NombreMateria nombre;
    public List<Materia> correlativas;

    public Materia(NombreMateria nombre) {
        this.nombre = nombre;
        this.correlativas = new ArrayList<>();
    }

    public List<Materia> getCorrelativas() {
        return correlativas;
    }

    public void agregarCorrelativa(Materia materia) {
        correlativas.add(materia);
    }

    public boolean esCorrelativa(Materia materia) {
        return correlativas.contains(materia);
    }
}
