package dppom.model.session;

public class SessionDTO {
   
   
   private String id;
   private String pass;
   private String name;
   private String univ;
   private String major;
   private String certi_flag;
   private String certi_url; 
   private String lastlogin;
   private int point;

public SessionDTO(){} //기본생성자 필수

   public SessionDTO(String id, String pass, String name, String univ, String major, String certi_flag, String certi_url, String lastlogin, int point) {
      super();
      this.id = id;
      this.pass = pass;
      this.name = name;
      this.univ = univ;
      this.major = major;
      this.certi_flag = certi_flag;
      this.certi_url = certi_url;
      this.lastlogin = lastlogin;
      this.point = point;
   }

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getPass() {
      return pass;
   }

   public void setPass(String pass) {
      this.pass = pass;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUniv() {
      return univ;
   }

   public void setUniv(String univ) {
      this.univ = univ;
   }

   public String getMajor() {
      return major;
   }

   public void setMajor(String major) {
      this.major = major;
   }
   
   public String getCerti_flag() {
      return certi_flag;
   }

   public void setCerti_flag(String certi_flag) {
      this.certi_flag = certi_flag;
   }

   public String getCerti_url() {
      return certi_url;
   }

   public void setCerti_url(String certi_url) {
      this.certi_url = certi_url;
   }

   public String getLastlogin() {
      return lastlogin;
   }
   
   public void setLastlogin(String lastlogin) {
      this.lastlogin = lastlogin;
   }

   public int getPoint() {
      return point;
   }
   
   public void setPoint(int point) {
      this.point = point;
   }
   
   
}