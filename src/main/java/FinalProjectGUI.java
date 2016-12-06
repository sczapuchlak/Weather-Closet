import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FinalProjectGUI extends JFrame {
    private JTextField zipCodeText;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;
    private JRadioButton bothRadioButton;
    private JCheckBox businessCheckBox;
    private JCheckBox casualCheckBox;
    private JCheckBox athleticCheckBox;
    private JCheckBox specialEventCheckBox;
    private JCheckBox allCheckBox;
    private JRadioButton yesRadioButton;
    private JRadioButton noRadioButton;
    private JButton iMReadyToButton;
    private JButton iVeDecidedIButton;
    private JPanel rootPanel;

    protected FinalProjectGUI() {
        this.setContentPane(this.rootPanel);
        this.pack();
        this.setVisible(true);
        this.iVeDecidedIButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
