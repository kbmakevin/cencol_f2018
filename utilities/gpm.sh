#!/bin/bash
# ----------------------------------------------------------------------------------------------------
#   Script Name :   gpm.sh
#   Function    :   This utility pushes all files from current git dir and commits 
#		    with arg message, and pushes to origin master.
# ----------------------------------------------------------------------------------------------------
#   Modified on         Modified by             Details
# ----------------------------------------------------------------------------------------------------
#   2018/08/02          Kevin Ma         	1. Created
#   2018/08/21          Kevin Ma         	1. Modified script to be more verbose
#						2. Added feature where script would pick up GIT_USER and GIT_PASSWORD
#						   from ~/.zshrc to avoid user prompt for credentials
#   2018/08/25		Kevin Ma		1. Moved script to utilities/ and moved common constants to app.properties
#						2. Add optional 2nd arg for specifying GIT_HOME if pushing from another dir
# ----------------------------------------------------------------------------------------------------
VERSION="1.3.1"

PROPERTIES_FILE="`dirname $0`/app.properties"

if [ ! -f "$PROPERTIES_FILE" ]
then
	echo "$PROPERTIES_FILE not found."
else
	. $PROPERTIES_FILE
	echo "$PROPERTIES_FILE file loaded"
fi

# CONSTANTS
if [ -z "$2" ]
then
	GIT_HOME=$PARENT_DIRECTORY
else
	GIT_HOME="$2"
fi
GIT_REPO=`git remote get-url origin`

# VARIABLES
commitMessage="$1"

echo "GIT_HOME is $GIT_HOME"
cd $GIT_HOME 

echo "Adding all unstaged contents to staging"
git add .
echo "Committing staged changes to git repo => $commitMessage"
git commit -m "$commitMessage"
echo "Pushing staged changes to $GIT_REPO"
git push https://$GIT_USER:$GIT_PASSWORD@${GIT_REPO:8} --all
