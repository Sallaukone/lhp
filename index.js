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