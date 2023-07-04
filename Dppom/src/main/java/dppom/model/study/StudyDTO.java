package dppom.model.study;

public class StudyDTO 
{
   private int idx;
   private String study_type;
   private String category;
   private String title;
   private String content;
   private String url;
   private int rNum;
   
   public StudyDTO(){}

   public StudyDTO(int idx, String study_type, String category, String title, String content, String url) {
      super();
      this.idx = idx;
      this.study_type = study_type;
      this.category = category;
      this.title = title;
      this.content = content;
      this.url = url;
   }

   public StudyDTO(int idx, String study_type, String category, String title, String content, String url,
         int rNum) {
      super();
      this.idx = idx;
      this.study_type = study_type;
      this.category = category;
      this.title = title;
      this.content = content;
      this.url = url;
      this.rNum = rNum;
   }

   public int getIdx() {
      return idx;
   }

   public void setIdx(int idx) {
      this.idx = idx;
   }

   public String getStudy_type() {
      return study_type;
   }

   public void setStudy_type(String study_type) {
      this.study_type = study_type;
   }

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }
   
   public int getrNum() {
      return rNum;
   }

   public void setrNum(int rNum) {
      this.rNum = rNum;
   }
}