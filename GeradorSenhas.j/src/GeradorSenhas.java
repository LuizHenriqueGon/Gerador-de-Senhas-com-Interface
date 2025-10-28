import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Clipboard;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom; // Importante para segurança!

public class GeradorSenhas extends JFrame {

    // Constantes dos caracteres
    private static final String MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMEROS = "0123456789";
    private static final String SIMBOLOS = "!@#$%^&*()-_=+<>?";

    // Componentes da UI
    private JSpinner spinnerTamanho;
    private JCheckBox checkMaiusculas;
    private JCheckBox checkMinusculas;
    private JCheckBox checkNumeros;
    private JCheckBox checkSimbolos;
    private JTextField textSenhaGerada;
    private JButton btnCopiar;

    public GeradorSenhas() {
        // --- Configuração básica da Janela ---
        setTitle("Gerador de Senhas Seguras 🔐");
        setSize(480, 320); // Tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centralizar na tela
        setLayout(new BorderLayout(10, 10)); // Layout principal
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margem

        // --- Painel de Opções (Centro) ---
        JPanel painelOpcoes = new JPanel();
        painelOpcoes.setLayout(new GridLayout(5, 1, 5, 5)); // 5 linhas, 1 coluna
        painelOpcoes.setBorder(BorderFactory.createTitledBorder("Opções"));

        // 1. Tamanho
        JPanel panelTamanho = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTamanho.add(new JLabel("Tamanho da Senha (8-128):"));
        // Spinner: min 8, inicial 16, max 128, passo 1
        spinnerTamanho = new JSpinner(new SpinnerNumberModel(16, 8, 128, 1));
        panelTamanho.add(spinnerTamanho);
        painelOpcoes.add(panelTamanho);

        // 2. Checkboxes
        checkMaiusculas = new JCheckBox("Incluir Maiúsculas (A-Z)", true);
        checkMinusculas = new JCheckBox("Incluir Minúsculas (a-z)", true);
        checkNumeros = new JCheckBox("Incluir Números (0-9)", true);
        checkSimbolos = new JCheckBox("Incluir Símbolos (!@#$%)", true);

        painelOpcoes.add(checkMaiusculas);
        painelOpcoes.add(checkMinusculas);
        painelOpcoes.add(checkNumeros);
        painelOpcoes.add(checkSimbolos);

        add(painelOpcoes, BorderLayout.CENTER);

        // --- Painel de Ação (Baixo) ---
        JPanel painelAcao = new JPanel(new GridLayout(2, 1, 5, 5));

        JButton btnGerar = new JButton("Gerar Nova Senha");
        btnGerar.setFont(new Font("Arial", Font.BOLD, 14));
        painelAcao.add(btnGerar);

        // --- Painel de Resultado (dentro do painel de ação) ---
        JPanel painelResultado = new JPanel(new BorderLayout(10, 0));
        textSenhaGerada = new JTextField("Clique em 'Gerar' para criar sua senha...");
        textSenhaGerada.setEditable(false);
        textSenhaGerada.setFont(new Font("Monospaced", Font.PLAIN, 14));

        btnCopiar = new JButton("Copiar");
        btnCopiar.setEnabled(false); // Começa desabilitado

        painelResultado.add(textSenhaGerada, BorderLayout.CENTER);
        painelResultado.add(btnCopiar, BorderLayout.EAST);
        painelAcao.add(painelResultado);

        add(painelAcao, BorderLayout.SOUTH);

        // --- Lógica (ActionListeners) ---

        // Ação do Botão GERAR
        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gerarSenha();
            }
        });

        // Ação do Botão COPIAR
        btnCopiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                copiarSenha();
            }
        });
    }

    private void gerarSenha() {
        int tamanho = (int) spinnerTamanho.getValue();

        StringBuilder poolCaracteres = new StringBuilder();
        if (checkMaiusculas.isSelected()) poolCaracteres.append(MAIUSCULAS);
        if (checkMinusculas.isSelected()) poolCaracteres.append(MINUSCULAS);
        if (checkNumeros.isSelected()) poolCaracteres.append(NUMEROS);
        if (checkSimbolos.isSelected()) poolCaracteres.append(SIMBOLOS);

        String pool = poolCaracteres.toString();

        if (pool.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Selecione pelo menos um tipo de caractere!", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Usar SecureRandom é crucial para senhas seguras!
        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder(tamanho);

        for (int i = 0; i < tamanho; i++) {
            int index = random.nextInt(pool.length());
            senha.append(pool.charAt(index));
        }

        textSenhaGerada.setText(senha.toString());
        btnCopiar.setEnabled(true); // Habilita o botão copiar
    }

    private void copiarSenha() {
        String senha = textSenhaGerada.getText();
        if (senha.isEmpty() || senha.startsWith("Clique em 'Gerar'")) {
            return; // Não copia o placeholder
        }

        // Copia para a Área de Transferência
        StringSelection stringSelection = new StringSelection(senha);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);

        JOptionPane.showMessageDialog(this, "Senha copiada para a área de transferência!");
    }

    // Método principal para rodar a aplicação
    public static void main(String[] args) {
        // Garante que a UI rode na thread de eventos do Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GeradorSenhas().setVisible(true);
            }
        });
    }
}
