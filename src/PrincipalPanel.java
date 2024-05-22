import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PrincipalPanel extends JPanel {

   public PrincipalPanel() {
      setLayout(new BorderLayout());

      JLabel labelPrincipal = new JLabel("Bem-vindo ao Sistema Hospitalar", SwingConstants.CENTER);
      labelPrincipal.setFont(new Font("Arial", Font.BOLD, 18));

      JButton btnNovoAgendamento = new JButton("Novo Agendamento");
      JButton btnVoltar = new JButton("Voltar");

      btnNovoAgendamento.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            App.changeScreen("agendarConsulta");
         }
      });

      btnVoltar.addActionListener(e -> App.changeScreen("login"));

      JPanel buttonsPanel = new JPanel();
      buttonsPanel.add(btnNovoAgendamento);
      buttonsPanel.add(btnVoltar);

      add(labelPrincipal, BorderLayout.CENTER);
      add(buttonsPanel, BorderLayout.SOUTH);
   }
}
