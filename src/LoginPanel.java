
import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginPanel extends JPanel {

   private JFormattedTextField campoCPF;
   private JPasswordField campoSenha;

   public LoginPanel() {
      setLayout(new GridLayout(6, 2));

      try {

         JLabel labelCPF = new JLabel("CPF:");

         campoCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
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
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void fazerLogin(ActionEvent event) {
      String cpf = campoCPF.getText();
      String senha = new String(campoSenha.getPassword());

      System.out.print("Cpf campo");
      System.out.println(cpf);

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

               String nome = queryOutput.getString("nome_completo");
               String email = queryOutput.getString("email");

               String telefone = queryOutput.getString("telefone");
               String sexo = queryOutput.getString("sexo");
               String alergias = queryOutput.getString("alergias");
               String seguro = queryOutput.getString("info_seguro_saude");
               String medicacoes = queryOutput.getString("medicacoes_uso");
               String condicoes = queryOutput.getString("condicoes_medicas_preexistentes");
               String historico = queryOutput.getString("historico_medico");

               dadosCadastro.setSenha(new String(campoSenha.getPassword()));
               dadosCadastro.setCpf(campoCPF.getText());

               dadosCadastro.setNome(nome);
               dadosCadastro.setEmail(email);

               dadosCadastro.setSexo(sexo);
               dadosCadastro.setAlergias(alergias);
               dadosCadastro.setSeguro(seguro);
               dadosCadastro.setMedicacoes(medicacoes);
               dadosCadastro.setCondicoes(condicoes);
               dadosCadastro.setHistorico(historico);
               dadosCadastro.setTelefone(telefone);

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
