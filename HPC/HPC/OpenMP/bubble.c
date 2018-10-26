#include <omp.h>
#include <stdio.h>
#include <stdlib.h>

void swap();

int main (int argc, char *argv[]) {

	int N;
	printf("Enter number of elements\n");
    scanf("%d",&N);
    int A[N];
    printf("Enter elements\n");
    for(int i=0;i<N;i++) {
    	int a;
    	scanf("%d",&a);
    	A[i]=a;
    }

    printf("Array is \n");
    for(int i=0;i<N;i++) {
    	printf("%d ",A[i]);
    }

	int i=0, j=0; 
	int first;

	for( i = 0; i < N; i++ )
	{
		first = i % 2; 
	
	    #pragma omp parallel for shared(A,first,N)
		
		for( j = first; j < N-1; j += 2 )
		{
			int id=omp_get_thread_num();
			if( A[ j ] > A[ j+1 ] )
			{
				swap( &A[ j ], &A[ j+1 ] );
			}
			printf("Id of thread is %d\n", id);
			printf("Value of j:%d\n",j);
				
		}
	}

	for(i=0;i<N;i++)
	{
		printf("\n %d",A[i]);
	}
}

void swap(int *num1, int *num2)
{

	int temp = *num1;
	*num1 =  *num2;
	*num2 = temp;
}



/*
OUTPUT:

[ccoew@localhost 4423]$ gcc -fopenmp bubble.c
[ccoew@localhost 4423]$ ./a.out

 1
 3
 6
 7
 9
*/

