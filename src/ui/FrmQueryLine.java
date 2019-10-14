package ui;

import test.DataBuilder;
import test.Line;
import test.Station;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import javax.swing.*;

public class FrmQueryLine extends JDialog implements ActionListener {
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JButton btnOk = new JButton("查询");
    private JLabel labelLineName = new JLabel("请输入线路名称：");
    private JTextArea lineinformation=new JTextArea();

    List<String> list=new Line().loadAllLineName();
    String[] ingName = list.toArray(new String[list.size()]);
    private JComboBox cmbIngredient= new JComboBox(ingName);

    public FrmQueryLine(Frame f, String s, boolean b) {
        super(f, s, b);
        workPane.setLayout(null);
        labelLineName.setBounds(10, 9, 129, 19);
        workPane.add(labelLineName);
        cmbIngredient.setBounds(146, 7, 96, 23);
        workPane.add(cmbIngredient);
        btnOk.setBounds(268, 5, 75, 23);
        workPane.add(btnOk);
        lineinformation.setFont(new Font("Monospaced", Font.BOLD, 14));
        lineinformation.setLineWrap(true);        //激活自动换行功能
        lineinformation.setWrapStyleWord(false);            // 激活断行不断字功能
        lineinformation.setBounds(22, 54, 643, 167);
        workPane.add(lineinformation);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(700, 300);
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
            String ingredientName=this.cmbIngredient.getSelectedItem().toString();
            try {
                List<Station> stations=DataBuilder.map.get(ingredientName);
                lineinformation.setText("");
                for(Station station:stations)
                {
                    lineinformation.append(station.getName()+"----");
                }
                lineinformation.paintImmediately(lineinformation.getBounds());
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
    }
}