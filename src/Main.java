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

        System.out.println("Ingrese el nombre del archivo");
        archivoNombre = read.next();

        //Para leer el archivo
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

            lstNodos.add(new Association<String, String>(traduc.get(0), traduc.get(1)));
        }

        BinaryTree<Association> diccionarioBT = new BinaryTree<Association>(lstNodos.get(0));

        BinaryTree<Association> btActual = diccionarioBT;

        for (int n = 1; n < lstNodos.size(); n++) {
            System.out.println(lstNodos.get(n));

            if (btActual.left == null) {

            }

        }
    }
}
