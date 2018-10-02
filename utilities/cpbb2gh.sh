#!/bin/bash
#---------------------------------------------------------------------------------
#   Script Name :   cpbb2gh.sh
#   Function    :   This utility copies all files from a bitbucket directory into
#		    a target github repository using rsync and excluding .git/
#---------------------------------------------------------------------------------
#   Modified on         Modified by             Details
#---------------------------------------------------------------------------------
#   2018/08/20          Kevin Ma         	1. Created
#---------------------------------------------------------------------------------
VERSION="1.0.0"

SRC="/c/Users/Kevin.Ma/Documents/managed-file-transfer/"
DEST="/c/Users/Kevin.Ma/Documents/ctc/mft-bitbucket"

echo "SRC = $SRC"
echo "DEST = $DEST"
rsync -ahvz $SRC $DEST --exclude .git
