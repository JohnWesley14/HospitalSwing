import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroDoisPanel extends JPanel {

   private JTextField txtAlergias;
   private JTextField txtMedicacoes;
   private JTextField txtCondicoes;
   private JTextField txtSeguro;
   private JTextField txtHistorico;
   private JButton btnProcurar;

   String nome;
   String cpf;
   String telefone;
   String email;
   String numeroSaude;
   String senha;

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
         txtHistorico.setText(fileChooser.getSelectedFile().getPath());
      }
   }

   public void fazerCadastro(ActionEvent event) {
      System.out.println(nome);
      System.out.println(getNome());
   }

   public String getNome() {
      return nome;
   }

   public void setNome(String nome) {
      this.nome = nome;
   }

   public String getCpf() {
      return cpf;
   }

   public void setCpf(String cpf) {
      this.cpf = cpf;
   }

   public String getTelefone() {
      return telefone;
   }

   public void setTelefone(String telefone) {
      this.telefone = telefone;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getNumeroSaude() {
      return numeroSaude;
   }

   public void setNumeroSaude(String numeroSaude) {
      this.numeroSaude = numeroSaude;
   }

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }
}
