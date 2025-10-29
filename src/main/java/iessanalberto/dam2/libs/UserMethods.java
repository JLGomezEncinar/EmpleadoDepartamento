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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import static java.lang.IO.println;

public class UserMethods {
    static void mostrarEnPantalla(String mensaje) {
        System.out.println(mensaje);
    }

    public static Integer fechaSistema() {
        return Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy")));
    }

    public static Path pedirRuta(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                pathOK = true;
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
    }

    public static Path pedirRutaXML(final String mensaje) {
        BufferedReader dataIn = new BufferedReader(new InputStreamReader(System.in));
        String rutaString = "";
        Path ruta = Path.of(rutaString);
        boolean pathOK = false;
        while (!pathOK) {
            try {
                mostrarEnPantalla(mensaje);
                rutaString = dataIn.readLine();
                ruta = Path.of(rutaString);
                if (rutaString.endsWith(".xml")) {
                    pathOK = true;
                } else {
                    mostrarEnPantalla("La ruta no se corresponde con un archivo xml. ");
                }
            } catch (IOException e) {
                mostrarEnPantalla("Vuelve a introducir el dato, por favor");
            } catch (InvalidPathException e) {
                mostrarEnPantalla("La ruta contiene caracteres ilegales");
            }
        }
        return ruta;
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

    public static boolean dirEscribible(Path p) {
        //método que chequea si se puede escribir en un directorio y si no lo crea
        boolean dirOK = false;
        if (Files.exists(p) && Files.isDirectory(p)) {
            if (Files.isWritable(p)) {
                dirOK = true;
            }

        } else {
            try {
                Files.createDirectory(p);
                dirOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return dirOK;
    }

    //
    public static boolean ficheroLegible(Path p) {
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isReadable(p)) {
                ficheroOK = true;
            }
        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

    public static boolean ficheroEscribible(Path p) {
        //método que comprueba si se puede escribir en un fichero y si no lo crea
        boolean ficheroOK = false;
        if (Files.exists(p)) {
            if (Files.isWritable(p)) {
                ficheroOK = true;
            }

        } else {
            try {
                Files.createFile(p);
                ficheroOK = true;
            } catch (IOException e) {
                System.out.println("Error al intentar crear el fichero.");
            }
        }
        return ficheroOK;
    }

    public static void leerCSV(ArrayList<Empleado> empleados, String rutaArchivo) {


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
                if (columnas.length == 5){
                    empleadoAux.setIdDep(columnas[4]);
                }
                empleados.add(empleadoAux);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static double leerDecimal(String mensaje, Scanner scanner){
        double decimal = 0;
        boolean valido = false;
        while (!valido){
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
    public static int leerEntero(String mensaje, Scanner scanner){
        int entero = 0;
        boolean valido = false;
        while (!valido){
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

    public static void guardarEmpleadosCSV(ArrayList<Empleado> empleados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/empleados.csv"), true)) {

            for (Empleado empleado : empleados) {

                writer.println(empleado.getNombre() + "," + empleado.getSueldo() + "," + empleado.getAnyo_nacimiento() + "," + empleado.getAntiguedad());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        empleados.clear();
    }
    public static void guardarEmpleadosConDepartamentoCSV(ArrayList<Empleado> empleados) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("target/empleadosConDepartamento.csv"), true)) {

            for (Empleado empleado : empleados) {

                writer.println(empleado.getNombre() + "," + empleado.getSueldo() + "," + empleado.getAnyo_nacimiento() + "," + empleado.getAntiguedad()+","+empleado.getIdDep());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        empleados.clear();
    }
    public static Departments unirEmpleados(ArrayList<Empleado> empleados) {
        if (Files.exists(Path.of("target/empleadosConDepartamento.csv"))) {
            leerCSV(empleados, "target/empleadosConDepartamento.csv");
        }
        ReadDepartmentServices readDepartmentServices = new ReadDepartmentServices();
        Departments departments =readDepartmentServices.readDepartment();
        for(Department department: departments.getDepartments()) {
            ArrayList<Empleado> empleadosPorDepartamento = new ArrayList<>();
            for (Empleado empleado: empleados){
                String numDepartamento = empleado.getIdDep();
                if (department.getId().equals(numDepartamento)){
                    empleadosPorDepartamento.add(empleado);
                }

            }
            department.setEmpleados(empleadosPorDepartamento);
        }
return departments;
    }


}

