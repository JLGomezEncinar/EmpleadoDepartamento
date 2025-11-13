package iessanalberto.dam2.libs;

import iessanalberto.dam2.models.Department;
import iessanalberto.dam2.models.Departments;
import iessanalberto.dam2.models.Empleado;
import iessanalberto.dam2.services.ReadDepartmentServices;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;

public class UserMethods {
    static void mostrarEnPantalla(String mensaje) {
        System.out.println(mensaje);
    }
// Función que devuelve la fecha actual
    public static Integer fechaSistema() {
        return Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
    }

    public static Path pedirRutaJson(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                if (rutaString.endsWith(".json")) {
                    pathOK = true;
                } else {
                    mostrarEnPantalla("La ruta no se corresponde con un archivo json. ");
                }
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
    }

    public static String leerCSV(ArrayList<Empleado> empleados, String rutaArchivo) {
    String respuesta = "";

        String linea;


        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {


            while ((linea = br.readLine()) != null) {
                // Divide la línea en columnas separadas por comas
                String[] columnas = linea.split(",");

                // Ejemplo: imprimir los valores

                Empleado empleadoAux = new Empleado();
                empleadoAux.setNombre(columnas[0]);
                empleadoAux.setSueldo(Double.parseDouble(columnas[1]));
                empleadoAux.setAnyo_nacimiento(Integer.valueOf(columnas[2]));
                empleadoAux.setAntiguedad(Integer.valueOf(columnas[3]));
                //Con esto podemos leer aquellos CSV de empleados que ya tienen un id de departamento
                if (columnas.length == 5) {
                    empleadoAux.setIdDep(columnas[4]);
                }
                empleados.add(empleadoAux);

            }
            respuesta = "Se ha leído correctamente el archivo CSV";


        } catch (IOException e) {
            respuesta = "Error al leer el archivo";
        }
        return respuesta;
    }
// Función que comprueba que el usuario introduce un double
    public static double leerDouble(String mensaje, Scanner scanner) {
        double decimal = 0;
        boolean valido = false;
        while (!valido) {
            println(mensaje);
            try {
                decimal = Double.parseDouble(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                println("El valor introducido no es válido");
            }
        }
        return decimal;
    }
    // Función que comprueba que el usuario introduce un entero
    public static int leerEntero(String mensaje, Scanner scanner) {
        int entero = 0;
        boolean valido = false;
        while (!valido) {
            println(mensaje);
            try {
                entero = Integer.parseInt(scanner.nextLine());
                valido = true;
            } catch (NumberFormatException e) {
                println("El valor introducido no es válido");
            }
        }
        return entero;
    }

    public static String guardarEmpleadosCSV(ArrayList<Empleado> empleados) {
        String respuesta = "";
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/empleados.csv"), true)) {

            for (Empleado empleado : empleados) {

                writer.println(empleado.getNombre() + "," + empleado.getSueldo() + "," + empleado.getAnyo_nacimiento() + "," + empleado.getAntiguedad());

            }
        respuesta = "Se han guardado correctamente los empleados en el archivo CSV";
        } catch (IOException e) {
            respuesta = "Error a la hora de guardar los empleados";
        }
//Limpiamos la lista de empleados para no volver a guardar en el CSV los empleados ya introducidos
        empleados.clear();
        return respuesta;
    }

    public static String guardarEmpleadosConDepartamentoCSV(ArrayList<Empleado> empleados) {
        String respuesta = "";
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/empleadosConDepartamento.csv"), true)) {

            for (Empleado empleado : empleados) {

                writer.println(empleado.getNombre() + "," + empleado.getSueldo() + "," + empleado.getAnyo_nacimiento() + "," + empleado.getAntiguedad() + "," + empleado.getIdDep());

            }
            respuesta = "Se han guardado correctamente los empleados en el archivo CSV";

        } catch (IOException e) {
            respuesta = "Error a la hora de guardar los empleados";
        }
//Limpiamos la lista de empleados para no volver a guardar en el CSV los empleados ya introducidos
        empleados.clear();
        return respuesta;
    }

    public static Departments unirEmpleados(ArrayList<Empleado> empleados) {
        if (Files.exists(Path.of("target/empleadosConDepartamento.csv"))) {
            leerCSV(empleados, "target/empleadosConDepartamento.csv");
        }
        ReadDepartmentServices readDepartmentServices = new ReadDepartmentServices();
        Departments departments = readDepartmentServices.readDepartment();
        for (Department department : departments.getDepartments()) {
            ArrayList<Empleado> empleadosPorDepartamento = new ArrayList<>();
            for (Empleado empleado : empleados) {
                // Obtenemos de cada empleado su Id de departamento para relacionarlo con su departamento
                String numDepartamento = empleado.getIdDep();
                if (department.getId().equals(numDepartamento)) {
                    empleadosPorDepartamento.add(empleado);
                }

            }
            department.setEmpleados(empleadosPorDepartamento);

        }
        println("Se ha relacionado cada empleado con su departamento");
        return departments;
    }


}

