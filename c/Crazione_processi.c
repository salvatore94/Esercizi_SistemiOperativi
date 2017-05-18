#include <stdio.h>
#include <sys/types.h>

pid_t pid = 0;
pid_t getpid(void);
pid_t fork(void);
int signal = 0;

int main () {

pid = fork();

if (pid==0) {
	//child
	printf("C: Child process -- \n");
	printf("C: Child ID is %d\n", (int)getpid());
	printf("C: Child pid %d\n",pid);
	sleep(1000);
  }
  else {
	//parent
	printf("P: Parent process -- \n");
	printf("P: Parent ID is %d\n", (int)getpid());
	printf("P: Child ID is %d\n", (int) pid);
  }

 wait(&signal);
return 0;
}
