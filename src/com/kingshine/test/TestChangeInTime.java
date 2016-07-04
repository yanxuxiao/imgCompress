package com.kingshine.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.kingshine.layout.BaseBorderLayout;
import com.kingshine.layout.CommonButton;
import com.kingshine.util.CompressPicUtils;

public class TestChangeInTime extends BaseBorderLayout{
	private static final long serialVersionUID = 1L;
	private JTextField tf ;
	private File dir ;
	private JPanel p ;
	private JLabel jl_count ;
	public TestChangeInTime(){
		this.setTitle("设置");
		this.setResizable(false);
		this.setBounds(new Rectangle(800, 600));
		this.setLocationRelativeTo(null);//让窗体居中
//		window.pack();//该代码依据放置的组件设定窗口的大小使之正好能容纳你放置的所有组件
		
		//顶部处理
		JPanel ptop=new JPanel();
		ptop.setLayout(new FlowLayout(FlowLayout.LEFT,5,5));
		
		CommonButton b1 = new CommonButton("文件压缩") ;
		CommonButton b9 = new CommonButton("退出") ;
		
		ptop.add(b1) ;
		ptop.add(b9) ;
		
		this.getContentPane().add("North", ptop);
		
		//中间界面
		p = new JPanel();
		p.setBorder(BorderFactory.createTitledBorder("设置"));
//		p.setBorder(BorderFactory.createEtchedBorder());
//		p.setPreferredSize(new Dimension(600, 400));
//		p.setBounds(new Rectangle(600, 400));
//		p.setSize(800, 600);
		
//		GridLayout layout = new GridLayout(0, 2) ;//GridBagLayout
//		layout.setHgap(10);
//	    layout.setVgap(10);
		GridBagLayout layout = new GridBagLayout();
		p.setLayout(layout);
	    
		// new JFormattedTextField(new SimpleDateFormat("mm/dd/yy")
		//new JFormattedTextField(new DecimalFormat("###,###")
		//JFormattedTextField phoneField = new JFormattedTextField(new MaskFormatter("(###)###-####"));
		
		GridBagConstraints s= new GridBagConstraints();
		s.fill = GridBagConstraints.BOTH;
        //该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        //NONE：不调整组件大小。
        //HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        //VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        //BOTH：使组件完全填满其显示区域。
		
		s.gridwidth=1;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 1;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        tf = new JTextField();
		p.add(tf,s) ;
		
		s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty = 0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        JButton filebtn = new JButton("选择文件夹") ;
		p.add(filebtn,s) ;
		
		
		s.gridwidth = 0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        s.weightx = 1;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        jl_count = new JLabel("0",JLabel.CENTER) ;
		p.add(jl_count,s) ;
//		tf = new JTextField();
//		s.gridwidth=0;//该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
//        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
//        s.weighty=0;//该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
//        p.add(tf, s);//设置组件
		
		
		JButton startbtn = new JButton("开始压缩") ;
		Dimension preferredSize = new Dimension(100,30);
		startbtn.setPreferredSize(preferredSize);
		s.gridwidth = 2;
        s.fill = GridBagConstraints.NONE ;
        p.add(startbtn, s);
		
		
		this.getContentPane().add("Center", p);
		
		startbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startCompress();
			}
		});
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickb1();
			}
		});
		b9.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clickb9();
			}
		});
		filebtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				filechose();
			}
		});
	}
	public static void main(String[] args) {
		TestChangeInTime window = new TestChangeInTime() ;
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 * 开始压缩
	 */
	public void startCompress(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String path = tf.getText() ;
				File dir=new File(path);
				String imageTypes = ".bmp.dib.gif.jfif.jpe.jpeg.jpg.png.tif.tiff.ico";
				String[] filesNames=dir.list();
				generatePreview(filesNames, imageTypes, path);
			}
		}).start() ;
	}
	/**
	 * 
	 * @param filesNames
	 * @param imageTypes
	 * @param path
	 * @return hasSubDir 
	 */
	int count = 0 ;
	private void generatePreview(String[]  filesNames,String imageTypes,String path){
		if(null!=filesNames){
			File temp = null ;
			String showtext = "";
			for (String fileName : filesNames) {
				count++;
				showtext = path+"/"+fileName ;
				System.out.println(showtext);
				showtext(showtext);
				temp = new File(path+"/"+fileName);
				if(temp.isDirectory()){
					generatePreview(temp.list(),imageTypes,temp.getPath());
			        continue;
			    }
			}
			jl_count.setText("预览图片生成成功,总共处理数量:"+count);
			jl_count.setForeground(Color.RED);
		}
	}
	private void showtext(final String showtext){
//		try {
//            Thread.sleep(500);
//        } catch (InterruptedException ex) {
//            ex.printStackTrace();
//        }
		jl_count.setText(showtext);
	}
	/**
	 * 选择文件
	 */
	public void filechose(){
		JFileChooser jfc = new JFileChooser();
		jfc.setDialogTitle("选择一个目录");
		jfc.setDialogType(JFileChooser.OPEN_DIALOG);
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);//只能选择目录
		int res = jfc.showOpenDialog(null);
		if (res == JFileChooser.APPROVE_OPTION) {
			dir = jfc.getSelectedFile();
//			System.out.println(dir.getAbsolutePath());
			tf.setText(dir.getAbsolutePath());
		}
	}
	/**
	 * 设置按钮点击事件
	 */
	public void clickb1(){
		p.setVisible(true);
	}
	
	/**
	 * 退出按钮点击事件
	 */
	public void clickb9(){
		int result = JOptionPane.showConfirmDialog(this, "确认退出?", "提示信息", JOptionPane.YES_NO_OPTION) ;
		//yes 0 ;no 1
		if(result==0){//退出
//			this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) );
			System.exit(0);
		}
	}
	
}
