using System.ComponentModel.DataAnnotations;

namespace comp306_wk11_web_app_crud
{
    public class Customer
    {
        public int Id { get; set; }
        [Required,StringLength(10)]
        public string Name { get; set; }
    }
}