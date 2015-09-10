package com.oddjob.job.form;

import java.util.Date;
import java.util.List;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.repackaged.com.google.common.collect.ImmutableList;
import com.googlecode.objectify.Key;

public class JobForm {
    /**
     * The title of the job.
     */
    private String title;

    /**
     * The description of the job.
     */
    private String description;


    /**
     * Job descriptor tags
     */
    private List<String> tags;
    
    /**
     * The posting date
     */
    private Date postDate;
    
    /**
     * Hours for job
     */
    private double jobHours;
    
    /**
     * Job pay
     */
    private double jobPay;
    
    /**
     * Needs completed by date
     */
    private Date completionDate;

    
    
    
	/**
	 * @param title
	 * @param description
	 * @param profileKey
	 * @param posterUserId
	 * @param tags
	 * @param postDate
	 * @param jobHours
	 * @param jobPay
	 * @param completionDate
	 */
	public JobForm(String title, String description, List<String> tags,
			Date postDate, double jobHours, double jobPay, Date completionDate) {
		this.title = title;
		this.description = description;
		this.tags = tags == null ? null : ImmutableList.copyOf(tags);
		this.postDate = postDate == null ? null : new Date(postDate.getTime());
		this.jobHours = jobHours;
		this.jobPay = jobPay;
		this.completionDate = completionDate == null ? null : new Date(completionDate.getTime());
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

}
