import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class RandomTriangle extends JPanel //draws a triangle with a randomly generated set of coordinate points
{
	static int[] xPoints = new int[3];
	static int[] yPoints = new int[3];

	public RandomTriangle(int[] xCoordinates, int[] yCoordinates) 
	{
		xPoints = xCoordinates;
		yPoints = yCoordinates;
	}

	public static int[] fillXPoints() 
	{
		xPoints[0] = 683; //one x point will always be in the middle of a JFrame with a width of 1366
		int count = 0;
		while (count < 2) 
		{
			int rand = (int) (Math.random() * 657) + 100;
			if (rand != 683 || rand != xPoints[count - 1]) 
			{
				xPoints[count + 1] = rand;
				count++;
			}
		}
		return xPoints;
	}

	public static int[] fillYPoints() 
	{
		yPoints[0] = 384; //one y point will always be in the middle of a JFrame with a width of 384
		int count = 0;
		while (count < 2) {
			int rand = (int) (Math.random() * 359) + 100;
			if (rand != 384 || rand != yPoints[count - 1]) 
			{
				yPoints[count + 1] = rand;
				count++;
			}
		}
		return yPoints;
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		this.setBackground(Color.WHITE);
		
		g.setColor(Color.BLACK);
		g.drawPolygon(xPoints, yPoints, 3);
		
		//g.setColor(new Color(200,100,200));
		//g.fillPolygon(xPoints, yPoints, 3);
		
		
	}

	public static void main(String args[]) 
	{
		JFrame f = new JFrame("Triangle");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		RandomTriangle t = new RandomTriangle(fillXPoints(), fillYPoints());
		f.add(t);
		f.setSize(1366, 768);
		f.setVisible(true);

	}

}
