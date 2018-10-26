
import java.util.Scanner;


public class puzzle {

public static void main(String[] args) {
	String[][] input=new String[3][3];
	String[][] goal=new String[3][3];
	String[][] in1=new String[3][3];
	String[][] in2=new String[3][3];
	String[][] in3=new String[3][3];
	String[][] in4=new String[3][3];
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the matrix");
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			input[i][j]=sc.next();
		}
	}
	System.out.println("Matrix");
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			System.out.print("   "+input[i][j]);
		}
		System.out.println();
	}
	System.out.println("Enter the goal state");
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			goal[i][j]=sc.next();
		}
	}
	System.out.println("Goal state");
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			System.out.print("   "+goal[i][j]);
		}
		System.out.println();
	}
	System.out.println("---------------------------");
	System.out.println("---------------------------");
	System.out.println("SOLUTION :- ");
	int min=Integer.MAX_VALUE;
	int level=1;
	int flag=0;
	while(min!=0){
		System.out.println("LEVEL "+level++);
	int[] position=fun(input);
	int i=position[0];
	int j=position[1];
	min=Integer.MAX_VALUE;
	int h_x1=Integer.MAX_VALUE,h_x2=Integer.MAX_VALUE,h_x3=Integer.MAX_VALUE,h_x4=Integer.MAX_VALUE;
	if(ifvalid(i,j-1)==true)
	{
		System.out.println("---------------------------");
		System.out.println("MOVING LEFT");
		swap(input,in1,i,j,i,j-1);
		printmatrix(in1);
		h_x1=comparematrix(in1,input,goal);
		System.out.println("h(x)="+h_x1);
		min=h_x1;
		flag=1;
		if(min==0)
		{
			
			System.out.println("==========================Reached the goal state ======================\nMoving left AT LEVEL "+(level)+"\n");
			printmatrix(in1);
			break;
		}
	}
	if(ifvalid(i,j+1)==true)
	{
		System.out.println("---------------------------");
		System.out.println("MOVING RIGHT");
		swap(input,in2,i,j,i,j+1);
		printmatrix(in2);
		h_x2=comparematrix(in2,input,goal);
		System.out.println("h(x)="+h_x2);
		if(min>h_x2)
		{
			min=h_x2;
			flag=2;
		}
		if(min==0)
		{
			
			System.out.println("===============================Reached the goal state =====================================\nMoving right AT LEVEL "+(level)+"\n");
			printmatrix(in2);
			break;
		}
	}
	if(ifvalid(i-1,j)==true)
	{
		System.out.println("---------------------------");
		System.out.println("MOVING UP");
		swap(input,in3,i,j,i-1,j);
		printmatrix(in3);
		h_x3=comparematrix(in3,input,goal);
		System.out.println("h(x)="+h_x3);
		if(min>h_x3)
		{
			min=h_x3;
			flag=3;
		}
		if(min==0)
		{
			
			System.out.println("================================Reached the goal state ===========================\nMoving Up AT LEVEL "+(level-1)+"\n");
			printmatrix(in3);
			break;
		}
	}
	if(ifvalid(i+1,j)==true)
	{
		System.out.println("---------------------------");
		System.out.println("MOVING DOWN");
		swap(input,in4,i,j,i+1,j);
		printmatrix(in4);
		h_x4=comparematrix(in4,input,goal);
		System.out.println("h(x)="+h_x4);
		if(min>h_x4)
		{
			min=h_x4;
			flag=4;
		}
		if(min==0)
		{
			System.out.println("=======================Reached the goal state ===========================\nMoving Down AT LEVEL "+(level-1)+"\n");
			printmatrix(in4);
			break;
		}
	}
	if(flag==1)
	{
		input=in1;
		System.out.println("Minimum heuristic function is of Left moved matrix in thelevel "+(level-1));
	}
	else if(flag==2)
	{
		input=in2;
		System.out.println("Minimum heuristic function is of Right moved matrix in the level  "+(level-1));
	
	}
	else if(flag==3)
	{
		input=in3;
		System.out.println("Minimum heuristic function is of Up moved matrix in the level "+(level-1));
	
	}
	else if(flag==4)
	{
		input=in4;
		System.out.println("Minimum heuristic function is of Down moved matrix in the level "+(level-1));
	
	}
	in1=new String[3][3];
	in2=new String[3][3];
	in3=new String[3][3];
	in4=new String[3][3];
	System.out.println("========================================================");
	}//while end
}

private static int comparematrix(String[][] in2, String[][] input, String[][] goal) {
	int h_x=0,g_x=0,f_n=0;
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(!goal[i][j].equals("-"))
			{
				if(!goal[i][j].equals(in2[i][j]))
				{
					h_x++;
				}
			}
		}
	}
	return h_x;
}

private static void printmatrix(String[][] in1) {
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			System.out.print("   "+in1[i][j]);
		}
		System.out.println();
	}
	
	
}

private static String[][] swap(String[][] input, String[][] in1, int i, int j,int i2, int k) {
	for(int p=0;p<3;p++)
	{
		for(int q=0;q<3;q++)
		{
			if(p==i2 && q==k)
			{
				in1[p][q]="-";
			}
			else if(p==i && q==j)
			{
				in1[p][q]=input[i2][k];
			}
			else
			{
				in1[p][q]=input[p][q];
			}
		}
	}
	return in1;
	
}



private static boolean ifvalid(int i, int j) {
	if(i<3 && i>=0 && j<3 && j>=0)
	{
		return true;
	}
	return false;
}

private static int[] fun(String[][] input) {
	
	int[] pos = new int[2];
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			if(input[i][j].equalsIgnoreCase("-"))
				{
					pos[0]=i;
					pos[1]=j;
					break;
				}
		}
	}
	
	return pos;
}
}
/*Output=>
 * Enter the matrix
-
A
C
H
B
D
G
F
E
Matrix
   -   A   C
   H   B   D
   G   F   E
Enter the goal state
A
B
C
H
-
D
G
F
E
Goal state
   A   B   C
   H   -   D
   G   F   E
---------------------------
---------------------------
SOLUTION :- 
LEVEL 1
---------------------------
MOVING RIGHT
   A   -   C
   H   B   D
   G   F   E
h(x)=1
---------------------------
MOVING DOWN
   H   A   C
   -   B   D
   G   F   E
h(x)=3
Minimum heuristic function is of Right moved matrix in the level  1
========================================================
LEVEL 2
---------------------------
MOVING LEFT
   -   A   C
   H   B   D
   G   F   E
h(x)=2
---------------------------
MOVING RIGHT
   A   C   -
   H   B   D
   G   F   E
h(x)=2
---------------------------
MOVING DOWN
   A   B   C
   H   -   D
   G   F   E
h(x)=0
=======================Reached the goal state ===========================
Moving Down AT LEVEL 2

   A   B   C
   H   -   D
   G   F   E

 */
