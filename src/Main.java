import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private JTextField gamerNameField;
    private JTextField countryField;
    private JTextField gameField;
    private JButton searchButton;
    private JTextArea resultsArea;
    private JLabel loadingLabel;

    private JPanel cardPanel;
    private CardLayout cardLayout;

    public Main() {
        setTitle("Gamer Finder");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        JPanel searchPanel = new JPanel(new GridLayout(5, 1));
        searchPanel.add(new JLabel("Gamer Name:"));
        gamerNameField = new JTextField();
        searchPanel.add(gamerNameField);

        searchPanel.add(new JLabel("Country:"));
        countryField = new JTextField();
        searchPanel.add(countryField);

        searchPanel.add(new JLabel("Game:"));
        gameField = new JTextField();
        searchPanel.add(gameField);

        searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchGamers();
            }
        });
        searchPanel.add(searchButton);

        JPanel loadingPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loadingLabel = new JLabel("Loading...");
        loadingPanel.add(loadingLabel);

        JPanel resultsPanel = new JPanel();
        resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);
        resultsPanel.add(scrollPane);

        cardPanel.add(searchPanel, "SearchPanel");
        cardPanel.add(loadingPanel, "LoadingPanel");
        cardPanel.add(resultsPanel, "ResultsPanel");

        add(cardPanel);

        setLocationRelativeTo(null);
    }

    private void searchGamers() {
        cardLayout.show(cardPanel, "LoadingPanel");
        // Имитация задержки для анимации обработки запроса
        Timer timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Выполнение поиска геймеров
                String gamerName = gamerNameField.getText();
                String country = countryField.getText();
                String game = gameField.getText();

                // Просто пример для вывода результата в JTextArea
                String result = "Ваш запрос обрабатывается";
                resultsArea.setText(result);

                cardLayout.show(cardPanel, "ResultsPanel");
            }
        });
        timer.setRepeats(false); // Остановить таймер после однократного выполнения
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
