package dppom.model.member;

import java.sql.Date;

public class MemberDTO {
   
   private String id;
   private String pass;
   private String name;
   private String email;
   private String phone;
   private String univ;  //학교
   private String major; //학과
   private java.sql.Date regidate; //회원가입날짜
   private int point;  //보유 포인트 
   private String pass_question; //비번찾기 질문
   private String pass_answer; //비번찾기 
   private int report_cnt; // 신고 횟수
   private String certi_flag; // 인증 여부
   private String certi_url;  // 인증 파일 
   private String lastlogin; // 마지막 로그인 날짜
   
   public MemberDTO(){} // 기본생성자 AutoWired 자동주입위해서 반드시 필요 (JDBC 템플릿)
   
   public MemberDTO(String id, String pass, String name, String email, String phone, String univ, String major,
         Date regidate, int point, String pass_question, String pass_answer, int report_cnt, String certi_flag, String certi_url, String lastlogin) {
      super();
      this.id = id;
      this.pass = pass;
      this.name = name;
      this.email = email;
      this.phone = phone;
      this.univ = univ;
      this.major = major;
      this.regidate = regidate;
      this.point = point;
      this.pass_question = pass_question;
      this.pass_answer = pass_answer;
      this.report_cnt = report_cnt;
      this.certi_flag = certi_flag;
      this.certi_url = certi_url;
      this.lastlogin = lastlogin;
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


   public String getEmail() {
      return email;
   }


   public void setEmail(String email) {
      this.email = email;
   }


   public String getPhone() {
      return phone;
   }


   public void setPhone(String phone) {
      this.phone = phone;
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


   public java.sql.Date getRegidate() {
      return regidate;
   }


   public void setRegidate(java.sql.Date regidate) {
      this.regidate = regidate;
   }


   public int getPoint() {
      return point;
   }


   public void setPoint(int point) {
      this.point = point;
   }


   public String getPass_question() {
      return pass_question;
   }


   public void setPass_question(String pass_question) {
      this.pass_question = pass_question;
   }


   public String getPass_answer() {
      return pass_answer;
   }


   public void setPass_answer(String pass_answer) {
      this.pass_answer = pass_answer;
   }


   public int getReport_cnt() {
      return report_cnt;
   }


   public void setReport_cnt(int report_cnt) {
      this.report_cnt = report_cnt;
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

}