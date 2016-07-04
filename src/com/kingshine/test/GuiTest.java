package com.kingshine.test;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class GuiTest extends JFrame {

	private static final long serialVersionUID = 1L;

	JLabel jl;

	public GuiTest() {
		// 初始化组件
		initComponents();
		// 设置窗口大小
		setSize(300, 300);
		// 关闭
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		// 可见
		setVisible(true);
		// 睡眠,便于观察
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
		// 改变背景颜色
		jl.setForeground(Color.BLUE);
	}

	// 初始化组件
	/**
	 * 添加一个标签,名字是"blue" 加入Frame 设置获取标签(它本身) 重画
	 * 
	 * @throws InterruptedException
	 */
	private void initComponents() {
		jl = new JLabel("blue");

		add(jl);

		jl.setText(jl.getText());
		jl.repaint();

	}

	// test
	// 启动窗口
	public static void main(String[] args) {
		new GuiTest();
	}
}