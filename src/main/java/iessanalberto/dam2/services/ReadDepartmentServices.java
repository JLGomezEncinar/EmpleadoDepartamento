package iessanalberto.dam2.services;
import iessanalberto.dam2.models.Department;
import iessanalberto.dam2.models.Departments;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;



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




            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return departments;
    }
}

