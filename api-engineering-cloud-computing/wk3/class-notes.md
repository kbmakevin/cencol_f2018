Sun 23 Sep 2018 08:37:06 AM DST
Cloud Computing and API Engineering Week3
---
watched utube video Introduction to Amazon RDS
	Relational Database Service

	pay for what u use
	ability to easily scale, take snapshots, clone db to experiment, backups, update db versions


watched utube video Introduction to Amazon RDS for Aurora

scaling up
	cost rises exponentially

scaling out
	distributed databases
	HADOOP, did this 13-14 years ago
		2003
		e.g. giant book, divided into small chapters and have different people count # of "the"'s within their assigned package and return the number
		problem is I/O
			came up with ideas of Spark and Storm
				doing same thing Hadoop does but inside memory, need strong RAM
	idea for clustering was from 30 years ago

data vs big data
	when working with lots of data, problems with SQL
		PK-FK integrity checking takes long time
			we want to be able to write data without discipline but have discipline when fetching the data
	when writing data there are policies for data
	when READING data there are policies for big data

