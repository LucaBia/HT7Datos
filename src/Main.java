import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        ArrayList<Association> lstNodos = new ArrayList();
        String archivoNombre;

        //Para leer el archivo
        System.out.println("Ingrese el nombre del archivo");
        archivoNombre = read.next();
        ArrayList<String> archivo = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(archivoNombre + ".txt"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(archivo::add);
        } catch (IOException e ){
            System.out.println("Ha ocurrido un error");
        }

        for (String linea : archivo) {
            String lineaClean = linea.replaceAll("\\(","").replaceAll("\\)", " ").replaceAll(",", "");

            ArrayList<String> traduc = new ArrayList();
            for (String palabra : lineaClean.trim().split("\\s+")) {
                traduc.add(palabra);
            }

            lstNodos.add(new Association<String, String>(traduc.get(0).toLowerCase(), traduc.get(1).toLowerCase()));
        }

        BinaryTree<Association> diccionarioBTraiz = new BinaryTree<Association>(lstNodos.get(0));

        for (int n = 0; n < lstNodos.size(); n++) {
            if ((n+1) < lstNodos.size()) {
                BinaryTree<Association> btSiguiente = new BinaryTree<>(lstNodos.get(n+1));
                agregar(diccionarioBTraiz, btSiguiente);
            }
        }

        //IMPRIMIR IN ORDER
        System.out.println();
        diccionarioBTraiz.inOrder(diccionarioBTraiz);

        //TRADUCCION DE ARCHIVO

        //Para leer el archivo
        System.out.println();
        System.out.println("Ingrese el nombre del archivo");
        archivoNombre = read.next();
        archivo = new ArrayList<>();
        try {
            Stream<String> lines = Files.lines(
                    Paths.get(archivoNombre + ".txt"),
                    StandardCharsets.UTF_8
            );
            lines.forEach(archivo::add);
        } catch (IOException e ){
            System.out.println("Ha ocurrido un error");
        }

        String traduccion = "";
        for (String linea : archivo) {
            String lineaClean = linea.replaceAll("\\.","");

            for (String palabra : lineaClean.trim().split("\\s+")) {
                if (diccionarioBTraiz.buscar(palabra) == null) {
                    traduccion += "*"+palabra+"* ";
                } else {
                    traduccion += diccionarioBTraiz.buscar(palabra) + " ";
                }
            }
            traduccion += "\n";
        }

        System.out.println(traduccion);

    }

    public static void agregar(BinaryTree btActual, BinaryTree btSiguiente) {

        if (btActual.value().toString().compareTo(btSiguiente.value().toString()) > 0) {
            if (btActual.left().value() == null) {
                btActual.setLeft(btSiguiente);
            } else {
                agregar(btActual.left(), btSiguiente);
            }
        } else if (btActual.value().toString().compareTo(btSiguiente.value().toString()) < 0) {
            if (btActual.right().value() == null) {
                btActual.setRight(btSiguiente);
            } else {
                agregar(btActual.right(), btSiguiente);
            }
        }

    }
}