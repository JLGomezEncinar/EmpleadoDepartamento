package iessanalberto.dam2.cmd;

import iessanalberto.dam2.libs.UserMethods;
import iessanalberto.dam2.models.Department;
import iessanalberto.dam2.models.Empleado;
import iessanalberto.dam2.services.LeerNuevosEmpleados;
import iessanalberto.dam2.services.ReadDepartmentServices;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;

public class MenuInicial {

    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;
    private boolean introducir = false;
    private boolean comprobar = false;
    private boolean seguir = false;

    ArrayList<Empleado> empleados = new ArrayList<>();
    ArrayList<Department> departmentList = new ArrayList<>();
    ReadDepartmentServices readDepartmentServices = new ReadDepartmentServices();
    UserMethods userMethods = new UserMethods();
    LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();

    public void muestraMenu() {
        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Introducir empleados");
            System.out.println("2. Sin implementar");
            System.out.println("3. Añadir empleados a departamento");
            System.out.println("4. Leer nuevosEmpleados.json");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }

    private void menuEmpleados() {
        String continuar;
        do {
            Empleado empleadoAux = new Empleado();
            println("Introduce el nombre del empleado");
            empleadoAux.setNombre(pideOpcion());
            println("Introduce el suedo del empleado");
            empleadoAux.setSueldo(Double.parseDouble(pideOpcion()));
            println("Introduce el año de nacimiento del empleado");
            empleadoAux.setAnyo_nacimiento(Integer.parseInt(pideOpcion()));
            println("Introduce la antiguedad del empleado");
            empleadoAux.setAntiguedad(Integer.parseInt(pideOpcion()));
            empleados.add(empleadoAux);
            try (PrintWriter writer = new PrintWriter(new FileWriter("target/empleados.csv"),true)) {

                writer.println(empleadoAux.getNombre()+ "," + empleadoAux.getSueldo()+ ","+empleadoAux.getAnyo_nacimiento()+","+empleadoAux.getAntiguedad());



            } catch (IOException e) {
                e.printStackTrace();
            }


            do {
                println("¿Desea introducir otro empleado?: s/n");
                continuar = this.pideOpcion().toLowerCase();
                if (continuar.equals("s") || continuar.equals("n")) {
                    comprobar = true;
                    introducirEmpleado(continuar);
                }

            } while (!comprobar);
            comprobar = false;

        } while (!introducir);
    }

    private void asignarEmpleado() {
        String avanzar;
        departmentList = (ArrayList<Department>) readDepartmentServices.readDepartment();
        for (Empleado empleado : empleados) {
            do {
                println("Introduce el departamento al que se asignará el empleado " + empleado.getNombre() + ":");
                int numDepartamento = 1;
                for (Department department : departmentList) {
                    println(numDepartamento + "-" + department.getName());
                    numDepartamento++;
                }
                avanzar = this.pideOpcion();
                introducirDepartamento(avanzar);
            } while (!seguir);
            seguir = false;
        }
    }


    private String pideOpcion() {
        return this.scanner.nextLine();
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> menuEmpleados();
            case "2" -> readDepartmentServices.readDepartment();
            case "3" -> asignarEmpleado();
            case "4" -> {
                ArrayList<Empleado> empleados1 = (leerNuevosEmpleados.leerEmpleadosJSON(userMethods.pedirRutaJson("Introduce la ruta al archivo JSON")));
                for (Empleado empleado : empleados1) {
                    println(empleado.getNombre() + empleado.getAntiguedad());
                }
            }

            default -> System.out.println("Opcion no valida");
        }
    }

    private void introducirEmpleado(String continuar) {
        switch (continuar) {
            case "s" -> {
            }
            case "n" -> introducir = true;
            default -> System.out.println("Opcion no valida");
        }
    }

    private void introducirDepartamento(String adicionar) {
        switch (adicionar) {
            case "Ventas", "Producción", "Informática", "Compras" -> seguir = true;


            default -> System.out.println("Opcion no valida");
        }
    }
}
