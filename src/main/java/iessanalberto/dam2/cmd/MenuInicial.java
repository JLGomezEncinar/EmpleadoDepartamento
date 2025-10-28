package iessanalberto.dam2.cmd;

import iessanalberto.dam2.libs.UserMethods;
import iessanalberto.dam2.models.Department;
import iessanalberto.dam2.models.Empleado;
import iessanalberto.dam2.services.EscribirEmpleados;
import iessanalberto.dam2.services.LeerNuevosEmpleados;
import iessanalberto.dam2.services.ReadDepartmentServices;
import jakarta.xml.bind.JAXBException;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;

public class MenuInicial {

    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;
    private boolean comprobar = false;
    private boolean seguir = false;

    ArrayList<Empleado> empleados = new ArrayList<>();
    ArrayList<Department> departmentList = new ArrayList<>();
    ReadDepartmentServices readDepartmentServices = new ReadDepartmentServices();
    UserMethods userMethods = new UserMethods();
    LeerNuevosEmpleados leerNuevosEmpleados = new LeerNuevosEmpleados();
    EscribirEmpleados escribirEmpleados = new EscribirEmpleados();

    public void muestraMenu() {
        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Introducir empleados");
            System.out.println("2. Mostrar departamentos.xml");
            System.out.println("3. Añadir empleados a departamento");
            System.out.println("4. Leer nuevosEmpleados.json");
            System.out.println("5. Escribir empresa.xml y empresa.json");
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
            try {
                empleadoAux.setSueldo(Double.parseDouble(pideOpcion()));
            } catch (NumberFormatException e) {
                println("El sueldo debe ser un numero");
            }
            println("Introduce el año de nacimiento del empleado");
            try {
                empleadoAux.setAnyo_nacimiento(Integer.parseInt(pideOpcion()));
            } catch (NumberFormatException e) {
                println("El año de nacimiento debe ser un entero");
            }
            println("Introduce la antiguedad del empleado");
            try {
            empleadoAux.setAntiguedad(Integer.parseInt(pideOpcion()));
            } catch (NumberFormatException e) {
                println("La antigüedad debe ser un entero");
            }
            empleados.add(empleadoAux);




            do {
                println("¿Desea introducir otro empleado?: s/n");
                continuar = this.pideOpcion().toLowerCase();
                if (continuar.equals("s") || continuar.equals("n")) {
                    comprobar = true;
                } else {
                        println("Opción no válida");

                }

            } while (!comprobar);
            comprobar = false;

        } while (continuar.equals("s"));
        userMethods.guardarEmpleadosCSV(empleados);

    }

    private void asignarEmpleado() {
        String avanzar;
        userMethods.leerCSV(empleados,"target/empleados.csv");
        for (Empleado empleado : empleados) {
            do {
                println("Introduce el departamento al que se asignará el empleado " + empleado.getNombre() + ":");
                int numDepartamento = 1;
                for (Department department : departmentList) {
                    println(numDepartamento + "-" + department.getName());
                    numDepartamento++;
                }
                avanzar = this.pideOpcion();
                introducirDepartamento(avanzar,empleado);
            } while (!seguir);
            seguir = false;
        }
        userMethods.guardarEmpleadosConDepartamentoCSV(empleados);
    }


    private String pideOpcion() {
        return this.scanner.nextLine();
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> menuEmpleados();
            case "2" -> {
                departmentList = (ArrayList<Department>) readDepartmentServices.readDepartment().getDepartments();
                for (Department department: departmentList){
                    println(department.getId()+" "+department.getName()+" "+department.getLocality());
                }
            }
            case "3" -> asignarEmpleado();
            case "4" -> {
               ArrayList<Empleado> empleados1 = (leerNuevosEmpleados.leerEmpleadosJSON(userMethods.pedirRutaJson("Introduce la ruta al archivo JSON")));

            }
            case "5" -> {
                try {
                    escribirEmpleados.escribirEmpresa(empleados);
                    empleados.clear();
                    escribirEmpleados.escribirEmpresaJSON(empleados);
                } catch (JAXBException e) {
                    throw new RuntimeException(e);
                }
            }

            default -> System.out.println("Opcion no valida");
        }
    }

    private void introducirDepartamento(String adicionar, Empleado empleado) {
        switch (adicionar) {
            case "Ventas" ->{
                empleado.setIdDep("1");
                    seguir = true;
            }
            case "Producción" -> {
                empleado.setIdDep("2");
                seguir = true;
            }
            case "Informática" -> {
                empleado.setIdDep("3");
                seguir = true;
            }
            case "Compras" ->{
                empleado.setIdDep("4");
                seguir = true;
            }


            default -> System.out.println("Opcion no valida");
        }
    }
}
