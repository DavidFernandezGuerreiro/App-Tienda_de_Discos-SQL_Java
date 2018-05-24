
package sqlitetrabajo;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class ConexionSQL {
    Connection miConexion=null;
    
    //Creamos la conexión con la Base de Datos.
    public Connection entrar(){
        try{
            miConexion=DriverManager.getConnection("jdbc:sqlite:tablavinilos.db");
            System.out.println("Conexión realizada con exito");
        }catch(Exception e){
            System.out.println("ERROR "+e);
        }
        return miConexion;
    }
    
    //Cerramos la conexión con la Base de Datos.
    public void close(){
        try {
            miConexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConexionSQL.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    //Construye los datos de clase Musica.
    public Musica datosMusica(String id,String nombre,String artista,String estilo,String cdvinilo,String precio){
        Musica obxMusica=new Musica(id,nombre,artista,estilo,cdvinilo,precio);
        return obxMusica;
    }
    
    //Creamos la secuencia sql para insertar datos en la tabla "TablaMusica" en todos sus campos.
    //Recogemos los datos.
    //Y los ejecutamos.
    public void insertarMusica(Musica obxMusica){
        try{
            PreparedStatement st=miConexion.prepareStatement("insert into TablaMusica (id, nombre, artista, estilo, cdvinilo, precio) values (?,?,?,?,?,?)");
            st.setString(1, obxMusica.getId());
            st.setString(2, obxMusica.getNombre());
            st.setString(3, obxMusica.getArtista());
            st.setString(4, obxMusica.getEstilo());
            st.setString(5, obxMusica.getCdvinilo());
            st.setString(6, obxMusica.getPrecio());
            st.execute();

        }catch(SQLException ex){
            System.out.println("No se puedo insertar los datos"+ex.getMessage());
        }
    }
    

    
    
}
