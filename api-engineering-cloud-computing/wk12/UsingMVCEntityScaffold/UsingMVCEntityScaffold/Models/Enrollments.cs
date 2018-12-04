using System;
using System.Collections.Generic;

namespace UsingMVCEntityScaffold.Models
{
    public partial class Enrollments
    {
        public int EnrollmentId { get; set; }
        public int? StudentId { get; set; }
        public int? CourseId { get; set; }
        public DateTime? StartDate { get; set; }
        public DateTime? EndDate { get; set; }
        public int? Grade { get; set; }

        public Courses Course { get; set; }
        public Students Student { get; set; }
    }
}
