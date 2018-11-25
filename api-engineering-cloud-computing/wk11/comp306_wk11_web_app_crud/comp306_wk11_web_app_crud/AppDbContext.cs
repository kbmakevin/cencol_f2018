using Microsoft.EntityFrameworkCore;

namespace comp306_wk11_web_app_crud
{
    public class AppDbContext:DbContext
    {
        public AppDbContext(DbContextOptions options):base(options)
        {
        }

        public DbSet<Customer>Customers { get; set; }
    }
}