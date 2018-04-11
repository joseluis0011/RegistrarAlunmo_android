package BDHELPER;

/**
 * Created by JOSE LUIS on 4/04/2018.
 */

public class alumnos {
    private int idalumno,codigo;
    private String nombre , escuela;
    public alumnos(int idalumno, String nombre, String escuela, int codigo)
    {
        setIdalumno(idalumno);
        setNombre(nombre);
        setCodigo(codigo);
        setEscuela(escuela);
    }
    public int getIdalumno() {
        return idalumno;
    }

    public void setIdalumno(int idalumno) {
        this.idalumno = idalumno;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }
}
