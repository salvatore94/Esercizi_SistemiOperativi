#!/bin/bash

if [ -f $@ ]
then
	awk -F : '{ print FNR "- " $0}' $@
fi
