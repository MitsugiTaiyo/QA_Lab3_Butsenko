import javax.swing.*;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class GUIForm extends JFrame {
    JComboBox<String> comboBox = new JComboBox<>();
    JTextPane textArea = new JTextPane();

    GUIForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(500, 300));
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        getRootPane().setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        addComponents();
        addActionListeners();

        setVisible(true);
    }

    void addComponents(){
        add(comboBox);
        comboBox.setModel(new DefaultComboBoxModel<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()));
        add(textArea);
        textArea.setText("He have a pencil");
    }
    void addActionListeners() {
        comboBox.addItemListener(e -> textArea.setFont(new Font((String) comboBox.getSelectedItem(), Font.PLAIN, 14)));
        textArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() == 122 || e.getKeyChar() == 120) {
                    int curPos = textArea.getSelectionStart();
                    textArea.select(curPos, curPos + 2);
                    if(textArea.getSelectedText().matches("[Aa]m")) {
                        if (e.getKeyChar() == 122) {
                            StyledDocument doc = textArea.getStyledDocument();
                            Style style = StyleContext.getDefaultStyleContext().getStyle(StyleContext.DEFAULT_STYLE);
                            StyleConstants.setBold(style, true);
                            doc.setCharacterAttributes(curPos, 2, style, true);
                        }
                        if (e.getKeyChar() == 120) {
                            textArea.replaceSelection("was");
                        }
                    }
                    textArea.setSelectionStart(curPos);
                    textArea.setSelectionEnd(curPos);
                    e.consume();
                }
            }
        });
    }
}
