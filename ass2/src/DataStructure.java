
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
		}
		else{ while(temp.getNext()!=null&&temp.getNext().getData().getX()<point.getX()){
			temp=temp.getNext();
		}
		temp.SetNext(new Container(point, temp.getNext(), temp));
		tempParallel=temp.getNext();
		temp=temp.getNext();
		}
		if(temp.getNext()==null){//defines min and max
			max_x=temp;
		}else if(temp.getPrev()==null)
			min_x=temp;
		
		temp=first_y;//to y list
		if(temp==null||temp.getNext().getData().getY()>point.getY()){
			first_y=new Container(point, first_y,null);
			first_y.SetParallel(tempParallel);
			tempParallel.SetParallel(first_y);
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
		}else if(temp.getPrev()==null)
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
		return size/((max_x.getData().getX()-min_x.getData().getX())*
				(max_y.getData().getY()-min_y.getData().getY()));
	}

	@Override
	public void narrowRange(int min, int max, Boolean axis) {
		if(axis&max_x!=null){
			Container tempmax=max_x;
			Container tempmin=min_x;
			Container tempmaxparallel=tempmax.getParallel();
            while(tempmax.getPrev()!=null&&tempmax.getData().getX()>max){
				size--;
            	tempmax=tempmax.getPrev();
				tempmax.SetNext(null);
				if(tempmaxparallel.getNext()!=null){
					tempmaxparallel.getNext().SetPrev(tempmaxparallel.getPrev());
				}else{
					max_y=tempmaxparallel.getPrev();
				}
				if(tempmaxparallel.getPrev()!=null){
					tempmaxparallel.SetNext(tempmaxparallel.getNext());
				}else{
					min_y=tempmaxparallel.getNext();
				}
				tempmaxparallel=tempmax.getParallel();
			}
            max_x=tempmax;
            if(tempmax.getPrev()==null&tempmax.getData().getX()>max){
            	first_x=null;
            	first_y=null;
            }
            while(tempmin.getNext()!=null&&tempmin.getData().getX()<min){
            	//��� ����� ���� �� �������� ���� �����
            }
			
		}else{
			
		}
		
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
	private Container max_x;
	private Container min_x;
	private Container first_y;
	private Container max_y;
	private Container min_y;
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

