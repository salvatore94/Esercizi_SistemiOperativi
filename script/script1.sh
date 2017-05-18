#!/bin/bash

if [ -d $1 ]
then
	awk '/[a-z]+\.[a-z]+@[a-z]+\.[a-z]+/ {print}' $@/[a-z]*[0-9]*\.txt

fi
