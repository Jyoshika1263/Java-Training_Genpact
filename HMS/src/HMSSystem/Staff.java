package HMSSystem;

public class Staff {

   private int id;
   private String name;
   private String contactNumber;

   public Staff(String name,String contactNumber) {
       super();
       this.name = name;
       this.contactNumber = contactNumber;
   }

   public Staff(int id, String name,String contactNumber) {
       super();
       this.id = id;
       this.name = name;
       this.contactNumber = contactNumber;
   }

   public int getId() {
       return id;
   }

   public void setId(int id) {
       this.id = id;
   }

   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }
   public String getContactNumber() {
       return contactNumber;
   }

   public void setContactNumber(String contactNumber) {
       this.contactNumber = contactNumber;
   }

   @Override
   public String toString() {
       return "Staff [id=" + id + ", name=" + name + ", contactNumber=" + contactNumber + "]";
   }
}



















