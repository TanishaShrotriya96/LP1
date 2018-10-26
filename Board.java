import java.util.ArrayList;
import java.util.Scanner;

class state
{
	state parent=null;
	int arr[];
	int f=0,g=0;
	state(state s)
	{
		this.arr = new int[9];
		for(int i=0;i<9;i++)
			this.arr[i]=s.arr[i];
	}
	
	state()
	{
		this.arr = new int[9];
	}
}

public class Board 
{
	state ss,cs,gs;
	ArrayList<state> openlist = new ArrayList<state>();
	ArrayList<state> closelist = new ArrayList<state>();
	
	Board()
	{
		cs = new state();
		ss = new state();
		gs = new state();
	}
	
	public int h(state s)
	{
		int h=0;
		for(int i=0;i<9;i++)
			if(s.arr[i]!=gs.arr[i] && s.arr[i]!=0)
				h++;
		return h;
	}
	
	public void display(state s)
	{
		for(int i=0;i<9;i++)
		{
			if(i==3 || i==6 || i==9)
				System.out.println();
			System.out.print(" "+s.arr[i]);
		}
		System.out.println();
	}
	
	public void input()
	{
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter start state:");
		for(int i=0;i<9;i++)
			ss.arr[i]=scan.nextInt();
		System.out.println("Enter goal state:");
		for(int i=0;i<9;i++)
			gs.arr[i]=scan.nextInt();
	}
	
	public int lowest()
	{
		int min=openlist.get(0).f;
		int p=0;
		for(int i=1;i<openlist.size();i++)
		{
			if(min>openlist.get(i).f)
			{
				min=openlist.get(i).f;
				p=i;
			}
		}
		return p;	
	}
	
	public boolean inclose(state s)
	{	
		for(int i=0;i<closelist.size();i++)
		{
			if(issame(closelist.get(i),s))
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean issame(state r,state s)
	{
		for(int i=0;i<9;i++)
		{
			if(r.arr[i]!=s.arr[i])
			{
				return false;
			}
		}
		return true;
	}
	
	public void move(state s)
	{
		int p=0;
		for(int i=0;i<9;i++)
			if(s.arr[i]==0)
			{
				p=i;
				break;
			}
		
		if(p>2)
		{
			state ns = new state(s);
			ns.arr[p]=ns.arr[p-3];
			ns.arr[p-3]=0;
			ns.g=s.g+1;
			ns.f=ns.g+h(ns);
			ns.parent=s;
			if(!inclose(ns))
				openlist.add(ns);
		}
		
		if(p%3!=0)
		{
			state ns = new state(s);
			ns.arr[p]=ns.arr[p-1];
			ns.arr[p-1]=0;
			ns.g=s.g+1;
			ns.f=ns.g+h(ns);
			ns.parent=s;
			if(!inclose(ns))
				openlist.add(ns);
		}
		
		if(p%3!=2)
		{
			state ns = new state(s);
			ns.arr[p]=ns.arr[p+1];
			ns.arr[p+1]=0;
			ns.g=s.g+1;
			ns.f=ns.g+h(ns);
			ns.parent=s;
			if(!inclose(ns))
				openlist.add(ns);
		}
		
		if(p<6)
		{
			state ns = new state(s);
			ns.arr[p]=ns.arr[p+3];
			ns.arr[p+3]=0;
			ns.g=s.g+1;
			ns.f=ns.g+h(ns);
			ns.parent=s;
			if(!inclose(ns))
				openlist.add(ns);
		}
	}
	
	public void astar()
	{
		int low=0;
		ss.f=h(ss);
		openlist.add(ss);
		while(!openlist.isEmpty())
		{
			low = lowest();
			cs = openlist.get(low);
			closelist.add(cs);
			openlist.remove(cs);
			
			if(h(cs)==0)
			{
				state temp=new state();
				temp=cs;
				ArrayList<state> path = new ArrayList<state>();
				path.add(temp);
				temp=temp.parent;
				while(temp!=null)
				{
					path.add(temp);
					temp=temp.parent;
				}
				System.out.println("Path Length: "+path.size());
				for(int i=path.size()-1;i>=0;i--)
				{
					display(path.get(i));
					path.remove(i);
					System.out.println();
				}
				break;
			}			
			move(cs);
		}
	}
	
	public static void main(String[] args) {
		Board b = new Board();
		b.input();
		b.astar();
	}
}

/*
Output:
Example 1:
Enter start state:
1 2 3
4 0 6
5 7 8
Enter goal state:
1 2 3
4 7 0
5 8 6
Path Length: 4
 1 2 3
 4 0 6
 5 7 8

 1 2 3
 4 7 6
 5 0 8

 1 2 3
 4 7 6
 5 8 0

 1 2 3
 4 7 0
 5 8 6
 
 Example 2:
 Enter start state:
1 3 4
8 6 2
7 0 5
Enter goal state:
1 2 3
8 0 4
7 6 5
Path Length: 6
 1 3 4
 8 6 2
 7 0 5

 1 3 4
 8 0 2
 7 6 5

 1 3 4
 8 2 0
 7 6 5

 1 3 0
 8 2 4
 7 6 5

 1 0 3
 8 2 4
 7 6 5

 1 2 3
 8 0 4
 7 6 5
*/