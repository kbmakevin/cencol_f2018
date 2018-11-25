using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;

namespace comp306_wk11_web_app_crud.Pages
{
    public class IndexModel : PageModel
    {
        private readonly AppDbContext _db;

        public IndexModel(AppDbContext db)
        {
            this._db = db;
        }

        public IList<Customer>Customers { get; private set; }
        [TempData]
        public string Message { get; set; }

        public async Task OnGetAsync()
        {
            Customers = await this._db.Customers.AsNoTracking().ToListAsync();
        }
        
        public async Task<IActionResult> OnPostDeleteAsync(int id)
        {
            Customer customer = await this._db.Customers.FindAsync(id);
            if (customer != null)
            {
                this._db.Customers.Remove(customer);
                await this._db.SaveChangesAsync();
                Message = $"Customer {customer.Name} was deleted!";
            }
            return RedirectToPage();
        }
    }
}
