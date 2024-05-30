
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class CadastroDoisPanel extends JPanel {

   private JTextField txtAlergias;
   private JTextField txtMedicacoes;
   private JTextField txtCondicoes;
   private JTextField txtSeguro;
   private JTextField txtHistorico;
   private JButton btnProcurar;

   public CadastroDoisPanel() {
      setLayout(new GridLayout(7, 2, 5, 5));

      JLabel lblHistorico = new JLabel("Histórico médico e odontológico:");
      txtHistorico = new JTextField();
      btnProcurar = new JButton("Procurar...");
      btnProcurar.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            escolherArquivo();
         }
      });

      JLabel lblAlergias = new JLabel("Alergias:");
      txtAlergias = new JTextField();

      JLabel lblMedicacoes = new JLabel("Medicações em uso:");
      txtMedicacoes = new JTextField();

      JLabel lblCondicoes = new JLabel("Condições médicas pré-existentes:");
      txtCondicoes = new JTextField();

      JLabel lblSeguro = new JLabel("Informações sobre seguro de saúde (se aplicável):");
      txtSeguro = new JTextField();

      JButton btnVoltar = new JButton("Voltar");
      JButton btnCadastrar = new JButton("Cadastrar");

      btnVoltar.addActionListener(e -> App.changeScreen("cadastro"));
      btnCadastrar.addActionListener(this::fazerCadastro);

      add(lblHistorico);
      add(txtHistorico);
      add(new JLabel()); // Placeholder to align the button correctly
      add(btnProcurar);

      add(lblAlergias);
      add(txtAlergias);

      add(lblMedicacoes);
      add(txtMedicacoes);

      add(lblCondicoes);
      add(txtCondicoes);

      add(lblSeguro);
      add(txtSeguro);

      add(btnVoltar);
      add(btnCadastrar);

   }

   private void escolherArquivo() {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(this);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
         File selectedFile = fileChooser.getSelectedFile();
         txtHistorico.setText(selectedFile.getPath());

         // Abrir o arquivo com o aplicativo padrão associado
         try {
            System.out.print("selectedFile: ");
            System.out.println(selectedFile);
            Desktop.getDesktop().open(selectedFile);
         } catch (IOException e) {
            e.printStackTrace();
         }
      }
   }

   public void fazerCadastro(ActionEvent event) {

      try {
         String historico = txtHistorico.getText();
         String alergias = txtAlergias.getText();
         String medicacoes = txtMedicacoes.getText();
         String condicoes = txtCondicoes.getText();
         String seguro = txtSeguro.getText();

         DadosCadastro dadosCadastro = DadosCadastro.getInstance();

         // Obtendo dados
         String nome = dadosCadastro.getNome();
         String cpf = dadosCadastro.getCpf();
         String telefone = dadosCadastro.getTelefone();
         String email = dadosCadastro.getEmail();

         String senha = dadosCadastro.getSenha();
         String sexo = dadosCadastro.getSexo();
         // Enviando dados
         dadosCadastro.setAlergias(alergias);
         dadosCadastro.setHistorico(historico);
         dadosCadastro.setMedicacoes(medicacoes);
         dadosCadastro.setCondicoes(condicoes);
         dadosCadastro.setSeguro(seguro);

         System.out.print("Sexo cadastrodois: ");
         System.out.println(sexo.toString());

         DatabaseConnection connectNow = new DatabaseConnection();
         Connection connectDB = connectNow.getConnection();
         String sql = "INSERT INTO pacientes (nome_completo, email, telefone, cpf, senha, sexo, alergias, info_Seguro_saude, medicacoes_uso, condicoes_medicas_preexistentes, historico_medico) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
         PreparedStatement statement = connectDB.prepareStatement(sql);
         statement.setString(1, nome);
         statement.setString(2, email);
         statement.setString(3, telefone);
         statement.setString(4, cpf);
         statement.setString(5, senha);
         statement.setString(6, sexo);
         statement.setString(7, alergias);
         statement.setString(8, seguro);
         statement.setString(9, medicacoes);
         statement.setString(10, condicoes);
         statement.setString(11, historico);

         statement.executeUpdate();

         JOptionPane.showMessageDialog(this, "Cadastro feito");
         App.changeScreen("login");

      } catch (Exception e) {
         // TODO: handle exception
         e.printStackTrace();
      }

   }
}
