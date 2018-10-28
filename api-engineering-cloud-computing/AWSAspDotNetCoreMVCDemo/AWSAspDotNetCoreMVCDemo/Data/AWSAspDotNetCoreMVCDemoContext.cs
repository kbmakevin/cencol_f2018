using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace AWSAspDotNetCoreMVCDemo.Models
{
    public class AWSAspDotNetCoreMVCDemoContext : DbContext
    {
        public AWSAspDotNetCoreMVCDemoContext (DbContextOptions<AWSAspDotNetCoreMVCDemoContext> options)
            : base(options)
        {
        }

        public DbSet<AWSAspDotNetCoreMVCDemo.Models.Movie> Movie { get; set; }
    }
}
