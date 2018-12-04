using System;
using System.Collections.Generic;

namespace UsingMVCEntityScaffold.Models
{
    public partial class Courses
    {
        public Courses()
        {
            Enrollments = new HashSet<Enrollments>();
        }

        public int CourseId { get; set; }
        public string CourseName { get; set; }
        public string Credits { get; set; }

        public ICollection<Enrollments> Enrollments { get; set; }
    }
}
