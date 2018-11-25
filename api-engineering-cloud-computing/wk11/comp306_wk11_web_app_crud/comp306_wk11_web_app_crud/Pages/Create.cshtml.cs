using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.RazorPages;
using Microsoft.Extensions.Logging;

namespace comp306_wk11_web_app_crud.Pages
{
    public class CreateModel : PageModel
    {
        private readonly AppDbContext _db;
        private ILogger<CreateModel> _log;

        public CreateModel(AppDbContext db, ILogger<CreateModel>log)
        {
            this._db = db;
            this._log = log;
        }

        [TempData]
        public string Message { get; set; }

        [BindProperty]
        public Customer Customer { get; set; }

        public async Task<IActionResult> OnPostAsync()
        {
            if (!ModelState.IsValid)
            {
                return Page();
            }
            this._db.Customers.Add(Customer);
            await this._db.SaveChangesAsync();

            var msg = $"Customer {Customer.Name} was added!";
            Message = msg;
            this._log.LogInformation(msg);

            return RedirectToPage("/Index");
        }

        public void OnGet()
        {

        }
    }
}