using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.EntityFrameworkCore;

namespace comp306_wk11_web_app_crud.Pages
{
    public class EditModel : PageModel
    {
        private readonly AppDbContext _db;
        public EditModel(AppDbContext db)
        {
            this._db = db;
        }

        [BindProperty]
        public Customer Customer { get; set; }
        [TempData]
        public string Message { get; set; }

        public async Task OnGetAsync(int id)
        {
            Customer = await this._db.Customers.FindAsync(id);
        }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
                return Page();
            this._db.Attach(Customer).State = EntityState.Modified;
            try
            {
                await this._db.SaveChangesAsync();
                Message = $"Customer {Customer.Name} was updated!";
            }
            catch (DbUpdateConcurrencyException e)
            {
                throw new Exception($"Customer {Customer.Id} not found", e);
            }
            return RedirectToPage("./Index");
        }
    }
}