package org.example

import org.example.repository.Empresarepository
import java.nio.file.Path

/**
EJERCICIO FICHEROS
El fichero cotizacion.csv (que podéis encontrar en la carpeta ficheros) contiene las cotizaciones de las empresas del IBEX35 con las siguientes columnas: Nombre (nombre de la empresa), Final (precio de la acción al cierre de bolsa), Máximo (precio máximo de la acción durante la jornada), Mínimo (precio mínimo de la acción durante la jornada), Volumen (Volumen al cierre de bolsa), Efectivo (capitalización al cierre en miles de euros).



Construir una función que reciba el diccionario devuelto por la función anterior y cree un fichero en formato csv con el mínimo, el máximo y la media de dada columna.
 * */
fun main() {
    val raiz = Path.of("src")
    val archivo = raiz.resolve("main").resolve("resources").resolve("cotizacion.csv")
    val prueba = Empresarepository(archivo)
    prueba.crearDiccionario()
    prueba.escribirFicheroNuevoCsv()
}