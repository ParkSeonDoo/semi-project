package score;
   
public class ScoreVo {
     String serial;
     String id;      
     String grade;
     String name;
     String ban;
     double  kor; 
	 double  math;
	 double  eng;
	 double  his;
	 double sci;
	 String test;
	    
	 double tot ;
	 double avg;
    
      public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBan() {
		return ban;
	}
	public void setBan(String ban) {
		this.ban = ban;
	}
      
   public String getSerial() {
      return serial;
   }
   public void setSerial(String serial) {
      this.serial = serial;
   }
   public String getId() {
      return id;
   }
   public void setId(String id) {
      this.id = id;
   }
   public double getKor() {
      return kor;
   }
   public void setKor(double kor) {
      this.kor = kor;
   }
   public double getMath() {
      return math;
   }
   public void setMath(double math) {
      this.math = math;
   }
   public double getEng() {
      return eng;
   }
   public void setEng(double eng) {
      this.eng = eng;
   }
   public double getHis() {
      return his;
   }
   public void setHis(double his) {
      this.his = his;
   }
   public double getSci() {
      return sci;
   }
   public void setSci(double sci) {
      this.sci = sci;
   }
   public String getTest() {
      return test;
   }
   public void setTest(String test) {
      this.test = test;
   }

   public double getAvg() {
      this.avg =(this.kor+this.eng+this.math+this.his+this.sci)/5;
      return avg;
   }
   public void setAvg(double avg) {
      this.avg = avg;
   }
      
      
}