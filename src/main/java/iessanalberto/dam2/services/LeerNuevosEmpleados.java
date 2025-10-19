package iessanalberto.dam2.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import iessanalberto.dam2.libs.UserMethods;
import iessanalberto.dam2.models.Empleado;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;


public class LeerNuevosEmpleados {

    public static ArrayList<Empleado> leerEmpleadosJSON(Path path) {
        UserMethods userMethods = new UserMethods();
        String mensaje = "";
        ArrayList<Empleado> empleados = new ArrayList<>();

        if (Files.isReadable(path)) {

            try {
                String empleadoJsonString = Files.readString(path);


                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Empleado>>() {
                }.getType();
                empleados = gson.fromJson(empleadoJsonString, listType);
                for (Empleado empleado : empleados) {

                    empleado.setAntiguedad(userMethods.fechaSistema());

                }
                gson = new GsonBuilder().setPrettyPrinting().create();

                try (FileWriter writer = new FileWriter("target/empleados.json")) {
                    gson.toJson(empleados, writer);
                }


            } catch (IOException e) {
                mensaje = "Error al leer la ruta del archivo JSON";
            }

        }
        return empleados;
    }
    public static ArrayList<Empleado> leerEmpleadosConDepartamentoJSON(Path path) {
        UserMethods userMethods = new UserMethods();
        String mensaje = "";
        ArrayList<Empleado> empleados = new ArrayList<>();

        if (Files.isReadable(path)) {

            try {
                String empleadoJsonString = Files.readString(path);


                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Empleado>>() {
                }.getType();
                empleados = gson.fromJson(empleadoJsonString, listType);



            } catch (IOException e) {
                mensaje = "Error al leer la ruta del archivo JSON";
            }

        }
        return empleados;
    }
}

