package polygonIsStar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class isStarShaped {
	
	public boolean isStarShaped(ArrayList<Point> points){
		ArrayList<Halfplane> planes = new ArrayList<Halfplane>();
		for(Point p: points){
			
		}
		/*
		ArrayList<HalfPlane> planes = new ArrayList<HalfPlane>();
		System.out.println("----------------------------");
		for(Node n : nodes){
			planes.add(new HalfPlane(n, n.getNextNode()));
		}
		System.out.println(planes);
		// TODO slumpa om halfplane
		long seed = System.nanoTime();
		Collections.shuffle(planes, new Random(seed));
		System.out.println(planes);
		Node inkernel = twoDBoundedLP(nodes, planes);
		if(inkernel!=null){
			return true;
		}
		return false;
		*/
		return true;
	}
	
	public void twoDBoundedLP(){
		
		
		// 1. Let v0 be the corner of C0.
		
		// v is the intersection of the first two halfplanes
		
		// 3. fori←1ton
	
			
			// 4. do if vi−1 ∈ hi
			
				// 5. then vi ← vi−1
				// v = v
		
				//    else vi ←the point p on li that maximizes f⃗c(p), 
				//	  subject to the constraints in Hi−1
				
		//System.out.println(v);
		//return v;
	}
	
	
	
	public void oneDLP(){
		// ???
	}
}
