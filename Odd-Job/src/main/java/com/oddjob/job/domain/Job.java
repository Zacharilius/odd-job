package com.oddjob.job.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.common.base.Preconditions;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;
import com.oddjob.job.form.JobForm;

@Entity
@Cache
public class Job {
    /**
     * The id for the datastore key.
     *
     * We use automatic id assignment for entities of Job class.
     */
    @Id
    private long id;

    /**
     * The title of the job.
     */
    @Index
    private String title;

    /**
     * The description of the job.
     */
    private String description;

    /**
     * Holds Profile key as the parent. The key belongs to the person posting the job.
     */
    @Parent
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private Key<Profile> profileKey;

    /**
     * The userId of the job poster.
     */
    @ApiResourceProperty(ignored = AnnotationBoolean.TRUE)
    private String posterUserId;

    /**
     * Job descriptor tags
     */
    @Index
    private List<String> tags;
    
    /**
     * The posting date
     */
    @Index
    private Date postDate;
    
    /**
     * Hours for job
     */
    @Index
    private double jobHours;
    
    /**
     * Job pay
     */
    @Index
    private double jobPay;
    
    /**
     * Needs completed by date
     */
    @Index
    private Date completionDate;
    /**
     * Just making the default constructor private.
     */
    private Job() {}

    /**
     * 
     * @param id
     * @param posterUserId
     * @param jobForm
     */
    public Job(final long id, final String posterUserId,final JobForm jobForm) {
        Preconditions.checkNotNull(jobForm.getTitle(), "The title is required");
        Preconditions.checkNotNull(jobForm.getDescription(), "The title is required");
        this.id = id;
        this.profileKey = Key.create(Profile.class, posterUserId);
        this.posterUserId = posterUserId;
        updateJobForm(jobForm);
    }
    /**
     * Helper method 
     * @param jobForm
     */
	private void updateJobForm(JobForm jobForm) {
		this.title = jobForm.getTitle();
		this.description = jobForm.getDescription();
		this.jobHours = jobForm.getJobHours();
		this.jobPay = jobForm.getJobPay();
		
		// If the user supplied a complete by date. Convert it into date format.
        Date completionDate = jobForm.getCompletionDate();
        this.completionDate = completionDate == null ? null : new Date(completionDate.getTime());
        
        //Adds the posting date.
        this.postDate = new Date();
		
	}
	
    /**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the profileKey
	 */
	public Key<Profile> getProfileKey() {
		return profileKey;
	}

	/**
	 * @return the posterUserId
	 */
	public String getPosterUserId() {
		return posterUserId;
	}

	/**
	 * @return the tags
	 */
	public List<String> getTags() {
		return tags;
	}

	/**
	 * @return the postDate
	 */
	public Date getPostDate() {
		return postDate;
	}

	/**
	 * @return the jobHours
	 */
	public double getJobHours() {
		return jobHours;
	}

	/**
	 * @return the jobPay
	 */
	public double getJobPay() {
		return jobPay;
	}

	/**
	 * @return the completionDate
	 */
	public Date getCompletionDate() {
		return completionDate;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @param profileKey the profileKey to set
	 */
	public void setProfileKey(Key<Profile> profileKey) {
		this.profileKey = profileKey;
	}

	/**
	 * @param posterUserId the posterUserId to set
	 */
	public void setPosterUserId(String posterUserId) {
		this.posterUserId = posterUserId;
	}

	/**
	 * @param tags the tags to set
	 */
	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	/**
	 * @param postDate the postDate to set
	 */
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	/**
	 * @param jobHours the jobHours to set
	 */
	public void setJobHours(double jobHours) {
		this.jobHours = jobHours;
	}

	/**
	 * @param jobPay the jobPay to set
	 */
	public void setJobPay(double jobPay) {
		this.jobPay = jobPay;
	}

	/**
	 * @param completionDate the completionDate to set
	 */
	public void setCompletionDate(Date completionDate) {
		this.completionDate = completionDate;
	}

	
}
