package ClinicaDental;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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

    public class CreacionBBDD {

//Con este m�todo hacemos la conexi�n de la BBDD mediante mysql

        public static void main(String[] args) throws IOException {

            String db_ = "Odontologia";
            String login_ = "root";
            String password_ = "";
            String url_ = "jdbc:mysql://127.0.0.1/" + db_;
            String url2_ = "jdbc:mysql://127.0.0.1/" + "mysql";
            Connection connection_;
            Statement st_;

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                connection_ = DriverManager.getConnection(url2_, login_, password_);

                    System.out.println("Conexion a base de datos mysql correcta");
                    st_ = connection_.createStatement();
                    st_.executeUpdate("Drop database if exists " + db_);
                    st_.executeUpdate("Create database " + db_);
                    connection_ = DriverManager.getConnection(url_, login_, password_);

                    System.out.println("Conexion a base de datos " + db_ + " correcta");
                    st_ = connection_.createStatement();

//Aqu� empezar�an las sintaxis de mysql y las sentencias para crear las tablas correspondientes


                    System.out.println("Creacion tabla pacientes");
                    st_.executeUpdate("Drop table if exists pacientes ");
                    st_.executeUpdate("create table pacientes(IDPaciente INT AUTO_INCREMENT PRIMARY KEY,Genero varchar(10), Nombre varchar(50), Apellidos varchar(50), Dni varchar(10), FechaNacimiento DATETIME, Telefono INT)");

                    System.out.println("Creacion tabla tratamientos");
                    st_.executeUpdate("Drop table if exists tratamientos ");
                    st_.executeUpdate("create table Tratamientos(IDTratamiento int AUTO_INCREMENT PRIMARY KEY, GrupoTratamiento VARCHAR(50), CodigoTratamiento VARCHAR(50), Tratamiento VARCHAR(50), PrecioTarifa int)");


                    try {
                        Connection connection_2;
                        st_ = connection_.createStatement();
                        BufferedReader fichero = new BufferedReader(new FileReader("C:\\Users\\AlbertoMonteroVera\\Desktop\\NOMBRES\\pacientes.csv"));
                        String [] linea;
                        String s;
                        System.out.println("Insertando datos en la tabla PACIENTES...");
                        while((s=fichero.readLine())!=null){
                            linea=s.split(";");

                            st_.executeUpdate("INSERT INTO PACIENTES(Nombre, Apellidos, Dni, Genero, FechaNacimiento, Telefono) VALUES('"
                                    + linea[0] +"','"
                                    + linea[1] +"','"
                                    + linea[2] +"','"
                                    + linea[3] +"','"
                                    + linea[4] +"','"
                                    + linea[5]
                                    + "')");

                        }
                    }catch(SQLException e) {
                        e.printStackTrace();
                    }catch(Exception e) {
                        e.printStackTrace();
                    }

                connection_.close();
            } catch (
                    SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();

            }
    }
}
