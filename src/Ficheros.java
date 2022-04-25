import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Ficheros {

    public static void main(String[] args) throws IOException {

        ArrayList<String> hombres = new ArrayList<String>();
        ArrayList<String> mujeres = new ArrayList<String>();
        ArrayList<String> apellidos = new ArrayList<String>();
        hombres = ficheros ("C:\\Users\\Alber\\Desktop\\NOMBRES\\hombres.txt");
        mujeres = ficheros ("C:\\Users\\Alber\\Desktop\\NOMBRES\\mujeres.txt");
        apellidos = ficheros ("C:\\Users\\Alber\\Desktop\\NOMBRES\\apellidos.txt");

        ArrayList<String> DNIs = DNIs();

        Pacientes(hombres, mujeres, apellidos, DNIs);
    }

//Este método nos lee los archivos de texto y los guarda en un array

    public static ArrayList<String> ficheros (String ruta){
        ArrayList<String> fichero = new ArrayList<String>();
        String linea;
        try {
            File texto = new File(ruta);
            BufferedReader leerfichero = new BufferedReader(new FileReader(ruta));
            while((linea=leerfichero.readLine()) != null){
                fichero.add(leerfichero.readLine());
            }
            leerfichero.close();

        } catch (FileNotFoundException fn) {

            System.out.println("No se encuentra el archivo");

        } catch (IOException e) {

            System.out.println("Error de E/S");
        }

        return fichero;

    }

//Este método nos genera un ArrayLists de DNIs sin repetirlos

    public static ArrayList<String> DNIs(){
        ArrayList<String> DNIs = new ArrayList<String>();
        String DNI;

        for (int i= 0; i <3000;i++) {
            DNI = CrearDNI();
            if (DNIs.contains(DNI)) {
                i--;
                break;
            }
            else {
                DNIs.add(DNI);
            }
        }
        return DNIs;
    }

//Este método nos genera una fecha aleatoria dentro de los límites

    public static LocalDate fecha_de_nacimiento(){
        LocalDate fecha = LocalDate.of(1917, 1 , 1);
        fecha.plusDays(Numaleatorio(1, (int)ChronoUnit.DAYS.between(LocalDate.of(1917, 1 , 1), LocalDate.of(2021, 12, 31))));
        return fecha;
    }

//Este método nos escribe todo lo recorrido en los archivos de texto en un archivo creado dentro de los parametros exigidos

    public static void Pacientes (ArrayList<String> hombres, ArrayList<String> mujeres, ArrayList<String> apellidos,ArrayList<String> DNIs){

        try{
            FileWriter fw = new FileWriter("C:\\Users\\Alber\\Desktop\\NOMBRES\\pacientes.txt");
            DNIs();
            //for que añade a 3000 veces a pacientes
            for(int i = 0; i < 2999; i++){
                //numero que se usa para manejar la proporcion de hombres y mujeres
                int HombreOMujer = Numaleatorio(1, 100);
                String nombre;
                if(HombreOMujer >= 56) {
                    //nombre de hombre
                    nombre = hombres.get(Numaleatorio(1, hombres.size()));
                    fw.write('H');
                }
                else{
                    //nombre de mujer
                    nombre = mujeres.get(Numaleatorio(1, mujeres.size()));
                    fw.write('M');

                }
                fw.write(';');
                fw.write(nombre);
                fw.write(';');
                //apellidos
                String Apellido1 = apellidos.get(Numaleatorio(1, apellidos.size()));
                fw.write(Apellido1);
                fw.write(' ');
                String Apellido2 = apellidos.get(Numaleatorio(1, apellidos.size()));
                fw.write(Apellido2);
                fw.write(';');
                //DNI
                String dni = DNIs.get(Numaleatorio(1,DNIs.size()));
                fw.write(dni);
                fw.write(';');
                //fecha
                fw.write(fecha_de_nacimiento().toString());
                fw.write(';');
                //telefono
                fw.write(String.valueOf(Numaleatorio(000000000, 999999999)));

                fw.write('\n');
            }
            fw.close();
        }

        catch(IOException e){
            System.out.println("Error E/S:" + e);
        }
    }

//Este método nos crea un número de DNI aleatorio con la letra incluida

    public static String CrearDNI() {
        //Se generan los 8 numeros, y se le añade la letra final según su resto
        int numero = Numaleatorio(100000000, 999999999);
        char [] letras = {'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
        char letra = letras[numero%23];
        String dni = String.valueOf(numero)+letra;
        return dni;
    }

//Este método nos crea un número aleatorio para poder usarlo en otros métodos

    public static int Numaleatorio(int desde, int hasta) {
        int numero;
        numero = (int) (Math.floor((hasta - desde + 1) * Math.random()));
        return numero;

    }
}