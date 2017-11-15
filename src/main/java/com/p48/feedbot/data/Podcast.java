package com.p48.feedbot.data;

import org.springframework.data.annotation.Id;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Podcast {

@Id
private Integer id;

@SerializedName("Name")
@Expose
private String name;

@SerializedName("URL")
@Expose
private String url;

@SerializedName("File Name")
@Expose
private String fileName;

@SerializedName("Date")
@Expose
private String date;


public Podcast(String name, String url, String fileName, String date) {
	super();
	this.name = name;
	this.url = url;
	this.fileName = fileName;
	this.date = date;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getFileName() {
return fileName;
}

public void setFileName(String fileName) {
this.fileName = fileName;
}

public String getDate() {
return date;
}

public void setDate(String date) {
this.date = date;
}

}
