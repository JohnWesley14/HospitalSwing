import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPanel extends JPanel {

   private JPanel dentistContainer;

   public ConsultaPanel() {
      setLayout(new BorderLayout());

      dentistContainer = new JPanel();
      dentistContainer.setLayout(new BoxLayout(dentistContainer, BoxLayout.Y_AXIS));

      JButton btnBackPrincipal = new JButton("Voltar");
      btnBackPrincipal.addActionListener(e -> App.changeScreen("principal"));

      add(new JScrollPane(dentistContainer), BorderLayout.CENTER);
      add(btnBackPrincipal, BorderLayout.SOUTH);

      try {

         loadData();
      } catch (Exception e) {
         e.printStackTrace();
         // TODO: handle exception
      }
   }

   private void loadData() {
      DatabaseConnection connectNow = new DatabaseConnection();

      try (Connection connectDB = connectNow.getConnection()) {
         String query = "SELECT name, time, image, agendado FROM dentists";
         PreparedStatement statement = connectDB.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery();

         while (resultSet.next()) {
            String name = resultSet.getString("name");
            String time = resultSet.getString("time");
            int agendado = resultSet.getInt("agendado");

            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            card.setBackground(agendado == 1 ? Color.LIGHT_GRAY : Color.WHITE);

            JLabel labelNome = new JLabel("Nome: " + name);
            JLabel labelTime = new JLabel("Horário: " + time);
            JButton btnAgendar = new JButton("Agendar consulta");

         }
      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}