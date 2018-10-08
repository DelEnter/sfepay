package com.ecpss.action.filemanager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.ecpss.action.BaseAction;
import com.ecpss.action.pay.SfePayAction;
import com.ecpss.model.FileManager;
import com.ecpss.util.UploadUtils;

public class FileUpLoadAction extends BaseAction{
	Logger logger = Logger.getLogger(FileUpLoadAction.class.getName());
	
	private static final long serialVersionUID = 572146812454l ;
    private static final int BUFFER_SIZE = 16 * 1024 ;
    
    private File myFile;
//  private String contentType;
    private String fileName;
    private String imageFileName;
    private String caption;

    private FileManager fm;
    private List<FileManager> fileList;
    
    private String inputPath; // 指定要被下载的文件路径 
    
	public String fileUpLoad() throws IOException{
		if(myFile!=null && myFile.exists()){
			 UUID UID=UUID.randomUUID();
			 logger.info("UID:"+UID);
	    	 imageFileName = new Date().getTime() + "_"+ fm.getFilename();
	    	 InputStream stream = FileUpLoadAction.class.getClassLoader().getResourceAsStream("/ecpss.properties");
			 Properties p = new Properties();
			 p.load(stream);
	         File imageFile = new File(UploadUtils.getUploadDir()+"/"+ UploadUtils.getUploadFileOpPath(imageFileName));
	         System.out.println("copy file:"+myFile+" to "+imageFile);
	         FileUtils.copyFile(myFile, imageFile);
	         	
	         fm.setFileroute(UploadUtils.getUploadFileOpPath(imageFileName));
	         fm.setFilesize(imageFile.length());
		     fm.setFiledate(new Date());
			 this.commonService.save(fm);
   	 	}    	 
        return SUCCESS;
	}

	public String filelist(){
		String sql = "select f from FileManager f ";
		fileList = this.commonService.list(sql);
		return SUCCESS;
	}
	
	public File getMyFile() {
		return myFile;
	}

	public void setMyFile(File myFile) {
		this.myFile = myFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public FileManager getFm() {
		return fm;
	}

	public void setFm(FileManager fm) {
		this.fm = fm;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<FileManager> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileManager> fileList) {
		this.fileList = fileList;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public static int getBUFFER_SIZE() {
		return BUFFER_SIZE;
	}
	 
}
