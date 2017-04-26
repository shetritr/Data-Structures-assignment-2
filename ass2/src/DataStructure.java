import java.lang.reflect.Array;
import java.util.Arrays;

public class DataStructure implements DT {
	
	//////////////// DON'T DELETE THIS CONSTRUCTOR ////////////////
	public DataStructure()
	{
		first_x=null;
		first_y=null;
		max_x=null;
		min_x=null;
		max_y=null;
		min_y=null;
		size=0;
	}

	@Override
	public void addPoint(Point point) {//add point to the two lists
		Container temp=first_x;//to x list
		Container tempParallel;
		size++;
		if(temp==null||temp.getData().getX()>point.getX()){
			first_x=new Container(point, first_x, null);
			tempParallel=first_x;
			temp=first_x;
		}
		else{ 
			while(temp.getNext()!=null&&temp.getNext().getData().getX()<point.getX()){
				temp=temp.getNext();
		}
		temp.SetNext(new Container(point, temp.getNext(), temp));
		tempParallel=temp.getNext();
		}
		if(temp.getNext()==null){//defines min and max
			max_x=temp;
		}if(temp.getPrev()==null)
			min_x=temp;
		
		temp=first_y;//to y list
		if(temp==null||temp.getData().getY()>point.getY()){
			first_y=new Container(point, first_y,null);
			first_y.SetParallel(tempParallel);
			tempParallel.SetParallel(first_y);
			temp=first_y;
		}
		else{ 
			while(temp.getNext()!=null&&temp.getNext().getData().getY()<point.getY()){
			temp=temp.getNext();
			}
		temp.SetNext(new Container(point, temp.getNext(), temp));
		temp.SetParallel(tempParallel);
		tempParallel.SetParallel(temp);
		temp=temp.getNext();
		}
		if(temp.getNext()==null){//defines min and max
			max_y=temp;
		} if(temp.getPrev()==null)
			min_y=temp;
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
		if((max_x.getData().getX()-min_x.getData().getX())*
				(max_y.getData().getY()-min_y.getData().getY())==0)
			return 0;
		return size/((max_x.getData().getX()-min_x.getData().getX())*
				(max_y.getData().getY()-min_y.getData().getY()));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		if(axis){
			while(max_x!=null&&max_x.getData().getX()>max){
				size--;
				max_x=max_x.getPrev();
				if(max_x!=null){
					max_x.SetNext(null);
					if(max_x.getParallel().getNext()!=null){
						max_x.getParallel().getNext().SetPrev(max_x.getParallel().getPrev());
					}else{
						max_y=max_x.getParallel().getPrev();
						if(max_y!=null){
							max_y.SetNext(null);
						}
					}
					if(max_x.getParallel().getPrev()!=null){
						max_x.getParallel().getPrev().SetNext(max_x.getParallel().getNext());
					}else{
						min_y=max_x.getParallel().getNext();
						if(min_y!=null){
							min_y.SetPrev(null);
							first_y=min_y;
						}
					}
				}
			}
			while(min_x!=null&&min_x.getData().getX()<min){
				size--;
				min_x=min_x.getNext();
				if(min_x!=null){
					min_x.SetPrev(null);
					if(min_x.getParallel().getNext()!=null){
						min_x.getParallel().getNext().SetPrev(min_x.getParallel().getPrev());
					}else{
						max_y=min_x.getParallel().getPrev();
						if(max_y!=null){
							max_y.SetNext(null);
						}
					}
					if(min_x.getParallel().getPrev()!=null){
						min_x.getParallel().getPrev().SetNext(min_x.getParallel().getNext());
					}else{
						min_y=min_x.getParallel().getNext();
						if(min_y!=null){
							min_y.SetPrev(null);
							first_y=min_y;
						}
					}
				}
			}
		}else{
			while(max_y!=null&&max_y.getData().getX()>max){
				size--;
				max_y=max_y.getPrev();
				if(max_y!=null){
					max_y.SetNext(null);
					if(max_y.getParallel().getNext()!=null){
						max_y.getParallel().getNext().SetPrev(max_y.getParallel().getPrev());
					}else{
						max_x=max_y.getParallel().getPrev();
						if(max_x!=null){
							max_x.SetNext(null);
						}
					}
					if(max_y.getParallel().getPrev()!=null){
						max_y.getParallel().getPrev().SetNext(max_y.getParallel().getNext());
					}else{
						min_x=max_y.getParallel().getNext();
						if(min_x!=null){
							min_x.SetPrev(null);
							first_x=min_x;
						}
					}
				}
			}
			while(min_y!=null&&min_y.getData().getX()<min){
				size--;
				min_y=min_y.getNext();
				if(min_y!=null){
					min_y.SetPrev(null);
					if(min_y.getParallel().getNext()!=null){
						min_y.getParallel().getNext().SetPrev(min_y.getParallel().getPrev());
					}else{
						max_x=min_y.getParallel().getPrev();
						if(max_x!=null){
							max_x.SetNext(null);
						}
					}
					if(min_y.getParallel().getPrev()!=null){
						min_y.getParallel().getPrev().SetNext(min_y.getParallel().getNext());
					}else{
						min_x=min_y.getParallel().getNext();
						if(min_x!=null){
							min_x.SetPrev(null);
							first_x=min_x;
						}
					}
				}
			}
		}
		
	}

