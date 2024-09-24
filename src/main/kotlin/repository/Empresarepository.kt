package org.example.repository

import java.io.BufferedReader
import java.io.BufferedWriter
import java.nio.file.Files
import java.nio.file.Path
import java.util.Dictionary
import kotlin.io.path.exists

class Empresarepository(val rutaFichero: Path) {
    // un diccionario donde la clave va a ser el nombre de la empresa
    // y el valor va a ser Maximo;Minimo;Volumen;Efectivo
    val dictionary: MutableMap<String, List<String>> = emptyMap<String, List<String>>().toMutableMap()

    //Construir una función reciba el fichero de cotizaciones y devuelva un diccionario con los datos del fichero por columnas.
    fun crearDiccionario(rutaFicheroDiferente: Path? = null) {

        if (rutaFicheroDiferente == null) {

            if (rutaFichero.exists()) {
                val br: BufferedReader = Files.newBufferedReader(rutaFichero)
                br.use {
                    br.forEachLine {
                        val lista = it.split(";")
                        dictionary[lista[0]] = listOf(lista[1], lista[2], lista[3], lista[4], lista[5])
                    }
                }
                print(dictionary)
            } else {
                println("ERROR EL ARCHIVO NO EXISTE")
            }
        } else {

            if (rutaFicheroDiferente.exists()) {
                val br: BufferedReader = Files.newBufferedReader(rutaFicheroDiferente)
                br.use {
                    br.forEachLine {
                        val lista = it.split(";")
                        dictionary[lista[0]] = listOf(lista[1], lista[2], lista[3], lista[4], lista[5])
                    }
                }
                print(dictionary)
            }
        }
    }

    //Construir una función que reciba el diccionario devuelto por la función anterior y cree un fichero en formato csv con el mínimo, el máximo y la media de dada columna.
    fun escribirFicheroNuevoCsv() {

        val rutaFicheroEscritura = Path.of("src/main/resources/ficheroEscrito/ficheroEscrito.csv")

        if (Files.notExists(rutaFicheroEscritura)) {
            //createdirectory crea todos los directorios necesarios
            Files.createDirectory(rutaFicheroEscritura.parent)
            // createFile crea el fichero
            Files.createFile(rutaFicheroEscritura)
        }

        val bw: BufferedWriter = Files.newBufferedWriter(rutaFicheroEscritura)

        bw.use {
            dictionary.forEach { clave, valor ->
                if (valor[1]
                        .replace(".", "")
                        .replace(",", ".")
                        .toFloatOrNull() != null
                ) {
                    bw.appendLine(
                        "$clave;${
                            valor[1]
                                .replace(".", "")
                                .replace(",", ".")
                        };${
                            valor[2]
                                .replace(".", "")
                                .replace(",", ".")
                        };${
                            ((valor[1]
                                .replace(".", "")
                                .replace(",", ".")
                                .toFloat() +
                                    valor[2]
                                        .replace(".", "")
                                        .replace(",", ".").toFloat()) / 2)
                        }"
                    )
                } else {
                    bw.appendLine("${clave};${valor[1]};${valor[2]}")
                }
            }
        }
    }
}