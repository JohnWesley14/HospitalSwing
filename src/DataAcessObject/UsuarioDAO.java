package DataAcessObject;

import java.sql.Connection;
import java.sql.PreparedStatement;

import Database.DatabaseConnection;
import Entity.Usuario;

public class UsuarioDAO {
   public void cadastrarUsuario(Usuario usuario) {

      try {

         DatabaseConnection connectNow = new DatabaseConnection();
         Connection connectDB = connectNow.getConnection();
         String sql = "INSERT INTO pacientes (nome_completo, email, telefone, cpf, senha, sexo, alergias, info_Seguro_saude, medicacoes_uso, condicoes_medicas_preexistentes, historico_medico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         PreparedStatement statement = connectDB.prepareStatement(sql);
         statement.setString(1, usuario.getNome());

         statement.setString(2, usuario.getEmail());
         statement.setString(3, usuario.getTelefone());
         statement.setString(4, usuario.getCpf());
         statement.setString(5, usuario.getSenha());
         statement.setString(6, usuario.getSexo());
         statement.setString(7, usuario.getAlergias());
         statement.setString(8, usuario.getSeguro());
         statement.setString(9, usuario.getMedicacoes());
         statement.setString(10, usuario.getCondicoes());
         statement.setString(11, usuario.getHistorico());

         statement.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
