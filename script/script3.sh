#!/bin/bash

if [ -d $@ ]
	then
	awk '/[a-z]+\.[a-z]+@[a-z]+\.[a-z]+/ {print FILENAME}' $@/[a-z]*[0-9].txt
fi
