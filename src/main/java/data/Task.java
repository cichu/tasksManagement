package data;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taskList")
public class Task implements Serializable {
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String description;
	private String comment;
	private int reward;
	private int assignedUser;
	private int priorirty;
	private boolean completed;
	private Date createDate;
	private Date dueDate;
	private Date completeDate;
	private Date checked;
	private int taskList;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getReward() {
		return reward;
	}
	public void setReward(int reward) {
		this.reward = reward;
	}
	public int getAssignedUser() {
		return assignedUser;
	}
	public void setAssignedUser(int assignedUser) {
		this.assignedUser = assignedUser;
	}
	public int getPriorirty() {
		return priorirty;
	}
	public void setPriorirty(int priorirty) {
		this.priorirty = priorirty;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public Date getChecked() {
		return checked;
	}
	public void setChecked(Date checked) {
		this.checked = checked;
	}
	public int getTaskList() {
		return taskList;
	}
	public void setTaskList(int taskList) {
		this.taskList = taskList;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + assignedUser;
		result = prime * result + ((checked == null) ? 0 : checked.hashCode());
		result = prime * result + ((comment == null) ? 0 : comment.hashCode());
		result = prime * result + ((completeDate == null) ? 0 : completeDate.hashCode());
		result = prime * result + (completed ? 1231 : 1237);
		result = prime * result + ((createDate == null) ? 0 : createDate.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + priorirty;
		result = prime * result + reward;
		result = prime * result + taskList;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		if (assignedUser != other.assignedUser)
			return false;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (comment == null) {
			if (other.comment != null)
				return false;
		} else if (!comment.equals(other.comment))
			return false;
		if (completeDate == null) {
			if (other.completeDate != null)
				return false;
		} else if (!completeDate.equals(other.completeDate))
			return false;
		if (completed != other.completed)
			return false;
		if (createDate == null) {
			if (other.createDate != null)
				return false;
		} else if (!createDate.equals(other.createDate))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (priorirty != other.priorirty)
			return false;
		if (reward != other.reward)
			return false;
		if (taskList != other.taskList)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "task [id=" + id + ", name=" + name + ", description=" + description + ", comment=" + comment
				+ ", reward=" + reward + ", assignedUser=" + assignedUser + ", priorirty=" + priorirty + ", completed="
				+ completed + ", createDate=" + createDate + ", dueDate=" + dueDate + ", completeDate=" + completeDate
				+ ", checked=" + checked + ", taskList=" + taskList + "]";
	}
	
}
