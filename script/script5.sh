#!/bin/bash

pass=$(awk -F : -v str="$@" 'str { print $2 }' password.txt)

awk -v str=$pass '$0==str { print "Insicura"}' /usr/share/dict/web2 
