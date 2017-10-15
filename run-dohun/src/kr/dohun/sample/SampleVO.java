package kr.dohun.sample;

public class SampleVO {

	private String title;
	private String id;
	private String name;
	
	public SampleVO(String title, String id, String name){
		this.title = title;
		this.id = id;
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
