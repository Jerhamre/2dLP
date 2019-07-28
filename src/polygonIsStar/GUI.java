package polygonIsStar;

import java.util.ArrayList;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class GUI {

	
	private static int shellWidth = 500;
	private static int shellHeight = 500;
	
	public GUI(){
		ArrayList<Point> points = new ArrayList<Point>();
		//points.add(new Point(10,10));
		//points.add(new Point(100,100));
		//points.add(new Point(200,200));
	
		// Instantiate Display object, it represents SWT session 
		Display display = new Display();
				
		// Define Shell 
		Shell shell = new Shell(display);
		shell.setSize(shellWidth, shellHeight);
		shell.setText("Polygon starshaped algorithm");
		
		
		shell.addMouseListener(new MouseListener(){
	
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Mouse Double click.");
			}
	
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println("Mouse Down.");
				System.out.println("x pos: "+ e.x);
				System.out.println("y pos: "+ e.y);
				points.add(new Point(e.x,e.y));
			}
	
			@Override
			public void mouseUp(MouseEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("Mouse Up.");
			}
			
		});
		
		
				
		// add graphics to shell
		addButtons(shell, points);
		drawGraphics(display, shell, points);
	
		/*
		 * Seperate thread for redrawing gui every 1ms
		 * The thread redraws the shell every 10ms 
		 */
		
		Thread updateGui = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					display.asyncExec(new Runnable() {
						public void run() {
							shell.redraw();
						}//run
					});
						    
					try {
						Thread.sleep(10); // 16.66ms = 60hz -ish
				    } catch (InterruptedException e) {
			    	e.printStackTrace();
				    }
				}
		    }//run
		}); // Runnable
		
		updateGui.start();
	    
	    shell.open();
	    while (!shell.isDisposed()) {
	        if (!display.readAndDispatch()) {
	            display.sleep();
	        }
	    }
	    display.dispose();
	}
	
	
	// PAINT CONTROLLER, FOR DRAWING 
	public void drawGraphics(Display display, Shell shell, ArrayList<Point> points){
		GC gc = new GC(shell);
		shell.open();
		shell.addPaintListener(new PaintListener(){ 
			public void paintControl(PaintEvent e){
				ArrayList<Integer> pointArraylist = new ArrayList<Integer>();
				
				for(Point point : points){
					e.gc.setForeground(display.getSystemColor(SWT.COLOR_RED));
					e.gc.drawPoint((int)point.getX(), (int)point.getY());
					e.gc.drawArc((int)point.getX(), (int)point.getY(), 5, 5, 0, 360);
					pointArraylist.add((int)point.getX());
					pointArraylist.add((int)point.getY());
				}
				int[] ret = new int[pointArraylist.size()];
			    Iterator<Integer> iterator = pointArraylist.iterator();
			    for (int i = 0; i < ret.length; i++)
			    {
			        ret[i] = iterator.next().intValue();
			    }
			    e.gc.setForeground(display.getSystemColor(SWT.COLOR_BLACK));
				e.gc.drawPolygon(ret);
			}
		});
		
		
		gc.dispose();
	}
	
	public void addButtons(Shell shell, ArrayList<Point> points){
		
		Button btnReset = new Button(shell, SWT.PUSH);
		btnReset.setText("Reset");
		btnReset.setLocation(0, 450);
		btnReset.setSize(200, 30);
		
		
		btnReset.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				// reset button was clicked
				points.clear();
			}
		});
	}
}
