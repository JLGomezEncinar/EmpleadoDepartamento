package iessanalberto.dam2.models;

import com.google.gson.annotations.SerializedName;

public class Empleado {
    private String nombre;
    private double sueldo;
    @SerializedName("año")
    private Integer anyo_nacimiento;
    private Integer antiguedad;
    private String idDep;

    public String getIdDep() {
        return idDep;
    }

    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public Integer getAnyo_nacimiento() {
        return anyo_nacimiento;
    }

    public void setAnyo_nacimiento(Integer anyo_nacimiento) {
        this.anyo_nacimiento = anyo_nacimiento;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
