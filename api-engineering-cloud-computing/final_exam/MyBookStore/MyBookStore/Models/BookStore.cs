using System;
using System.ComponentModel.DataAnnotations;

namespace MyBookStore.Models
{
    public class BookStore
    {
        [Key]
        public int BookId { get; set; }
        public String BookName { get; set; }
        public DateTime AvailableDate { get; set; }
        public Decimal Price { get; set; }

    }
}
