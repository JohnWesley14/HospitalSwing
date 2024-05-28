import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AvaliacaoPanel extends JPanel {

   public AvaliacaoPanel() {

      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

      // Adiciona alguns cards de exemplo

   }

   private JPanel createAvaliacaoCard(String titulo, String medico, String condicoes, String sintomas, String higiene,
         String outrasObservacoes) {
      JPanel card = new JPanel();
      card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
      card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      card.setPreferredSize(new Dimension(300, 200));
      card.setMaximumSize(new Dimension(600, 300));

      JLabel tituloLabel = new JLabel(titulo);
      tituloLabel.setFont(new Font("Arial", Font.BOLD, 14));
      JLabel medicoLabel = new JLabel(medico);
      JLabel condicoesLabel = new JLabel("<html><b>Condições patológicas:</b> " + condicoes + "</html>");
      JLabel sintomasLabel = new JLabel("<html><b>Sintomas:</b> " + sintomas + "</html>");
      JLabel higieneLabel = new JLabel("<html><b>Higiene bucal:</b> " + higiene + "</html>");
      JLabel outrasObservacoesLabel = new JLabel("<html><b>Outras Observações:</b> " + outrasObservacoes + "</html>");

      card.add(tituloLabel);
      card.add(medicoLabel);
      card.add(condicoesLabel);
      card.add(sintomasLabel);
      card.add(higieneLabel);
      card.add(outrasObservacoesLabel);

      return card;
   }

   public void puxarAvaliacoes() {

      DatabaseConnection connectAvaliacao = new DatabaseConnection();
      Connection connectDBAvaliacao = connectAvaliacao.getConnection();

      try {
         DadosCadastro dadosCadastro = DadosCadastro.getInstance();
         String cpf = dadosCadastro.getCpf();
         System.out.print("Cpf AvaliacaoPanel: ");
         System.out.println(cpf);
         System.out.println("");
         String connectQuery = "SELECT medico, mensagemCondicoes, mensagemSintomas, mensagemHigiene, mensagemOutrasObservacoes FROM avaliacoes WHERE cpf_cliente=?";

         PreparedStatement statementAvaliacao = connectDBAvaliacao.prepareStatement(connectQuery);
         statementAvaliacao.setString(1, cpf);

         ResultSet queryOutput = statementAvaliacao.executeQuery();
         while (queryOutput.next()) {

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

            createAvaliacaoCard("Avaliacao", medico, mensagemCondicoes, mensagemSintomas, mensagemHigiene,
                  mensagemOutrasObservacoes);
            revalidate();
            repaint();

         }

      } catch (Exception e) {
         e.printStackTrace();
      }

   }
}
