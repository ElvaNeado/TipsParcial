/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tipsparaparcial;

/**
 *
 * @author Danny
 */
public class Buffer {
    /*
    El momento crítico y la regla de oro para limpiar el buffer en Java se resume en una sola combinación peligrosa:
cuando usas un método que lee un dato específico (como un número) y la instrucción que le sigue directamente es un nextLine() para leer un texto
Aquí tienes la guía definitiva para identificar cuándo actuar y cuándo no:

🚨 EL MOMENTO CRÍTICO (Donde SÍ debes limpiarlo)
Debes colocar el sc.nextLine(); vacío siempre que ocurra esta secuencia exacta:

Lees un número usando nextInt(), nextDouble(), o una sola palabra/booleano con next(), nextBoolean().

Inmediatamente después (o al inicio de la siguiente vuelta de un ciclo), necesitas leer un texto con espacios usando nextLine().
    
    ZONAS SEGURAS (Donde NO debes limpiarlo)
Si pones una limpieza de buffer en estos casos, vas a pausar tu programa innecesariamente y la consola se quedará congelada esperando a que el usuario presione un "Enter" fantasma para poder continuar:

Número seguido de Número: Si pides un nextInt() y luego un nextDouble(). Los métodos numéricos se entienden bien entre ellos y saltan los "Enters" sobrantes automáticamente.

Texto seguido de Texto: Si pides un nextLine() y luego otro nextLine(). Este método está diseñado para atrapar y consumir el Enter completo, así que deja la sala de espera impecable por su propia cuenta.

Texto seguido de Número: Si pides primero el nombre (nextLine()) y luego la edad (nextInt()). Funciona perfecto porque el texto ya dejó el área limpia antes de que llegara el número.
    */
}
