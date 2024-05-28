import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPanel extends JPanel {

   private JTextField campoCPF;
   private JPasswordField campoSenha;

   public LoginPanel() {
      setLayout(new GridLayout(6, 2));

      JLabel labelCPF = new JLabel("CPF:");
      campoCPF = new JTextField();
      JLabel labelSenha = new JLabel("Senha:");
      campoSenha = new JPasswordField();

      JButton btnEntrar = new JButton("Entrar");
      JButton btnCadastro = new JButton("Cadastro");

      btnEntrar.addActionListener(this::fazerLogin);
      btnCadastro.addActionListener(e -> App.changeScreen("cadastro"));

      add(labelCPF);
      add(campoCPF);
      add(labelSenha);
      add(campoSenha);
      add(new JLabel());
      add(new JLabel());
      add(btnEntrar);
      add(btnCadastro);
   }

   private void fazerLogin(ActionEvent event) {
      String cpf = campoCPF.getText();
      String senha = new String(campoSenha.getPassword());

      DatabaseConnection connectNow = new DatabaseConnection();
      Connection connectDB = connectNow.getConnection();

      try {
         String connectQuery = "SELECT * FROM pacientes WHERE cpf=?";
         PreparedStatement statement = connectDB.prepareStatement(connectQuery);
         statement.setString(1, cpf);

         ResultSet queryOutput = statement.executeQuery();
         if (queryOutput.next()) {
            String senhaDB = queryOutput.getString("senha");
            if (senhaDB.equals(senha)) {
               DadosCadastro dadosCadastro = DadosCadastro.getInstance();
               dadosCadastro.setSenha(new String(campoSenha.getPassword()));
               dadosCadastro.setCpf(campoCPF.getText());
               App.changeScreen("principal");
            } else {
               JOptionPane.showMessageDialog(this, "Login inválido, verifique o CPF e a senha.");
            }
         } else {
            JOptionPane.showMessageDialog(this, "Usuário não encontrado.");
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
