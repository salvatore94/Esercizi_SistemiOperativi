#include<stdio.h>
#include<unistd.h>
#include<sys/types.h>
#include<stdbool.h>

bool t1WhantsToEnter = false;
bool t2WhantsToEnter = false;

int favored = 0;

pid_t pid;
int status = 0;

pid_t fork(void);

int main () {

  pid = fork();
  favored = 2;
  t1WhantsToEnter = true;

  if (pid == 0) {
    bool done = false;
    int counter = 10
    while (!done) {
      t2WhantsToEnter = true;
      while (t1WhantsToEnter) {
        if(favored == 1) {
          t2WhantsToEnter = false;
          while(favored == 1);
          t2WhantsToEnter = true;
        }
      }

      //sezione critica
      printf("Processo secondario\n");
      //fine
      // done = true;
        favored = 1;
        t2WhantsToEnter = false;
    }
  } else {
    bool done = false;
    int counter = 10;

    while (!done) {
      t1WhantsToEnter = true;
      while (t2WhantsToEnter) {
        if ( favored == 2 ) {
          t1WhantsToEnter = false;
          while (favored == 2);
          t1WhantsToEnter = true;
        }
      }

      //sezione critica
      sleep(1000);
      printf("Processo primario\n");
      //fine
        done = true;
        favored = 2;
        t1WhantsToEnter = false;
    }
  }

  // wait(&status);
  return 0;
}
