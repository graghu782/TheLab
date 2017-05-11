package GUI;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import java.util.*;
import java.awt.geom.*;


public class GamePanel extends JPanel implements KeyListener, Runnable
{
  public static final int ASSUMED_DRAWING_WIDTH = 400; // These numbers are way too small
  public static final int ASSUMED_DRAWING_HEIGHT = 300; // We are only using them to zoom in on the scene
  
  private double ratioX, ratioY;

  private MovingObject mario;

  private boolean rightKey, leftKey, upKey, downKey;

  private Rectangle2D.Double visibleSpace;
  private Rectangle2D.Double characterSpace;
  
  private Level thisLevel;
  
  public GamePanel () 
  {
	  super();
	  setBackground(Color.WHITE);
	  thisLevel = new Level();
	  visibleSpace = new Rectangle2D.Double(0,thisLevel.getHeight()-ASSUMED_DRAWING_HEIGHT,ASSUMED_DRAWING_WIDTH,ASSUMED_DRAWING_HEIGHT);
	  characterSpace = new Rectangle2D.Double(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
	  
	  mario = new MovingObject("mario.png",0,300,50,60, false);
	  
	  addComponentListener(new ComponentAdapter() {
	  	public void componentResized(ComponentEvent e) {ratioX = (double)e.getComponent().getWidth()/ASSUMED_DRAWING_WIDTH;ratioY = (double)e.getComponent().getHeight()/ASSUMED_DRAWING_HEIGHT;}	  	
	  	});
	  
	  
	  new Thread(this).start();
  }

  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);  // Call JPanel's paintComponent method to paint the background

	Graphics2D g2 = (Graphics2D)g;
	g2.setColor(Color.black);

	AffineTransform at = g2.getTransform();
	g2.scale(ratioX,ratioY);
	g2.translate(-visibleSpace.getX(),-visibleSpace.getY());
	
	thisLevel.draw(g2, this);
    mario.draw(g2,this);
    
    g2.setTransform(at);
  }
  

  
  public void keyPressed(KeyEvent e) 
  {
  	
  	if (e.getKeyCode() == KeyEvent.VK_D) 
  	{
  		this.rightKey = true;
  	} 
  	else if (e.getKeyCode() == KeyEvent.VK_A) 
  	{
  		this.leftKey = true;
  	} 
  	else if (e.getKeyCode() == KeyEvent.VK_W)
  	{
  		this.upKey = true;
  	} 
  	else if (e.getKeyCode() == KeyEvent.VK_S)
  	{
  		this.downKey = true;
  	}
  }
    
  public void keyReleased(KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_D) 
    {
  		this.rightKey = false;
  	} 
    else if (e.getKeyCode() == KeyEvent.VK_A) 
  	{
  		this.leftKey = false;
  	} 
  	else if (e.getKeyCode() == KeyEvent.VK_W) 
  	{
  		this.upKey = false;
  	} 
  	else if (e.getKeyCode() == KeyEvent.VK_S)
  	{
  		this.downKey = false;
  	}
  }

  public void keyTyped(KeyEvent e) 
  {
  	
  }
  

  public void run() 
  {
  	while(true) 
  	{
  		long startTime = System.currentTimeMillis();
	  	
	  	if (upKey) 
	  		mario.moveInLimits(thisLevel, 0, -5);
	  	if (downKey) 
	  		mario.moveInLimits(thisLevel, 0, 5);
	  	if (leftKey) 
	  		mario.moveInLimits(thisLevel, -5, 0);
	  	if (rightKey) 
	  		mario.moveInLimits(thisLevel, 5, 0);
	  	
	  	slideWorldToImage(mario);
	  	
	  	repaint();
	  	
	  	long waitTime = 20 - (System.currentTimeMillis() - startTime);
            
        if (waitTime > 0) 
        {
            try 
            {
                Thread.sleep(waitTime);
            } 
            catch (InterruptedException e) 
            {
            	
            }
        } else Thread.yield();
  	}
  }
  
  public void slideWorldToImage(MovingObject img) 
  {
  	Point2D.Double center = img.getCenter();
	if (!characterSpace.contains(center)) 
	{
		double newX = visibleSpace.getX();
		double newY = visibleSpace.getY();
		
	  	if (center.getX() < characterSpace.getX())
	  	{
	  		newX -= (characterSpace.getX() - center.getX());
	  	} 
	  	else if (center.getX() > characterSpace.getX() + characterSpace.getWidth())
	  	{
	  		newX += (center.getX() - (characterSpace.getX() + characterSpace.getWidth()));
	  	}
	  	
	  	if (center.getY() < characterSpace.getY()) 
	  	{
	  		newY -= (characterSpace.getY() - center.getY());
	  	} 
	  	else if (center.getY() > characterSpace.getY() + characterSpace.getHeight())
	  	{
	  		newY += (center.getY() - characterSpace.getY() - characterSpace.getHeight());
	  	}
	  	newX = Math.max(newX,0);
	  	newY = Math.max(newY,0);
	  	newX = Math.min(newX,thisLevel.getWidth()-visibleSpace.getWidth());
	  	newY = Math.min(newY,thisLevel.getHeight()-visibleSpace.getHeight());
	  	
	  	visibleSpace.setRect(newX,newY,visibleSpace.getWidth(),visibleSpace.getHeight());
	  	
	  	characterSpace.setRect(visibleSpace.getX()+visibleSpace.getWidth()/5,visibleSpace.getY()+visibleSpace.getHeight()/5,visibleSpace.getWidth()*3/5,visibleSpace.getHeight()*3/5);
	}
  }
  
  public Point assumedCoordinatesToActual(Point assumed) 
  {
    return new Point((int)((assumed.getX() - visibleSpace.getX())*ratioX), (int)((assumed.getY() - visibleSpace.getY())*ratioY));
  }

  public Point actualCoordinatesToAssumed(Point actual) 
  {
    return new Point((int)(actual.getX()/ratioX + visibleSpace.getX()), (int)(actual.getY()/ratioY + visibleSpace.getY()));
  }


}
