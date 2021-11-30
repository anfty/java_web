package tom.bean;

public class UploadPic {
	private String fileName;// 声明文件路径变量
	private String savedFileName;// 声明被保存的图片文件名
	private String backNews="";

	public String getBackNews() {
		return backNews;
	}

	public void setBackNews(String backNews) {
		this.backNews = backNews;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getSavedFileName() {
		return savedFileName;
	}

	public void setSavedFileName(String savedFileName) {
		this.savedFileName = savedFileName;
	}

}
