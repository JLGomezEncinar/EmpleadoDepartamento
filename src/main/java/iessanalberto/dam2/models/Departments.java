package iessanalberto.dam2.models;

import com.google.gson.annotations.Expose;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "departamentos")
public class Departments {
    @Expose
    private List<Department> departments;

    @XmlElement(name = "departamento")
    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }
}