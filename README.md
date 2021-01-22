# Asteroid Radar App

This is the 2nd project for Udacity Android Kotlin Developer Nanodegree Program. 

It is a part of Course 2: Developing Android Apps with Kotlin - Part 2

Project rubric is [here! ](https://review.udacity.com/#!/rubrics/2851/view)

## Summarized Tasks

1. Include Main screen with a list of clickable asteroids as seen in the provided design using a RecyclerView with its adapter. You could insert some fake manually created asteroids to try this before downloading any data.
2. Include a Details screen that displays the selected asteroid data once it’s clicked in the Main screen as seen in the provided design. The images in the details screen are going to be provided with the starter code: an image for a potentially hazardous asteroid and another one for the non-hazardous ones, you have to display the correct image depending on the isPotentiallyHazardous asteroid parameter. Navigation xml file is already included with starter code.
3. Download and parse the data from NASA NeoWS (Near Earth Object Web Service) API. As this response cannot be parsed directly with Moshi, we are providing a method to parse the data “manually” for you, it’s called parseAsteroidsJsonResult inside NetworkUtils class, we recommend trying for yourself before using this method or at least take a close look at it as it is an extremely common problem in real-world apps. For this response we need retrofit-converter-scalars instead of Moshi, you can check this dependency in build.gradle (app) file.
4. When asteroids are downloaded, save them in the local database.
5. Fetch and display the asteroids from the database and only fetch the asteroids from today onwards, ignoring asteroids before today. Also, display the asteroids sorted by date (Check SQLite documentation to get sorted data using a query).
6. Be able to cache the data of the asteroid by using a worker, so it downloads and saves today's asteroids in background once a day when the device is charging and wifi is enabled.
7. Download Picture of Day JSON, parse it using Moshi and display it at the top of Main screen using Picasso Library. (You can find Picasso documentation here: https://square.github.io/picasso/) You could use Glide if you are more comfortable with it, although be careful as we found some problems displaying NASA images with Glide.
8. Create the details screen and display the name of the repository and status of the download
9. Add content description to the views: Picture of the day (Use the title dynamically for this), details images and dialog button. Check if it works correctly with talk back.
10. Make sure the entire app works without an internet connection.

### Dependencies

   def nav_version = "2.3.2"
    def version_retrofit = "2.9.0"
    def version_moshi = "1.8.0"
    def version_room = "2.2.6"
    def version_coroutine = "2.2.0"
    def version_work = "1.0.1"
    def version_timber = "4.7.1"


    implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.2.0'
    implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0'
    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.2.1'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'

    //Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"
    
    //RecyclerView
    implementation 'androidx.recyclerview:recyclerview:1.1.0'

    //Retrofit
    implementation "com.squareup.retrofit2:retrofit:$version_retrofit"
    implementation "com.squareup.retrofit2:converter-scalars:$version_retrofit"
    implementation "com.squareup.retrofit2:converter-moshi:$version_retrofit"

    //moshi
    implementation "com.squareup.moshi:moshi:$version_moshi"
    implementation "com.squareup.moshi:moshi-kotlin:$version_moshi"

    //Coroutine
    //implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version_coroutine"
   // implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version_coroutine"

    //ViewModelScope
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$version_coroutine"
    //LifecycleScope
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$version_coroutine"
    //LiveData
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$version_coroutine"


    //Picasso
    implementation 'com.squareup.picasso:picasso:2.5.2'

    //Room
    implementation "androidx.room:room-runtime:$version_room"
    kapt "androidx.room:room-compiler:$version_room"
    // Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:$version_room"


    // WorkManager
    implementation "android.arch.work:work-runtime-ktx:$version_work"

    // Logging
    implementation "com.jakewharton.timber:timber:$version_timber"
    // Desugaring
    coreLibraryDesugaring 'com.android.tools:desugar_jdk_libs:1.0.9'

    //Testing
    testImplementation 'junit:junit:4.+'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'
    

