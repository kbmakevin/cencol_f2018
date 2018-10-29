using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace WebAccess2RDS.Models
{
    public partial class SMSContext : DbContext
    {
        public SMSContext()
        {
        }

        public SMSContext(DbContextOptions<SMSContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Course> Course { get; set; }
        public virtual DbSet<Enrollment> Enrollment { get; set; }
        public virtual DbSet<Login> Login { get; set; }
        public virtual DbSet<Student> Student { get; set; }

//        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
//        {
//            if (!optionsBuilder.IsConfigured)
//            {
//#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
//                optionsBuilder.UseMySql("Data Source=myamazonrdsserver.cuzbla1wmfwf.ca-central-1.rds.amazonaws.com; database=SMS; User ID=admin; Password=password;");
//            }
//        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Course>(entity =>
            {
                entity.HasKey(e => e.CourseCode);

                entity.Property(e => e.CourseCode).HasColumnType("varchar(10)");

                entity.Property(e => e.CourseTitle)
                    .IsRequired()
                    .HasColumnType("varchar(50)");

                entity.Property(e => e.Department)
                    .IsRequired()
                    .HasColumnType("varchar(50)");

                entity.Property(e => e.School)
                    .IsRequired()
                    .HasColumnType("varchar(50)");

                entity.Property(e => e.TotalCourseHours).HasColumnType("int(11)");
            });

            modelBuilder.Entity<Enrollment>(entity =>
            {
                entity.HasKey(e => new { e.StudentId, e.CourseCode });

                entity.HasIndex(e => e.CourseCode)
                    .HasName("FK_Enrollment_Course");

                entity.Property(e => e.StudentId)
                    .HasColumnName("StudentID")
                    .HasColumnType("varchar(10)");

                entity.Property(e => e.CourseCode).HasColumnType("varchar(10)");

                entity.HasOne(d => d.CourseCodeNavigation)
                    .WithMany(p => p.Enrollment)
                    .HasForeignKey(d => d.CourseCode)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Enrollment_Course");

                entity.HasOne(d => d.Student)
                    .WithMany(p => p.Enrollment)
                    .HasForeignKey(d => d.StudentId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Enrollment_Student");
            });

            modelBuilder.Entity<Login>(entity =>
            {
                entity.HasKey(e => e.LoginName);

                entity.Property(e => e.LoginName).HasColumnType("varchar(10)");

                entity.Property(e => e.Password)
                    .IsRequired()
                    .HasColumnType("varchar(12)");

                entity.HasOne(d => d.LoginNameNavigation)
                    .WithOne(p => p.Login)
                    .HasForeignKey<Login>(d => d.LoginName)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK_Login_Student");
            });

            modelBuilder.Entity<Student>(entity =>
            {
                entity.Property(e => e.StudentId)
                    .HasColumnName("StudentID")
                    .HasColumnType("varchar(10)");

                entity.Property(e => e.FirstName).HasColumnType("varchar(50)");

                entity.Property(e => e.LastName).HasColumnType("varchar(50)");

                entity.Property(e => e.Program).HasColumnType("varchar(8)");
            });
        }
    }
}
