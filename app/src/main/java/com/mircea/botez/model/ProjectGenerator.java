package com.mircea.botez.model;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Property;
import org.greenrobot.greendao.generator.Schema;
import org.greenrobot.greendao.generator.ToMany;

/**
 * Created by botez on 2/18/2018.
 */

public class ProjectGenerator {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.mircea.botez.model");

        // hospital table
        Entity user = schema.addEntity("User");
        user.addIdProperty();
        user.addStringProperty("name");
        user.addStringProperty("email");
        user.addStringProperty("password");

        // patient table
        Entity cyclingActivity = schema.addEntity("CyclingActivity");
        cyclingActivity.addIdProperty();
        cyclingActivity.addDateProperty("startTime");
        cyclingActivity.addDateProperty("endTime");
        Property userId = cyclingActivity.addLongProperty("userId").getProperty();

        // patient has a one assigned hospital
        cyclingActivity.addToOne(user, userId);

        // hospital has many patients
        ToMany userToActivities = user.addToMany(cyclingActivity, userId);
        userToActivities.setName("cycling_activities");

        // trigger generation with path to the Android project
        new DaoGenerator().generateAll(schema, "app/src/main/java");
    }
}
