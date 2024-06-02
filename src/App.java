import javax.swing.*;
import java.awt.*;

public class App {

    private static JFrame frame;
    private static JPanel loginPanel;
    private static JPanel cadastroPanel;
    private static JPanel cadastroDoisPanel;

    private static JPanel principalPanel;
    private static JPanel avaliacaoPanel;
    private static JPanel perfilPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        frame = new JFrame("Hospital");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setLayout(new CardLayout());

        loginPanel = createLoginPanel();
        cadastroPanel = createCadastroPanel();
        cadastroDoisPanel = createCadastroDoisPanel();
        principalPanel = createPrincipalPanel();

        avaliacaoPanel = createAvaliacaoPanel();
        perfilPanel = createPerfilPanel();

        frame.add(loginPanel, "login");
        frame.add(cadastroPanel, "cadastro");
        frame.add(cadastroDoisPanel, "cadastroDois");
        frame.add(principalPanel, "principal");
        frame.add(avaliacaoPanel, "avaliacao");
        frame.add(perfilPanel, "perfil");

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

    private static JPanel createCadastroDoisPanel() {
        return new CadastroDoisPanel();
    }

    private static JPanel createAvaliacaoPanel() {
        return new AvaliacaoPanel();
    }

    private static JPanel createPerfilPanel() {
        return new PerfilPanel();
    }
}
