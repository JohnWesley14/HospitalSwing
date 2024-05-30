
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class PrincipalPanel extends JPanel {

   public PrincipalPanel() {
      setLayout(new BorderLayout());

      // Adiciona a barra de título
      JPanel titlePanel = new JPanel();
      titlePanel.setBackground(Color.LIGHT_GRAY);
      JLabel titleLabel = new JLabel("Sorriso+");
      titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
      titlePanel.add(titleLabel);

      // Adiciona o menu de navegação
      JPanel navPanel = new JPanel();
      navPanel.setBackground(Color.GRAY);
      JButton homeButton = new JButton("Voltar para o login");
      homeButton.addActionListener(e -> App.changeScreen("login"));
      navPanel.add(homeButton);

      // Painel central com os botões
      JPanel centerPanel = new JPanel(new GridLayout(2, 3, 10, 10));
      centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

      JButton btnAgendamento = new JButton("Novo Agendamento");
      JButton btnPerfil = new JButton("Perfil");
      JButton btnContato = new JButton("Contato");
      JButton btnAvaliacoes = new JButton("Avaliações");
      JButton btnDicas = new JButton("Dicas");
      JButton btnDentistaVirtual = new JButton("Dentista Virtual");
      JButton btnAjuda = new JButton("Ajuda?");

      btnAvaliacoes.addActionListener(this::irParaAvaliacoes);
      btnPerfil.addActionListener(this::irParaPerfil);

      centerPanel.add(btnAgendamento);
      centerPanel.add(btnPerfil);
      centerPanel.add(btnContato);
      centerPanel.add(btnAvaliacoes);
      centerPanel.add(btnDicas);
      centerPanel.add(btnDentistaVirtual);
      centerPanel.add(btnAjuda);

      // Adiciona os painéis ao painel principal
      add(titlePanel, BorderLayout.NORTH);
      add(navPanel, BorderLayout.SOUTH);
      add(centerPanel, BorderLayout.CENTER);
   }

   public void irParaAvaliacoes(ActionEvent e) {

      DadosCadastro dadosCadastro = DadosCadastro.getInstance();
      String cpf = dadosCadastro.getCpf();
      System.out.print("Cpf: ");
      System.out.println(cpf);
      System.out.println("");

      App.changeScreen("avaliacao");
   }

   public void irParaPerfil(ActionEvent e) {
      App.changeScreen("perfil");
   }

}