	@Override
	public Boolean getLargestAxis() {
		
		return (max_x.getData().getX()-min_x.getData().getX())>
		(max_y.getData().getY()-min_y.getData().getY());
	}

	@Override
	public Container getMedian(Boolean axis) {
		Container ans=null;
		Container jumper=null;
		if(axis){
			ans=first_x;//the current Container and the jumper begin in the first link
			jumper=first_x;
			while((jumper != null) && (jumper.getNext()!= null) ){
				ans=ans.getNext();//jump once 
				jumper=jumper.getNext().getNext();//the jumper jump twice  
			}
		}else{
			ans=first_y;
			jumper=first_y;
			while((jumper != null) && (jumper.getNext()!= null) ){
				ans=ans.getNext();
				jumper=jumper.getNext().getNext();
			}
		}
		return ans;
	}
 
	@Override
	public Point[] nearestPairInStrip(Container container, int width,
			Boolean axis) {
		Point [] ans=null;
		int dis=Integer.MAX_VALUE;
		if(axis){
			int numof=Count(axis, container, container.getData().getX()-width/2, container.getData().getX()+width/2);
			Point [] tempoint=new Point [numof];
			Container temp=container;
			while(temp!=null&&temp.getData().getX()>container.getData().getX()-width/2)
				temp=temp.getPrev();
			for (int i = 0; i < tempoint.length; i++) {
				tempoint[i]=temp.getData();
				temp=temp.getNext();
			}
			Arrays.sort(tempoint, new comparY());
			for (int i = 0; i < tempoint.length; i++) {
				for (int j = i+1; j <= i+6&j<tempoint.length; j++) {
					if((tempoint[i].getX()-tempoint[j].getX())*(tempoint[i].getX()-tempoint[j].getX())+
							(tempoint[j].getY()-tempoint[j].getY())*(tempoint[j].getY()-tempoint[j].getY())<dis){
						ans[0]=tempoint[i];
						ans[1]=tempoint[j];
					}
				}
			}
			if(ans[0]==null|ans[1]==null)
				return null;
			return ans;
		}else{
			int numof=Count(axis, container, container.getData().getY()-width/2, container.getData().getY()+width/2);
			Point [] tempoint=new Point [numof];
			Container temp=container;
			while(temp!=null&&temp.getData().getY()>container.getData().getY()-width/2)
				temp=temp.getPrev();
			for (int i = 0; i < tempoint.length; i++) {
				tempoint[i]=temp.getData();
				temp=temp.getNext();
			}
			Arrays.sort(tempoint, new comparX());
			for (int i = 0; i < tempoint.length; i++) {
				for (int j = i+1; j <= i+6&j<tempoint.length; j++) {
					if((tempoint[i].getX()-tempoint[j].getX())*(tempoint[i].getX()-tempoint[j].getX())+
							(tempoint[j].getY()-tempoint[j].getY())*(tempoint[j].getY()-tempoint[j].getY())<dis){
						ans[0]=tempoint[i];
						ans[1]=tempoint[j];
					}
				}
			}
			if(ans==null||ans[0]==null|ans[1]==null)
				return null;
			return ans;
		}
	}

