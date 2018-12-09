using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;

namespace MyBookStore.Models
{
    public class MyBookStoreContext : DbContext
    {
        public MyBookStoreContext (DbContextOptions<MyBookStoreContext> options)
            : base(options)
        {
        }

        public DbSet<MyBookStore.Models.BookStore> BookStore { get; set; }
    }
}
