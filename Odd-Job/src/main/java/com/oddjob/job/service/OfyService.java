package com.oddjob.job.service;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;
import com.oddjob.job.domain.Conference;
import com.oddjob.job.domain.Profile;
import com.oddjob.job.domain.Session;

/**
 * Custom Objectify Service that this application should use.
 */
public class OfyService {
    /**
     * This static block ensure the entity registration.
     */
    static {
        // factory().register(Conference.class);
        factory().register(Profile.class);
        factory().register(Conference.class);
        factory().register(Session.class);
    }

    /**
     * Use this static method for getting the Objectify service object in order to make sure the
     * above static block is executed before using Objectify.
     * @return Objectify service object.
     */
    public static Objectify ofy() {
        return ObjectifyService.ofy();
    }

    /**
     * Use this static method for getting the Objectify service factory.
     * @return ObjectifyFactory.
     */
    public static ObjectifyFactory factory() {
        return ObjectifyService.factory();
    }
}