	@Override
	public Point[] nearestPair() {
		boolean axis=getLargestAxis();
		Point [] ans=null;
		Container mid=getMedian(axis);
		if(first_x!=null&&first_x.getNext()!=null){
			if(first_x.getNext().getNext()==null){
				ans[0]=first_x.getData();
				ans[1]=first_x.getNext().getData();
			}else{
				Point [] temp;
			      if(axis){
			    	   temp=RecNearestPair(getPointsInRangeRegAxis(0, mid.getData().getX(), axis), axis);
			    	  ans=RecNearestPair(getPointsInRangeRegAxis(mid.getData().getX()+1,0,  axis), axis);
			      }else{
			    	  temp=RecNearestPair(getPointsInRangeRegAxis(0, mid.getData().getY(), axis), axis);
			    	  ans=RecNearestPair(getPointsInRangeRegAxis(mid.getData().getY()+1,0,  axis), axis);
			      }
			      double dis=Integer.MAX_VALUE;
			      if(ans!=null) 
			          { dis=Math.hypot(ans[0].getX()-ans[1].getX()
							, ans[0].getY()-ans[1].getY());
			          }
			          
			      if(Math.hypot(temp[0].getX()-temp[1].getX()
							, temp[0].getY()-temp[1].getY())<dis){
			    	  ans=temp;
			    	  dis=Math.hypot(ans[0].getX()-ans[1].getX()
								, ans[0].getY()-ans[1].getY());
			      }
			        temp=nearestPairInStrip(mid,(int)dis*2, axis);
			      if(temp!=null&&Math.hypot(temp[0].getX()-temp[1].getX()
							, temp[0].getY()-temp[1].getY())<dis){
			    	  ans=temp; 
			      }
			      
			}
		}
     return ans;
	}
	private Point[] RecNearestPair(Point[] pointsInRangeRegAxis,boolean axis) {
        Point [] ans=null;
		if(pointsInRangeRegAxis.length<=3){
			ans=NearestPairbadtime(pointsInRangeRegAxis);
        }else{
        	Point [] left=RecNearestPair(getPointsInRangeRegAxis(0, pointsInRangeRegAxis.length/2, axis), axis);
        	Point [] right=RecNearestPair(getPointsInRangeRegAxis(pointsInRangeRegAxis.length/2, pointsInRangeRegAxis.length-1, axis), axis);
        	ans=right;
        	double dis=Math.hypot(right[0].getX()-right[1].getX(), right[0].getY()-right[1].getY());
        	if((left[0]!=null&left[1]!=null&right[0]!=null&right[1]!=null)&&Math.hypot(left[0].getX()-left[1].getX(), left[0].getY()-left[1].getY())<
        			dis){
        		ans=left;
        		dis=Math.hypot(left[0].getX()-left[1].getX(), left[0].getY()-left[1].getY());
        	}
        	int j=0;
        	for (int i = 0; i < pointsInRangeRegAxis.length; i++) {
				if(Math.abs(pointsInRangeRegAxis[i].getX()-pointsInRangeRegAxis[pointsInRangeRegAxis.length/2].getX())<
						dis){
					j++;
				}
			}
        	Point [] temp=new Point[j];
        	j=0;
        	for (int i = 0; i < pointsInRangeRegAxis.length; i++) {
				if(Math.abs(pointsInRangeRegAxis[i].getX()-pointsInRangeRegAxis[pointsInRangeRegAxis.length/2].getX())<
						dis){
					temp[j]=pointsInRangeRegAxis[i];
					j++;
				}
			}
            for (int i = 0; i < temp.length; i++) {
			  j=i+1;
			  while(j<temp.length){
				  if(Math.hypot(temp[i].getX()-temp[j].getX()
							, temp[i].getY()-temp[j].getY())<dis){
						ans[0]=temp[i];
						ans[1]=temp[j];
						dis=Math.hypot(temp[i].getX()-temp[j].getX()
								, temp[i].getY()-temp[j].getY());
					}
				  j++;
			  }
			
            }
        }
		return ans;
	}


	
	private Point [] NearestPairbadtime(Point[] pointsInRangeRegAxis) {
		if(pointsInRangeRegAxis.length<2)
			return null;
		double dis=Integer.MAX_VALUE;
		Point []ans=new Point[2];
		for (int i = 0; i < pointsInRangeRegAxis.length; i++) {
			for (int j = i+1; j < pointsInRangeRegAxis.length; j++) {
				if(Math.hypot(pointsInRangeRegAxis[i].getX()-pointsInRangeRegAxis[j].getX()
						, pointsInRangeRegAxis[i].getY()-pointsInRangeRegAxis[j].getY())<dis){
					ans[0]=pointsInRangeRegAxis[i];
					ans[1]=pointsInRangeRegAxis[j];
					dis=Math.hypot(pointsInRangeRegAxis[i].getX()-pointsInRangeRegAxis[j].getX()
							, pointsInRangeRegAxis[i].getY()-pointsInRangeRegAxis[j].getY());
				}
			}
		}
		return ans;
	}



	private Container first_x;
	private Container max_x;
	private Container min_x;
	private Container first_y;
	private Container max_y;
	private Container min_y;
	private int size;
	private int Count(boolean axis,Container mid,int max,int min){//count the number of points between the min & max
		int count=0;
		if(axis){
			Container temp=mid;
			while(temp!=null&&temp.getData().getX()>=min)
				temp=temp.getPrev();
			while(temp!=null&&temp.getData().getX()<=max){
				if(temp.getData().getX()>=min){
					count++;
				}
				temp=temp.getNext();
			}
		}else{
			Container temp=mid;
			while(temp!=null&&temp.getData().getX()>=min)
				temp=temp.getPrev();
			while(temp!=null&&temp.getData().getY()<=max){
				if(temp.getData().getY()>=min){
					count++;
				}
				temp=temp.getNext();
			}
		}
			
		return count;
	}
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

