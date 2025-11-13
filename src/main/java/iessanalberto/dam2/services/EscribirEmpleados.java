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
    public static String escribirEmpresa(ArrayList<Empleado> empleados) throws JAXBException {
        String respuesta = "";
        UserMethods userMethods = new UserMethods();
        LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();
        ArrayList<Empleado> empleadosJSON = new ArrayList<>();
        // Si no hemos añadido empleados nuevos al JSON leemos el JSON original para añadir los empleados
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
        respuesta = "Se ha creado el archivo de la empresa XML";
        } catch (Exception e) {
            respuesta = "Error a la hora de crear el archivo";
        }
        return respuesta;
    }

    public static String escribirEmpresaJSON(ArrayList<Empleado> empleados) {
        String respuesta = "";
        UserMethods userMethods = new UserMethods();
        LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();
        ArrayList<Empleado> empleadosJSON = new ArrayList<>();
        // Si no hemos añadido empleados nuevos al JSON leemos el JSON original para añadir los empleados
        if (!Files.exists(Path.of("target/empleados.json"))) {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosJSON(Path.of("src/main/resources/nuevosEmpleados.json"));
        } else {
            empleadosJSON = leerNuevosEmpleados.leerEmpleadosConDepartamentoJSON(Path.of("target/empleados.json"));
        }
        for (Empleado empleado : empleadosJSON) {
            empleados.add(empleado);
        }
        final Path rutaDestino = Path.of("target/empresa.json");
        // Como no hemos marcado como Expose el id de departamento del empleado no nos aparecerá en el JSON creado para la empresa
        Gson gson = new GsonBuilder()
                .setPrettyPrinting().excludeFieldsWithoutExposeAnnotation() // para formato bonito
                .create();
        try (FileWriter writer = new FileWriter(rutaDestino.toFile())) {
            gson.toJson(userMethods.unirEmpleados(empleados), writer);
            respuesta = "Se ha creado el archivo de la empresa JSON";
        } catch (IOException e) {
            respuesta = "Error a la hora de crear el archivo";;
        }
        return respuesta;
    }
}
