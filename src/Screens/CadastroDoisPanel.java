package Screens;

import javax.swing.*;

import DataAcessObject.UsuarioDAO;
import Entity.Usuario;
import main.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

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
         UsuarioDAO usuarioDAO = new UsuarioDAO();
         Usuario usuario = new Usuario().getInstance();
         String historico = txtHistorico.getText();
         String alergias = txtAlergias.getText();
         String medicacoes = txtMedicacoes.getText();
         String condicoes = txtCondicoes.getText();
         String seguro = txtSeguro.getText();

         // Enviando dados
         usuario.setAlergias(alergias);
         usuario.setHistorico(historico);
         usuario.setMedicacoes(medicacoes);
         usuario.setCondicoes(condicoes);
         usuario.setSeguro(seguro);

         usuarioDAO.cadastrarUsuario(usuario);

         JOptionPane.showMessageDialog(this, "Cadastro feito");
         App.changeScreen("login");

      } catch (Exception e) {

         e.printStackTrace();
      }

   }
}