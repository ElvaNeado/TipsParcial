/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipsparaparcial;

/**
 *
 * @author Danny
 */
public class EvaluaciónCafetera {
    public static void main(String[] args) {
        String[] fincas = {"La Esperanza", "El Encanto", "Vista Hermosa", "Los Pinos"};
        double[] hectareas = {5.5, 3.0, 8.2, 4.0};
        double[] toneladas = {12.1, 8.4, 15.0, 7.2};
        
        System.out.println("=== REPORTE DE COSECHA ===");
        
        double rendimientoGlobal = calcularRendimientoGlobal(hectareas, toneladas);
        System.out.println("Rendimiento global de la cooperativa: " + rendimientoGlobal + " ton/ha");
        
        int idxMejor = buscarMejorFinca(hectareas, toneladas);
        double mejorRendimiento = toneladas[idxMejor] / hectareas[idxMejor];
        System.out.println("Finca más eficiente: " + fincas[idxMejor] + " con " + mejorRendimiento + " ton/ha");
        
        double limiteCritico = 2.0; 
        mostrarFincasCriticas(fincas, hectareas, toneladas, limiteCritico);
    }

    public static double calcularRendimientoGlobal(double[] hectareas, double[] toneladas) {
        double totalHectareas = 0;
        double totalToneladas = 0;
        
        for (int i = 0; i < hectareas.length; i++) {
            totalHectareas += hectareas[i];
            totalToneladas += toneladas[i];
        }
        
        if (totalHectareas == 0) return 0;
        return totalToneladas / totalHectareas;
    }

    public static int buscarMejorFinca(double[] hectareas, double[] toneladas) {
        int indiceMayor = 0;
        double mayorRendimiento = toneladas[0] / hectareas[0];
        
        for (int i = 1; i < hectareas.length; i++) {
            double rendimientoActual = toneladas[i] / hectareas[i];
            if (rendimientoActual > mayorRendimiento) {
                mayorRendimiento = rendimientoActual;
                indiceMayor = i;
            }
        }
        return indiceMayor;
    }

    public static void mostrarFincasCriticas(String[] fincas, double[] hectareas, double[] toneladas, double limite) {
        System.out.println(); // Salto de línea manual
        System.out.println("--- Fincas en estado crítico (< " + limite + " ton/ha) ---");
        boolean existen = false;
        
        for (int i = 0; i < fincas.length; i++) {
            double rendimiento = toneladas[i] / hectareas[i];
            if (rendimiento < limite) {
                // Aquí se une el texto con la variable usando el signo +
                System.out.println("Finca: " + fincas[i] + " | Rendimiento: " + rendimiento + " ton/ha");
                existen = true;
            }
        }
        
        if (!existen) {
            System.out.println("Ninguna finca presenta rendimiento crítico.");
        }
    }
}
