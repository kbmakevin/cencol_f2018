#!/bin/bash
# ----------------------------------------------------------------------------------------------------
#   Script Name :   eod-gp.sh
#   Function    :   This utility pushes all files from current git dir and commits 
#		    with "EoD push" message, and pushes to origin master.
# ----------------------------------------------------------------------------------------------------
#   Modified on         Modified by             Details
# ----------------------------------------------------------------------------------------------------
#   2018/08/02          Kevin Ma         	1. Created
#   2018/08/20          Kevin Ma         	1. Added call to other shell script to copy contents of bitbucket
#						   mft repo into this GH repo.
#   2018/08/21          Kevin Ma         	1. Added call to other shell script to copy contents of mft qa hub to
#						   bit bucket repo.
#   2018/08/25		Kevin Ma		1. Moved script to utilities/ and moved common constants to app.properties
# ----------------------------------------------------------------------------------------------------
VERSION="1.1.1"

PROPERTIES_FILE="`dirname $0`/app.properties"

if [ ! -f "$PROPERTIES_FILE" ]
then
	echo "$PROPERTIES_FILE not found."
else
	. $PROPERTIES_FILE
	echo "$PROPERTIES_FILE file loaded"
fi

# CONSTANTS
MFTQAHUB_TO_BITBUCKET="$UTILITIES_HOME/qahub2bb.sh"
BITBUCKET_TO_GITHUB="$UTILITIES_HOME/cpbb2gh.sh"
GIT_SCRIPT="$UTILITIES_HOME/gpm.sh"

# VARIABLES
commitMessage="`date` EoD push" 

echo -e "\nrunning $BITBUCKET_TO_GITHUB" 
. $BITBUCKET_TO_GITHUB

echo -e "\nrunning $MFTQAHUB_TO_BITBUCKET"
. $MFTQAHUB_TO_BITBUCKET

echo -e "\nrunning $GIT_SCRIPT"
. $GIT_SCRIPT "$commitMessage"
