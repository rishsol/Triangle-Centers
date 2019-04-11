import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Line2D;

import javax.swing.JFrame;

public class Centroid extends RandomTriangle 
{
	static double[] centroidXPoints = new double[3];
	static double[] centroidYPoints = new double[3];
	static Point centroidPoint = new Point();
	public Centroid(int[] xCoordinates, int[] yCoordinates, double[] centroidX, double[] centroidY)
	{
	  super(xPoints, yPoints);
	  centroidXPoints = centroidX;
	  centroidYPoints = centroidY;
	}
	
	
	public static double[] xPointsCentroid()
	{

		for (int i = 0; i < xPoints.length - 1; i++) 
		{
			centroidXPoints[i] = (xPoints[i] + xPoints[i + 1]) / 2;
		}
		centroidXPoints[centroidXPoints.length - 1] = (xPoints[0] + xPoints[2]) / 2;
		return centroidXPoints;
		
	}

	public static double[] yPointsCentroid() 
	{
	
		for (int i = 0; i < xPoints.length - 1; i++) 
		{
			centroidYPoints[i] = (yPoints[i] + yPoints[i + 1]) / 2;
		}
		centroidYPoints[centroidYPoints.length - 1] = (yPoints[0] + yPoints[2]) / 2;
		return centroidYPoints;
		
	}
	
/*
	public static Point findCentroidPoint()
	{
	  int slope1 = (yPoints[0] - centroidYPoints[1])/(xPoints[0] - centroidXPoints[1]);
	  int slope2 = (yPoints[1] - centroidYPoints[2])/(xPoints[1] - centroidXPoints[2]); 
	  
	  int intercept1 = yPoints[0] - (slope1 * xPoints[0]);
	  int intercept2 = yPoints[1] - (slope2 * xPoints[1]);
	  
	  int x = (intercept2 - intercept1)/(slope1 - slope2);
	  int y = (slope1 * x) + intercept1;
	  
	  centroidPoint = new Point(x, y);
	  return centroidPoint;
	}
	*/
	

	
	
	public void paintComponent(Graphics h)
	{
		super.paintComponent(h);
		Graphics2D h2D = (Graphics2D) h;
		
		h2D.setColor(Color.BLACK);
		h2D.draw(new Line2D.Double(xPoints[0],  yPoints[0], (int) centroidXPoints[1], (int) centroidYPoints[1]));
		
		h2D.setColor(Color.RED);
		h2D.draw(new Line2D.Double(xPoints[1],  yPoints[1], (int) centroidXPoints[2],(int) centroidYPoints[2]));
		
		h2D.setColor(Color.CYAN);
		h2D.draw(new Line2D.Double(xPoints[2],  yPoints[2], (int) centroidXPoints[0],(int) centroidYPoints[0]));
		
		//h.setColor(Color.GREEN);
		//h.fillOval((int) findCentroidPoint().getX(), (int) findCentroidPoint().getY(), 10, 10);	
		
		
	}
	public static void main(String args[])
	{
		JFrame f = new JFrame("Centroid Triangle");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1366,768);
		f.add(new Centroid(fillXPoints(), fillYPoints(), xPointsCentroid(), yPointsCentroid()));
		f.setVisible(true);
	}
	
}
