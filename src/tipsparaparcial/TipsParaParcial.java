
package tipsparaparcial;

import java.util.Scanner;

/**
 *
 * @author Danny
 */
public class TipsParaParcial {

    // Scanner global para evitar instancias múltiples y problemas con System.in
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String[] placas = null;/*
        Declaramos el arreglo tipo null porque todavía
        no sabemos que espacio va a ocupar
        */
        double[] kilometros = null;
        double[] galones = null;
        boolean datosRegistrados = false;
        int opcion;

        do {
            System.out.println(" SISTEMA DE CONTROL DE FLOTA RUTARÁPIDA ");
            System.out.println("1. Registrar / Sobrescribir datos de la flota de vehículos");
            System.out.println("2. Consultar el rendimiento promedio general de la flota");
            System.out.println("3. Filtrar y mostrar vehículos ineficientes");
            System.out.println("4. Mostrar la placa y kilometraje del vehículo con mayor recorrido");
            System.out.println("5. Estadísticas");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese la cantidad de vehículos en la flota: ");
                    int cantidad = scanner.nextInt();
                    scanner.nextLine(); // Limpiar buffer

                    if (cantidad > 0) {
                        placas = new String[cantidad];
                        kilometros = new double[cantidad];
                        galones = new double[cantidad];

                        for (int i = 0; i < cantidad; i++) {
                            System.out.println(" Vehículo " + (i + 1) + " ");
                            System.out.print("Placa: ");
                            placas[i] = scanner.nextLine();
                            kilometros[i] = validarDatoPositivo("Kilómetros recorridos: ");
                            galones[i] = validarDatoPositivo("Galones consumidos: ");
                        }
                        datosRegistrados = true;
                        System.out.println("¡Datos registrados con éxito!");
                    } else {
                        System.out.println("Error: La cantidad de vehículos debe ser mayor a 0.");
                    }
                    break;

                case 2:
                    if (datosRegistrados) {/*el if(datos registrados) está solo porque ya confirmamos en la 
                        primera opción que es true, si es falso, el else 
                        le dice que primero debe registrar los datos
                        */
                        double rendimientoPromedio = calcularRendimientoPromedio(kilometros, galones);
                        System.out.printf("El rendimiento promedio general de la flota es:"+rendimientoPromedio+"km/galón");
                    } else {
                        System.out.println("Error: Primero debe registrar los datos de la flota (Opción 1).");
                    }
                    break;

                case 3:
                    if (datosRegistrados) {
                        double limite = validarDatoPositivo("Ingrese el límite de rendimiento mínimo aceptable (km/galón): ");
                        mostrarVehiculosIneficientes(placas, kilometros, galones, limite);
                    } else {
                        System.out.println("Error: Primero debe registrar los datos de la flota (Opción 1).");
                    }
                    break;

                case 4:
                    if (datosRegistrados) {
                        int indiceMayor = buscarVehiculoMasRecorrido(kilometros);
                        System.out.printf("\nVehículo con mayor recorrido:\nPlaca: %s | Kilómetros: %.2f\n",
                                placas[indiceMayor], kilometros[indiceMayor]);
                    } else {
                        System.out.println("Error: Primero debe registrar los datos de la flota (Opción 1).");
                    }
                    break;

                case 5:
                    if (datosRegistrados) {
                        generarEstadisticass(placas, kilometros, galones);
                    } else {
                        System.out.println("Error: Primero debe registrar los datos de la flota (Opción 1).");
                    }
                    break;

                case 6:
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 6);
    }

    // Método para validar que los datos sean estrictamente positivos
    public static double validarDatoPositivo(String mensaje) {
        double valor;
        do {
            System.out.print(mensaje);
            valor = scanner.nextDouble();
            if (valor <= 0) {
                System.out.println("Error: El valor debe ser mayor a cero.");
            }
        } while (valor <= 0);
        // Limpiar buffer si se requiere leer un String posteriormente
        scanner.nextLine();
        return valor;
    }

    // Método requerido 1
    public static double calcularRendimientoPromedio(double[] kilometros, double[] galones) {
        double totalKm = 0;
        double totalGalones = 0;

        for (int i = 0; i < kilometros.length; i++) {
            totalKm += kilometros[i]; //+= es lo mismo que ecribir totalkm= totalkm+kilometros[i]
            totalGalones += galones[i];
        }

        if (totalGalones == 0) {
            return 0;
        }
        return totalKm / totalGalones; //Devuelve el promedio entre km y galones
    }

    // Método requerido 2
    public static void mostrarVehiculosIneficientes(String[] placas, double[] kilometros, double[] galones, double limiteRendimiento) {
        System.out.println(" Vehículos Ineficientes (< " + limiteRendimiento + " km/galón)");
        boolean existen = false;
        for (int i = 0; i < placas.length; i++) {
            double rendimiento = kilometros[i] / galones[i];
            if (rendimiento < limiteRendimiento) {
                System.out.printf(" Placa:  | Rendimiento:  km/galón", placas[i], rendimiento);
                existen = true;
            }
        }

        if (!existen) { //!xisten es lo mismo que decir existen==false
            //recordar que = es para asignar y == para comparar
            System.out.println("Ningún vehículo está por debajo del límite de rendimiento.");
        }
    }

    // Método requerido 3
    public static int buscarVehiculoMasRecorrido(double[] kilometros) {
        int indiceMayor = 0;
        for (int i = 1; i < kilometros.length; i++) {
            if (kilometros[i] > kilometros[indiceMayor]) {
                indiceMayor = i;
            }
        }
        return indiceMayor;
    }

    // Método requerido 4 (Nombrado 'generarEstadisticass' tal cual solicita el enunciado)
    public static void generarEstadisticass(String[] placas, double[] kilometros, double[] galones) {
        System.out.println(" REPORTE DE ESTADÍSTICAS ");

        // 1. Listado general
        System.out.println("Listado general de vehículos:");
        for (int i = 0; i < placas.length; i++) {
            System.out.printf("  Placa:  | Km:  | Galones: ", (i + 1), placas[i], kilometros[i], galones[i]);
        }

        // 2. Datos del vehículo con mayor recorrido
        int idxMasRecorrido = buscarVehiculoMasRecorrido(kilometros);
        System.out.printf("Vehículo con mayor recorrido:  Placa:  con km\n",
                placas[idxMasRecorrido], kilometros[idxMasRecorrido]);

        // 3. Datos del vehículo con mayor consumo y sumatorias para promedios
        int idxMasConsumo = 0;
        double sumaKm = 0;
        double sumaGalones = 0;

        for (int i = 0; i < placas.length; i++) {
            if (galones[i] > galones[idxMasConsumo]) {
                idxMasConsumo = i;
            }
            sumaKm += kilometros[i];
            sumaGalones += galones[i];
        }
        System.out.printf("Vehículo con mayor consumo:  Placa: con galones\n",
                placas[idxMasConsumo], galones[idxMasConsumo]);

        // 4. Promedio de recorrido y consumo
        double promedioKm = sumaKm / placas.length;
        double promedioGalones = sumaGalones / placas.length;
        System.out.printf("\nPromedios globales (por vehículo):\n  Recorrido: %.2f km\n  Consumo: %.2f galones\n",
                promedioKm, promedioGalones);
    }
}
    
    

