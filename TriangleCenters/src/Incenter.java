import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;

public class Incenter extends RandomTriangle
{
    static double[] xIncenterPoints = new double[3];
    static double[] yIncenterPoints = new double[3];
    static double[] sideLengths = new double[3];
    static double perimeter = 0;
    static double[] incenterPoint = new double[2];

	public Incenter(int[] xCoordinates, int[] yCoordinates, double[] xIncPoints, double[] yIncPoints)
	{
	  super(xPoints, yPoints);
	  xIncenterPoints = xIncPoints;
	  yIncenterPoints = yIncPoints;
	}
	
	public static double distance(int x1, int x2, int y1, int y2)
	{
	  double x = Math.pow((x1 - x2), 2);
	  double y = Math.pow(y1 - y2,  2);
	  return Math.sqrt(x + y);
	}
	
	public static void findSideLengths(int[] x, int y[])
	{
	  for(int i = 0; i < xPoints.length - 1; i++)
	  {
		 sideLengths[i] = distance(xPoints[i], xPoints[i+1], yPoints[i], yPoints[i+1]);
	  }
	  sideLengths[2] = distance(xPoints[0], xPoints[2], yPoints[0], yPoints[2]);
	}
	
	public static double getPerimeter()
	{
	  for(int i = 0; i < sideLengths.length; i++)
		 perimeter+= sideLengths[i];
	  
	  return perimeter;
	}
	
	
	public static void fillIncenterPoint()
	{
	  incenterPoint[0] = ((sideLengths[0] * xPoints[2]) +  (sideLengths[1] * xPoints[1]) + (sideLengths[2] * xPoints[0]))/(getPerimeter());
	  incenterPoint[1] = ((sideLengths[0] * yPoints[2]) +  (sideLengths[1] * yPoints[1]) + (sideLengths[2] * yPoints[0]))/(getPerimeter());
	}

	public static double findSlope(double x1, double x2, double y1, double y2)
	{
	  return (y2 - y1)/(x2 - x1);
	}
	
	public static double findYIntercept(double m, int x, int y)
	{
	  return y - (m * x);
	}
	
	public static double findIntersectionTwoLinesX(double m1, double m2, double b1, double b2)
	{
	  return (b2 - b1)/(m1 - m2);
	}
	
	public static double findIntersectionTwoLinesY(double m1, double x, double b1)
	{
		return m1 * x + b1;
	}
	public static void fillXandYIncenterPoints()
	{
	  double m1 = 	findSlope(xPoints[2], incenterPoint[0], yPoints[0], incenterPoint[1]);
	  double m2 =  findSlope(xPoints[0], xPoints[1], yPoints[0], yPoints[1]);
			  
	  xIncenterPoints[0] = findIntersectionTwoLinesX(m1, m2, findYIntercept(m1, xPoints[2], yPoints[2]), 
			  findYIntercept(m2, xPoints[0], yPoints[0])); 
	  
	 yIncenterPoints[0] = findIntersectionTwoLinesY(m1, xPoints[2], findYIntercept(m1, xPoints[0], yPoints[0])); 
	 
	 m1 = findSlope(xPoints[0], incenterPoint[0], yPoints[0], incenterPoint[1]);
	 m2 = findSlope(xPoints[1], xPoints[2], yPoints[1], yPoints[2]);
	  
	  xIncenterPoints[1] = findIntersectionTwoLinesX(m1, m2, findYIntercept(m1, xPoints[0], yPoints[0]), 
			  findYIntercept(m2, xPoints[1], yPoints[1])); 
	  
	  yIncenterPoints[1] = findIntersectionTwoLinesY(m1, xPoints[0], findYIntercept(m1, xPoints[1], yPoints[1])); 
	  
	  m1 = findSlope(xPoints[1], incenterPoint[0], yPoints[1], incenterPoint[1]);
	  m2 = findSlope(xPoints[0], xPoints[2], yPoints[0], yPoints[2]);
	  
	  xIncenterPoints[2] = findIntersectionTwoLinesX(m1, m2, findYIntercept(m1, xPoints[1], yPoints[1]), 
			  findYIntercept(m2, xPoints[0], yPoints[0])); 
	  
	  yIncenterPoints[2] = findIntersectionTwoLinesY(m1, xPoints[1], findYIntercept(m1, xPoints[0], yPoints[0])); 
	  
	}
	
	public static double[] getxIncenterPoints()
	{
      return xIncenterPoints;
	}
	
	public static double[] getyIncenterPoints()
	{
	  return yIncenterPoints;
	}
	
	public void paintComponent(Graphics h)
	{
	  super.paintComponent(h);
	  Graphics2D h2D = (Graphics2D) h;
	  	  
	  h2D.setColor(Color.BLUE);
	  h2D.draw(new Line2D.Double(xPoints[0], yPoints[0], xIncenterPoints[1], yIncenterPoints[1]));
	  
	  //h2D.setColor(Color.MAGENTA);
	  //h2D.draw(new Line2D.Double(xPoints[1], yPoints[1], incenterPoint[0], incenterPoint[1]));
	  
	  h2D.setColor(Color.RED);
	  h2D.draw(new Line2D.Double(xPoints[1], yPoints[1], xIncenterPoints[2], yIncenterPoints[2]));
	  
	  h2D.setColor(Color.GREEN);
	  h2D.draw(new Line2D.Double(xPoints[2], yPoints[2], xIncenterPoints[0], yIncenterPoints[0]));
	}
	
	public static void main(String args[])
	{
		JFrame f = new JFrame("Incenter Triangle");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(1366,768);
		f.add(new Incenter(fillXPoints(), fillYPoints(), getxIncenterPoints(), getyIncenterPoints()));
		f.setVisible(true);
	}
}
