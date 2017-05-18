#!/bin/bash

awk -F : '/cane e gatto/{ print FILENAME }' $@/[a-z]*\.txt
