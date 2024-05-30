package Screens;

import javax.swing.*;

import Database.DatabaseConnection;
import main.App;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ConsultaPanel extends JPanel {

   private Boolean hasExecuted = false;
   private Boolean hasQueries = false;
   private Map<Integer, JPanel> cardMap = new HashMap<>(); // Mapa para armazenar os cartões por ID

   public ConsultaPanel() {
      setLayout(new GridLayout(0, 2, 10, 10)); // Layout de grade com 2 colunas e espaçamento de 10 pixels

      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            if (!hasExecuted) {
               loadData();
               System.out.println("Mouse está sobre o componente!");
               hasExecuted = true;
            }
         }
      });
   }

   private void loadData() {
      DatabaseConnection connectNow = new DatabaseConnection();

      try (Connection connectDB = connectNow.getConnection()) {
         String query = "SELECT name, time, agendado, id FROM dentists";
         PreparedStatement statement = connectDB.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
            String name = resultSet.getString("name");
            String time = resultSet.getString("time");
            int agendado = resultSet.getInt("agendado");
            int id = resultSet.getInt("id");

            System.out.println(id);
            System.out.println(time);

            JPanel card = createCard(name, time, agendado, id);
            cardMap.put(id, card); // Armazena o cartão no mapa
            add(card);
            hasQueries = true;
         }

         if (!hasQueries) {
            // Se não houver registros, exiba uma mensagem
            System.out.println("Sem registros");
            JLabel noRecordsLabel = new JLabel("Nenhuma avaliação encontrada.");
            add(noRecordsLabel);
         }

         // Adiciona o botão de voltar
         JButton btnVoltar = new JButton("Voltar");
         btnVoltar.addActionListener(this::irParaPrincipal);
         add(btnVoltar);

         revalidate();
         repaint();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   private JPanel createCard(String nome, String time, int agendado, int id) {
      JPanel card = new JPanel();
      card.setLayout(new BorderLayout());
      card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      card.setPreferredSize(new Dimension(300, 200)); // Ajuste o tamanho conforme necessário

      JLabel nomeLabel = new JLabel(nome);
      JLabel medicoLabel = new JLabel(time);
      JLabel agendadoLabel = new JLabel(agendado == 1 ? "Agendado" : "Não agendado");

      JButton btnAgendar = new JButton("Agendar");
      btnAgendar.addActionListener(e -> agendar(e, id));

      if (agendado == 1) {
         card.setBackground(Color.GREEN);
      } else {
         card.setBackground(Color.RED);
      }

      card.add(nomeLabel, BorderLayout.NORTH);
      card.add(medicoLabel, BorderLayout.CENTER);
      card.add(agendadoLabel, BorderLayout.SOUTH);
      card.add(btnAgendar, BorderLayout.EAST);

      return card;
   }

   public void irParaPrincipal(ActionEvent e) {
      hasExecuted = false;
      hasQueries = false;
      App.changeScreen("principal");
   }

   public void agendar(ActionEvent e, int id) {
      try {
         DatabaseConnection connectNow = new DatabaseConnection();
         Connection connectDB = connectNow.getConnection();
         String sql = "UPDATE dentists SET agendado = CASE WHEN agendado = 1 THEN 0 ELSE 1 END WHERE id = ?";
         PreparedStatement statement = connectDB.prepareStatement(sql);
         statement.setInt(1, id);
         statement.executeUpdate();

         // Atualiza a cor do cartão correspondente
         updateCardColor(id);

         revalidate();
         repaint();
      } catch (Exception ex) {
         ex.printStackTrace();
      }
   }

   private void updateCardColor(int id) {
      DatabaseConnection connectNow = new DatabaseConnection();

      try (Connection connectDB = connectNow.getConnection()) {
         String query = "SELECT agendado FROM dentists WHERE id = ?";
         PreparedStatement statement = connectDB.prepareStatement(query);
         statement.setInt(1, id);
         ResultSet resultSet = statement.executeQuery();

         if (resultSet.next()) {
            int agendado = resultSet.getInt("agendado");
            JPanel card = cardMap.get(id);

            if (card != null) {
               if (agendado == 1) {
                  card.setBackground(Color.GREEN);
               } else {
                  card.setBackground(Color.RED);
               }
            }
         }
      } catch (Exception e) {
         e.printStackTrace();
      }
   }
}
