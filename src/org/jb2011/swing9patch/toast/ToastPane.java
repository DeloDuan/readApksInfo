/*
 * Copyright (C) 2015 Jack Jiang(cngeeker.com) The Swing9patch Project. 
 * All rights reserved.
 * Project URL:https://github.com/JackJiang2011/Swing9patch
 * Version 1.0
 * 
 * Jack Jiang PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * 
 * ToastPane.java at 2015-2-6 16:10:04, original version by Jack Jiang.
 * You can contact author with jb2011@163.com.
 */
package org.jb2011.swing9patch.toast;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jb2011.ninepatch4j.NinePatch;
import org.jb2011.swing9patch.utils.DragToMove;

public class ToastPane extends JPanel
{
	private NinePatch npBackground = null;
	private JComponent content = null;
	
	public ToastPane()
	{
		super(new BorderLayout());
		
		initGUI();
	}
	
	/**
	 * Override to impl ninepatch image background.
	 */
	@Override
	public void paintChildren(Graphics g)
	{
		if(npBackground == null)
			// load the nine patch .PNG
			npBackground = NPIconFactory.getInstance().getToastBg();
		if(npBackground != null)
			// paint background with ninepath
			npBackground.draw((Graphics2D)g, 0, 0, this.getWidth(), this.getHeight());
		super.paintChildren(g);
	}
	
	protected void initGUI()
	{
		this.setOpaque(false);
		
		content = createContent();
		// drag to move
		DragToMove.apply(new Component[]{content});
		
		this.add(content, BorderLayout.CENTER);
		this.setBorder(BorderFactory.createEmptyBorder(19,20,28,20));
	}
	
	/**
	 * Subclass may be override to implement itself logic.
	 * 
	 * @return
	 */
	protected JComponent createContent()
	{
		JLabel lb = new JLabel("");
		lb.setForeground(new Color(230,230,230));
		return lb;
	}
	
	/**
	 * Subclass may be override to implement itself logic.
	 * 
	 * @param message
	 * @see #createContent()
	 */
	public void setMessage(String message)
	{
		((JLabel)content).setText(message);
	}
	
	public JComponent getContent()
	{
		return content;
	}
}
