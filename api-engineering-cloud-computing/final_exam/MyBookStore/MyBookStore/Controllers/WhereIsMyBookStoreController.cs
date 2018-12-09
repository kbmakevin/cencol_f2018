using System;
using Microsoft.AspNetCore.Mvc;

namespace MyBookStore.Controllers
{
    public class WhereIsMyBookStoreController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public String OldIndex()
        {
            return "Welcome to My Book Store!";
        }

        public IActionResult Welcome(string name, int numTimes=1)
        {
            ViewData["Message"] = "Hello " + name;
            ViewData["numTimes"] = numTimes;
            return View();
        }

        public String MyAdd()
        {
            return "Join us for free to win!";
        }

        public String MyCustomer(String name, int ID)
        {
            return $"Welcome to my world, {name}! I know you will win {ID} times!";
        }
    }
}