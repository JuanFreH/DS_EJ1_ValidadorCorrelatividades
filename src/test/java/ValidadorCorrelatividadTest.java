import domain.Alumno;
import domain.Inscripcion;
import domain.Materia;
import domain.NombreMateria;
import org.junit.jupiter.api.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidadorCorrelatividadTest {

    private Materia algoritmos;
    private Materia arquitecturaDeComputadoras;
    private Materia paradigmas;
    private Materia diseñoDeSistemas;
    private Materia sistemasOperativos;
    private Alumno juan;

    @BeforeEach
    void init() {
        algoritmos = new Materia(NombreMateria.ALGORITMOS);
        arquitecturaDeComputadoras = new Materia(NombreMateria.ARQUITECTURA_DE_COMPUTADORAS);
        paradigmas = new Materia(NombreMateria.PARADIGMAS);
        diseñoDeSistemas = new Materia(NombreMateria.DISEÑO_DE_SISTEMAS);
        sistemasOperativos = new Materia(NombreMateria.SISTEMAS_OPERATIVOS);

        paradigmas.agregarCorrelativa(algoritmos);
        diseñoDeSistemas.agregarCorrelativa(algoritmos);
        diseñoDeSistemas.agregarCorrelativa(paradigmas);
        sistemasOperativos.agregarCorrelativa(algoritmos);
        sistemasOperativos.agregarCorrelativa(arquitecturaDeComputadoras);

        juan = new Alumno("Juan");
    }

    //ALGORITMOS NO TIENE CORRELATIVAS
    @Test
    public void algoritmosCorrelatividad() {
        assertTrue(algoritmos.getCorrelativas().isEmpty());
    }

    //ARQUITECTURA_DE_COMPUTADORAS NO TIENE CORRELATIVAS
    @Test
    public void arquitecturaCorrelatividad() {
        assertTrue(arquitecturaDeComputadoras.getCorrelativas().isEmpty());
    }

    //PARADIGMAS TIENE CORRERLATIVA ALGORITMOS
    @Test
    public void paradigmasCorrelatividad() {
        assertTrue(paradigmas.esCorrelativa(algoritmos));
    }

    //DISEÑO_DE_SISTEMAS TIENE CORRELATIVAS ALGORITMOS Y PARADIGMAS
    @Test
    public void diseñoSistemasCorrelatividad() {
        assertTrue(diseñoDeSistemas.esCorrelativa(algoritmos));
        assertTrue(diseñoDeSistemas.esCorrelativa(paradigmas));
    }

    //SISTEMAS_OPERATIVOS TIENE CORRELATIVAS ALGORITMOS Y ARQUITECTURA
    @Test
    public void sistemasOperativosCorrelatividad() {
        assertTrue(sistemasOperativos.esCorrelativa(algoritmos));
        assertTrue(sistemasOperativos.esCorrelativa(arquitecturaDeComputadoras));
    }

    //ALUMNO PUEDE INSCRIBIRSE
    @Test
    public void inscripcionAprobada() {
        juan.agregarMateriaAprobada(algoritmos);
        juan.agregarMateriaAprobada(arquitecturaDeComputadoras);
        juan.agregarMateriaAprobada(paradigmas);

        List<Materia> materiasInscripcion = List.of(diseñoDeSistemas, sistemasOperativos);

        Inscripcion inscripcion = new Inscripcion(juan, materiasInscripcion);

        assertTrue(inscripcion.aprobada());
    }

    //ALUMNO NO PUEDE INSCRIBIRSE
    @Test
    public void inscripcionDesaprobada() {
        juan.agregarMateriaAprobada(algoritmos);
        juan.agregarMateriaAprobada(paradigmas);

        List<Materia> materiasInscripcion = List.of(diseñoDeSistemas, sistemasOperativos);

        Inscripcion inscripcion = new Inscripcion(juan, materiasInscripcion);

        assertFalse(inscripcion.aprobada());
    }
}
