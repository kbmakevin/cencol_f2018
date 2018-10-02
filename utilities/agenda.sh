#!/bin/bash
#---------------------------------------------------------------------------------
#   Script Name :   agenda.sh
#   Function    :   This utility creates and manages todo lists/daily agendas for the uesr:
#			- create new file with today's date as name in $TODO_HOME/todo/YYYYMMDD file 
#			- retrieve most recent file (since can be firday -> monday or day off) into newly created file
#			- archive all files other than today. (parse where files go based on their name)
# 				- year
# 					- month
#---------------------------------------------------------------------------------
#   Modified on         Modified by             Details
#---------------------------------------------------------------------------------
#   2018/06/14          Kevin Ma         	1. Created
#   2018/07/06		Kevin Ma		1. Update Utility for new requirements  
#   2018/07/11		Kevin Ma		1. Changed files to be markdown files (*.md)
#   2018/07/28		Kevin Ma		1. Changed TODO_HOME to use ./todo
#   2018/08/02		Kevin Ma		1. Changed TODO home to be in script home/todo 
#   2018/08/25		Kevin Ma		1. Moved script to utilities/ and moved common constants to app.properties
#---------------------------------------------------------------------------------
VERSION="1.1.4"

PROPERTIES_FILE="`dirname $0`/app.properties"

if [ ! -f "$PROPERTIES_FILE" ]
then
	echo "$PROPERTIES_FILE not found."
else
	. $PROPERTIES_FILE
	echo "$PROPERTIES_FILE file loaded"
fi

# CONSTANTS
TODO_HOME=$PARENT_DIRECTORY/todo
DFORMAT=$(date +"%Y%m%d")
TODAY_AGENDA=$TODO_HOME/$DFORMAT.md

# create new file with today's date as name in $TODO_HOME/todo/YYYYMMDD file  
# read contents of  most recent file into newly created file
mostRecentAgenda=$TODO_HOME/$(ls -r $TODO_HOME | head -1)
if [ ! -f $TODAY_AGENDA ]
then
	cp $mostRecentAgenda $TODAY_AGENDA
	echo "$TODAY_AGENDA has been created!"
else
	echo "$TODAY_AGENDA already exists!"
fi

# archive all files other than today. (parse where files go based on their name)
# into YEAR/MONTH/FILE_NAME
fileList=$(ls -p $TODO_HOME | grep -v /) # grep -v selects all non-matching; append / to directories with ls -p
for f in ${fileList[@]}
do
	if [ $TODO_HOME/$f != $TODAY_AGENDA ]
	then
		year=$(echo $f | cut -c1-4)
		month=$(echo $f | cut -c5-6)
		day=$(echo $f | cut -c7-8)
		archiveDir=$TODO_HOME/$year/$month
		mkdir -p $archiveDir
		mv $TODO_HOME/$f $archiveDir
	fi
done
echo "old agendas have been successfully archived!"

vi $TODAY_AGENDA
