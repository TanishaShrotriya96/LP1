/**
 * Copyright 1993-2012 NVIDIA Corporation.  All rights reserved.
 *
 * Please refer to the NVIDIA end user license agreement (EULA) associated
 * with this source code for terms and conditions that govern your use of
 * this software. Any use, reproduction, disclosure, or distribution of
 * this software and related documentation outside the terms of the EULA
 * is strictly prohibited.
 */
#include <stdio.h>
#include<cuda.h>
#include <stdlib.h>
#include<time.h>

#define SIZE 3

__global__ void matrixvectmult(int *mat,int *vect,int *res)
{
	int tid=blockIdx.x*blockDim.x;
	int mult=0;
	for(int i=0;i<SIZE;i++)
	{
		mult=mult+(mat[tid+i]*vect[i]);
	}
	res[blockIdx.x]=mult;
}

int main(void)
{
	int i,j;
	srand(time(NULL));
	int a[SIZE][SIZE],b[SIZE],c[SIZE];

	int *dev_a,*dev_b,*dev_c;

	cudaMalloc((void **)&dev_a, SIZE*SIZE*sizeof(int));
	cudaMalloc((void **)&dev_b, SIZE*sizeof(int));
	cudaMalloc((void **)&dev_c, SIZE*sizeof(int));

	for(i=0;i<SIZE;i++)
	{
		for(j=0;j<SIZE;j++)
		{
			a[i][j] = rand()%20+1;
		}
	}

	printf("\nThe matrix is:\n");
	for(i=0;i<SIZE;i++)
	{
		for(j=0;j<SIZE;j++)
		{
			printf("%d\t",a[i][j]);
		}
		printf("\n");
	}

	for(i=0;i<SIZE;i++)
	{
		b[i] = rand()%20+1;
	}

	printf("\nThe vector is:\n");
	for(i=0;i<SIZE;i++)
	{
		printf("%d  ",b[i]);
	}

	cudaMemcpy(dev_a,a,sizeof(a),cudaMemcpyHostToDevice);
	cudaMemcpy(dev_b,b,sizeof(b),cudaMemcpyHostToDevice);
	matrixvectmult<<<SIZE,SIZE>>>(dev_a,dev_b,dev_c);
	cudaMemcpy(&c,dev_c,sizeof(c),cudaMemcpyDeviceToHost);

	printf("\nThe result is:\n");
	for(int i=0;i<SIZE;i++)
	{
		printf("%d ",c[i]);
	}


	return 0;
}
