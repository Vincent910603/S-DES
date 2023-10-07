import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class window extends JFrame{
    JFrame deswindow = new JFrame("操作界面");
    public window(){
        deswindow.setSize(400,300);
        deswindow.setDefaultCloseOperation(3);
        JPanel panel = new JPanel();
        deswindow.add(panel);
        panelset(panel);
        deswindow.setVisible(true);
    }

    public void panelset(JPanel panel){
        panel.setLayout(null);
        JLabel minglabel = new JLabel("明文");
        minglabel.setBounds(10,20,80,25);
        panel.add(minglabel);
        JLabel mwlabel = new JLabel("密文");
        mwlabel.setBounds(10,110,80,25);
        panel.add(mwlabel);
        JTextField mingtext = new JTextField(20);
        mingtext.setBounds(50,20,165,25);
        panel.add(mingtext);
        panel.setLayout(null);
        JTextField mwtext = new JTextField(20);
        mwtext.setBounds(50,110,165,25);
        panel.add(mwtext);
        panel.setLayout(null);
        JLabel yuelabel = new JLabel("密钥");
        yuelabel.setBounds(10,50,80,25);
        panel.add(yuelabel);
        JTextField yuetext = new JTextField(1);
        yuetext.setBounds(50,50,165,25);
        panel.add(yuetext);
        JButton trbutton = new JButton("转换");
        trbutton.setBounds(10,80,80,25);
        panel.add(trbutton);
        JButton jiebutton = new JButton("解密");
        jiebutton.setBounds(100,80,80,25);
        panel.add(jiebutton);
        JTextField mitext = new JTextField(1);
        mitext.setBounds(50,140,165,25);
        panel.add(mitext);
        mitext.setEnabled(false);
        JTextField jmtext = new JTextField(1);
        jmtext.setBounds(50,170,165,25);
        panel.add(jmtext);
        jmtext.setEnabled(false);
        trbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ming = mingtext.getText();
                String mi = yuetext.getText();
                String miwen = jjmi.jiami(ming,mi);
                mitext.setText(miwen);
                System.out.println(miwen);
            }
        });
        jiebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mw = mwtext.getText();
                String mi = yuetext.getText();
                String miwen = jjmi.jiemi(mw,mi);
                jmtext.setText(miwen);
                System.out.println(miwen);
            }
        });
        mitext.setText("密钥");
    }
}