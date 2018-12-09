using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace asp_net_core_mvc.Models
{
    public class asp_net_core_mvcContext : DbContext
    {
        public asp_net_core_mvcContext (DbContextOptions<asp_net_core_mvcContext> options)
            : base(options)
        {
        }

        public DbSet<asp_net_core_mvc.Models.Movie> Movie { get; set; }
    }
}
