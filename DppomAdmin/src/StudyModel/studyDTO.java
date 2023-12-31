package StudyModel;

public class studyDTO {

   private int idx;
   private String study_type; // English , License
   private String category; // 종류
   private String title;  
   private String content;
   private String url; // 동영상 URL 
   
   public studyDTO(){}

   public studyDTO(int idx, String study_type, String category, String title, String content, String url) {
      super();
      this.idx = idx;
      this.study_type = study_type;
      this.category = category;
      this.title = title;
      this.content = content;
      this.url = url;
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
}