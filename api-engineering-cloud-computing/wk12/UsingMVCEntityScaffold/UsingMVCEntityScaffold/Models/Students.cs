using System;
using System.Collections.Generic;

namespace UsingMVCEntityScaffold.Models
{
    public partial class Students
    {
        public Students()
        {
            Enrollments = new HashSet<Enrollments>();
        }

        public int StudentId { get; set; }
        public string FirstName { get; set; }
        public string LastName { get; set; }
        public DateTime? DoB { get; set; }
        public int? GenderId { get; set; }
        public DateTime? RegistrationDate { get; set; }
        public int? Status { get; set; }

        public Genders Gender { get; set; }
        public ICollection<Enrollments> Enrollments { get; set; }
    }
}
