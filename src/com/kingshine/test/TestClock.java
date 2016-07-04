package com.kingshine.test;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestClock extends JFrame {
	MyPanel clockPanel;
	Ellipse2D.Double e;
	int x;
	int y;
	Line2D.Double hourLine;
	Line2D.Double minLine;
	Line2D.Double secondLine;
	GregorianCalendar calendar;
	int hour;
	int minute;
	int second;
	public static final int X = 60;
	public static final int Y = 60;
	public static final int X_BEGIN = 10;
	public static final int Y_BEGIN = 10;
	public static final int RADIAN = 50;

	public TestClock() {
		setSize(300, 400);
		clockPanel = new MyPanel();
		add(clockPanel);
		Timer t = new Timer();
		Task task = new Task();
		t.schedule(task, 0, 1000);
	}

	public static void main(String[] args) {
		TestClock t = new TestClock();
		t.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		t.setVisible(true);

	}

	class MyPanel extends JPanel {
		public MyPanel() {
			e = new Ellipse2D.Double(X_BEGIN, Y_BEGIN, 100, 100);
			hourLine = new Line2D.Double(X, Y, X, Y);
			minLine = new Line2D.Double(X, Y, X, Y);
			secondLine = new Line2D.Double(X, Y, X, Y);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			g2.drawString("12", 55, 25);
			g2.drawString("6", 55, 105);
			g2.drawString("9", 15, 65);
			g2.drawString("3", 100, 65);
			g2.draw(e);
			g2.draw(hourLine);
			g2.draw(minLine);
			g2.draw(secondLine);
		}
	}

	class Task extends TimerTask {
		public void run() {
			calendar = new GregorianCalendar();
			hour = calendar.get(Calendar.HOUR);
			minute = calendar.get(Calendar.MINUTE);
			second = calendar.get(Calendar.SECOND);
			hourLine.x2 = X + 40 * Math.cos(hour * (Math.PI / 6) - Math.PI / 2);
			hourLine.y2 = Y + 40 * Math.sin(hour * (Math.PI / 6) - Math.PI / 2);
			minLine.x2 = X + 45
					* Math.cos(minute * (Math.PI / 30) - Math.PI / 2);
			minLine.y2 = Y + 45
					* Math.sin(minute * (Math.PI / 30) - Math.PI / 2);
			secondLine.x2 = X + 50
					* Math.cos(second * (Math.PI / 30) - Math.PI / 2);
			secondLine.y2 = Y + 50
					* Math.sin(second * (Math.PI / 30) - Math.PI / 2);
			repaint();
		}
	}

}
