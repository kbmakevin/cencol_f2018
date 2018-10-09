using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace AWSAspDotNetCoreMVCMovie.Models
{
    public class AWSAspDotNetCoreMVCMovieContext : DbContext
    {
        public AWSAspDotNetCoreMVCMovieContext (DbContextOptions<AWSAspDotNetCoreMVCMovieContext> options)
            : base(options)
        {
        }

        public DbSet<AWSAspDotNetCoreMVCMovie.Models.Movie> Movie { get; set; }
    }
}
