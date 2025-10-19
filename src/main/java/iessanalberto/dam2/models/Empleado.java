package iessanalberto.dam2.models;

import com.google.gson.annotations.SerializedName;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"nombre", "sueldo","anyo_nacimiento","antiguedad"})
public class Empleado {
    private String nombre;
    private double sueldo;
    @SerializedName("año")
    private Integer anyo_nacimiento;
    private Integer antiguedad;
    private String idDep;
@XmlTransient
    public String getIdDep() {
        return idDep;
    }

    public void setIdDep(String idDep) {
        this.idDep = idDep;
    }

    public Empleado() {
    }
@XmlElement(name = "nombre")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
@XmlElement(name= "sueldo")
    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }
@XmlElement(name = "año")
    public Integer getAnyo_nacimiento() {
        return anyo_nacimiento;
    }

    public void setAnyo_nacimiento(Integer anyo_nacimiento) {
        this.anyo_nacimiento = anyo_nacimiento;
    }
@XmlElement(name = "antigüedad")
    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }
}
