public class DadosCadastro {

   private static DadosCadastro instance;

   private String nome;
   private String cpf;
   private String telefone;
   private String email;
   private String numeroSaude;
   private String senha;
   private String sexo;

   private String alergias;
   private String medicacoes;
   private String condicoes;
   private String seguro;
   private String historico;

   // Getters and Setters for all fields
   public static synchronized DadosCadastro getInstance() {
      if (instance == null) {
         instance = new DadosCadastro();
      }
      return instance;
   }

   public String getSexo() {
      return sexo;
   }

   public void setSexo(String sexo) {
      this.sexo = sexo;
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

   public String getSenha() {
      return senha;
   }

   public void setSenha(String senha) {
      this.senha = senha;
   }

   public String getAlergias() {
      return alergias;
   }

   public void setAlergias(String alergias) {
      this.alergias = alergias;
   }

   public String getMedicacoes() {
      return medicacoes;
   }

   public void setMedicacoes(String medicacoes) {
      this.medicacoes = medicacoes;
   }

   public String getCondicoes() {
      return condicoes;
   }

   public void setCondicoes(String condicoes) {
      this.condicoes = condicoes;
   }

   public String getSeguro() {
      return seguro;
   }

   public void setSeguro(String seguro) {
      this.seguro = seguro;
   }

   public String getHistorico() {
      return historico;
   }

   public void setHistorico(String historico) {
      this.historico = historico;
   }

   @Override
   public String toString() {
      return "DadosCadastro{" +
            "nome='" + nome + '\'' +
            ", cpf='" + cpf + '\'' +
            ", telefone='" + telefone + '\'' +
            ", email='" + email + '\'' +
            ", numeroSaude='" + numeroSaude + '\'' +
            ", senha='" + senha + '\'' +
            ", alergias='" + alergias + '\'' +
            ", medicacoes='" + medicacoes + '\'' +
            ", condicoes='" + condicoes + '\'' +
            ", seguro='" + seguro + '\'' +
            ", historico='" + historico + '\'' +
            '}';
   }
}
