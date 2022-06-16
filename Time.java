import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
public class Time extends JFrame
{
    private static final long serialVersionUID = 1L;
    private static final String[] NS =
            { "会议发言计时器",
                    "时", "分", "秒",
                    "计时总分钟数:",
                    "你已用:", "时", "分", "秒","发言剩余时间:",
                    "开始倒计时", "暂停计时", "停止计时" };
    private static int index = 0;
    private long ms = 0;
    private boolean isPaused = false;
    private Timer timer;
    public Time(String title)
    {
        setTitle(title);
    }
    private Time addComponents()
    {
        final JTextField[] ts = new JTextField[7];
        for(int i = 0; i < 4; i++)
        {//界面设计
            ts[i] = new JTextField();
            ts[i].setPreferredSize(new Dimension(50, 50));
            ts[i].setFont(new Font("宋体",Font.BOLD,30));
            ts[i].setHorizontalAlignment(SwingConstants.CENTER);
            ts[i].setOpaque(true);
            ts[i].setBackground(Color.CYAN);
            ts[i].setForeground(Color.RED);
            if(i != 3)
            {
                ts[i].setEditable(false);
            }
        }
        for(int i = 4; i < 7; i++)
        {
            ts[i] = new JTextField();
            ts[i].setPreferredSize(new Dimension(40, 60));
            ts[i].setFont(new Font("宋体",Font.BOLD,30));
            ts[i].setHorizontalAlignment(SwingConstants.CENTER);
            ts[i].setOpaque(true);
            ts[i].setBackground(Color.BLUE);
            ts[i].setForeground(Color.RED);
            if(i != 3)
            {
                ts[i].setEditable(false);
            }
        }
        JLabel[] ls = new JLabel[10];
        for(int i = 0; i < 10; i++)
        {
            ls[i] = new JLabel(NS[index++]);
            ls[i].setForeground(Color.black);
            ls[i].setFont(new Font("宋体",Font.BOLD,30));
        }
        final JButton[] bs = new JButton[3];
        for(int i = 0; i < 3; i++)
        {
            bs[i] = new JButton(NS[index++]);
            bs[i].setBackground(Color.BLUE);
            bs[i].setForeground(Color.YELLOW);
        }
        bs[0].addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        try
                                        {
                                            if(!isPaused)
                                            {
                                                long min = Long.parseLong(ts[3].getText());
                                                long h = min / 60;//时分秒
                                                h = h < 0 ? 0 : h;
                                                long m = min - h * 60 - 1;
                                                m = m < 0 ? 0 : m;
                                                long s = min == 0 ? 0 : 60;
                                                ts[0].setText(h + "");
                                                ts[0].setBackground(Color.BLUE);
                                                ts[0].setForeground(Color.YELLOW);
                                                ts[1].setText(m + "");
                                                ts[1].setBackground(Color.BLUE);
                                                ts[1].setForeground(Color.YELLOW);
                                                ts[2].setText(s + "");
                                                ts[2].setBackground(Color.BLUE);
                                                ts[2].setForeground(Color.YELLOW);
                                            }
                                            timer = new Timer();
                                            timer.schedule(new TimerTask()
                                            {
                                                @Override
                                                public void run()
                                                {
                                                    long h = Long.parseLong(ts[0].getText());
                                                    long m = Long.parseLong(ts[1].getText());
                                                    long s = Long.parseLong(ts[2].getText());
                                                    s--;
                                                    ms++;
                                                    if((h != 0 || m != 0) && s == 0)
                                                    {
                                                        m--;
                                                        s = 59;
                                                    }
                                                    if(h != 0 && m == 0)
                                                    {
                                                        h--;
                                                        m = 59;
                                                    }
                                                    h = h < 0 ? 0 : h;
                                                    m = m < 0 ? 0 : m;
                                                    s = s < 0 ? 0 : s;
                                                    ts[0].setText(h + "");
                                                    ts[1].setText(m + "");
                                                    ts[2].setText(s + "");
                                                    long ph = ms / 60 / 60;
                                                    long pm = ms / 60;
                                                    long ps = ms % 60;
                                                    ts[4].setText(ph + "");
                                                    ts[5].setText(pm + "");
                                                    ts[6].setText(ps + "");
                                                    if(h == 0 && m == 0 && s == 0)
                                                    {
                                                        bs[2].doClick();
                                                    }
                                                }
                                            }, 0, 1000);
                                            bs[0].setEnabled(false);
                                        }
                                        catch(NumberFormatException nfe)
                                        {
                                            JOptionPane.showConfirmDialog(Time.this,
                                                    "输入错误，请重新输入分钟的整数。",
                                                    "友情提示",
                                                    JOptionPane.PLAIN_MESSAGE,
                                                    //Java消息提示框JOptionPane的使用方法
                                                    JOptionPane.WARNING_MESSAGE);
                                        }
                                    }
                                }
        );
        bs[1].addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        if(null != timer)
                                        {
                                            timer.cancel();
                                            timer = null;
                                        }
                                        bs[0].setEnabled(true);
                                        isPaused = true;
                                    }
                                }
        );
        bs[2].addActionListener(new ActionListener()
                                {
                                    @Override
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        if(null != timer)
                                        {
                                            timer.cancel();
                                            timer = null;
                                        }
                                        bs[0].setEnabled(true);
                                        isPaused = false;
                                        ms = 0;
                                    }
                                }
        );
        JComponent[][] cs = { { ls[0] },
                {ls[4], ts[3] },
                {ls[9],ts[0], ls[1], ts[1], ls[2], ts[2], ls[3] },
                { ls[5], ts[4], ls[6], ts[5], ls[7], ts[6], ls[8] },
                { bs[0], bs[1], bs[2] } };
        JPanel[] ps = new JPanel[5];
        JPanel wrap = new JPanel();
        wrap.setLayout(new BoxLayout(wrap, BoxLayout.Y_AXIS));
        for(int i = 0; i < 5; i++)
        {
            ps[i] = new JPanel(new FlowLayout(FlowLayout.CENTER));
            for(int j = 0; j < cs[i].length; j++)
            {
                ps[i].add(cs[i][j]);
                ps[i].setBackground(Color.YELLOW);
            }
            wrap.add(ps[i]);
        }
        add(wrap);
        return this;
    }
    private Time init()
    {
        pack();
        setResizable(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        return this;
    }
    public static void main(String[] args)
    {
        Time time = new Time(NS[index]);
        time.addComponents().init();
    }
}