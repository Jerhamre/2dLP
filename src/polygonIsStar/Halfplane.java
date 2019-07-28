package polygonIsStar;
import java.awt.geom.Line2D;
import java.util.ArrayList;



public class Halfplane {
	
	 /**
     * Initializes a halfplane by its boundary line
     * and by the side where the halfplane lies.
     * 
     * @param line The boundary line
     * @param rightSide true, if the halfplanes lies on the right side
     *                  of the line, false otherwise
     */
    public Halfplane(Line line, boolean rightSide) {
        this.line = line;
        this.rightSide = rightSide;
    }
    
    /**
     * Returns the boundary line for the halfplane.
     * 
     * @return The boundary line
     */
    public Line getLine() {
        return line;
    }
    
    /**
     * Determines whether the boundary line
     * lies on the left side of the halfplane.
     * 
     * @return true, if the boundary line is on the left, flase otherwise
     */
    public boolean isLeftBoundary() {
        return rightSide;
    }
    
    /**
     * Determines whether the boundary line
     * lies on the right side of the halfplane.
     * 
     * @return true, if the boundary line is on the right, flase otherwise
     */
    public boolean isRightBoundary() {
        return ! rightSide;
    }
    
    
    
    /**
     * Determines whether the point lies
     * in the halfplane.
     * 
     * @param p The point
     * @return true, if the point lies in the left halfplane, false otherwise
     */
    public boolean includes(Point p) {
        
        if (isLeftBoundary()) {
            
            if (line.isHorizontal()) { // == upper boundary
                return (p.getY() < line.YforX(0));
            } else {
                return (p.getX() > line.XforY(p.getY()));
            }
            
        } else {
            
            if (line.isHorizontal()) { // == lower boundary
                return (p.getY() > line.YforX(0));
            } else {
                return (p.getX() < line.XforY(p.getY()));
            }
        }
    }
    
    /**
     * Initializes a set of halfplanes with a bounding rectangle.
     * 
     * @param x1 left X coordinate
     * @param x2 right X coordinate
     * @param y1 bottom Y coordinate
     * @param y2 upper Y coordinate
     * @return Set of bounding halfplanes
     */
    public static ArrayList<Halfplane> boundingRectangle(double x1, double x2,
                                                         double y1, double y2) {
        
        ArrayList<Halfplane> res = new ArrayList<Halfplane>();
        
        if (x1 > x2) {
            double temp = x1;
            x1 = x2;
            x2 = temp;
        }
        
        if (y1 > y2) {
            double temp = y1;
            y1 = y2;
            y2 = temp;
        }
        
        res.add(new Halfplane(new Line(new Point(x1, 0), new Point(x1, 1)), true));
        res.add(new Halfplane(new Line(new Point(0, y2), new Point(1, y2)), true));
        res.add(new Halfplane(new Line(new Point(x2, 0), new Point(x2, 1)), false));
        res.add(new Halfplane(new Line(new Point(0, y1), new Point(1, y1)), false));
        
        return res;
    }
    
    private Line line;
    private boolean rightSide;

	
	/*
	private Line2D.Float line;
	private double angle;
	private Node node1;
	private Node node2;
	
	public HalfPlane(Node a, Node b){	
		this.node1 = a;
		this.node2 = b;
		this.angle = Math.atan2((double)(b.getY()-a.getY()), (b.getX()-a.getX()));
		float startX = (float) (a.getX() - 100000 * Math.sin(Math.toRadians(90-(Math.toDegrees(this.angle)))));
		float startY = (float) (a.getY() - 100000 * Math.cos(Math.toRadians(90-(Math.toDegrees(this.angle)))));
		float endX   = (float) (a.getX() + 100000 * Math.sin(Math.toRadians(90-(Math.toDegrees(this.angle)))));
		float endY   = (float) (a.getY() + 100000 * Math.cos(Math.toRadians(90-(Math.toDegrees(this.angle)))));
		
		this.line = new Line2D.Float(startX, startY, endX, endY);
	}


	public Line2D.Float getLine() {
		return line;
	}


	public void setLine(Line2D.Float line) {
		this.line = line;
	}
	
	public boolean isLeft(Node node){
		
		float position = Math.round((node.getX() - line.x1) * (line.y2 - line.y1) - (node.getY() - line.y1)*(line.x2 - line.x1));
		
		//System.out.println("point pos: "+ node.getX()+":"+node.getY());
		
		if((Math.toDegrees(this.angle) > 90) ||
				(Math.toDegrees(this.angle) < -90)){
			//System.out.println("angle: < 90, angle: > -90:  "+Math.toDegrees(this.angle));
			if(position >= 0){
				return true;
				
			}else{
				return false;
			}
		}
		//System.out.println("angle: -90 < angle < 90: "+Math.toDegrees(this.angle));
		if(position <= 0){
			return true;
			
		}else{
			return false;
		}
	}
	
	
	public double getAngle(){
		return this.angle;
	}
	
	public Node getNode1(){
		return this.node1;
	}
	
	public Node getNode2(){
		return this.node2;
	}
	*/
}
