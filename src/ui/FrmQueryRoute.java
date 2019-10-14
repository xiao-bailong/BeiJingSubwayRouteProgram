package ui;

import test.DataBuilder;
import test.Line;
import test.Station;
import test.Subway;
import util.BusinessException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.*;

public class FrmQueryRoute extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("查询");
    private JLabel labelS1 = new JLabel("起始站：");
    private JLabel labelS2 = new JLabel("目标站：");
    private JTextArea lineinformation=new JTextArea();

    private JTextField edtS1 = new JTextField(20);
    private JTextField edtS2 = new JTextField(20);

    public FrmQueryRoute(Frame f, String s, boolean b) {
        super(f, s, b);
        workPane.setLayout(null);
        labelS1.setBounds(26, 9, 67, 15);
        workPane.add(labelS1);
        edtS1.setBounds(103, 6, 126, 21);
        workPane.add(edtS1);
        labelS2.setBounds(265, 9, 67, 15);
        workPane.add(labelS2);
        edtS2.setBounds(361, 6, 126, 21);
        workPane.add(edtS2);
        btnOk.setBounds(519, 5, 77, 23);
        workPane.add(btnOk);
        lineinformation.setFont(new Font("Monospaced", Font.BOLD, 14));
        lineinformation.setLineWrap(true);        //激活自动换行功能
        lineinformation.setWrapStyleWord(false);            // 激活断行不断字功能
        lineinformation.setBounds(36, 33, 908, 204);
        workPane.add(lineinformation);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(1000, 300);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();
        this.btnOk.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this.btnOk){
            String S1Name=this.edtS1.getText();
            String S2Name=this.edtS2.getText();
            try {
                int flag1=0,flag2=0;
                for(Station s:DataBuilder.mapOfStation) {
                    if(S1Name.equals(s.getName())) {
                        flag1=1;
                    }
                    if(S2Name.equals(s.getName())) {
                        flag2=1;
                    }
                }
                if(flag1==0) throw new BusinessException("起始站不存在");
                if(flag2==0) throw new BusinessException("目标站不存在");
                lineinformation.setText("");//清空JTextArea
                if(S1Name.equals(S2Name))
                    lineinformation.append("目的站与起始站相同");
                else {
                    Subway sw = new Subway();
                    Subway.route.clear();//再次运行清空路线
                    sw.calculate(new Station(S1Name), new Station(S2Name));
                    for(String str:Subway.route)
                    {
                        lineinformation.append(str);
                    }
                }
                lineinformation.paintImmediately(lineinformation.getBounds());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}
