#!/bin/bash

if [ $# -ne 1 ]
then 
	echo "Inserire il nome del file"
elif [ ! -s $@ ]
then
	echo "File non esistente"
else
	awk '{ print $0; print '\n'; }' $@
fi
