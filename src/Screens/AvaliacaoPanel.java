package Screens;

import javax.swing.*;

import Database.DatabaseConnection;
import Entity.Usuario;
import main.App;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvaliacaoPanel extends JPanel {
   private boolean hasExecuted = false;
   boolean hasRecords = false;

   public AvaliacaoPanel() {

      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            if (hasExecuted != true) {

               puxarAvaliacoes(null);
               // Função a ser executada quando o mouse entra no componente
               System.out.println("Mouse está sobre o componente!");
               hasExecuted = true;
            }
         }

      });

   }

   private JPanel createAvaliacaoCard(String titulo, String medico, String condicoes, String sintomas, String higiene,
         String outrasObservacoes) {
      JPanel card = new JPanel();
      card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
      card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      card.setPreferredSize(new Dimension(300, 200));
      card.setMaximumSize(new Dimension(600, 300));

      JLabel tituloLabel = new JLabel(titulo);

      JLabel medicoLabel = new JLabel(medico);
      JLabel condicoesLabel = new JLabel("<html><b>Condições patológicas:</b> " + condicoes + "</html>");
      JLabel sintomasLabel = new JLabel("<html><b>Sintomas:</b> " + sintomas + "</html>");
      JLabel higieneLabel = new JLabel("<html><b>Higiene bucal:</b> " + higiene + "</html>");
      JLabel outrasObservacoesLabel = new JLabel("<html><b>Outras Observações:</b> " + outrasObservacoes + "</html>");

      JButton btnVoltar = new JButton("Voltar");
      btnVoltar.addActionListener(this::irParaPrincipal);

      card.add(tituloLabel);
      card.add(medicoLabel);
      card.add(condicoesLabel);
      card.add(sintomasLabel);
      card.add(higieneLabel);
      card.add(outrasObservacoesLabel);

      card.add(btnVoltar);

      return card;
   }

   public void puxarAvaliacoes(ActionEvent e) {
      DatabaseConnection connectAvaliacao = new DatabaseConnection();
      Connection connectDBAvaliacao = connectAvaliacao.getConnection();

      try {
         final Usuario usuario = new Usuario().getInstance();
         String cpf = usuario.getCpf();
         System.out.print("Cpf AvaliacaoPanel: ");
         System.out.println(cpf);
         System.out.println("");
         String connectQuery = "SELECT medico, mensagemCondicoes, mensagemSintomas, mensagemHigiene, mensagemOutrasObservacoes FROM avaliacoes WHERE cpf_cliente=?";

         PreparedStatement statementAvaliacao = connectDBAvaliacao.prepareStatement(connectQuery);
         statementAvaliacao.setString(1, cpf);

         ResultSet queryOutput = statementAvaliacao.executeQuery();
         removeAll(); // Remove todos os componentes antes de adicionar novos

         while (queryOutput.next()) {
            System.out.println("Recordando");
            String mensagemHigiene = queryOutput.getString("mensagemHigiene");
            String mensagemCondicoes = queryOutput.getString("mensagemCondicoes");
            String mensagemSintomas = queryOutput.getString("mensagemSintomas");
            String mensagemOutrasObservacoes = queryOutput.getString("mensagemOutrasObservacoes");
            String medico = queryOutput.getString("medico");

            System.out.println("Mensagem de Higiene: " + mensagemHigiene);
            System.out.println("Mensagem das Condicoes: " + mensagemCondicoes);
            System.out.println("Mensagem dos Sintomas: " + mensagemSintomas);
            System.out.println("Mensagem das Outras Observacoes " + mensagemOutrasObservacoes);
            System.out.println("Nome do médico:" + medico);

            // Adiciona os cards ao painel
            add(createAvaliacaoCard("Avaliacao", medico, mensagemCondicoes, mensagemSintomas, mensagemHigiene,
                  mensagemOutrasObservacoes));
            hasRecords = true;
         }
         if (!hasRecords) {
            // Se não houver registros, exiba uma mensagem
            System.out.println("Sem records");
            JButton btnVoltar = new JButton("Voltar");
            btnVoltar.addActionListener(this::irParaPrincipal);
            JLabel noRecordsLabel = new JLabel("Nenhuma avaliação encontrada.");
            add(noRecordsLabel);
            add(btnVoltar);
         }

         // Atualiza a interface gráfica após adicionar todos os cards
         revalidate();
         repaint();

      } catch (Exception err) {
         err.printStackTrace();
      }
   }

   public void irParaPrincipal(ActionEvent e) {
      hasRecords = false;
      hasExecuted = false;
      App.changeScreen("principal");
   }

}
