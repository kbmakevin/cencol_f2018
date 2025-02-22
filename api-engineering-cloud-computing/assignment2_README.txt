Hi Reza,

For this assignment, I investigated the root causes for the issues you were encountering with AWS RDS.

In order to resolve the issues you were encountering with RDS, I had to do the following steps:

1. Re-write the "SMS.sql" file. 

This is because it was written for Microsoft SQL Server so the syntax needed to be rewritten into MySQL syntax as that is the DB instance we created on AWS RDS. 

This is shown on Slide 22 in my powerpoint presentation.

2. The "Scaffold-DbContext" provided on e-centennial was raising issues as well. This is because there needed to be a few changes.

It needed to be changed from:

Scaffold-DbContext "Data Source=mytestrds.cwmpwisqbbbu.ca-central-1.rds.amazonaws.com,1433; database=SMS; User ID=Admin; Password=Reza1234;" "Microsoft.EntityFrameworkCore.SqlServer" �O Models

to

Scaffold-DbContext "Data Source=myamazonrdsserver.cuzbla1wmfwf.ca-central-1.rds.amazonaws.com; database=SMS; User ID=admin; Password=password;" "Pomelo.EntityFrameworkCore.MySql" -OutputDir Models

This is shown on slide 25 in my powerpoint presentation.

3. The connection string in appsettings.json shown in the lecture slides needed to be rewritten. ConnectRetryCount=0; does not exist for MySQL and the other details are for SQLServer as well.

It needed to be changed from:

 "ConnectionStrings": {
    "Connection2RDS": "Server=sqlserver4comp306.xxxaws.com,1433;Database=SMS;User ID=admin;Password=password;ConnectRetryCount=0;"
  }

to

 "ConnectionStrings": {
    "Connection2RDS": "Server=myamazonrdsserver.cuzbla1wmfwf.ca-central-1.rds.amazonaws.com,3306;Database=SMS;User ID=admin;Password=password;"
  }

This is shown on slide 27 in my powerpoint presentation.

4. The lambda function in startup.cs needed to be rewritten:

Changed from:

services.AddDbContext<SMSContext>(options => options.UseSqlServer(Configuration.GetConnectionString("Connection2RDS")));

to

services.AddDbContext<SMSContext>(options => options.UseMySql(Configuration.GetConnectionString("Connection2RDS")));

This is shown on slide 28 in my powerpoint presentation.

Thank you for providing us with this realistic and hands on assignment! I really enjoyed working on troubleshooting the issues provided in this case study because this is definitely something which we would be encountering while working in the industry after we graduate. I really enjoyed it.

Thanks,
Kevin