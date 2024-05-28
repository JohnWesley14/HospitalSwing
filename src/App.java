import javax.swing.*;
import java.awt.*;

public class App {

    private static JFrame frame;
    private static JPanel loginPanel;
    private static JPanel cadastroPanel;
    private static JPanel cadastroDoisPanel;
    private static JPanel consultaPanel;
    private static JPanel principalPanel;
    private static JPanel avaliacaoPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Hospital");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setLayout(new CardLayout());

        loginPanel = createLoginPanel();
        cadastroPanel = createCadastroPanel();
        cadastroDoisPanel = createCadastroDoisPanel();
        principalPanel = createPrincipalPanel();
        consultaPanel = createConsultaPanel();
        avaliacaoPanel = createAvaliacaoPanel();

        frame.add(loginPanel, "login");
        frame.add(cadastroPanel, "cadastro");
        frame.add(cadastroDoisPanel, "cadastroDois");
        frame.add(principalPanel, "principal");
        frame.add(consultaPanel, "agendarConsulta");
        frame.add(avaliacaoPanel, "avaliacao");

        changeScreen("login");
        frame.setVisible(true);
    }

    public static void changeScreen(String scr) {
        CardLayout cl = (CardLayout) (frame.getContentPane().getLayout());
        cl.show(frame.getContentPane(), scr);
    }

    private static JPanel createLoginPanel() {
        return new LoginPanel();
    }

    private static JPanel createCadastroPanel() {
        return new CadastroPanel();
    }

    private static JPanel createPrincipalPanel() {
        return new PrincipalPanel();
    }

    private static JPanel createConsultaPanel() {
        return new ConsultaPanel();
    }

    private static JPanel createCadastroDoisPanel() {
        return new CadastroDoisPanel();
    }

    private static JPanel createAvaliacaoPanel() {
        return new AvaliacaoPanel();
    }
}
