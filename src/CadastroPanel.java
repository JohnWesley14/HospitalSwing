import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadastroPanel extends JPanel {

   private String maskCampoTelefone;
   private String maskCampoCPF;
   private JTextField campoNomeCompleto;
   private JFormattedTextField campoCPF;
   private JFormattedTextField campoTelefone;
   private JTextField campoEmail;
   private JFormattedTextField campoNumeroSaude;
   private JPasswordField campoSenha;
   private JRadioButton campoSexoMasculino;
   private JRadioButton campoSexoFeminino;
   private ButtonGroup grupoSexo;

   public CadastroPanel() {
      try {

         setLayout(new GridLayout(10, 2));

         campoNomeCompleto = new JTextField();
         campoCPF = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
         campoTelefone = new JFormattedTextField(new MaskFormatter("(##) #####-####"));
         campoEmail = new JTextField();
         campoNumeroSaude = new JFormattedTextField(new MaskFormatter("#####"));
         campoSenha = new JPasswordField();
         campoSexoMasculino = new JRadioButton("M");
         campoSexoFeminino = new JRadioButton("F");

         // Grupo para os botões de rádio
         grupoSexo = new ButtonGroup();
         grupoSexo.add(campoSexoMasculino);
         grupoSexo.add(campoSexoFeminino);

         JButton btnCadastrar = new JButton("Cadastrar");
         JButton btnVoltarLogin = new JButton("Voltar");

         // btnCadastrar.addActionListener(e -> teste(e, dadosCadastro));
         btnCadastrar.addActionListener(e -> App.changeScreen("cadastroDois"));
         btnVoltarLogin.addActionListener(e -> App.changeScreen("login"));

         add(new JLabel("Nome Completo:"));
         add(campoNomeCompleto);
         add(new JLabel("CPF:"));
         add(campoCPF);
         maskCampoCPF = campoCPF.getText();
         add(new JLabel("Telefone:"));
         add(campoTelefone);
         maskCampoTelefone = campoTelefone.getText();
         add(new JLabel("Email:"));
         add(campoEmail);
         add(new JLabel("Número do Plano de Saúde:"));
         add(campoNumeroSaude);
         add(new JLabel("Senha:"));
         add(campoSenha);

         add(new JLabel("Sexo:"));
         JPanel sexoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
         sexoPanel.add(campoSexoMasculino);
         sexoPanel.add(campoSexoFeminino);
         add(sexoPanel);

         add(btnCadastrar);
         add(btnVoltarLogin);

      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private void teste(ActionEvent event, DadosCadastro dadosCadastro) {
      String nome = campoNomeCompleto.getText();
      String cpf = campoCPF.getText();
      String telefone = campoTelefone.getText();
      String email = campoEmail.getText();
      String numeroSaude = campoNumeroSaude.getText();
      String senha = new String(campoSenha.getPassword());

      dadosCadastro.setNome(nome);
      dadosCadastro.setCpf(cpf);
      dadosCadastro.setTelefone(telefone);
      dadosCadastro.setEmail(email);
      dadosCadastro.setNumeroSaude(numeroSaude);
      dadosCadastro.setSenha(senha);

      CadastroDoisPanel cadastroDoisPanel = new CadastroDoisPanel();
      cadastroDoisPanel.setNome(nome);
      App.changeScreen("cadastroDois");
      System.out.println("iupi");

   }

   private void realizarCadastro(ActionEvent event) {
      String nome = campoNomeCompleto.getText();
      String cpf = campoCPF.getText();
      String telefone = campoTelefone.getText();
      String email = campoEmail.getText();
      String numeroSaude = campoNumeroSaude.getText();
      String senha = new String(campoSenha.getPassword());

      // Check which radio button is selected
      String sexo = null;
      if (grupoSexo.getSelection() != null) {
         if (grupoSexo.getSelection().equals(campoSexoMasculino.getModel())) {
            sexo = "M";
         } else if (grupoSexo.getSelection().equals(campoSexoFeminino.getModel())) {
            sexo = "F";
         }
      }

      if (cpf.equals(maskCampoCPF)) {
         JOptionPane.showMessageDialog(this, "Preencha o campo CPF corretamente");
      } else if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || senha.isEmpty() || sexo == null) {
         JOptionPane.showMessageDialog(this, "Preencha os campos corretamente");
      } else {

         DatabaseConnection connectNow = new DatabaseConnection();
         Connection connectDB = connectNow.getConnection();

         try {
            String sql = "INSERT INTO pacientes (nome_completo, email, telefone, cpf, numero_plano_saude, senha, sexo) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connectDB.prepareStatement(sql);
            statement.setString(1, nome);
            statement.setString(2, email);
            statement.setString(3, telefone);
            statement.setString(4, cpf);
            statement.setString(5, numeroSaude);
            statement.setString(6, senha);
            statement.setString(7, sexo);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Cadastro realizado com sucesso!");
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   }
}
