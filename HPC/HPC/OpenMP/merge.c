#include <stdio.h>
#include <string.h>
#include "omp.h"

#define MAX_SIZE 10


void generate_list(int * x, int n) {
   printf("Enter numbers in list\n");
   for (int i = 0; i < n; i++) {
     //int j = rand() % n;
      scanf("%d",&x[i]);
   }
}

void print_list(int * x, int n) {
   for (int i = 0; i < n; i++) {
      printf("%d ",x[i]);
   }
}

void merge(int * X, int n, int * tmp) {
   int i = 0;
   int j = n/2;
   int ti = 0;

   while (i<n/2 && j<n) {
      if (X[i] < X[j]) {
         tmp[ti] = X[i];
         ti++; i++;
      } else {
         tmp[ti] = X[j];
         ti++; j++;
      }
   }
   while (i<n/2) { /* finish up lower half */
      tmp[ti] = X[i];
      ti++; i++;
   }
      while (j<n) { /* finish up upper half */
         tmp[ti] = X[j];
         ti++; j++;
   }
  /* printf("\n Elements in X: ");
   for(int k=0;k<n;k++) {
       printf("%d ",X[k]);
   }
   printf("\nElements in tmp: ");
   for(int k=0;k<n;k++) {
       printf("%d ",tmp[k]);
   }
*/
   memcpy(X, tmp, n*sizeof(int)); // note, it is N which varies for all threads from 2 to 10

   // and X is also not starting at index 0 always, it is at X+2 and so on at times. 

} // end of merge()

void mergesort(int * X, int n, int * tmp)
{
   if (n < 2) return;

   #pragma omp task firstprivate (X, n, tmp)
   mergesort(X, n/2, tmp);

   #pragma omp task firstprivate (X, n, tmp)
   mergesort(X+(n/2), n-(n/2), tmp);    // each thread has it's own copy of the original X

   #pragma omp taskwait

    /* merge sorted halves into sorted list */
   merge(X, n, tmp);  // original X copy
}


int main()
{
   int n = 10;
   long double start, stop;

   int data[MAX_SIZE], tmp[MAX_SIZE];

   generate_list(data, n);
   printf("List Before Sorting...\n");
   print_list(data, n);

   start = omp_get_wtime();
   #pragma omp parallel
   {
      #pragma omp single
      mergesort(data, n, tmp);
   }
   stop = omp_get_wtime();
   
   printf("\nList After Sorting...\n");
   print_list(data, n);
   printf("\nTime: Start:  %0.16g Stop:  %0.16g Diff:  %0.16g\n",start,stop,stop-start);
}
/*output
[ccoew@localhost 4423]$ gcc -fopenmp merge.c
[ccoew@localhost 4423]$ ./a.out
List Before Sorting...
3 8 2 4 5 0 1 7 9 6 
List After Sorting...
0 1 2 3 4 5 6 7 8 9 

 */

