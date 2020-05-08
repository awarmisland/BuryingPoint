package com.awarmisland.plugin

import com.android.build.gradle.AppExtension
import com.awarmisland.transform.ActivityLifecycleTransform
import com.awarmisland.transform.FragmentLifecycleTransform
import com.awarmisland.transform.RecordTransform
import org.gradle.api.Plugin
import org.gradle.api.Project

class CusPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
//        println("this is CusPlugin")
        def android = project.extensions.getByType(AppExtension)
        //注册Transform
        android.registerTransform(new ActivityLifecycleTransform(project),Collections.EMPTY_LIST)
        android.registerTransform(new FragmentLifecycleTransform(project),Collections.EMPTY_LIST)
        android.registerTransform(new RecordTransform(project),Collections.EMPTY_LIST)


//        SensorsAnalyticsExtension extension = new SensorsAnalyticsExtension()
//        extension.disableAppClick = false
//        android.registerTransform(new SensorsAnalyticsTransform(project,extension),Collections.EMPTY_LIST)
    }
}