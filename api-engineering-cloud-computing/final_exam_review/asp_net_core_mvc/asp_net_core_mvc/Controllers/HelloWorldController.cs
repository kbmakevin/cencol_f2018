using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Encodings.Web;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace asp_net_core_mvc.Controllers
{
    public class HelloWorldController : Controller
    {
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Welcome(String name, int numTimes = 1)
        {

            ViewData["Message"] = $"Hello {name}";
            ViewData["NumTimes"] = numTimes; 

            return View();
        }
    }
}