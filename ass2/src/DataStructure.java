
public class DataStructure implements DT {
	
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		first_x=null;
		first_y=null;
		max_x=Integer.MIN_VALUE;
		min_x=Integer.MAX_VALUE;
		max_y=Integer.MIN_VALUE;
		min_y=Integer.MAX_VALUE;
		size=0;
	}

	@Override
	public void addPoint(Point point) {//add point to the two lists
		Container temp=first_x;//to x list
		size++;
		if(temp==null){
			first_x=new Container(point);
		}
		else while(temp.getNext()!=null&&temp.getNext().getData().getX()<point.getX()){
			temp=temp.getNext();
		}
		temp.SetNext(new Container(point, temp.getNext(), temp));
		if(point.getX()>max_x){//defines min and max
			max_x=point.getX();
		}else if(point.getX()<min_x)
			min_x=point.getX();
		
		temp=first_y;//to y list
		if(temp==null){
			first_y=new Container(point);
		}
		else while(temp.getNext()!=null&&temp.getNext().getData().getY()<point.getY()){
			temp=temp.getNext();
		}
		temp.SetNext(new Container(point, temp.getNext(), temp));
		if(point.getY()>max_y){//defines min and max
			max_y=point.getY();
		}else if(point.getY()<min_y)
			min_y=point.getY();
			}

	@Override
	public Point[] getPointsInRangeRegAxis(int min, int max, Boolean axis) {//return the points between min and max 
	    int count=Count(axis, max, min);//get the number of points
	    Point [] Pointans=new Point[count];
	    if(axis){//if is the x axis
	    	Container temp=first_x;
	    	int i=0;
	    	while(temp!=null&&temp.getData().getX()<=max){
				if(temp.getData().getX()>=min){
					Pointans[i]=temp.getData();
					i++;
				}
				temp=temp.getNext();
			}
	    }
	    else{//y axis
	    	Container temp=first_y;
	    	int i=0;
	    	while(temp!=null&&temp.getData().getY()<=max){
				if(temp.getData().getY()>=min){
					Pointans[i]=temp.getData();
					i++;
				}
				temp=temp.getNext();
			}
	    }
		return Pointans;
	}

	@Override
	public Point[] getPointsInRangeOppAxis(int min, int max, Boolean axis) {//return the points between min and max on the !axis
		int Count = Count(axis, max, min);
		Point [] ans =new Point[Count];
		int i=0;
		if(axis){//y axis
			Container temp=first_y;
			while(temp!=null&i<ans.length){
				if(temp.getData().getX()<=max&temp.getData().getX()>=min){
					ans[i]=temp.getData();
					i++;
				}
				temp=temp.getNext();
			}
			
		}else{//x axis
			Container temp=first_x;
			while(temp!=null&i<ans.length){
				if(temp.getData().getY()<=max&temp.getData().getY()>=min){
					ans[i]=temp.getData();
					i++;
				}
				temp=temp.getNext();
			}
		}
		
		return ans;
	}

	@Override
	public double getDensity() {
		return size/((max_x-min_x)*(max_y-min_y));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean getLargestAxis() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Container getMedian(Boolean axis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] nearestPairInStrip(Container container, int width,
			Boolean axis) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Point[] nearestPair() {
		// TODO Auto-generated method stub
		return null;
	}
	private Container first_x;
	private int max_x;
	private int min_x;
	private Container first_y;
	private int max_y;
	private int min_y;
	private int size;
	private int Count(boolean axis,int max,int min){//count the number of points between the min & max
		int count=0;
		if(axis){
			Container temp=first_x;
			while(temp!=null&&temp.getData().getX()<=max){
				if(temp.getData().getX()>=min){
					count++;
				}
				temp=temp.getNext();
			}
		}else{
			Container temp=first_y;
			while(temp!=null&&temp.getData().getY()<=max){
				if(temp.getData().getY()>=min){
					count++;
				}
				temp=temp.getNext();
			}
		}
			
		return count;
	}
	//TODO: add members, methods, etc.
	
}

