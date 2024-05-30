package Database;

public class DadosCadastro {

   private String alergias;
   private String medicacoes;
   private String condicoes;
   private String seguro;
   private String historico;

   // Getters and Setters for all fields

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
      return "Oopa";
   }
}
