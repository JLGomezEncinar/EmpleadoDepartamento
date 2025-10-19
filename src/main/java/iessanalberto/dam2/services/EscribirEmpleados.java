package iessanalberto.dam2.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import iessanalberto.dam2.libs.UserMethods;
import iessanalberto.dam2.models.Departments;
import iessanalberto.dam2.models.Empleado;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class EscribirEmpleados {
    public static void escribirEmpresa(ArrayList<Empleado> empleados) throws JAXBException {
        UserMethods userMethods = new UserMethods();
        LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();
        ArrayList<Empleado> empleadosJSON = new ArrayList<>();
        if (!Files.exists(Path.of("target/empleados.json"))) {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosJSON(Path.of("src/main/resources/nuevosEmpleados.json"));
        } else {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosConDepartamentoJSON(Path.of("target/empleados.json"));
        }
        for (Empleado empleado : empleadosJSON) {
            empleados.add(empleado);
        }
        final Path rutaDestino = Path.of("target/empresa.xml");
        JAXBContext jaxbContext = JAXBContext.newInstance(Departments.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        try {Departments departments = userMethods.unirEmpleados(empleados);
        jaxbMarshaller.marshal(departments, rutaDestino.toFile());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void escribirEmpresaJSON(ArrayList<Empleado> empleados) {
        UserMethods userMethods = new UserMethods();
        LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();
        ArrayList<Empleado> empleadosJSON = new ArrayList<>();
        if (!Files.exists(Path.of("target/empleados.json"))) {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosJSON(Path.of("src/main/resources/nuevosEmpleados.json"));
        } else {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosConDepartamentoJSON(Path.of("target/empleados.json"));
        }
        for (Empleado empleado : empleadosJSON) {
            empleados.add(empleado);
        }
        final Path rutaDestino = Path.of("target/empresa.json");
        Gson gson = new GsonBuilder()
                .setPrettyPrinting() // para formato bonito
                .create();
        try (FileWriter writer = new FileWriter(rutaDestino.toFile())) {
            gson.toJson(userMethods.unirEmpleados(empleados), writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
