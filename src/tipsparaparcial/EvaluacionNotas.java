/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipsparaparcial;
import java.util.Scanner;
/**
 *
 * @author Danny
 */
public class EvaluacionNotas {
    static Scanner sc = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CALIFICACIONES ===");
        
        // 1. Pedimos la cantidad para saber de qué tamaño crear los arreglos
        System.out.print("¿Cuántos estudiantes desea registrar?: ");
        int cantidadEstudiantes = sc.nextInt();
        sc.nextLine(); // Limpieza obligatoria del buffer (tecla Enter)
        
        // 2. AHORA creamos los arreglos usando la variable 'cantidadEstudiantes'
        String[] nombres = new String[cantidadEstudiantes];
        double[] notaPractica = new double[cantidadEstudiantes];
        double[] notaTeorica = new double[cantidadEstudiantes];
        
        // 3. Llamamos al método que se encarga de llenar los datos por teclado
        llenarDatos(nombres, notaPractica, notaTeorica);
        
        // 4. Calculamos las definitivas almacenándolas en un nuevo arreglo paralelo
        double[] definitivas = calcularDefinitivas(notaPractica, notaTeorica);
        
        // 5. Imprimimos el mejor estudiante
        int idxMejor = buscarMejorEstudiante(definitivas);
        System.out.println("\n--- ESTUDIANTE DESTACADO ---");
        System.out.println("El mejor estudiante es " + nombres[idxMejor] + 
                           " con una definitiva de " + definitivas[idxMejor]);
        
        // 6. Mostramos los que están perdiendo la materia
        mostrarEstudiantesEnRiesgo(nombres, definitivas);
    }

    // Método para llenar arreglos mediante digitación del usuario
    public static void llenarDatos(String[] nombres, double[] notaPractica, double[] notaTeorica) {
        System.out.println("\n--- INGRESO DE DATOS ---");
        for (int i = 0; i < nombres.length; i++) {
            System.out.println("Estudiante #" + (i + 1));
            
            System.out.print("Nombre completo: ");
            nombres[i] = sc.nextLine();
            
            System.out.print("Nota de la evaluación práctica (0.0 a 5.0): ");
            notaPractica[i] = sc.nextDouble();
            
            System.out.print("Nota de la evaluación teórica (0.0 a 5.0): ");
            notaTeorica[i] = sc.nextDouble();
            
            /* Siempre limpiar el buffer después de un nextDouble o nextInt
             si en la siguiente vuelta del ciclo vas a pedir un nextLine (un texto).*/
            sc.nextLine(); 
            System.out.println(); // Un espacio en blanco para separar en la consola
        }
    }

    // Método que crea y devuelve un arreglo completo
    public static double[] calcularDefinitivas(double[] practicas, double[] teoricas) {
        // Creamos un arreglo nuevo del mismo tamaño que los demás
        double[] promedios = new double[practicas.length];
        
        for (int i = 0; i < practicas.length; i++) {
            promedios[i] = (practicas[i] + teoricas[i]) / 2.0;
        }
        return promedios;
    }

    // Retorna la posición (índice) del número mayor
    public static int buscarMejorEstudiante(double[] definitivas) {
        int indiceMayor = 0;
        for (int i = 1; i < definitivas.length; i++) {
            if (definitivas[i] > definitivas[indiceMayor]) {
                indiceMayor = i;
            }
        }
        return indiceMayor;
    }

    // Método void (solo imprime, no devuelve nada)
    public static void mostrarEstudiantesEnRiesgo(String[] nombres, double[] definitivas) {
        System.out.println("\n--- ESTUDIANTE(S) EN RIESGO ACADÉMICO (< 3.0) ---");
        boolean existen = false;
        
        for (int i = 0; i < nombres.length; i++) {
            if (definitivas[i] < 3.0) {
                System.out.println("Atención: " + nombres[i] + " va perdiendo con " + definitivas[i]);
                existen = true;
            }
        }
        
        if (existen == false) {
            System.out.println("Ningún estudiante está en riesgo. ¡Buen grupo!");
        }
    }
}
