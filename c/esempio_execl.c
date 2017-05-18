#include<stdio.h>
#include<unistd.h>


int main () {

execl("/bin/ps", "-e", NULL);


return 0;
}
