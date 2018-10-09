using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.Encodings.Web;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;

namespace AWSAspDotNetCoreMVCMovie.Controllers
{
    public class HelloWorldController : Controller
    {
        //Get: /HelloWorld
        public IActionResult Index()
        {
            return View();
        }
        public IActionResult Welcome(string name, int numTimes = 1)
        {
            ViewData["Message"] = $"Hello {name}";
            ViewData["NumTimes"] = numTimes;
            return View();
        }
        //public string Index()
        //{
        //    return ("This is my default action...");
        //}
        ////GET: /HelloWorld/Welcome
        //public string Welcome(string name, int numTimes = 1)
        //{
        //    return HtmlEncoder.Default.Encode($"Hello {name}, NumTimes: {numTimes}");
        //}
    }
}