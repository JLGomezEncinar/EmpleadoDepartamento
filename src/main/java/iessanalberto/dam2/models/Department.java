package iessanalberto.dam2.models;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.ArrayList;

@XmlRootElement(name = "departamento")

public class Department {
    @Expose
    private String id;
    @Expose
    private String name;
    @Expose
    private String locality;
    @Expose
    private ArrayList<Empleado> empleados;

    @XmlElement(name = "nombre")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "localidad")
    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public Department() {
    }

    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElementWrapper(name = "empleados")
    @XmlElement(name = "empleado")
    public ArrayList<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(ArrayList<Empleado> empleados) {
        this.empleados = empleados;
    }
}
