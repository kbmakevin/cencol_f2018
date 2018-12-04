using System;
using System.Collections.Generic;

namespace UsingMVCEntityScaffold.Models
{
    public partial class Genders
    {
        public Genders()
        {
            Students = new HashSet<Students>();
        }

        public int GenderId { get; set; }
        public string GenderName { get; set; }

        public ICollection<Students> Students { get; set; }
    }
}
