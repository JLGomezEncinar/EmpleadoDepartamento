package iessanalberto.dam2.services;
import iessanalberto.dam2.models.Department;
import iessanalberto.dam2.models.Departments;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static java.lang.IO.println;


public class ReadDepartmentServices {
    public static Departments readDepartment() {

        Departments departments = new Departments();
        final Path ruta = Path.of("src/main/resources/departamento.xml");
        if (Files.isReadable(ruta)) {

            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(Departments.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                departments = (Departments) jaxbUnmarshaller.unmarshal(ruta.toFile());
                println("Se han cargado correctamente los departamentos");



            } catch (JAXBException e) {
                println("No se han podido cargar los departamentos");
            }
        }
        return departments;
    }
}

