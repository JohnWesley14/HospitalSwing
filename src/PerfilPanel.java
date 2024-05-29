import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.*;

public class PerfilPanel extends JPanel {
   private Boolean hasExecuted = false;

   public PerfilPanel() {

      addMouseListener(new MouseAdapter() {
         @Override
         public void mouseEntered(MouseEvent e) {
            if (hasExecuted != true) {

               puxarInfoPerfil(null);
               // Função a ser executada quando o mouse entra no componente
               System.out.println("Mouse está sobre o componente!");
               hasExecuted = true;
            }
         }

      });

   }

   public void puxarInfoPerfil(ActionEvent e) {
      DadosCadastro dadosCadastro = DadosCadastro.getInstance();

      // Recupera os dados do cadastro
      String nome = dadosCadastro.getNome();
      String cpf = dadosCadastro.getCpf();
      String telefone = dadosCadastro.getTelefone();
      String email = dadosCadastro.getEmail();

      String sexo = dadosCadastro.getSexo();
      String alergias = dadosCadastro.getAlergias();
      String medicacoes = dadosCadastro.getMedicacoes();
      String condicoes = dadosCadastro.getCondicoes();
      String seguro = dadosCadastro.getSeguro();
      String historico = dadosCadastro.getHistorico();

      // Remova o método createInfo() daqui e chame-o diretamente

      JPanel infoPanel = createInfo(nome, cpf, telefone, email, sexo, alergias, medicacoes,
            condicoes, seguro, historico);
      removeAll(); // Limpa todos os componentes existentes no PerfilPanel
      add(infoPanel); // Adiciona o novo painel de informações
      revalidate();
      repaint();
   }

   public JPanel createInfo(String nome, String cpf, String telefone, String email,
         String sexo, String alergias, String medicacoes, String condicoes, String seguro, String historico) {
      // Adiciona labels e valores ao painel
      JPanel card = new JPanel();
      card.setLayout(new GridLayout(0, 2, 10, 5)); // Configura um layout de grade com 0 linhas e 2 colunas (linhas
                                                   // automáticas)
      card.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Adiciona uma margem interna ao painel

      card.add(new JLabel("Nome:"));
      card.add(new JLabel(nome));

      card.add(new JLabel("CPF:"));
      card.add(new JLabel(cpf));

      card.add(new JLabel("Telefone:"));
      card.add(new JLabel(telefone));

      card.add(new JLabel("Email:"));
      card.add(new JLabel(email));

      card.add(new JLabel("Sexo:"));
      card.add(new JLabel(sexo));

      card.add(new JLabel("Alergias:"));
      card.add(new JLabel(alergias));

      card.add(new JLabel("Medicações:"));
      card.add(new JLabel(medicacoes));

      card.add(new JLabel("Condições:"));
      card.add(new JLabel(condicoes));

      card.add(new JLabel("Seguro:"));
      card.add(new JLabel(seguro));

      // Adiciona o histórico médico em uma área de texto com barra de rolagem

      JButton btnAbrir = new JButton("Abrir");
      btnAbrir.addActionListener(this::abrirHistorico);
      card.add(btnAbrir);

      JButton btnVoltar = new JButton("Voltar");
      btnVoltar.addActionListener(this::irParaPrincipal);
      card.add(btnVoltar);

      return card;
   }

   public void irParaPrincipal(ActionEvent e) {

      hasExecuted = false;
      App.changeScreen("principal");
   }

   public void abrirHistorico(ActionEvent e) {
      DadosCadastro dadosCadastro = new DadosCadastro().getInstance();
      try {
         File arquivo = new File(dadosCadastro.getHistorico());
         Desktop.getDesktop().open(arquivo);
      } catch (IOException err) {
         err.printStackTrace();
      }
   }

}
