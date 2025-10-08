package iessanalberto.dam2.cmd;

import iessanalberto.dam2.models.Empleado;
import iessanalberto.dam2.services.ReadDepartmentServices;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.IO.println;

public class MenuInicial {

    private Scanner scanner = new Scanner(System.in);
    private boolean salir = false;
    private boolean introducir = false;
    private boolean comprobar = false;

    ArrayList <Empleado> empleados = new ArrayList<>();
    ReadDepartmentServices readDepartmentServices = new ReadDepartmentServices();

    public void muestraMenu(){
        String opcion;
        do {
            System.out.println("Elige una opcion:");
            System.out.println("1. Introducir empleados");
            System.out.println("2. Sin implementar");
            System.out.println("0. Salir");
            opcion = this.pideOpcion();
            this.procesaOpcion(opcion);
        } while (!salir);
    }
    private void menuEmpleados(){
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

            do {
                println("¿Desea introducir otro empleado?: s/n");
                continuar = this.pideOpcion().toLowerCase();
                if (continuar.equals("s") || continuar.equals("n")){
                    comprobar = true;
                }
introducirEmpleado(continuar);
            } while (!comprobar);
            comprobar = false;

        } while (!introducir);
    }


    private String pideOpcion() {
        return this.scanner.nextLine();
    }

    private void procesaOpcion(String opcion) {
        switch (opcion) {
            case "0" -> salir = true;
            case "1" -> menuEmpleados();
            case "2" -> readDepartmentServices.readDepartment();
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
}
