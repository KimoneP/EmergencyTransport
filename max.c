#include <stdio.h>
#include <stdlib.h>


int main(int argc,char *argv[]){
	int i;
	int n;
	int max=0;
	for(i=1;i<argc;i++){
	n=atoi(argv[i]);
	if(n>max)
		max=n;
	}
	printf("%d\n",max);
	return(EXIT_SUCCESS);
}
