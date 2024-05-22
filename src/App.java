import javax.swing.*;
import java.awt.*;

public class App {

    private static JFrame frame;
    private static JPanel loginPanel;
    private static JPanel cadastroPanel;
    private static JPanel consultaPanel;
    private static JPanel principalPanel;

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
        principalPanel = createPrincipalPanel();
        consultaPanel = createConsultaPanel();

        frame.add(loginPanel, "login");
        frame.add(cadastroPanel, "cadastro");
        frame.add(principalPanel, "principal");
        frame.add(consultaPanel, "agendarConsulta");

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
}