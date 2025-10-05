package com.PJ.dtos;

public class TaskDTO {
    private Long id;
    private String title;
    private boolean finished;
    
    
	public TaskDTO(Long id, String title, boolean finished) {
		super();
		this.id = id;
		this.title = title;
		this.finished = finished;
	}
	public TaskDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isFinished() {
		return finished;
	}
	public void setFinished(boolean finished) {
		this.finished = finished;
	}
    
    
}
