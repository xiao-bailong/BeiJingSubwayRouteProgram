package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import test.DataBuilder;
import util.BaseException;
import util.BusinessException;

//extends JFrame implements ActionListener
public class FrmMain extends JFrame implements ActionListener {
    private JMenuBar menubar = new JMenuBar();
    private JPanel toolBar = new JPanel();
    private JPanel workPane = new JPanel();
    private JPanel statusBar = new JPanel();
    private JButton btnLogin = new JButton("查询最短路径");
    private JButton btnRegister = new JButton("查询线路信息");
    private JButton btnCancel = new JButton("退出");


    private JLabel labelUser = new JLabel("欢迎使用北京地铁系统");

    public FrmMain() {
        statusBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        JLabel label=new JLabel();
        label=new JLabel("总站点数: "+ DataBuilder.totalStaion);
        statusBar.add(label);
        menubar.add(statusBar);
        this.setJMenuBar(menubar);
        this.setTitle("北京地铁系统");
        toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
        toolBar.add(btnLogin);
        toolBar.add(btnRegister);
        toolBar.add(btnCancel);
        this.getContentPane().add(toolBar, BorderLayout.SOUTH);
        workPane.setLayout(null);
        labelUser.setFont(new Font("宋体", Font.PLAIN, 25));
        labelUser.setBounds(22, 25, 269, 100);
        workPane.add(labelUser);
        this.getContentPane().add(workPane, BorderLayout.CENTER);
        this.setSize(330, 250);
        // 屏幕居中显示
        double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        this.setLocation((int) (width - this.getWidth()) / 2,
                (int) (height - this.getHeight()) / 2);

        this.validate();

        btnLogin.addActionListener(this);
        btnCancel.addActionListener(this);
        this.btnRegister.addActionListener(this);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.btnLogin) {
            FrmQueryRoute dlg=new FrmQueryRoute(this, "查询最短路径", true);
            dlg.setVisible(true);
        } else if (e.getSource() == this.btnCancel) {
            System.exit(0);
        } else if(e.getSource()==this.btnRegister){
            FrmQueryLine dlg=new FrmQueryLine(this, "查询线路站点信息", true);
            dlg.setVisible(true);
        }
    }

}
