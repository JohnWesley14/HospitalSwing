import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadastroPanel extends JPanel {

   private JTextField campoNomeCompleto;
   private JTextField campoCPF;
   private JTextField campoTelefone;
   private JTextField campoEmail;
   private JTextField campoNumeroSaude;
   private JPasswordField campoSenha;
   private JPasswordField campoConfirmarSenha;

   public CadastroPanel() {
      setLayout(new GridLayout(9, 2));

      campoNomeCompleto = new JTextField();
      campoCPF = new JTextField();
      campoTelefone = new JTextField();
      campoEmail = new JTextField();
      campoNumeroSaude = new JTextField();
      campoSenha = new JPasswordField();
      campoConfirmarSenha = new JPasswordField();

      JButton btnCadastrar = new JButton("Cadastrar");
      JButton btnVoltarLogin = new JButton("Voltar");

      btnCadastrar.addActionListener(this::realizarCadastro);
      btnVoltarLogin.addActionListener(e -> App.changeScreen("login"));

      add(new JLabel("Nome Completo:"));
      add(campoNomeCompleto);
      add(new JLabel("CPF:"));
      add(campoCPF);
      add(new JLabel("Telefone:"));
      add(campoTelefone);
      add(new JLabel("Email:"));
      add(campoEmail);
      add(new JLabel("Número do Plano de Saúde:"));
      add(campoNumeroSaude);
      add(new JLabel("Senha:"));
      add(campoSenha);
      add(new JLabel("Confirmar Senha:"));
      add(campoConfirmarSenha);
      add(btnCadastrar);
      add(btnVoltarLogin);
   }

   private void realizarCadastro(ActionEvent event) {
      String nome = campoNomeCompleto.getText();
      String cpf = campoCPF.getText();
      String telefone = campoTelefone.getText();
      String email = campoEmail.getText();
      String numeroSaude = campoNumeroSaude.getText();
      String senha = new String(campoSenha.getPassword());
      String confirmacaoSenha = new String(campoConfirmarSenha.getPassword());

      if (!senha.equals(confirmacaoSenha)) {
         JOptionPane.showMessageDialog(this, "Senhas não correspondem.");
         return;
      }

      DatabaseConnection connectNow = new DatabaseConnection();
      Connection connectDB = connectNow.getConnection();

      try {
         String sql = "INSERT INTO pacientes (nome_completo, email, telefone, cpf, numero_plano_saude, senha) VALUES (?, ?, ?, ?, ?, ?)";
         PreparedStatement statement = connectDB.prepareStatement(sql);
         statement.setString(1, nome);
         statement.setString(2, email);
         statement.setString(3, telefone);
         statement.setString(4, cpf);
         statement.setString(5, numeroSaude);
         statement.setString(6, senha);
         statement.executeUpdate();
         JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
